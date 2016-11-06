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

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by herman on 6/11/2016.
 */
public class StdoutLogTest {

    private DateFormat dateFormat = new SimpleDateFormat("MM-dd kk:mm:ss.SSS");
    private ByteArrayOutputStream baosOut;
    private ByteArrayOutputStream baosErr;

    @Before
    public void setUp() {
        baosOut = new ByteArrayOutputStream();
        PrintStream psOut = new PrintStream(baosOut, true);
        System.setOut(psOut);
        baosErr = new ByteArrayOutputStream();
        PrintStream psErr = new PrintStream(baosErr, true);
        System.setErr(psErr);
    }

    @Test
    public void testStdoutLog() {

        StdoutLog log = new StdoutLog("tag");
        assertThat(log.getTag()).isEqualTo("tag");

    }

    private boolean startsWithDate(DateFormat df, String s, String delimiter) {
        int index = s.indexOf(delimiter);
        String subStr;
        if (index==-1)
            subStr = s;
        else
            subStr = s.substring(0, index);
        try {
            df.parse(subStr);
            return true;
        }
        catch (ParseException e) {
            return false;
        }
    }

    @Test
    public void testLogD() throws UnsupportedEncodingException {

        // suppose to do nothing
        StdoutLog log = new StdoutLog("tag");
        log.d("a");

        String[] outResult = new String(baosOut.toByteArray(), "utf-8").split("\n");
        assertThat(outResult).hasSize(1);

        assertThat(startsWithDate(dateFormat, outResult[0], " -/")).isTrue();
        assertThat(outResult[0]).endsWith(" -/tag d/a");

        String errResult = new String(baosErr.toByteArray(), "utf-8");
        assertThat(errResult).isEmpty();

//        log.w("a");
//        log.e("a");
//        log.i("a");
//        log.e("a", new RuntimeException());
//
//        String[] outResult = new String(baosOut.toByteArray(), "utf-8").split("\n");
//        String[] errResult = new String(baosErr.toByteArray(), "utf-8").split("\n");
//
//
//        assertThat(outResult).hasSize(3);
//
//        assertThat(startsWithDate(dateFormat, outResult[0], " -/")).isTrue();
//        assertThat(outResult[0]).endsWith(" -/tag d/a");
//
//        assertThat(startsWithDate(dateFormat, outResult[1], " -/")).isTrue();
//        assertThat(outResult[1]).endsWith(" -/tag w/a");
//
//        assertThat(startsWithDate(dateFormat, outResult[2], " -/")).isTrue();
//        assertThat(outResult[2]).endsWith(" -/tag i/a");
//
//
//        assertThat(errResult).hasSize(2);
//
//        assertThat(startsWithDate(dateFormat, errResult[0], " -/")).isTrue();
//        assertThat(errResult[0]).endsWith(" -/tag e/a");
//
//        assertThat(startsWithDate(dateFormat, errResult[1], " -/")).isTrue();
//        assertThat(errResult[1]).endsWith(" -/tag e/a");


    }

    @Test
    public void testLogW() throws UnsupportedEncodingException {

        // suppose to do nothing
        StdoutLog log = new StdoutLog("tag");
        log.w("a");

        String[] outResult = new String(baosOut.toByteArray(), "utf-8").split("\n");
        assertThat(outResult).hasSize(1);

        assertThat(startsWithDate(dateFormat, outResult[0], " -/")).isTrue();
        assertThat(outResult[0]).endsWith(" -/tag w/a");

        String errResult = new String(baosErr.toByteArray(), "utf-8");
        assertThat(errResult).isEmpty();
    }

    @Test
    public void testLogI() throws UnsupportedEncodingException {

        // suppose to do nothing
        StdoutLog log = new StdoutLog("tag");
        log.i("a");

        String[] outResult = new String(baosOut.toByteArray(), "utf-8").split("\n");
        assertThat(outResult).hasSize(1);

        assertThat(startsWithDate(dateFormat, outResult[0], " -/")).isTrue();
        assertThat(outResult[0]).endsWith(" -/tag i/a");

        String errResult = new String(baosErr.toByteArray(), "utf-8");
        assertThat(errResult).isEmpty();
    }

    @Test
    public void testLogE_WithoutException() throws UnsupportedEncodingException {

        // suppose to do nothing
        StdoutLog log = new StdoutLog("tag");
        log.e("a");

        String[] errResult = new String(baosErr.toByteArray(), "utf-8").split("\n");
        assertThat(errResult).hasSize(1);

        assertThat(startsWithDate(dateFormat, errResult[0], " -/")).isTrue();
        assertThat(errResult[0]).endsWith(" -/tag e/a");

    }

    @Test
    public void testLogE_WithException() throws UnsupportedEncodingException {

        // suppose to do nothing
        StdoutLog log = new StdoutLog("tag");
        log.e("a", new RuntimeException("run time exception"));

        String[] errResult = new String(baosErr.toByteArray(), "utf-8").split("\n");
        assertThat(errResult.length).isGreaterThanOrEqualTo(2);

        assertThat(startsWithDate(dateFormat, errResult[0], " -/")).isTrue();
        assertThat(errResult[0]).endsWith(" -/tag e/a");

        assertThat(startsWithDate(dateFormat, errResult[1], " -/")).isTrue();
        assertThat(errResult[1]).endsWith(" -/tag e/java.lang.RuntimeException: run time exception");

    }

    @Test
    public void testLogFactory() {
        StdoutLog.Factory factory = new StdoutLog.Factory();
        L l = factory.newLog("tag");
        assertThat(l).isInstanceOf(StdoutLog.class);
        assertThat(l.getTag()).isEqualTo("tag");
    }

}
