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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by herman on 4/11/2016.
 */
public class FileUtilsTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testReadFileToString() throws IOException {
        assertThat(FileUtils.readFileToString("testdata/hello.txt")).isEqualTo("HELLO");
        assertThat(FileUtils.readFileToString("testdata/hello_utf8.txt", "utf-8")).isEqualTo("你好");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testReadFileToString_NullPath() throws IOException {
        FileUtils.readFileToString(null, "utf-8");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testReadFileToString_NullEncoding() throws IOException {
        FileUtils.readFileToString("testdata/hello.txt", null);
    }

    @Test(expected=UnsupportedEncodingException.class)
    public void testReadFileToString_InvalidEncoding() throws IOException {
        FileUtils.readFileToString("testdata/hello.txt", "ABCDE");
    }

    @Test
    public void testReadStreamToString() throws IOException {
        String hello = "HELLO";
        ByteArrayInputStream bais = new ByteArrayInputStream(hello.getBytes());
        assertThat(FileUtils.readStreamToString(bais)).isEqualTo("HELLO");
    }

    @Test
    public void testReadFileToByteArray() throws IOException {
        assertThat(FileUtils.readFileToByteArray("testdata/hello.txt")).isEqualTo("HELLO".getBytes());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testReadFileToByteArray_NullPath() throws IOException {
        FileUtils.readFileToByteArray(null);
    }

    @Test
    public void testReadStreamToByteArray() throws IOException {

        String hello = "HELLO";
        ByteArrayInputStream bais = new ByteArrayInputStream(hello.getBytes());
        assertThat(FileUtils.readStreamToByteArray(bais)).isEqualTo("HELLO".getBytes());

    }

    @Test(expected=IllegalArgumentException.class)
    public void testReadStreamToByteArray_NullInputStream() throws IOException {
        FileUtils.readStreamToByteArray(null);
    }

    @Test
    public void testWriteStringToFile() throws IOException {

        File temp = folder.newFile("test.txt");

        FileUtils.writeStringToFile(temp.toString(), "utf-8", "hello");

        assertThat(FileUtils.readFileToString(temp.toString())).isEqualTo("hello");

    }

    @Test
    public void testWriteBytesToFile() throws IOException {

        File temp = folder.newFile("test.txt");

        FileUtils.writeBytesToFile(temp.toString(), "hello".getBytes());

        assertThat(FileUtils.readFileToByteArray(temp.toString())).isEqualTo("hello".getBytes());

    }

    @Test
    public void testWriteStreamToStream() throws IOException {

        ByteArrayInputStream bais = new ByteArrayInputStream("HELLO".getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        FileUtils.writeStreamToStream(baos,bais);
        assertThat(baos.toByteArray()).isEqualTo("HELLO".getBytes());

    }

    @Test(expected=FileNotFoundException.class)
    public void testReadFileToString_FileNotFound() throws IOException {
        FileUtils.readFileToString("testdata/world.txt");
    }

    @Test
    public void testEnsureDirectory_Path() throws IOException {

        File pathFile = new File(folder.getRoot(), "ensure");

        assertThat(pathFile.exists()).isFalse();

        FileUtils.ensureDirectory(pathFile.toString());

        assertThat(pathFile.exists()).isTrue();

    }

    @Test
    public void testEnsureDirectory() throws IOException {

        File pathFile = new File(folder.getRoot(), "ensure");

        assertThat(pathFile.exists()).isFalse();
        assertThat(FileUtils.ensureDirectory(pathFile)).isTrue();
        assertThat(pathFile.exists()).isTrue();
        assertThat(FileUtils.ensureDirectory(pathFile)).isFalse();
        assertThat(pathFile.exists()).isTrue();

    }

    @Test(expected=IllegalArgumentException.class)
    public void testEnsureDirectory_NullPath() throws IOException {
        FileUtils.ensureDirectory((String)null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testEnsureDirectory_NullFile() throws IOException {
        FileUtils.ensureDirectory((File)null);
    }

    @Test
    public void testGetDir() throws IOException {

        // no parent directory
        assertThat(FileUtils.getDir("")).isEqualTo("");
        assertThat(FileUtils.getDir("hello")).isEqualTo("");
        assertThat(FileUtils.getDir("/hello")).isEqualTo("");

        // single parent directory
        assertThat(FileUtils.getDir("hello/world")).isEqualTo("hello");

        // deep directory
        assertThat(FileUtils.getDir("apple/orange/hello/world")).isEqualTo("hello");

    }

    @Test(expected=IllegalArgumentException.class)
    public void testGetDir_NullPath() throws IOException {

        String path = FileUtils.getDir(null);

    }

    @Test
    public void testDeleteFolder() throws IOException {

        File pathFile = new File(folder.getRoot(), "ensure");
        File dataFile = new File(pathFile, "data");
        FileUtils.ensureDirectory(pathFile);
        FileUtils.writeStringToFile(dataFile.toString(), "utf-8", "Hello");
        assertThat(pathFile.exists()).isTrue();

        assertThat(FileUtils.deleteFolder(pathFile)).isTrue();
        assertThat(pathFile.exists()).isFalse();

    }

    @Test
    public void testDeleteFolder_deep() throws IOException {

        File base = new File(folder.getRoot(), "ensure");
        File pathFile = new File(base, "a/for/apple");
        File dataFile = new File(pathFile, "data");
        FileUtils.ensureDirectory(pathFile);
        FileUtils.writeStringToFile(dataFile.toString(), "utf-8", "Hello");
        assertThat(base.exists()).isTrue();

        assertThat(FileUtils.deleteFolder(base)).isTrue();
        assertThat(pathFile.exists()).isFalse();

    }

    @Test
    public void testDeleteFolder_deleteNotExists() throws IOException {

        File path = new File(folder.getRoot(), "dir");

        assertThat(FileUtils.deleteFolder(path)).isFalse();

    }

    @Test
    public void testDeleteFolder_notDeleteSelf() throws IOException {

        File base = new File(folder.getRoot(), "ensure");
        File base2 = new File(base, "a");
        File pathFile = new File(base2, "for/apple");
        File dataFile = new File(pathFile, "data");
        FileUtils.ensureDirectory(pathFile);
        FileUtils.writeStringToFile(dataFile.toString(), "utf-8", "Hello");
        assertThat(base.exists()).isTrue();

        assertThat(FileUtils.deleteFolder(base, false)).isTrue();
        assertThat(base.exists()).isTrue();
        assertThat(base2.exists()).isFalse();


    }

    @Test(expected=IllegalArgumentException.class)
    public void testDeleteFolder_NullPath() throws IOException {

        FileUtils.deleteFolder(null);

    }

}