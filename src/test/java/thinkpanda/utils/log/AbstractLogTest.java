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

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by herman on 6/11/2016.
 */
public class AbstractLogTest {

    private class TestAbstractLog extends AbstractLog {

        private List<String> journal = new ArrayList<>();

        public TestAbstractLog(@SuppressWarnings("SameParameterValue") String tag) {
            super(tag);
        }

        public List<String> getJournal() {
            return journal;
        }

        @Override
        public void d(String tag, String msg) {
            journal.add("d||"+tag+"||"+msg);
        }

        @Override
        public void w(String tag, String msg) {
            journal.add("w||"+tag+"||"+msg);
        }

        @Override
        public void i(String tag, String msg) {
            journal.add("i||"+tag+"||"+msg);
        }

        @Override
        public void e(String tag, String msg) {
            journal.add("e||"+tag+"||"+msg);
        }

        @Override
        public void e(String tag, String msg, Throwable t) {
            journal.add("e||"+tag+"||"+msg+"||"+t.getMessage());
        }
    }

    @Test
    public void testGetTag() {
        TestAbstractLog tal = new TestAbstractLog("test");
        assertThat(tal.getTag()).isEqualTo("test");
    }

    @Test
    public void testLogging() {

        TestAbstractLog tal = new TestAbstractLog("test");

        tal.d("hello 1");
        tal.w("hello 2");
        tal.i("hello 3");
        tal.e("hello 4");
        tal.e("hello 5", new RuntimeException("runTimeException"));

        assertThat(tal.getJournal()).containsExactly(
                "d||test||hello 1",
                "w||test||hello 2",
                "i||test||hello 3",
                "e||test||hello 4",
                "e||test||hello 5||runTimeException"
        );

    }

}
