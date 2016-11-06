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

/**
 * Created by herman on 21/2/2016.
 */
public abstract class AbstractLog implements L {

    protected String tag;
    private String prefixFilter = null;
    public AbstractLog(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public boolean filter(String tag) {
        return prefixFilter==null || prefixFilter.startsWith(tag);
    }

    public String getPrefixFilter() {
        return prefixFilter;
    }

    public void setPrefixFilter(String prefixFilter) {
        this.prefixFilter = prefixFilter;
    }

    @Override
    public void e(String msg, Throwable t) {
        e(tag, msg, t);
    }

    @Override
    public void d(String msg) {
        d(tag, msg);
    }

    @Override
    public void w(String msg) {
        w(tag, msg);
    }

    @Override
    public void i(String msg) {
        i(tag, msg);
    }

    @Override
    public void e(String msg) {
        e(tag, msg);
    }

}
