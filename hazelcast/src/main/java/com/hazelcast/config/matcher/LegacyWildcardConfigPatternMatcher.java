/*
 * Copyright (c) 2008-2017, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.config.matcher;

import com.hazelcast.config.ConfigPatternMatcher;

/**
 * This {@code ConfigPatternMatcher} supports a simplified wildcard matching.
 * See "Config.md ## Using Wildcard" for details about the syntax.
 * <p>
 * This matcher represents a "contains" matching to provide backward compatibility.
 * No exception will be thrown if multiple configurations are found (first match is returned).
 * <p>
 * Please adapt your configuration and use {@link com.hazelcast.config.matcher.WildcardConfigPatternMatcher} instead.
 */
@Deprecated
public class LegacyWildcardConfigPatternMatcher implements ConfigPatternMatcher {

    @Override
    public String matches(Iterable<String> configPatterns, String itemName) {
        for (String pattern : configPatterns) {
            if (matches(pattern, itemName)) {
                return pattern;
            }
        }
        return null;
    }

    /**
     * This method is public to be accessible by {@link com.hazelcast.security.permission.InstancePermission}.
     *
     * @param pattern  configuration pattern to match with
     * @param itemName item name to match
     * @return {@code true} if itemName matches, {@code false} otherwise
     */
    public boolean matches(String pattern, String itemName) {
        int index = pattern.indexOf('*');
        if (index == -1) {
            return itemName.equals(pattern);
        }

        String firstPart = pattern.substring(0, index);
        int indexFirstPart = itemName.indexOf(firstPart, 0);
        if (indexFirstPart == -1) {
            return false;
        }

        String secondPart = pattern.substring(index + 1);
        int indexSecondPart = itemName.indexOf(secondPart, index + 1);
        return (indexSecondPart != -1);
    }
}
