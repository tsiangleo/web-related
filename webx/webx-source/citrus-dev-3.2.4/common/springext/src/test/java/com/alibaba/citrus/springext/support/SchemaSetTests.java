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

package com.alibaba.citrus.springext.support;

import static com.alibaba.citrus.test.TestEnvStatic.*;
import static com.alibaba.citrus.test.TestUtil.*;
import static com.alibaba.citrus.util.CollectionUtil.*;
import static javax.xml.XMLConstants.*;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.citrus.springext.Namespaces;
import com.alibaba.citrus.springext.Schema;
import com.alibaba.citrus.springext.Schema.Element;
import com.alibaba.citrus.springext.Schemas;
import com.alibaba.citrus.springext.impl.SchemaImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.InputStreamSource;

public class SchemaSetTests {
    private Schema            s1;
    private Schema            s2;
    private Schema            s3;
    private Schema            s4;
    private NamespacesSchemas schemas1;
    private Schemas           schemas2;
    private SchemaSet         set1;

    @Before
    public void init_data1() {
        // create schema
        s1 = createSchemaFromFile("b/c", "schema/ns.xsd");
        s2 = createSchemaFromFile("b/d", "schema/ns.xsd");
        s3 = createSchemaFromFile("a/b/c", "schema/ns.xsd");
        s4 = createSchemaFromFile("a/b/d", "schema/ns.xsd");

        // mocks to Schemas
        schemas1 = createMock(NamespacesSchemas.class);
        schemas2 = createMock(Schemas.class);

        Map<String, Schema> nameToSchemas1 = createHashMap();
        nameToSchemas1.put("b/c", s1);
        nameToSchemas1.put("b/d", s2);
        expect(schemas1.getNamedMappings()).andReturn(nameToSchemas1);

        Set<String> namespaces1 = createHashSet();
        namespaces1.add("http://www.alibaba.com/schema/test");
        namespaces1.add("http://www.springframework.com/schema/p");
        namespaces1.add("http://www.springframework.com/schema/c");
        expect(schemas1.getAvailableNamespaces()).andReturn(namespaces1);

        Map<String, Schema> nameToSchemas2 = createHashMap();
        nameToSchemas2.put("a/b/c", s3);
        nameToSchemas2.put("a/b/d", s4);
        expect(schemas2.getNamedMappings()).andReturn(nameToSchemas2);

        replay(schemas1, schemas2);

        // schema set
        set1 = new SchemaSet(schemas1, schemas2);
    }

    @Test
    public void new_noSchemas() {
        try {
            new SchemaSet((Schemas[]) null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e, exception("schemasList"));
        }

        try {
            new SchemaSet(new Schemas[0]);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e, exception("schemasList"));
        }
    }

    @Test
    public void getNamespaceMappings_getAvailableNamespaces() {
        Map<String, Set<Schema>> namespaceMappings = set1.getNamespaceMappings();
        Set<String> namespaces = set1.getAvailableNamespaces();

        assertEquals(3, namespaces.size());
        assertEquals(3, namespaceMappings.size());

        assertTrue(namespaces.contains("http://www.alibaba.com/schema/test"));
        assertTrue(namespaces.contains("http://www.springframework.com/schema/p"));
        assertTrue(namespaces.contains("http://www.springframework.com/schema/c"));

        assertTrue(namespaceMappings.containsKey("http://www.alibaba.com/schema/test"));
        assertTrue(namespaceMappings.containsKey("http://www.springframework.com/schema/p"));
        assertTrue(namespaceMappings.containsKey("http://www.springframework.com/schema/c"));

        assertEquals(4, namespaceMappings.get("http://www.alibaba.com/schema/test").size());
        assertEquals(0, namespaceMappings.get("http://www.springframework.com/schema/p").size());
        assertEquals(0, namespaceMappings.get("http://www.springframework.com/schema/c").size());
    }

    @Test
    public void getNamedMappings() {
        Map<String, Schema> schemas = createHashMap();
        schemas.putAll(set1.getNamedMappings());

        assertSame(s1, schemas.remove("b/c"));
        assertSame(s2, schemas.remove("b/d"));
        assertSame(s3, schemas.remove("a/b/c"));
        assertSame(s4, schemas.remove("a/b/d"));

        assertTrue(schemas.isEmpty());
    }

    @Test
    public void findSchema() {
        // empty
        try {
            set1.findSchema(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e, exception("systemId"));
        }

        try {
            set1.findSchema("  ");
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e, exception("systemId"));
        }

        // not found
        assertSame(null, set1.findSchema("b/e"));

        // priority
        assertSame(s1, set1.findSchema("b/c"));
        assertSame(s2, set1.findSchema("b/d"));
        assertSame(s3, set1.findSchema("a/b/c")); // 同时匹配b/c和a/b/c，但后者优先
        assertSame(s4, set1.findSchema("a/b/d")); // 同时匹配b/d和a/b/d，但后者优先

        // normalize
        assertSame(s3, set1.findSchema(" a/b/c "));
        assertSame(s3, set1.findSchema(" a//b\\/c "));
        assertSame(s3, set1.findSchema(" http://localhost:8080/a//b\\/c "));
    }

    @Test
    public void toString_() {
        String str = "";

        str += "SchemaSet [\n";
        str += "  [1/4] a/b/c\n";
        str += "  [2/4] a/b/d\n";
        str += "  [3/4] b/c\n";
        str += "  [4/4] b/d\n";
        str += "]";

        assertEquals(str, set1.toString());
    }

    private Schema    all;
    private Schema    x;
    private Schema    y;
    private Schema    z;
    private Schema    a;
    private Schema    b;
    private Schemas   schemas;
    private SchemaSet set2;

    @Before
    public void init_data2() {
        // create schema:
        all = createSchemaFromFile("schema/all.xsd");
        x = createSchemaFromFile("schema/x.xsd");
        y = createSchemaFromFile("schema/y.xsd");
        z = createSchemaFromFile("schema/z.xsd");
        a = createSchemaFromFile("schema/a.xsd");
        b = createSchemaFromFile("schema/b.xsd");

        // mocks to Schemas
        schemas = createMock(Schemas.class);

        Map<String, Schema> nameToSchemas = createHashMap();
        nameToSchemas.put("schema/all.xsd", all);
        nameToSchemas.put("schema/x.xsd", x);
        nameToSchemas.put("schema/y.xsd", y);
        nameToSchemas.put("schema/z.xsd", z);
        nameToSchemas.put("schema/a.xsd", a);
        nameToSchemas.put("schema/b.xsd", b);
        expect(schemas.getNamedMappings()).andReturn(nameToSchemas);

        replay(schemas);

        // schema set
        set2 = new SchemaSet(schemas);
    }

    @Test
    public void getAnnotation() {
        String annotation1 = all.getElement("test").getAnnotation();
        String annotation2 = all.getElement("testa").getAnnotation();
        String annotation3 = all.getElement("testb").getAnnotation();

        assertEquals("line 1\n" +
                     "line 2\n" +
                     "line 3", annotation1);

        assertEquals("line 1/a\n" +
                     "line 2/a\n" +
                     "line 3/a", annotation2);

        assertEquals(null, annotation3);
    }

    @Test
    public void getAllElements() {
        // all -> x, y, z
        // x   -> a, z
        // y   -> b, z
        // z   ->
        // a   ->
        // b   ->
        assertArrayEquals(new String[] { "test", "testa", "testb", "testx", "testy", "testz" },
                          getElements(set2, "all"));

        assertArrayEquals(new String[] { "testa", "testx", "testz" }, getElements(set2, "x"));
        assertArrayEquals(new String[] { "testb", "testy", "testz" }, getElements(set2, "y"));
        assertArrayEquals(new String[] { "testz" }, getElements(set2, "z"));
        assertArrayEquals(new String[] { "testa" }, getElements(set2, "a"));
        assertArrayEquals(new String[] { "testb" }, getElements(set2, "b"));
    }

    private String[] getElements(Schemas schemas, String name) {
        Schema schema = schemas.getNamedMappings().get("schema/" + name + ".xsd");
        Collection<Element> elements = schema.getElements();
        String[] elementNames = new String[elements.size()];

        int i = 0;
        for (Element element : elements) {
            String elementName = element.getName();

            assertGetElement(schema, element, "Element[" + elementName + "]");

            elementNames[i++] = elementName;
        }

        return elementNames;
    }

    private void assertGetElement(Schema schema, Element element, String toString) {
        assertSame(element, schema.getElement(element.getName()));
        assertSame(null, schema.getElement(null));
        assertSame(null, schema.getElement("not exists"));
        assertEquals(toString, element.toString());
    }

    @Test
    public void processIncludes() throws Exception {
        // all -> x, y, z
        // x   -> a, z
        // y   -> b, z
        // z   ->
        // a   ->
        // b   ->
        Map<String, Schema> nameToSchemas = set2.getNamedMappings();

        assertEquals(6, nameToSchemas.size());

        assertSame(all, nameToSchemas.get("schema/all.xsd"));
        assertSame(x, nameToSchemas.get("schema/x.xsd"));
        assertSame(y, nameToSchemas.get("schema/y.xsd"));

        assertSame(z, nameToSchemas.get("schema/z.xsd"));
        assertSame(a, nameToSchemas.get("schema/a.xsd"));
        assertSame(b, nameToSchemas.get("schema/b.xsd"));

        // 检查内容: all被转换
        Iterator<org.dom4j.Element> i = getNodes(all, "http://www.alibaba.com/schema/test", 6).iterator();
        assertEquals("schema/a.xsd", i.next().attributeValue("schemaLocation"));
        assertEquals("schema/z.xsd", i.next().attributeValue("schemaLocation"));
        assertEquals("schema/x.xsd", i.next().attributeValue("schemaLocation"));
        assertEquals("schema/b.xsd", i.next().attributeValue("schemaLocation"));
        assertEquals("schema/y.xsd", i.next().attributeValue("schemaLocation"));
        assertEquals("test", i.next().attributeValue("name"));

        // 检查内容: x被转换
        i = getNodes(x, null, 1).iterator();
        assertEquals("testx", i.next().attributeValue("name"));

        // 检查内容: y被转换
        i = getNodes(y, null, 1).iterator();
        assertEquals("testy", i.next().attributeValue("name"));
    }

    private List<org.dom4j.Element> getNodes(Schema schema, String ns, int count) {
        org.dom4j.Element root = schema.getDocument().getRootElement();

        assertEquals(W3C_XML_SCHEMA_NS_URI, root.getNamespaceURI());
        assertEquals("schema", root.getName());
        assertEquals(ns, root.attributeValue("targetNamespace"));

        @SuppressWarnings("unchecked")
        List<org.dom4j.Element> elements = root.elements();
        assertEquals(count, elements.size());

        return elements;
    }

    private Schema createSchemaFromFile(String fileName) {
        return createSchemaFromFile(fileName, fileName);
    }

    private Schema createSchemaFromFile(String name, final String fileName) {
        return SchemaImpl.create(name, null, true, fileName + " desc", new InputStreamSource() {
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(new File(srcdir, fileName));
            }
        });
    }

    interface NamespacesSchemas extends Schemas, Namespaces {
    }
}
