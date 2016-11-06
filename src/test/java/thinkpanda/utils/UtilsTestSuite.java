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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import thinkpanda.utils.log.AbstractLogTest;
import thinkpanda.utils.log.NullLogTest;
import thinkpanda.utils.log.StdoutLog;
import thinkpanda.utils.log.StdoutLogTest;

/**
 * Created by herman on 13/9/15.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ConstsTest.class,
        ArrayUtilsTest.class,
        ByteUtilsTest.class,
        FileUtilsTest.class,
        StringUtilsTest.class,
        AbstractLogTest.class,
        NullLogTest.class,
        StdoutLogTest.class
})
public class UtilsTestSuite {
}