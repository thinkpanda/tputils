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

/**
 * Created by pandac on 12/12/14.
 */
@SuppressWarnings("WeakerAccess")
public class ByteUtils {

    public static void fillInt(byte[] b, int offset, long value) {
        b[offset] = (byte)((value >> 24) &0xff);
        b[offset+1] = (byte)((value >> 16) &0xff);
        b[offset+2] = (byte)((value >> 8) &0xff);
        b[offset+3] = (byte)(value &0xff);
    }

    public static void fillShort(byte[] b, int offset, int value) {
        b[offset] = (byte)((value >> 8) &0xff);
        b[offset+1] = (byte)(value &0xff);
    }

}
