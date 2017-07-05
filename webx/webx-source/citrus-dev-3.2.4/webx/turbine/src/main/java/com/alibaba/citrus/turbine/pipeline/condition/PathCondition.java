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

package com.alibaba.citrus.turbine.pipeline.condition;

import static com.alibaba.citrus.springext.util.SpringExtUtil.*;
import static com.alibaba.citrus.util.ObjectUtil.*;
import static com.alibaba.citrus.util.StringUtil.*;
import static com.alibaba.citrus.util.regex.MatchResultSubstitution.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.citrus.service.pipeline.PipelineStates;
import com.alibaba.citrus.service.pipeline.support.AbstractConditionDefinitionParser;
import com.alibaba.citrus.util.FileUtil;
import com.alibaba.citrus.util.ServletUtil;
import com.alibaba.citrus.util.StringUtil;
import com.alibaba.citrus.util.regex.MatchResultSubstitution;
import com.alibaba.citrus.util.regex.Substitution;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * 根据servletPath + componentPath + pathInfo来判断。
 *
 * @author Michael Zhou
 */
public class PathCondition extends AbstractTurbineCondition {
    public final static String DEFAULT_VAR = "subst";
    private Pattern[] patterns;
    private boolean[] negativePatterns;
    private String[]  patternStrings;
    private String    var;

    public void setName(String patterns) {
        if (patterns != null) {
            this.patternStrings = StringUtil.split(patterns, ", ");
            this.patterns = new Pattern[patternStrings.length];
            this.negativePatterns = new boolean[patternStrings.length];

            for (int i = 0; i < patternStrings.length; i++) {
                compilePattern(patternStrings[i], i);
            }
        }
    }

    private void compilePattern(String patternString, int index) {
        if (patternString.startsWith("!")) {
            this.negativePatterns[index] = true;
            patternString = patternString.substring(1);
        }

        this.patterns[index] = Pattern.compile(patternString);
    }

    public void setVar(String var) {
        this.var = trimToNull(var);
    }

    public final boolean isSatisfied(PipelineStates states) {
        String path = FileUtil.normalizeAbsolutePath(getPath());

        for (int i = 0; i < patterns.length; i++) {
            Matcher matcher = patterns[i].matcher(path);
            boolean matched = matcher.find();
            boolean negative = negativePatterns[i];

            if (negative != matched) {
                log(patternStrings[i]);

                String var = defaultIfNull(this.var, DEFAULT_VAR);
                Substitution subst = new MatchResultSubstitution(negative ? EMPTY_MATCH_RESULT : matcher);

                states.setAttribute(var, subst);

                return true;
            }
        }

        return false;
    }

    protected String getPath() {
        return ServletUtil.getResourcePath(getRunData().getRequest());
    }

    protected void log(String patternString) {
        log.debug("URL path(servletPath/pathInfo) matched pattern: {}", patternString);
    }

    public static class DefinitionParser extends AbstractPathConditionDefinitionParser<PathCondition> {
    }

    protected static abstract class AbstractPathConditionDefinitionParser<T extends PathCondition>
            extends AbstractConditionDefinitionParser<T> {
        @Override
        protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
            attributesToProperties(element, builder, "name", "var");
        }
    }
}
