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

import static com.alibaba.citrus.springext.support.SchemaUtil.*;
import static com.alibaba.citrus.util.Assert.*;
import static com.alibaba.citrus.util.BasicConstant.*;
import static com.alibaba.citrus.util.CollectionUtil.*;
import static com.alibaba.citrus.util.StringUtil.*;
import static com.alibaba.citrus.util.io.StreamUtil.*;
import static java.util.Collections.*;
import static javax.xml.XMLConstants.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.citrus.springext.ConfigurationPointException;
import com.alibaba.citrus.springext.ResourceResolver;
import com.alibaba.citrus.springext.ResourceResolver.Resource;
import com.alibaba.citrus.springext.Schema;
import com.alibaba.citrus.springext.SourceInfo;
import com.alibaba.citrus.springext.support.ConfigurationPointSchemaSourceInfo;
import com.alibaba.citrus.springext.support.ConfigurationPointSourceInfo;
import com.alibaba.citrus.springext.support.ContributionSchemaSourceInfo;
import com.alibaba.citrus.springext.support.ContributionSourceInfo;
import com.alibaba.citrus.springext.support.SchemaUtil;
import com.alibaba.citrus.springext.support.SourceInfoSupport;
import com.alibaba.citrus.springext.support.SpringPluggableSchemaSourceInfo;
import com.alibaba.citrus.springext.support.SpringSchemasSourceInfo;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.springframework.core.io.InputStreamSource;

public class SchemaImpl<P extends SourceInfo<?>> extends SchemaBase {
    private final String               name;
    private final String               version;
    private final String               sourceDesc;
    private       String               targetNamespace;
    private       String               preferredNsPrefix;
    private       String[]             includes;
    private final Map<String, Element> elements;
    private final Collection<Element>  elementCollection;
    private final boolean              parsingTargetNamespace;
    private final SourceInfo<P>        sourceInfo;

    /** 创建 configuration point 的 main schema 和 versioned schema。 */
    public static Schema createForConfigurationPoint(String name, String version, String targetNamespace, String preferredNsPrefix, String sourceDesc, Document sourceDocument, SourceInfo<ConfigurationPointSourceInfo> sourceInfo) {

        class ConfigurationPointSchemaImpl extends SchemaImpl<ConfigurationPointSourceInfo>
                implements ConfigurationPointSchemaSourceInfo {
            private ConfigurationPointSchemaImpl(String name, String version, String targetNamespace, String preferredNsPrefix,
                                                 boolean parsingTargetNamespace, String sourceDesc, InputStreamSource source,
                                                 Document sourceDocument, boolean isInputStreamSource, SourceInfo<ConfigurationPointSourceInfo> sourceInfo) {
                super(name, version, targetNamespace, preferredNsPrefix,
                      parsingTargetNamespace, sourceDesc, source,
                      sourceDocument, isInputStreamSource, sourceInfo);
            }
        }

        return new ConfigurationPointSchemaImpl(name, version, targetNamespace, preferredNsPrefix, false, sourceDesc, null, sourceDocument, false, sourceInfo);
    }

    /** 创建 contribution 的 main schema 和 versioned schema。 */
    public static Schema createForContribution(String name, String version, String sourceDesc, InputStreamSource source, SourceInfo<ContributionSourceInfo> sourceInfo, Transformer transformer, ResourceResolver resourceResolver) {

        class ContributionSchemaImpl extends SchemaImpl<ContributionSourceInfo>
                implements ContributionSchemaSourceInfo {
            private ContributionSchemaImpl(String name, String version, String targetNamespace, String preferredNsPrefix,
                                           boolean parsingTargetNamespace, String sourceDesc, InputStreamSource source,
                                           Document sourceDocument, boolean isInputStreamSource, SourceInfo<ContributionSourceInfo> sourceInfo) {
                super(name, version, targetNamespace, preferredNsPrefix,
                      parsingTargetNamespace, sourceDesc, source,
                      sourceDocument, isInputStreamSource, sourceInfo);
            }
        }

        SchemaImpl schema = new ContributionSchemaImpl(name, version, null, null, false, sourceDesc, source, null, true, sourceInfo);

        if (transformer != null) {
            schema.transform(transformer); // 必须延迟处理（doNow == false），否则会死循环
        }

        // 强制转换成unqualified style
        schema.transform(getUnqualifiedStyleTransformer(resourceResolver));

        return schema;
    }

    /** 创建spring.schemas中定义的schema。 */
    public static Schema createSpringPluggableSchema(String name, String version, boolean parsingTargetNamespace, String sourceDesc,
                                                     InputStreamSource source, SourceInfo<SpringSchemasSourceInfo> sourceInfo,
                                                     final Map<String, Map<String, String>> toolingParameters) {

        class SpringPluggableSchemaImpl extends SchemaImpl<SpringSchemasSourceInfo>
                implements SpringPluggableSchemaSourceInfo {
            private SpringPluggableSchemaImpl(String name, String version, String targetNamespace, String preferredNsPrefix,
                                              boolean parsingTargetNamespace, String sourceDesc, InputStreamSource source,
                                              Document sourceDocument, boolean isInputStreamSource, SourceInfo<SpringSchemasSourceInfo> sourceInfo) {
                super(name, version, targetNamespace, preferredNsPrefix,
                      parsingTargetNamespace, sourceDesc, source,
                      sourceDocument, isInputStreamSource, sourceInfo);
            }

            @Override
            protected void doAnalyze() {
                super.doAnalyze();

                // 从spring.tooling参数表中获取preferredNsPrefix
                String namespace = getTargetNamespace();

                if (toolingParameters != null && namespace != null && toolingParameters.containsKey(namespace)) {
                    Map<String, String> params = toolingParameters.get(getTargetNamespace());
                    String preferredNsPrefix = trimToNull(params.get("prefix"));

                    if (preferredNsPrefix != null) {
                        setPreferredNsPrefix(preferredNsPrefix);
                    }
                }
            }
        }

        return new SpringPluggableSchemaImpl(name, version, null, null, parsingTargetNamespace, sourceDesc, source, null, true, sourceInfo);
    }

    /** 创建一般的schema。 */
    public static Schema create(String name, String version, boolean parsingTargetNamespace, String sourceDesc, InputStreamSource source) {
        return new SchemaImpl<SourceInfo<?>>(name, version, null, null, parsingTargetNamespace, sourceDesc, source, null, true, new SourceInfoSupport<SourceInfo<?>>());
    }

    /**
     * 创建spring.schemas中定义的schema。
     * <p>
     * 如果<code>parsingTargetNamespace</code>为 <code>true</code>，则试图通过解析xml来取得ns。
     * </p>
     */
    private SchemaImpl(String name, String version, String targetNamespace, String preferredNsPrefix,
                       boolean parsingTargetNamespace, String sourceDesc,
                       InputStreamSource source, Document sourceDocument, boolean isInputStreamSource,
                       SourceInfo<P> sourceInfo) {
        super(source, sourceDocument, isInputStreamSource);

        this.name = name;
        this.version = version;
        this.targetNamespace = trimToNull(targetNamespace);
        this.preferredNsPrefix = trimToNull(preferredNsPrefix);
        this.parsingTargetNamespace = parsingTargetNamespace;
        this.elements = createTreeMap();
        this.elementCollection = unmodifiableCollection(elements.values());
        this.sourceDesc = sourceDesc;
        this.sourceInfo = assertNotNull(sourceInfo, "sourceInfo");
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getTargetNamespace() {
        if (parsingTargetNamespace) {
            analyze();
        }

        return targetNamespace;
    }

    public String getPreferredNsPrefix() {
        return preferredNsPrefix;
    }

    protected void setPreferredNsPrefix(String preferredNsPrefix) {
        this.preferredNsPrefix = trimToNull(preferredNsPrefix);
    }

    public String[] getIncludes() {
        analyze();
        return includes == null ? EMPTY_STRING_ARRAY : includes;
    }

    public Collection<Element> getElements() {
        analyze();
        return elementCollection;
    }

    @Override
    public Element getElement(String elementName) {
        if (elementName != null) {
            analyze();
            return elements.get(elementName);
        } else {
            return null;
        }
    }

    /** 由schemaSet来设置。 */
    public void setElements(Collection<Element> elements) {
        this.elements.clear();

        if (elements != null) {
            for (Element element : elements) {
                this.elements.put(element.getName(), element);
            }
        }
    }

    public String getNamespacePrefix() {
        return SchemaUtil.getNamespacePrefix(getPreferredNsPrefix(), getTargetNamespace());
    }

    public String getSourceDescription() {
        return sourceDesc;
    }

    public String getText() {
        return getText(null, null);
    }

    public String getText(String charset) {
        return getText(charset, null);
    }

    public String getText(String charset, Transformer transformer) {
        String content;

        // parse
        Document doc = getDocument();

        // read text if it's an invalid XML doc.
        if (doc == null) {
            try {
                return readText(getInputStream(), "ISO-8859-1", true);
            } catch (IOException e) {
                return null; // 不会发生
            }
        }

        // filter the doc
        if (transformer != null) {
            doc = (Document) doc.clone(); // 避免修改schema中的document对象
            transformer.transform(doc, getName());
        }

        // output
        try {
            content = SchemaUtil.getDocumentText(doc, charset);
        } catch (Exception e) {
            throw new ConfigurationPointException("Failed to read text of schema file: " + name + ", source=" + super.toString(), e);
        }

        return content;
    }

    /**
     * 解析schema，取得以下信息：
     * <ol>
     * <li>targetNamespace</li>
     * <li>include name</li>
     * </ol>
     */
    @Override
    protected void doAnalyze() {
        Document doc = getDocument(); // 不可能是null
        org.dom4j.Element root = doc.getRootElement();

        // return if not a schema file
        if (!W3C_XML_SCHEMA_NS_URI.equals(root.getNamespaceURI()) || !"schema".equals(root.getName())) {
            return;
        }

        // parse targetNamespace
        if (parsingTargetNamespace) {
            Attribute attr = root.attribute("targetNamespace");

            if (attr != null) {
                targetNamespace = trimToNull(attr.getStringValue());
            }
        }

        // parse include
        Namespace xsd = DocumentHelper.createNamespace("xsd", W3C_XML_SCHEMA_NS_URI);
        QName includeName = DocumentHelper.createQName("include", xsd);
        List<String> includeNames = createLinkedList();

        // for each <xsd:include>
        for (Iterator<?> i = root.elementIterator(includeName); i.hasNext(); ) {
            org.dom4j.Element includeElement = (org.dom4j.Element) i.next();
            String schemaLocation = trimToNull(includeElement.attributeValue("schemaLocation"));

            if (schemaLocation != null) {
                includeNames.add(schemaLocation);
            }
        }

        includes = includeNames.toArray(new String[includeNames.size()]);

        // parse xsd:element
        QName elementName = DocumentHelper.createQName("element", xsd);

        // for each <xsd:element>
        for (Iterator<?> i = root.elementIterator(elementName); i.hasNext(); ) {
            Element element = new ElementImpl((org.dom4j.Element) i.next());

            if (element.getName() != null) {
                this.elements.put(element.getName(), element);
            }
        }
    }

    public P getParent() {
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
        if (targetNamespace == null) {
            return String.format("Schema[name=%s, version=%s, source=%s]", name, version, super.toString());
        } else {
            return String.format("Schema[name=%s, version=%s, targetNamespace=%s, source=%s]", name, version,
                                 targetNamespace, super.toString());
        }
    }

    private static class ElementImpl implements Element {
        private final static Namespace xsd               = DocumentHelper.createNamespace("xsd", W3C_XML_SCHEMA_NS_URI);
        private final static QName     annotationName    = DocumentHelper.createQName("annotation", xsd);
        private final static QName     documentationName = DocumentHelper.createQName("documentation", xsd);
        private final String name;
        private final String annotation;

        private ElementImpl(org.dom4j.Element elementElement) {
            // name
            String name = trimToNull(elementElement.attributeValue("name"));

            // annotation/documentation
            org.dom4j.Element annotationElement = elementElement.element(annotationName);
            org.dom4j.Element documentationElement = annotationElement == null ? null : annotationElement.element(documentationName);
            String documentation = documentationElement == null ? null : documentationElement.getText();

            if (documentation != null) {
                documentation = trimToNull(documentation.replaceAll("[ \\t]*[\\r|\\n|\\r\\n][ \\t]*", "\n")); // 除去每行首尾空白，保留换行符
            }

            this.name = name;
            this.annotation = documentation;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getAnnotation() {
            return annotation;
        }

        @Override
        public String toString() {
            return "Element[" + name + "]";
        }
    }
}
