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

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by herman on 21/2/2016.
 */
public class StdoutLog extends AbstractLog {

    public static class Factory implements LogFactory {
        @Override
        public L newLog(String tag) {
            return new StdoutLog(tag);
        }
    }

    private DateFormat dateFormat;
    private String pidStr;
    private boolean useTimestamp = true;

    public StdoutLog(String tag) {
        this(tag, true);
    }

    public StdoutLog(String tag, @SuppressWarnings("SameParameterValue") boolean timestamp) {
        super(tag);
        dateFormat = new SimpleDateFormat("MM-dd kk:mm:ss.SSS");
        int pid = getPid();
        if (pid==0)
            pidStr = "-";
        else
            pidStr = Integer.toString(pid);
    }

    private int getPid() {

        try {
            Class processCls = Class.forName("android.os.Process");
            Method method = processCls.getMethod("myPid");
            return (Integer)method.invoke(new Object[] {});
        } catch (ClassNotFoundException e) {
            return 0;
        } catch (NoSuchMethodException e) {
            return 0;
        } catch (InvocationTargetException e) {
            return 0;
        } catch (IllegalAccessException e) {
            return 0;
        }


    }

    private void printPadding(int size) {
        for(int i=0;i<size;i++) System.out.print(' ');
    }

    private void _log(PrintStream ps, char verbosity, String tag, String msg) {
        if (!filter(tag)) return;
        String prefix =
                (useTimestamp? dateFormat.format(System.currentTimeMillis()) + ' ' : "")
                + pidStr + '/'
                + tag + ' '
                + verbosity + '/';
        int currPos = 0;
        boolean first = true;
        while (currPos<msg.length()) {
            if (first)
                ps.print(prefix);
            else
                printPadding(prefix.length());
            int lineBreak = msg.indexOf('\n', currPos);
            if (lineBreak==-1) {
                ps.println(msg.substring(currPos));
                break;
            }
            else {
                ps.println(msg.substring(currPos, lineBreak));
                currPos = lineBreak+1;
                first = false;
            }
        }
    }

    @Override
    public void d(String tag, String msg) {
        _log(System.out,'d', tag, msg);
    }

    @Override
    public void w(String tag, String msg) {
        _log(System.out,'w', tag, msg);
    }

    @Override
    public void i(String tag, String msg) {
        _log(System.out,'i', tag, msg);
    }

    @Override
    public void e(String tag, String msg) {
        _log(System.err,'e', tag, msg);
    }

    @Override
    public void e(String tag, String msg, Throwable t) {
        StringWriter strWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(strWriter);
        _log(System.err,'e', tag, msg);
        t.printStackTrace(printWriter);
        printWriter.flush();
        _log(System.err,'e', tag, strWriter.toString());
    }
}
