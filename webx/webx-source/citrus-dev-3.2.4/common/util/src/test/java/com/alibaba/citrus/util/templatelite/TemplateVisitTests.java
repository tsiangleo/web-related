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

package com.alibaba.citrus.util.templatelite;

import static com.alibaba.citrus.test.TestUtil.*;
import static com.alibaba.citrus.util.StringUtil.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.hamcrest.Matchers;
import org.junit.Test;

public class TemplateVisitTests extends AbstractTemplateTests {
    @Test
    public void render_methodNotFound_forText() throws Exception {
        String s;

        s = "";
        s += "test";

        loadTemplate(s.getBytes(), "test.txt", 1, 0, 0);

        // 无visitText(String)方法
        class Visitor {
        }

        acceptFailure(new Visitor());
        assertThat(runtimeError, exception(NoSuchMethodException.class, "Error rendering Text with 4 characters"));
        assertEquals("Visitor.visitText(String)", runtimeError.getCause().getMessage());

        // 有visitText()方法，但参数不匹配
        @SuppressWarnings("unused")
        class Visitor2 {
            public void visitText() {
            }
        }

        acceptFailure(new Visitor2());
        assertThat(runtimeError, exception(NoSuchMethodException.class, "Error rendering Text with 4 characters"));
        assertEquals("Visitor2.visitText(String)", runtimeError.getCause().getMessage());

        // 有visitText()方法，但参数不匹配
        @SuppressWarnings("unused")
        class Visitor3 {
            public void visitText(Object o) {
            }
        }

        acceptFailure(new Visitor3());
        assertThat(runtimeError, exception(NoSuchMethodException.class, "Error rendering Text with 4 characters"));
        assertEquals("Visitor3.visitText(String)", runtimeError.getCause().getMessage());
    }

    @Test
    public void render_methodNotFound_forPlaceholder_All_Strings() throws Exception {
        @SuppressWarnings("unused")
        class Visitor {
            // 无方法
            // public void visitTest1() {
            // }

            // 有1个参数，但参数类型非数组
            public void visitTest2(Object obj) {
            }

            // 有1个参数，但参数类型非正确的数组
            public void visitTest3(Template[] params) {
            }

            // 有多个参数，参数数量大于placeholder参数数量，多余的参数类型不正确
            public void visitTest4(String a, String b, String c, Object d) {
            }

            // 有多个参数，但参数类型不匹配
            public void visitTest5(String a, String b, Template c) {
            }
        }

        loadTemplate("${test1: a, b, c}".getBytes(), "test.txt", 1, 0, 0);
        acceptFailure(new Visitor());
        assertThat(runtimeError,
                   exception(NoSuchMethodException.class, "Error rendering ${test1:a, b, c} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest1(String, String, String)\n" //
                     + "  2. Visitor.visitTest1(String[])\n" //
                     + "  3. Visitor.visitTest1()", runtimeError.getCause().getMessage());

        loadTemplate("${test1}".getBytes(), "test.txt", 1, 0, 0);
        acceptFailure(new Visitor());
        assertThat(runtimeError,
                   exception(NoSuchMethodException.class, "Error rendering ${test1} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest1()\n" //
                     + "  2. Visitor.visitTest1(String[])", runtimeError.getCause().getMessage());

        loadTemplate("${test2: a, b, c}".getBytes(), "test.txt", 1, 0, 0);
        acceptFailure(new Visitor());
        assertThat(runtimeError,
                   exception(NoSuchMethodException.class, "Error rendering ${test2:a, b, c} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest2(String, String, String)\n" //
                     + "  2. Visitor.visitTest2(String[])\n" //
                     + "  3. Visitor.visitTest2()", runtimeError.getCause().getMessage());

        loadTemplate("${test3: a, b, c}".getBytes(), "test.txt", 1, 0, 0);
        acceptFailure(new Visitor());
        assertThat(runtimeError,
                   exception(NoSuchMethodException.class, "Error rendering ${test3:a, b, c} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest3(String, String, String)\n" //
                     + "  2. Visitor.visitTest3(String[])\n" //
                     + "  3. Visitor.visitTest3()", runtimeError.getCause().getMessage());

        loadTemplate("${test4: a, b, c}".getBytes(), "test.txt", 1, 0, 0);
        acceptFailure(new Visitor());
        assertThat(runtimeError,
                   exception(NoSuchMethodException.class, "Error rendering ${test4:a, b, c} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest4(String, String, String)\n" //
                     + "  2. Visitor.visitTest4(String[])\n" //
                     + "  3. Visitor.visitTest4()", runtimeError.getCause().getMessage());

        loadTemplate("${test5: a, b, c}".getBytes(), "test.txt", 1, 0, 0);
        acceptFailure(new Visitor());
        assertThat(runtimeError,
                   exception(NoSuchMethodException.class, "Error rendering ${test5:a, b, c} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest5(String, String, String)\n" //
                     + "  2. Visitor.visitTest5(String[])\n" //
                     + "  3. Visitor.visitTest5()", runtimeError.getCause().getMessage());
    }

    @Test
    public void render_methodNotFound_forPlaceholder_All_Template() throws Exception {
        @SuppressWarnings("unused")
        class Visitor {
            // 无方法
            // public void visitTest1() {
            // }

            // 有1个参数，但参数类型非数组
            public void visitTest2(Object obj) {
            }

            // 有1个参数，但参数类型非正确的数组
            public void visitTest3(String[] params) {
            }

            // 有多个参数，参数数量大于placeholder参数数量，多余的参数类型不正确
            public void visitTest4(Template a, Template b, Template c, Object d) {
            }

            // 有多个参数，但参数类型不匹配
            public void visitTest5(Template a, Template b, String c) {
            }
        }

        String s = "\n#a\n#end\n#b\n#end\n#c\n#end\n";

        loadTemplate(("${test1: #a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        acceptFailure(new Visitor());
        assertThat(
                runtimeError,
                exception(NoSuchMethodException.class,
                          "Error rendering ${test1:#a, #b, #c} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest1(Template, Template, Template)\n" //
                     + "  2. Visitor.visitTest1(Template[])\n" //
                     + "  3. Visitor.visitTest1()", runtimeError.getCause().getMessage());

        loadTemplate(("${test2: #a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        acceptFailure(new Visitor());
        assertThat(
                runtimeError,
                exception(NoSuchMethodException.class,
                          "Error rendering ${test2:#a, #b, #c} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest2(Template, Template, Template)\n" //
                     + "  2. Visitor.visitTest2(Template[])\n" //
                     + "  3. Visitor.visitTest2()", runtimeError.getCause().getMessage());

        loadTemplate(("${test3: #a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        acceptFailure(new Visitor());
        assertThat(
                runtimeError,
                exception(NoSuchMethodException.class,
                          "Error rendering ${test3:#a, #b, #c} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest3(Template, Template, Template)\n" //
                     + "  2. Visitor.visitTest3(Template[])\n" //
                     + "  3. Visitor.visitTest3()", runtimeError.getCause().getMessage());

        loadTemplate(("${test4: #a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        acceptFailure(new Visitor());
        assertThat(
                runtimeError,
                exception(NoSuchMethodException.class,
                          "Error rendering ${test4:#a, #b, #c} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest4(Template, Template, Template)\n" //
                     + "  2. Visitor.visitTest4(Template[])\n" //
                     + "  3. Visitor.visitTest4()", runtimeError.getCause().getMessage());

        loadTemplate(("${test5: #a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        acceptFailure(new Visitor());
        assertThat(
                runtimeError,
                exception(NoSuchMethodException.class,
                          "Error rendering ${test5:#a, #b, #c} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest5(Template, Template, Template)\n" //
                     + "  2. Visitor.visitTest5(Template[])\n" //
                     + "  3. Visitor.visitTest5()", runtimeError.getCause().getMessage());
    }

    @Test
    public void render_methodNotFound_forPlaceholder_Hybrid_String_and_Template() throws Exception {
        @SuppressWarnings("unused")
        class Visitor {
            // 无方法
            // public void visitTest1() {
            // }

            // 有1个参数，但参数类型非数组
            public void visitTest2(Object obj) {
            }

            // 有1个参数，但参数类型非正确的数组
            public void visitTest3(String[] params) {
            }

            // 有1个参数，但参数类型非正确的数组
            public void visitTest4(Template[] params) {
            }

            // 有多个参数，参数数量大于placeholder参数数量，多余的参数类型不正确
            public void visitTest5(String a, Template b, Template c, Object d) {
            }

            // 有多个参数，但参数类型不匹配
            public void visitTest6(String a, Template b, String c) {
            }
        }

        String s = "\n#b\n#end\n#c\n#end\n";

        loadTemplate(("${test1: a, #b, #c}" + s).getBytes(), "test.txt", 1, 2, 0);
        acceptFailure(new Visitor());
        assertThat(
                runtimeError,
                exception(NoSuchMethodException.class,
                          "Error rendering ${test1:a, #b, #c} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest1(String, Template, Template)\n" //
                     + "  2. Visitor.visitTest1(Object[])\n" //
                     + "  3. Visitor.visitTest1()", runtimeError.getCause().getMessage());

        loadTemplate(("${test2: a, #b, #c}" + s).getBytes(), "test.txt", 1, 2, 0);
        acceptFailure(new Visitor());
        assertThat(
                runtimeError,
                exception(NoSuchMethodException.class,
                          "Error rendering ${test2:a, #b, #c} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest2(String, Template, Template)\n" //
                     + "  2. Visitor.visitTest2(Object[])\n" //
                     + "  3. Visitor.visitTest2()", runtimeError.getCause().getMessage());

        loadTemplate(("${test3: a, #b, #c}" + s).getBytes(), "test.txt", 1, 2, 0);
        acceptFailure(new Visitor());
        assertThat(
                runtimeError,
                exception(NoSuchMethodException.class,
                          "Error rendering ${test3:a, #b, #c} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest3(String, Template, Template)\n" //
                     + "  2. Visitor.visitTest3(Object[])\n" //
                     + "  3. Visitor.visitTest3()", runtimeError.getCause().getMessage());

        loadTemplate(("${test4: a, #b, #c}" + s).getBytes(), "test.txt", 1, 2, 0);
        acceptFailure(new Visitor());
        assertThat(
                runtimeError,
                exception(NoSuchMethodException.class,
                          "Error rendering ${test4:a, #b, #c} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest4(String, Template, Template)\n" //
                     + "  2. Visitor.visitTest4(Object[])\n" //
                     + "  3. Visitor.visitTest4()", runtimeError.getCause().getMessage());

        loadTemplate(("${test5: a, #b, #c}" + s).getBytes(), "test.txt", 1, 2, 0);
        acceptFailure(new Visitor());
        assertThat(
                runtimeError,
                exception(NoSuchMethodException.class,
                          "Error rendering ${test5:a, #b, #c} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest5(String, Template, Template)\n" //
                     + "  2. Visitor.visitTest5(Object[])\n" //
                     + "  3. Visitor.visitTest5()", runtimeError.getCause().getMessage());

        loadTemplate(("${test6: a, #b, #c}" + s).getBytes(), "test.txt", 1, 2, 0);
        acceptFailure(new Visitor());
        assertThat(
                runtimeError,
                exception(NoSuchMethodException.class,
                          "Error rendering ${test6:a, #b, #c} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTest6(String, Template, Template)\n" //
                     + "  2. Visitor.visitTest6(Object[])\n" //
                     + "  3. Visitor.visitTest6()", runtimeError.getCause().getMessage());
    }

    @Test
    public void render_forPlaceholder_All_Strings() throws Exception {
        @SuppressWarnings("unused")
        class Visitor extends TextWriter<StringBuilder> {
            // 无参数
            public void visitTest1() {
                out().append("no_params");
            }

            // 1个参数：String
            public void visitTest2(String a) {
                out().append(a);
            }

            // 有1个参数：String[]
            public void visitTest3(String[] params) {
                out().append(join(params, ","));
            }

            // 有1个参数：Object[]
            public void visitTest4(Object[] params) {
                out().append(join(params, ","));
            }

            // 有多个参数，参数数量小于placeholder参数数量
            public void visitTest5(String a, String b) {
                out().append(a + "," + b);
            }

            // 有多个参数，参数数量完全匹配
            public void visitTest6(String a, String b, String c) {
                out().append(a + "," + b + "," + c);
            }
        }

        loadTemplate("${test1: a, b, c}".getBytes(), "test.txt", 1, 0, 0);
        assertEquals("no_params", template.renderToString(new Visitor()));

        loadTemplate("${test2: a, b, c}".getBytes(), "test.txt", 1, 0, 0);
        assertEquals("a", template.renderToString(new Visitor()));

        loadTemplate("${test3: a, b, c}".getBytes(), "test.txt", 1, 0, 0);
        assertEquals("a,b,c", template.renderToString(new Visitor()));

        loadTemplate("${test4: a, b, c}".getBytes(), "test.txt", 1, 0, 0);
        assertEquals("a,b,c", template.renderToString(new Visitor()));

        loadTemplate("${test5: a, b, c}".getBytes(), "test.txt", 1, 0, 0);
        assertEquals("a,b", template.renderToString(new Visitor()));

        loadTemplate("${test6: a, b, c}".getBytes(), "test.txt", 1, 0, 0);
        assertEquals("a,b,c", template.renderToString(new Visitor()));
    }

    @Test
    public void render_forPlaceholder_All_Templates() throws Exception {
        @SuppressWarnings("unused")
        class Visitor extends TextWriter<StringBuilder> {
            // 无参数
            public void visitTest1() {
                out().append("no_params");
            }

            // 1个参数：String
            public void visitTest2(Template a) {
                a.accept(this);
            }

            // 有1个参数：Template[]
            public void visitTest3(Template[] params) {
                for (Template template : params) {
                    template.accept(this);
                }
            }

            // 有1个参数：Object[]
            public void visitTest4(Object[] params) {
                for (Object template : params) {
                    ((Template) template).accept(this);
                }
            }

            // 有多个参数，参数数量小于placeholder参数数量
            public void visitTest5(Template a, Template b) {
                a.accept(this);
                b.accept(this);
            }

            // 有多个参数，参数数量完全匹配
            public void visitTest6(Template a, Template b, Template c) {
                a.accept(this);
                b.accept(this);
                c.accept(this);
            }
        }

        String s = "\n#a\na\n#end\n#b\nb\n#end\n#c\nc\n#end\n";

        loadTemplate(("${test1: #a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        assertEquals("no_params", template.renderToString(new Visitor()));

        loadTemplate(("${test2: #a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        assertEquals("a", template.renderToString(new Visitor()));

        loadTemplate(("${test3: #a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        assertEquals("abc", template.renderToString(new Visitor()));

        loadTemplate(("${test4: #a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        assertEquals("abc", template.renderToString(new Visitor()));

        loadTemplate(("${test5: #a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        assertEquals("ab", template.renderToString(new Visitor()));

        loadTemplate(("${test6: #a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        assertEquals("abc", template.renderToString(new Visitor()));
    }

    @Test
    public void render_forPlaceholder_Hybrid_String_and_Template() throws Exception {
        @SuppressWarnings("unused")
        class Visitor extends TextWriter<StringBuilder> {
            // 无参数
            public void visitTest1() {
                out().append("no_params");
            }

            // 1个参数：String
            public void visitTest2(String a) {
                out().append(a);
            }

            // 有1个参数：Object[]
            public void visitTest3(Object[] params) {
                out().append(params[0]);
                ((Template) params[1]).accept(this);
                ((Template) params[2]).accept(this);
            }

            // 有多个参数，参数数量小于placeholder参数数量
            public void visitTest4(String a, Template b) {
                out().append(a);
                b.accept(this);
            }

            // 有多个参数，参数数量完全匹配
            public void visitTest5(String a, Template b, Template c) {
                out().append(a);
                b.accept(this);
                c.accept(this);
            }
        }

        String s = "\n";
        s += "#a\n";
        s += "a\n";
        s += "#end\n";
        s += "#b\n";
        s += "b\n";
        s += "#end\n";
        s += "#c\n";
        s += "c\n";
        s += "#end\n";

        loadTemplate(("${test1: a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        assertEquals("no_params", template.renderToString(new Visitor()));

        loadTemplate(("${test2: a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        assertEquals("a", template.renderToString(new Visitor()));

        loadTemplate(("${test3: a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        assertEquals("abc", template.renderToString(new Visitor()));

        loadTemplate(("${test4: a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        assertEquals("ab", template.renderToString(new Visitor()));

        loadTemplate(("${test5: a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        assertEquals("abc", template.renderToString(new Visitor()));
    }

    @Test
    public void render_forPlaceholder_TemplateRefs() throws Exception {
        @SuppressWarnings("unused")
        class Visitor extends TextWriter<StringBuilder> {
            public void visitTest(Template[] params) {
                for (Template template : params) {
                    template.accept(this);
                }
            }

            public void visitA(Template a) {
            }

            /*
             * // 缺失visitB public void visitB(Template b) { b.accept(this); }
             */

            public void visitC(Template c) {
                c.accept(this);
                c.accept(this);
                c.accept(this);
            }
        }

        String s = "\n";
        s += "#a\n";
        s += "a\n";
        s += "#end\n";
        s += "#b\n";
        s += "b\n";
        s += "#end\n";
        s += "#c\n";
        s += "c\n";
        s += "#end\n";

        loadTemplate(("${test: #a, #b, #c}" + s).getBytes(), "test.txt", 1, 3, 0);
        assertEquals("bccc", template.renderToString(new Visitor()));
    }

    @Test
    public void render_include_template() throws Exception {
        loadTemplate("$#{a}\n#a\naaa\n#end".getBytes(), "test.txt", 1, 1, 0);

        assertEquals("aaa", template.renderToString(new FallbackTextWriter<StringBuilder>()));
    }

    @Test
    public void render_include_templateRef() throws Exception {
        @SuppressWarnings("unused")
        class Visitor extends TextWriter<StringBuilder> {
            public void visitA(Template a) {
                a.accept(this);
                a.accept(this);
                a.accept(this);
            }
        }

        loadTemplate("$#{a}\n#a\na\n#end".getBytes(), "test.txt", 1, 1, 0);

        assertEquals("aaa", template.renderToString(new Visitor()));
    }

    @Test
    public void render_include_redirect_templateRef() throws Exception {
        @SuppressWarnings("unused")
        class Visitor1 extends TextWriter<StringBuilder> {
            public Visitor1(StringBuilder out) {
                super(out);
            }

            public void visitA(Template a) {
                a.accept(this);
                a.accept(this);
                a.accept(this);
            }
        }
        @SuppressWarnings("unused")
        class Visitor2 extends TextWriter<StringBuilder> {
            public Visitor1 visitA(Template a) {
                return new Visitor1(out());
            }
        }

        loadTemplate("$#{a}\n#a\na\n#end".getBytes(), "test.txt", 1, 1, 0);

        assertEquals("aaa", template.renderToString(new Visitor2()));
    }

    @Test
    public void render_visitorThrowsException() throws Exception {
        @SuppressWarnings("unused")
        class Visitor {
            public void visitTitle() throws IOException {
                throw new IllegalArgumentException();
            }
        }

        loadTemplate("${title}".getBytes(), "test.txt", 1, 0, 0);
        acceptFailure(new Visitor());
        assertThat(runtimeError.getCause(), Matchers.instanceOf(IllegalArgumentException.class));
        assertThat(runtimeError,
                   exception(IllegalArgumentException.class, "Error rendering ${title} at test.txt: Line 1 Column 1"));
    }

    @Test
    public void render_visitorThrowsIOException() throws Exception {
        @SuppressWarnings("unused")
        class Visitor {
            public void visitTitle() throws IOException {
                throw new IOException();
            }
        }

        loadTemplate("${title}".getBytes(), "test.txt", 1, 0, 0);
        acceptFailure(new Visitor());
        assertThat(runtimeError.getCause(), Matchers.instanceOf(IOException.class));
        assertThat(runtimeError, exception(IOException.class, "Error rendering ${title} at test.txt: Line 1 Column 1"));
    }

    @Test
    public void render_visitorThrowsException_withInvocationHandler() throws Exception {
        @SuppressWarnings("unused")
        class Visitor implements VisitorInvocationErrorHandler {
            public void visitTitle() throws IOException {
                throw new IllegalArgumentException("haha");
            }

            public void handleInvocationError(String desc, Throwable e) {
                assertThat(desc, containsAll("${title} at test.txt: Line 1 Column 1"));
                runtimeError = new TemplateRuntimeException(e);
            }
        }

        loadTemplate("${title}".getBytes(), "test.txt", 1, 0, 0);
        template.accept(new Visitor());
        assertThat(runtimeError.getCause(), exception(IllegalArgumentException.class, "haha"));
    }

    @Test
    public void render_visitorThrowsException_withInvocationHandlerError() throws Exception {
        @SuppressWarnings("unused")
        class Visitor implements VisitorInvocationErrorHandler {
            public void visitTitle() throws IOException {
                throw new IllegalArgumentException("haha");
            }

            public void handleInvocationError(String desc, Throwable e) {
                throw new IllegalArgumentException("handleInvocationError");
            }
        }

        loadTemplate("${title}".getBytes(), "test.txt", 1, 0, 0);

        try {
            template.accept(new Visitor());
            fail();
        } catch (TemplateRuntimeException e) {
            assertThat(
                    e,
                    exception(IllegalArgumentException.class, "handleInvocationError",
                              "${title} at test.txt: Line 1 Column 1"));
        }
    }

    @Test
    public void render_visitorThrowsException_textWriter() throws Exception {
        @SuppressWarnings("unused")
        class Visitor extends TextWriter<StringBuilder> {
            public void visitTitle() throws IOException {
                IOException e = new IOException();
                e.initCause(new IllegalArgumentException("haha"));
                throw e;
            }
        }

        loadTemplate("${title}".getBytes(), "test.txt", 1, 0, 0);

        // 打印root cause
        assertThat(template.renderToString(new Visitor()),
                   containsAll("IllegalArgumentException - haha - ", Visitor.class.getName() + ".visitTitle("));
    }

    @Test
    public void render_fallbackVisitor() throws Exception {
        // no context
        loadTemplate(("$#{a}\n" //
                      + "#a\n" //
                      + "${title:a,b}\n" //
                      + "#end\n").getBytes(), "test.txt", 1, 1, 0);

        assertEquals("${title}", template.renderToString(new FallbackTextWriter<StringBuilder>()));

        // with context
        FallbackTextWriter<StringBuilder> visitor = new FallbackTextWriter<StringBuilder>();

        visitor.context().put("title", "hello, world");

        assertEquals("hello, world", template.renderToString(visitor));
    }

    @Test
    public void render_fallbackVisitor_failed() throws Exception {
        loadTemplate("${title:a,b}".getBytes(), "test.txt", 1, 0, 0);

        class Visitor implements FallbackVisitor {
            public boolean visitPlaceholder(String name, Object[] params) throws Exception {
                return false;
            }
        }

        acceptFailure(new Visitor());
        assertThat(runtimeError,
                   exception(NoSuchMethodException.class, "Error rendering ${title:a,b} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor.visitTitle(String, String)\n" //
                     + "  2. Visitor.visitTitle(String[])\n" //
                     + "  3. Visitor.visitTitle()", runtimeError.getCause().getMessage());
    }

    @Test
    public void render_placeholder_string_noparam() throws Exception {
        @SuppressWarnings("unused")
        class Visitor extends TextWriter<StringBuilder> {
            private int count;

            public void visitTitle() throws IOException {
                out().append("myTitle");
            }

            public void visitItem(Template dateItem) throws IOException {
                for (count = 1; count < 6; count++) {
                    dateItem.accept(this);
                }
            }

            public void visitDate() throws IOException {
                out().append("count " + count);
            }
        }

        render(new Visitor(), "");
    }

    @Test
    public void render_placeholder_string_array() throws Exception {
        @SuppressWarnings("unused")
        class Visitor extends TextWriter<StringBuilder> {
            private int count;

            public void visitTitle() throws IOException {
                out().append("myTitle");
            }

            public void visitItem(Template dateItem) throws IOException {
                for (count = 1; count < 6; count++) {
                    dateItem.accept(this);
                }
            }

            public void visitDate(String[] params) throws IOException {
                out().append("count " + count + " - ").append(formatGMT(params[0]));

                if (params.length > 1) {
                    out().append(" ").append(formatGMT(params[1]));
                }
            }
        }

        render(new Visitor(), " - 1970-01-01");
    }

    @Test
    public void render_placeholder_strings() throws Exception {
        @SuppressWarnings("unused")
        class Visitor extends TextWriter<StringBuilder> {
            private int count;

            public void visitTitle() throws IOException {
                out().append("myTitle");
            }

            public void visitItem(Template dateItem, Template datetimeItem) throws IOException {
                for (count = 1; count < 6; count++) {
                    dateItem.accept(this);
                }
            }

            public void visitDate(String p1, String p2) throws IOException {
                out().append("count " + count + " - ").append(formatGMT(p1));

                if (p2 != null) {
                    out().append(" ").append(formatGMT(p2));
                }
            }
        }

        render(new Visitor(), " - 1970-01-01");
    }

    @Test
    public void render_placeholder_template_array() throws Exception {
        @SuppressWarnings("unused")
        class Visitor extends TextWriter<StringBuilder> {
            private int count;

            public void visitTitle() throws IOException {
                out().append("myTitle");
            }

            public void visitItem(Template[] tpls) throws IOException {
                for (count = 1; count < 6; count++) {
                    tpls[0].accept(this);
                }
            }

            public void visitDate(String p1, String p2) throws IOException {
                out().append("count " + count + " - ").append(formatGMT(p1));

                if (p2 != null) {
                    out().append(" ").append(formatGMT(p2));
                }
            }
        }

        render(new Visitor(), " - 1970-01-01");
    }

    @Test
    public void render_placeholder_templates() throws Exception {
        @SuppressWarnings("unused")
        class Visitor extends TextWriter<StringBuilder> {
            private int count;

            public void visitTitle() throws IOException {
                out().append("myTitle");
            }

            public void visitItem(Template dateItem, Template datetimeItem) throws IOException {
                for (count = 1; count < 6; count++) {
                    datetimeItem.accept(this);
                }
            }

            public void visitDate(String p1, String p2) throws IOException {
                out().append("count " + count + " - ").append(formatGMT(p1));

                if (p2 != null) {
                    out().append(" ").append(formatGMT(p2));
                }
            }
        }

        render(new Visitor(), " - 1970-01-01 00:00");
    }

    @Test
    public void render_placeholder_method_overrides() throws Exception {
        assertRenderOverride("${title}", "title()");

        assertRenderOverride("${title:a}", "title(String)");
        assertRenderOverride("${title:a,b}", "title(String, String)");
        assertRenderOverride("${title:a,b,c}", "title(String[])");
        assertRenderOverride("${title:a,b,c,d}", "title(String[])");

        assertRenderOverride("${title:#a}", "title(Template)");
        assertRenderOverride("${title:#a,#b}", "title(Template, Template)");
        assertRenderOverride("${title:#a,#b,#c}", "title(Template[])");
        assertRenderOverride("${title:#a,#b,#c,#d}", "title(Template[])");

        assertRenderOverride("${title:#a,b}", "title(Template, String)");
        assertRenderOverride("${title:#a,b,c}", "title(Object[])");
    }

    private void assertRenderOverride(String placeholder, String result) {
        @SuppressWarnings("unused")
        class Visitor extends TextWriter<StringBuilder> {
            public void visitTitle() {
                out().append("title()");
            }

            public void visitTitle(String[] s) {
                out().append("title(String[])");
            }

            public void visitTitle(Template[] s) {
                out().append("title(Template[])");
            }

            public void visitTitle(Object[] s) {
                out().append("title(Object[])");
            }

            public void visitTitle(String s) {
                out().append("title(String)");
            }

            public void visitTitle(String s, String s1) {
                out().append("title(String, String)");
            }

            public void visitTitle(Template s) {
                out().append("title(Template)");
            }

            public void visitTitle(Template s, Template s1) {
                out().append("title(Template, Template)");
            }

            public void visitTitle(Template s, String s1) {
                out().append("title(Template, String)");
            }
        }

        String s = "\n";
        s += "#a\n";
        s += "#end\n";
        s += "#b\n";
        s += "#end\n";
        s += "#c\n";
        s += "#end\n";
        s += "#d\n";
        s += "#end\n";

        loadTemplate((placeholder + s).getBytes(), "test.txt", 1, 4, 0);
        assertEquals(result, template.renderToString(new Visitor()));
    }

    @Test
    public void render_forPlaceholder_redirect() {
        @SuppressWarnings("unused")
        class Visitor1 extends TextWriter<StringBuilder> {
            public Visitor1(StringBuilder out) {
                super(out);
            }

            public void visitItems(Template t, String s) {
                out().append(s);
                t.accept(this);
            }
        }
        @SuppressWarnings("unused")
        class Visitor2 extends TextWriter<StringBuilder> {
            public Visitor1 visitItems() {
                return new Visitor1(out()); // redirect to visitor1
            }
        }

        String s = "${items: #a, hello}\n";
        s += "#a\n";
        s += "world\n";
        s += "#end\n";

        loadTemplate(s.getBytes(), "test.txt", 1, 1, 0);
        assertEquals("helloworld", template.renderToString(new Visitor2()));
    }

    @Test
    public void render_forPlaceholder_redirectToSelf() {
        @SuppressWarnings("unused")
        class Visitor2 extends TextWriter<StringBuilder> {
            public Visitor2 visitItems() {
                return this; // redirect to self
            }
        }

        String s = "${items: #a, hello}\n";
        s += "#a\n";
        s += "world\n";
        s += "#end\n";

        loadTemplate(s.getBytes(), "test.txt", 1, 1, 0);
        assertEquals("", template.renderToString(new Visitor2()));
    }

    @Test
    public void render_forPlaceholder_redirect_infinite() {
        @SuppressWarnings("unused")
        class Visitor2 extends TextWriter<StringBuilder> {
            public Visitor2(StringBuilder out) {
                super(out);
            }

            public Visitor2 visitItems() {
                return new Visitor2(out()); // redirect to another visitor2
            }
        }

        String s = "${items: #a, hello}\n";
        s += "#a\n";
        s += "world\n";
        s += "#end\n";

        loadTemplate(s.getBytes(), "test.txt", 1, 1, 0);

        try {
            template.renderToString(new Visitor2(null));
            fail();
        } catch (TemplateRuntimeException e) {
            assertThat(e, exception("Redirection out of control (depth>10) in ", "Visitor2 ", "Visitor2.visitItems()"));
        }
    }

    @Test
    public void render_fallbackToVisitor() {
        try {
            new FallbackToVisitor(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e, exception("fallback to visitor"));
        }

        @SuppressWarnings("unused")
        class Visitor1 extends TextWriter<StringBuilder> {
            public Visitor1(StringBuilder out) {
                super(out);
            }

            public void visitTitle() {
                out().append("hello");
            }
        }

        class Visitor2 extends TextWriter<StringBuilder> implements FallbackVisitor {
            public boolean visitPlaceholder(String name, Object[] params) throws Exception {
                FallbackToVisitor ftv = new FallbackToVisitor(new Visitor1(out()));
                return ftv.visitPlaceholder(name, params);
            }
        }

        String s = "${title}";

        loadTemplate(s.getBytes(), "test.txt", 1, 0, 0);

        assertEquals("hello", template.renderToString(new Visitor2()));
    }

    @Test
    public void render_fallbackToFallbackVisitor() {
        class Visitor1 extends FallbackTextWriter<StringBuilder> {
            public Visitor1(StringBuilder out) {
                super(out);
                context().put("title", "hello");
            }
        }

        class Visitor2 extends TextWriter<StringBuilder> implements FallbackVisitor {
            public boolean visitPlaceholder(String name, Object[] params) throws Exception {
                FallbackToVisitor ftv = new FallbackToVisitor(new Visitor1(out()));
                return ftv.visitPlaceholder(name, params);
            }
        }

        String s = "${title}";

        loadTemplate(s.getBytes(), "test.txt", 1, 0, 0);

        assertEquals("hello", template.renderToString(new Visitor2()));
    }

    @Test
    public void render_fallbackToFallbackVisitor_failed() {
        class Visitor1 implements FallbackVisitor {
            public boolean visitPlaceholder(String name, Object[] params) throws Exception {
                return false;
            }
        }

        class Visitor2 extends TextWriter<StringBuilder> implements FallbackVisitor {
            public boolean visitPlaceholder(String name, Object[] params) throws Exception {
                FallbackToVisitor ftv = new FallbackToVisitor(new Visitor1());
                return ftv.visitPlaceholder(name, params);
            }
        }

        String s = "${title}";
        loadTemplate(s.getBytes(), "test.txt", 1, 0, 0);

        acceptFailure(new Visitor2());
        assertThat(runtimeError,
                   exception(NoSuchMethodException.class, "Error rendering ${title} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor2.visitTitle()\n" //
                     + "  2. Visitor2.visitTitle(String[])", runtimeError.getCause().getMessage());
    }

    @Test
    public void render_fallbackToVisitor_failed() {
        class Visitor2 extends TextWriter<StringBuilder> implements FallbackVisitor {
            public boolean visitPlaceholder(String name, Object[] params) throws Exception {
                FallbackToVisitor ftv = new FallbackToVisitor(new Object());
                return ftv.visitPlaceholder(name, params);
            }
        }

        String s = "${title}";
        loadTemplate(s.getBytes(), "test.txt", 1, 0, 0);

        acceptFailure(new Visitor2());
        assertThat(runtimeError,
                   exception(NoSuchMethodException.class, "Error rendering ${title} at test.txt: Line 1 Column 1"));

        assertEquals("One of the following method:\n" //
                     + "  1. Visitor2.visitTitle()\n" //
                     + "  2. Visitor2.visitTitle(String[])", runtimeError.getCause().getMessage());
    }

    private String formatGMT(String format) {
        DateFormat fmt = new SimpleDateFormat(format, Locale.US);
        fmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        return fmt.format(new Date(0));
    }

    private void render(TextWriter<StringBuilder> visitor, String extra) throws Exception {
        loadTemplate("test06_real_case_2.txt", 5, 1, 3);

        String expected = "";
        expected += "<html>\n";
        expected += "<head>\n";
        expected += "<title>myTitle</title>\n";
        expected += "</head>\n";
        expected += "<body>\n";
        expected += "<ul>\n";
        expected += "<li>count 1" + extra + "</li>";
        expected += "<li>count 2" + extra + "</li>";
        expected += "<li>count 3" + extra + "</li>";
        expected += "<li>count 4" + extra + "</li>";
        expected += "<li>count 5" + extra + "</li>\n";
        expected += "</ul>\n";
        expected += "</body>\n";
        expected += "</html>";

        assertEquals(expected, template.renderToString(visitor));
    }
}
