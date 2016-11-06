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

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by herman on 4/11/2016.
 */
public class ArrayUtilsTest {
    @Test
    public void box() throws Exception {
        assertThat(ArrayUtils.box(null)).isNull();
        assertThat(ArrayUtils.box(new int[] {})).isEqualTo(new Integer[] {});
        assertThat(ArrayUtils.box(new int[] {1})).isEqualTo(new Integer[] {1});
        assertThat(ArrayUtils.box(new int[] {1,2,3})).isEqualTo(new Integer[] {1,2,3});
    }

    @Test
    public void isEmpty() throws Exception {

        assertThat(ArrayUtils.isEmpty(null)).isTrue();
        assertThat(ArrayUtils.isEmpty(new Integer[] {})).isTrue();
        assertThat(ArrayUtils.isEmpty(new Integer[] {1})).isFalse();

    }

}