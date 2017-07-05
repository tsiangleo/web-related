/*
 * Copyright (c) 2002-2012 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.citrus.springext.impl;

import static com.alibaba.citrus.util.Assert.*;
import static com.alibaba.citrus.util.CollectionUtil.*;
import static com.alibaba.citrus.util.FileUtil.*;
import static com.alibaba.citrus.util.StringUtil.*;
import static java.util.Collections.*;

import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.alibaba.citrus.springext.ConfigurationPoint;
import com.alibaba.citrus.springext.ConfigurationPointException;
import com.alibaba.citrus.springext.ConfigurationPoints;
import com.alibaba.citrus.springext.Contribution;
import com.alibaba.citrus.springext.ResourceResolver;
import com.alibaba.citrus.springext.ResourceResolver.PropertyHandler;
import com.alibaba.citrus.springext.ResourceResolver.Resource;
import com.alibaba.citrus.springext.Schema;
import com.alibaba.citrus.springext.SourceInfo;
import com.alibaba.citrus.springext.support.SourceInfoSupport;
import com.alibaba.citrus.util.ToStringBuilder;
import com.alibaba.citrus.util.ToStringBuilder.MapBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解析configuration point namespace。
 *
 * @author Michael Zhou
 */
public class ConfigurationPointsImpl implements ConfigurationPoints {
    private final static Logger log                 = LoggerFactory.getLogger(ConfigurationPoints.class);
    private final static String NAMESPACE_URI_KEY   = "namespaceUri";
    private final static String DEFAULT_ELEMENT_KEY = "defaultElement";
    private final static String PREFERRED_NS_PREFIX = "nsPrefix";
    private final ConfigurationPointSettings      settings;
    private final String                          configurationPointsLocation;
    private final Map<String, ConfigurationPoint> namespaceUriToConfigurationPoints; // namespaceUri => configurationPoint
    private final Set<String>                     namespaces;
    private final Map<String, ConfigurationPoint> nameToConfigurationPoints; // name => configurationPoint
    private final Collection<ConfigurationPoint>  configurationPoints;
    private       boolean                         initialized;

    /** 通过默认的<code>ClassLoader</code>来查找和创建<code>ConfigurationPoint</code>s。 */
    public ConfigurationPointsImpl() {
        this(null, null, null);
    }

    /**
     * 通过指定的<code>ClassLoader</code>来查找和创建<code>ConfigurationPoint</code>s。
     * 如果未指定<code>ClassLoader</code>，则使用默认的<code>ClassLoader</code>。
     */
    public ConfigurationPointsImpl(ClassLoader classLoader) {
        this(classLoader, null);
    }

    /**
     * 通过指定的<code>ClassLoader</code>来查找和创建<code>ConfigurationPoint</code>s。
     * 如果未指定<code>ClassLoader</code>，则使用默认的<code>ClassLoader</code>。
     */
    public ConfigurationPointsImpl(ClassLoader classLoader, String configurationPointsLocation) {
        this(classLoader, null, configurationPointsLocation);
    }

    /** 通过指定<code>ResourceResolver</code>来查找和创建<code>ConfigurationPoint</code>s。 */
    public ConfigurationPointsImpl(ResourceResolver resourceResolver) {
        this(resourceResolver, null);
    }

    /** 通过指定<code>ResourceResolver</code>来查找和创建<code>ConfigurationPoint</code>s。 */
    public ConfigurationPointsImpl(ResourceResolver resourceResolver, String configurationPointsLocation) {
        this(null, assertNotNull(resourceResolver, "no resourceResolver was specified"), configurationPointsLocation);
    }

    private ConfigurationPointsImpl(ClassLoader classLoader, ResourceResolver resourceResolver, String configurationPointsLocation) {
        this.configurationPointsLocation = defaultIfEmpty(configurationPointsLocation, DEFAULT_CONFIGURATION_POINTS_LOCATION);
        this.namespaceUriToConfigurationPoints = createTreeMap();
        this.namespaces = unmodifiableSet(namespaceUriToConfigurationPoints.keySet()); // sorted by ns
        this.nameToConfigurationPoints = createTreeMap();
        this.configurationPoints = unmodifiableCollection(nameToConfigurationPoints.values());// sorted by name

        if (resourceResolver == null) {
            this.settings = new ConfigurationPointSettings(classLoader, this.configurationPointsLocation);
        } else {
            // IDE plugin mode
            this.settings = new ConfigurationPointSettings(resourceResolver, this.configurationPointsLocation);
        }
    }

    private void ensureInit() {
        if (initialized) {
            return;
        }

        initialized = true;
        loadConfigurationPoints();
    }

    private void loadConfigurationPoints() {
        log.trace("Trying to load configuration points at {}", configurationPointsLocation);

        settings.resourceResolver.loadAllProperties(configurationPointsLocation, new PropertyHandler() {
            public void handle(String key, String value, Resource source, int lineNumber) {
                String name = normalizeConfigurationPointName(key);

                if (name == null) {
                    return;
                }

                Map<String, String> params = parseNamespaceUriAndParams(value);
                String namespaceUri = assertNotNull(params.get(NAMESPACE_URI_KEY), "namespaceUri");

                if (!namespaceUri.endsWith(name)) {
                    throw new ConfigurationPointException("Naming Convention Violation: namespace URI [" + namespaceUri
                                                          + "] of configuration point should end with its name [" + name
                                                          + "].  This configuration point is located at " + configurationPointsLocation + ".");
                }

                String defaultElementName = params.get(DEFAULT_ELEMENT_KEY);
                String preferredNsPrefix = params.get(PREFERRED_NS_PREFIX);

                ConfigurationPoint cp = new ConfigurationPointImpl(
                        ConfigurationPointsImpl.this, settings, name, namespaceUri, defaultElementName, preferredNsPrefix,
                        new SourceInfoSupport<SourceInfo<?>>().setSource(source, lineNumber));

                namespaceUriToConfigurationPoints.put(namespaceUri, cp);
                nameToConfigurationPoints.put(name, cp);
            }
        });

        if (log.isDebugEnabled()) {
            ToStringBuilder buf = new ToStringBuilder();

            buf.format("Loaded configuration points at %s, %d configuration points found.",
                       configurationPointsLocation, nameToConfigurationPoints.size());

            MapBuilder mb = new MapBuilder().setSortKeys(true).setPrintCount(true);

            for (String name : nameToConfigurationPoints.keySet()) {
                mb.append(name, nameToConfigurationPoints.get(name).getNamespaceUri());
            }

            mb.appendTo(buf);

            log.debug(buf.toString());
        }
    }

    public Set<String> getAvailableNamespaces() {
        ensureInit();
        return namespaces;
    }

    public Collection<ConfigurationPoint> getConfigurationPoints() {
        ensureInit();
        return configurationPoints;
    }

    public ConfigurationPoint getConfigurationPointByName(String name) {
        ensureInit();
        name = normalizeConfigurationPointName(name);

        if (name != null) {
            return ensureInitConfigurationPoint((ConfigurationPointImpl) nameToConfigurationPoints
                    .get(name));
        } else {
            return null;
        }
    }

    public ConfigurationPoint getConfigurationPointByNamespaceUri(String namespaceUri) {
        ensureInit();
        namespaceUri = normalizeNamespaceUri(namespaceUri);

        if (namespaceUri != null) {
            return ensureInitConfigurationPoint((ConfigurationPointImpl) namespaceUriToConfigurationPoints
                    .get(namespaceUri));
        } else {
            return null;
        }
    }

    private ConfigurationPoint ensureInitConfigurationPoint(ConfigurationPointImpl cp) {
        if (cp != null) {
            cp.init(); // if not inited yet
        }

        return cp;
    }

    private String normalizeConfigurationPointName(String name) {
        return trimToNull(normalizeRelativePath(name, true)); // 规格化，除去首尾slash
    }

    private String normalizeNamespaceUri(String uri) {
        uri = trimToNull(uri);

        if (uri != null) {
            uri = URI.create(uri).normalize().toString().replaceAll("/$", "");
        }

        return uri;
    }

    private Map<String, String> parseNamespaceUriAndParams(String uriAndParams) {
        uriAndParams = trimToNull(uriAndParams);

        if (uriAndParams == null) {
            return null;
        }

        Map<String, String> params = createHashMap(4);
        String[] parts = uriAndParams.split(",|;");

        for (String part : parts) {
            part = trimToNull(part);

            if (part != null) {
                int index = part.indexOf("=");

                if (index >= 0) {
                    String paramKey = trimToNull(part.substring(0, index));
                    String paramValue = trimToNull(part.substring(index + 1));

                    if (paramKey == null) {
                        throw new IllegalArgumentException("Illegal namespace URI: " + uriAndParams);
                    }

                    params.put(paramKey, paramValue);
                } else {
                    if (params.containsKey(NAMESPACE_URI_KEY)) {
                        throw new IllegalArgumentException("Illegal namespace URI: " + uriAndParams);
                    }

                    params.put(NAMESPACE_URI_KEY, normalizeNamespaceUri(part));
                }
            }
        }

        if (!params.containsKey(NAMESPACE_URI_KEY)) {
            throw new IllegalArgumentException("Illegal namespace URI: " + uriAndParams);
        }

        return params;
    }

    public Map<String, Schema> getNamedMappings() {
        Map<String, Schema> mappings = createTreeMap();

        for (ConfigurationPoint cp : getConfigurationPoints()) {
            mappings.putAll(cp.getSchemas().getNamedMappings()); // side effects: init cp if not inited yet

            for (Contribution contrib : cp.getContributions()) {
                mappings.putAll(contrib.getSchemas().getNamedMappings());
            }
        }

        return mappings;
    }

    @Override
    public String toString() {
        if (!initialized) {
            return "ConfigurationPoints[uninitialized]";
        } else {
            ToStringBuilder buf = new ToStringBuilder();

            buf.format("ConfigurationPoints[%d cps, loaded from %s]", configurationPoints.size(),
                       configurationPointsLocation);

            if (!configurationPoints.isEmpty()) {
                buf.appendCollection(configurationPoints);
            }

            return buf.toString();
        }
    }
}
