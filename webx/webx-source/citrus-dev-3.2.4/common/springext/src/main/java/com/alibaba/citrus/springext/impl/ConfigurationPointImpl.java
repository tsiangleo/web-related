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

import static com.alibaba.citrus.springext.Schema.*;
import static com.alibaba.citrus.springext.support.SchemaUtil.*;
import static com.alibaba.citrus.util.Assert.*;
import static com.alibaba.citrus.util.CollectionUtil.*;
import static com.alibaba.citrus.util.ObjectUtil.*;
import static com.alibaba.citrus.util.StringUtil.*;
import static java.util.Collections.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import com.alibaba.citrus.springext.ConfigurationPoint;
import com.alibaba.citrus.springext.ConfigurationPointException;
import com.alibaba.citrus.springext.ConfigurationPoints;
import com.alibaba.citrus.springext.Contribution;
import com.alibaba.citrus.springext.ContributionAware;
import com.alibaba.citrus.springext.ContributionType;
import com.alibaba.citrus.springext.ResourceResolver.PropertyHandler;
import com.alibaba.citrus.springext.ResourceResolver.Resource;
import com.alibaba.citrus.springext.Schema;
import com.alibaba.citrus.springext.SourceInfo;
import com.alibaba.citrus.springext.VersionableSchemas;
import com.alibaba.citrus.springext.support.ConfigurationPointSourceInfo;
import com.alibaba.citrus.springext.support.SourceInfoSupport;
import com.alibaba.citrus.springext.support.parser.DefaultElementDefinitionParser;
import com.alibaba.citrus.util.ToStringBuilder;
import com.alibaba.citrus.util.ToStringBuilder.MapBuilder;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.xml.BeanDefinitionDecorator;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.util.ClassUtils;

/**
 * 代表一个configuration point的实现，并处理configuration point
 * namespace下的elements及attributes。
 *
 * @author Michael Zhou
 */
public class ConfigurationPointImpl extends NamespaceHandlerSupport
        implements ConfigurationPoint, NamespaceHandler, ConfigurationPointSourceInfo {
    private final static Logger log = LoggerFactory.getLogger(ConfigurationPoint.class);
    private final ConfigurationPoints                cps;
    private final ConfigurationPointSettings         settings;
    private final String                             name;
    private final String                             namespaceUri;
    private final String                             defaultElementName;
    private final String                             preferredNsPrefix;
    private final String                             contributionLocationPrefix;
    private final Map<ContributionKey, Contribution> contributions;
    private final Map<String, Contribution>          dependingContributions;
    private final Collection<Contribution>           dependingContributionsUnmodifiable;
    private final SourceInfo<SourceInfo<?>>          sourceInfo;
    private       VersionableSchemas                 schemas;
    private       boolean                            initialized;

    ConfigurationPointImpl(ConfigurationPoints cps, ConfigurationPointSettings settings, String name,
                           String namespaceUri, String defaultElementName, String preferredNsPrefix, SourceInfo<SourceInfo<?>> sourceInfo) {
        this.cps = cps;
        this.settings = settings;
        this.name = assertNotNull(name, "name");
        this.namespaceUri = assertNotNull(namespaceUri, "namespaceUri");
        this.defaultElementName = trimToNull(defaultElementName);
        this.preferredNsPrefix = trimToNull(preferredNsPrefix);
        this.contributionLocationPrefix = settings.baseLocation + name.replace('/', '-'); // eg. my-conf-point
        this.contributions = createTreeMap();
        this.dependingContributions = createTreeMap(); // 使用排序的map，使测试结果恒定
        this.dependingContributionsUnmodifiable = unmodifiableCollection(dependingContributions.values());
        this.sourceInfo = assertNotNull(sourceInfo, "sourceInfo");
    }

    public ConfigurationPoints getConfigurationPoints() {
        return cps;
    }

    public String getName() {
        return name;
    }

    public String getNamespaceUri() {
        return namespaceUri;
    }

    public String getDefaultElementName() {
        return defaultElementName;
    }

    public String getPreferredNsPrefix() {
        return preferredNsPrefix;
    }

    public NamespaceHandler getNamespaceHandler() {
        return this;
    }

    public Contribution getContribution(String name, ContributionType type) {
        return contributions.get(new ContributionKey(name, type));
    }

    public Collection<Contribution> getContributions() {
        return contributions.values();
    }

    @Override
    public Collection<Contribution> getDependingContributions() {
        return dependingContributionsUnmodifiable;
    }

    public void addDependingContribution(Contribution contribution) {
        if (contribution != null) {
            String key = contribution.getConfigurationPoint().getName() + "." + contribution.getName();
            dependingContributions.put(key, contribution);
        }
    }

    public void init() {
        if (initialized) {
            return;
        }

        initialized = true;

        for (ContributionType type : ContributionType.values()) {
            loadContributions(type);
        }

        // 注册default element parser
        String defaultName = getDefaultElementName();

        if (defaultName != null) {
            registerBeanDefinitionParser(defaultName, new DefaultElementDefinitionParser());
        }
    }

    private void loadContributions(final ContributionType contribType) {
        final String contribLocation = contributionLocationPrefix + contribType.getContributionsLocationSuffix();

        log.trace("Trying to load contributions at {}", contribLocation);

        final Map<String, String> sortedMappings = createTreeMap();

        settings.resourceResolver.loadAllProperties(contribLocation, new PropertyHandler() {
            public void handle(String key, String value, Resource source, int lineNumber) {
                String contribName = trimToNull(key);
                String contribClassName = trimToNull(value);

                if (getDefaultElementName() != null && isEquals(contribName, getDefaultElementName())) {
                    throw new FatalBeanException(
                            "Contribution has a same name as the default element name for configuration point: contributionType="
                            + contribType + ", contribuitionClass=" + contribClassName + ", contributionName="
                            + contribName + ", configurationPoint=" + getName() + ", namespaceUri="
                            + getNamespaceUri());
                }

                sortedMappings.put(contribName, contribClassName);

                ContributionImpl contrib = new ContributionImpl(
                        ConfigurationPointImpl.this, settings, contribType, contribName, contribClassName,
                        new SourceInfoSupport<ConfigurationPointSourceInfo>(ConfigurationPointImpl.this).setSource(source, lineNumber));

                Contribution existContrib = contributions.get(contrib.getKey());

                if (existContrib != null) {
                    throw new ConfigurationPointException("Duplicated contributions from locations: " + contribLocation
                                                          + "\n" + "     " + existContrib + "\n and " + contrib);
                }

                register(contrib);

                contributions.put(contrib.getKey(), contrib);
            }
        });

        if (log.isDebugEnabled() && !sortedMappings.isEmpty()) {
            ToStringBuilder buf = new ToStringBuilder();

            buf.format("Loaded contributions at %s", contribLocation);
            buf.appendMap(sortedMappings);

            log.debug(buf.toString());
        }
    }

    private void register(Contribution contrib) {
        // 当classLoader为null（IDE plugin）时，不创建和注册contribution class。
        if (settings.classLoader == null) {
            return;
        }

        Object obj;

        try {
            obj = instantiateContributionImplementation(contrib);
        } catch (FatalBeanException e) {
            log.warn("Skipped registration of {} due to the error: {}", contrib.getDescription(), e);
            return;
        }

        if (obj instanceof ContributionAware) {
            ((ContributionAware) obj).setContribution(contrib);
        }

        switch (contrib.getType()) {
            case BEAN_DEFINITION_PARSER:
                registerBeanDefinitionParser(contrib.getName(), (BeanDefinitionParser) obj);
                break;

            case BEAN_DEFINITION_DECORATOR:
                registerBeanDefinitionDecorator(contrib.getName(), (BeanDefinitionDecorator) obj);
                break;

            case BEAN_DEFINITION_DECORATOR_FOR_ATTRIBUTE:
                registerBeanDefinitionDecoratorForAttribute(contrib.getName(), (BeanDefinitionDecorator) obj);
                break;

            default:
                unreachableCode("unknown contributionType: %s", contrib.getType());
        }
    }

    private Object instantiateContributionImplementation(Contribution contrib)
            throws FatalBeanException {
        String implementationClassName = contrib.getImplementationClassName();

        if (implementationClassName == null) {
            throw new FatalBeanException("Contribution class not defined: contributionType=" + contrib.getType()
                                         + ", contributionName=" + contrib.getName() + ", configurationPoint="
                                         + contrib.getConfigurationPoint().getName() + ", namespaceUri="
                                         + contrib.getConfigurationPoint().getNamespaceUri());
        }

        Class<?> implementationClass;

        try {
            implementationClass = ClassUtils.forName(implementationClassName, settings.classLoader);
        } catch (ClassNotFoundException e) {
            throw new FatalBeanException("Contribution class not found: contributionType=" + contrib.getType()
                                         + ", contribuitionClass=" + implementationClassName + ", contributionName=" + contrib.getName()
                                         + ", configurationPoint=" + contrib.getConfigurationPoint().getName() + ", namespaceUri="
                                         + contrib.getConfigurationPoint().getNamespaceUri(), e);
        }

        if (!contrib.getType().getContributionInterface().isAssignableFrom(implementationClass)) {
            throw new FatalBeanException("Contribution class does not implement the "
                                         + contrib.getType().getContributionInterface().getSimpleName() + " interface:  contributionType="
                                         + contrib.getType() + ", contribuitionClass=" + implementationClassName + ", contributionName="
                                         + contrib.getName() + ", configurationPoint=" + contrib.getConfigurationPoint().getName()
                                         + ", namespaceUri=" + contrib.getConfigurationPoint().getNamespaceUri());
        }

        return BeanUtils.instantiateClass(implementationClass);
    }

    public VersionableSchemas getSchemas() {
        if (schemas == null) {
            init(); // if not inited yet

            // eg. my-conf-point
            String mainName = getName().replace('/', '-');

            Schema mainSchema = loadMainSchema(mainName);
            Schema[] versionedSchemas = loadVersionedSchemas(mainName);

            schemas = new VersionableSchemasImpl(mainSchema, versionedSchemas);
        }

        return schemas;
    }

    private Schema loadMainSchema(String mainName) {
        String schemaName = mainName + "." + XML_SCHEMA_EXTENSION;
        Document schemaSource = createConfigurationPointSchema(this, null);

        return SchemaImpl.createForConfigurationPoint(
                schemaName, null, namespaceUri, preferredNsPrefix, getDescription(), schemaSource,
                new SourceInfoSupport<ConfigurationPointSourceInfo>(this));
    }

    private Schema[] loadVersionedSchemas(String mainName) {
        // 收集所有contribution schema的版本
        Set<String> allVersions = createTreeSet();

        for (Contribution contrib : getContributions()) {
            Collections.addAll(allVersions, contrib.getSchemas().getVersions());
        }

        // 生成每个schemas。
        Schema[] schemas = new Schema[allVersions.size()];
        int i = 0;
        for (String version : allVersions) {
            String schemaName = mainName + "-" + version + "." + XML_SCHEMA_EXTENSION;
            Document schemaSource = createConfigurationPointSchema(this, version);

            schemas[i++] = SchemaImpl.createForConfigurationPoint(
                    schemaName, version, namespaceUri, preferredNsPrefix, getDescription(), schemaSource,
                    new SourceInfoSupport<ConfigurationPointSourceInfo>(this));
        }

        return schemas;
    }

    public String getDescription() {
        return String.format("ConfigurationPoint[%s]", name);
    }

    public SourceInfo<?> getParent() {
        return sourceInfo.getParent();
    }

    public Resource getSource() {
        return sourceInfo.getSource();
    }

    public int getLineNumber() {
        return sourceInfo.getLineNumber();
    }

    @Override
    public String toString() {
        ToStringBuilder buf = new ToStringBuilder();

        buf.format("ConfigurationPoint[%s=%s, loaded contributions from %s.*]", name, namespaceUri,
                   contributionLocationPrefix);

        MapBuilder mb = new MapBuilder();

        if (!contributions.isEmpty()) {
            mb.append("Contributions", contributions.values());
        }

        mb.append("Schemas", getSchemas()).appendTo(buf);

        return buf.toString();
    }
}
