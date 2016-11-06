/*
 * Copyright 2016 Herman Cheung
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package thinkpanda.utils;

import org.assertj.core.api.StrictAssertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by herman on 14/8/15.
 */
public class StringUtilsTest {

    @Test
    public void testConstruct() {
        StringUtils s = new StringUtils();
    }

    @Test
    public void testEqualsCharSequence() {

        StrictAssertions.assertThat(StringUtils.equalCharSequence(null, null)).isTrue();
        StrictAssertions.assertThat(StringUtils.equalCharSequence("a", null)).isFalse();
        StrictAssertions.assertThat(StringUtils.equalCharSequence(null, "a")).isFalse();
        StrictAssertions.assertThat(StringUtils.equalCharSequence("a", "b")).isFalse();
        StrictAssertions.assertThat(StringUtils.equalCharSequence("a", "a")).isTrue();

    }


    @Test
    public void testIsEmpty() {

        StrictAssertions.assertThat(StringUtils.isEmpty("")).isTrue();
        StrictAssertions.assertThat(StringUtils.isEmpty(null)).isTrue();
        StrictAssertions.assertThat(StringUtils.isEmpty(" ")).isFalse();
        StrictAssertions.assertThat(StringUtils.isEmpty("Hello")).isFalse();

    }

    @Test
    public void testOptional() {

        assertThat(StringUtils.optional("Hello")).isEqualTo("Hello");
        assertThat(StringUtils.optional("")).isEqualTo("");
        assertThat(StringUtils.optional(null)).isEqualTo("");

    }

    @Test
    public void testLengthInRange() {
        StrictAssertions.assertThat(StringUtils.lengthInRange("Hello", 0, 6)).isTrue();
        StrictAssertions.assertThat(StringUtils.lengthInRange("Hello", 5, 6)).isTrue();
        StrictAssertions.assertThat(StringUtils.lengthInRange("Hello", 0, 5)).isTrue();
        StrictAssertions.assertThat(StringUtils.lengthInRange("Hello", 0, 4)).isFalse();
        StrictAssertions.assertThat(StringUtils.lengthInRange("Hello", 6, 6)).isFalse();
    }

    @Test
    public void testIdeographic() {
        StrictAssertions.assertThat(StringUtils.isIdeographic('一')).isTrue();
        StrictAssertions.assertThat(StringUtils.isIdeographic('「')).isFalse();
        StrictAssertions.assertThat(StringUtils.isIdeographic('1')).isFalse();
        StrictAssertions.assertThat(StringUtils.isIdeographic('A')).isFalse();
    }

    @Test
    public void testIsNumber() {
        assertThat(StringUtils.isInteger(null)).isFalse();
        assertThat(StringUtils.isInteger("1")).isTrue();
        assertThat(StringUtils.isInteger("123")).isTrue();

        // decimal point
        assertThat(StringUtils.isInteger("1.23")).isFalse();

        // alphabet
        assertThat(StringUtils.isInteger("AA")).isFalse();
        assertThat(StringUtils.isInteger("1A")).isFalse();
        assertThat(StringUtils.isInteger("A1")).isFalse();

        // negative
        assertThat(StringUtils.isInteger("-1")).isTrue();

        // space
        assertThat(StringUtils.isInteger(" 1 ")).isFalse();

        // range
        assertThat(StringUtils.isInteger("123456789901234567890")).isFalse();

    }

    @Test
    public void testAllEmpty() {

        assertThat(StringUtils.allEmpty()).isTrue();
        assertThat(StringUtils.allEmpty(null, null)).isTrue();
        assertThat(StringUtils.allEmpty("", "")).isTrue();
        assertThat(StringUtils.allEmpty(null, "ABC")).isFalse();
        assertThat(StringUtils.allEmpty("ABC", "")).isFalse();
        assertThat(StringUtils.allEmpty("ABC", "", "")).isFalse();
        assertThat(StringUtils.allEmpty("ABC", "ABC", "")).isFalse();

    }

    @Test
    public void testAllNotEmpty() {

        assertThat(StringUtils.allNotEmpty()).isTrue();
        assertThat(StringUtils.allNotEmpty(null, null)).isFalse();
        assertThat(StringUtils.allNotEmpty("", "")).isFalse();
        assertThat(StringUtils.allNotEmpty(null, "ABC")).isFalse();
        assertThat(StringUtils.allNotEmpty("ABC", "")).isFalse();
        assertThat(StringUtils.allNotEmpty("ABC", "", "")).isFalse();
        assertThat(StringUtils.allNotEmpty("ABC", "ABC", "")).isFalse();
        assertThat(StringUtils.allNotEmpty("ABC", "ABC", "ABC")).isTrue();

    }

    @Test
    public void testAnyEmpty() {

        assertThat(StringUtils.anyEmpty()).isFalse();
        assertThat(StringUtils.anyEmpty(null, null)).isTrue();
        assertThat(StringUtils.anyEmpty("", "")).isTrue();
        assertThat(StringUtils.anyEmpty(null, "ABC")).isTrue();
        assertThat(StringUtils.anyEmpty("ABC", "")).isTrue();
        assertThat(StringUtils.anyEmpty("ABC", "", "")).isTrue();
        assertThat(StringUtils.anyEmpty("ABC", "ABC", "")).isTrue();
        assertThat(StringUtils.anyEmpty("ABC", "ABC", "ABC")).isFalse();

    }

    @Test
    public void testAnyNotEmpty() {

        assertThat(StringUtils.anyNotEmpty()).isFalse();
        assertThat(StringUtils.anyNotEmpty(null, null)).isFalse();
        assertThat(StringUtils.anyNotEmpty("", "")).isFalse();
        assertThat(StringUtils.anyNotEmpty(null, "ABC")).isTrue();
        assertThat(StringUtils.anyNotEmpty("ABC", "")).isTrue();
        assertThat(StringUtils.anyNotEmpty("ABC", "", "")).isTrue();
        assertThat(StringUtils.anyNotEmpty("ABC", "ABC", "")).isTrue();
        assertThat(StringUtils.anyNotEmpty("ABC", "ABC", "ABC")).isTrue();

    }

}
