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
import thinkpanda.utils.ByteUtils;

/**
 * Created by herman on 14/8/15.
 */
public class ByteUtilsTest {

    @Test
    public void testConstruct() {
        ByteUtils b = new ByteUtils();
    }

    @Test
    public void testFillIntOffset0() {


        byte[] data = new byte[6];
        data[4] = 0x33;
        data[5] = 0x44;
        ByteUtils.fillInt(data, 0, 0x01020304);

        StrictAssertions.assertThat(data).isEqualTo(new byte[]{1, 2, 3, 4, 0x33, 0x44});

    }

    @Test
    public void testFillIntOffset2() {
        byte[] data = new byte[8];
        data[0] = 0x55;
        data[1] = 0x66;
        data[6] = 0x33;
        data[7] = 0x44;
        ByteUtils.fillInt(data, 2, 0x01020304);

        StrictAssertions.assertThat(data).isEqualTo(new byte[]{0x55, 0x66, 1, 2, 3, 4, 0x33, 0x44});

    }

    @Test
    public void testFillShortOffset0() {

        byte[] data = new byte[4];
        data[2] = 0x33;
        data[3] = 0x44;
        ByteUtils.fillShort(data, 0, 0x0102);

        StrictAssertions.assertThat(data).isEqualTo(new byte[]{1, 2, 0x33, 0x44});

    }

    @Test
    public void testFillShortOffset2() {
        byte[] data = new byte[6];
        data[0] = 0x55;
        data[1] = 0x66;
        data[4] = 0x33;
        data[5] = 0x44;
        ByteUtils.fillShort(data, 2, 0x0102);

        StrictAssertions.assertThat(data).isEqualTo(new byte[]{0x55, 0x66, 1, 2, 0x33, 0x44});

    }

}
