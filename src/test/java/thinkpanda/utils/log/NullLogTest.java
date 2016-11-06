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

package thinkpanda.utils.log;

import org.assertj.core.api.StrictAssertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by herman on 6/11/2016.
 */
public class NullLogTest {

    @Test
    public void testNullLog() {

        NullLog log = new NullLog("tag");
        assertThat(log.getTag()).isEqualTo("tag");

    }

    @Test
    public void testLog() {
        // suppose to do nothing
        NullLog log = new NullLog("tag");
        log.d("a");
        log.w("a");
        log.e("a");
        log.i("a");
        log.e("a", new RuntimeException());
    }

    @Test
    public void testLogFactory() {
        NullLog.Factory factory = new NullLog.Factory();
        L l = factory.newLog("tag");
        assertThat(l).isInstanceOf(NullLog.class);
        assertThat(l.getTag()).isEqualTo("tag");
    }

}
