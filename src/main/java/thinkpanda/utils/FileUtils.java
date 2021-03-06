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


import java.io.*;

@SuppressWarnings("WeakerAccess")
public class FileUtils {
	
	private static final String TAG = Consts.logTag("FU");

	public static final String DEFAULT_ENCODING="utf-8";

	public static String readFileToString(String path) throws IOException {
		return readFileToString(path, DEFAULT_ENCODING);
	}

	public static String readFileToString(String path, String encoding) throws IOException {

		if (path==null) throw new IllegalArgumentException("path is null");

		return readFileToString(new File(path), encoding);

	}

	public static String readFileToString(File file, String encoding) throws IOException {

		if (file==null) throw new IllegalArgumentException("file is null");

		return readStreamToString(new FileInputStream(file), encoding);

	}

	public static String readStreamToString(InputStream is) throws IOException {

		return readStreamToString(is, DEFAULT_ENCODING);
	}

	public static String readStreamToString(InputStream is, String encoding) throws IOException {

		if (is==null) throw new IllegalArgumentException("inputStream is null");
		if (encoding==null) throw new IllegalArgumentException("encoding is null");

		Reader r = new InputStreamReader(is, encoding);
		
		StringBuilder builder = new StringBuilder();
		
		char[] buffer = new char[4096];
		int count;
		while ((count=r.read(buffer))!=-1) {
			builder.append(buffer, 0, count);
		}
		
		r.close();
		
		return builder.toString();
		
	}

	public static byte[] readFileToByteArray(String path) throws IOException {

		if (path==null) throw new IllegalArgumentException("path is null");

		return readFileToByteArray(new File(path));
	}

	public static byte[] readFileToByteArray(File file) throws IOException {

		if (file==null) throw new IllegalArgumentException("file is null");

		return readStreamToByteArray(new FileInputStream(file));
	}

	public static byte[] readStreamToByteArray(InputStream is) throws IOException {

		if (is==null) throw new IllegalArgumentException("inputStream is null");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buffer = new byte[4096];
		int count;
		while ((count=is.read(buffer))!=-1) {
			baos.write(buffer, 0, count);
		}

		return baos.toByteArray();

	}

	public static void writeStringToFile(String path, @SuppressWarnings("SameParameterValue") String encoding, String value) throws IOException {

		if (path==null) throw new IllegalArgumentException("path is null");

		writeStringToStream(new FileOutputStream(path), encoding, value);
		
	}

	public static void writeStreamToStream(OutputStream os, InputStream is) throws IOException {
		writeStreamToStream(os, is, true);
	}

	public static void writeStreamToStream(OutputStream os, InputStream is, @SuppressWarnings("SameParameterValue") boolean closeInputStream) throws IOException {

		if (os==null) throw new IllegalArgumentException("output stream is null");
		if (is==null) throw new IllegalArgumentException("input stream is null");

		int bufferSize = 16384;
		byte[] buffer = new byte[bufferSize];
		int count;
		while ((count=is.read(buffer, 0, bufferSize))!=-1) {
			os.write(buffer, 0, count);
		}
		
		if (closeInputStream)
			is.close();
		os.close();
		
	}
	
	public static void writeStringToStream(OutputStream os, String encoding, String value) throws IOException {

		if (os==null) throw new IllegalArgumentException("output stream is null");
		if (encoding==null) throw new IllegalArgumentException("encoding is null");

		Writer w = new OutputStreamWriter(os, encoding);
		if (value!=null)
			w.write(value);
		w.close();
		
	}
	
	public static void writeBytesToFile(String path, byte[] data) throws IOException {

		if (path==null) throw new IllegalArgumentException("path is null");

		FileOutputStream os = new FileOutputStream(path);
		if (data!=null)
			os.write(data);
		os.close();
		
	}
	
	public static String getDir(String path) {

		if (path==null) throw new IllegalArgumentException("path is null");

		String[] parts = path.split("/");
		if (parts.length<=1)
			return "";
		else
			return parts[parts.length-2];

	}
	
	public static boolean ensureDirectory(String path) {

		if (path==null) throw new IllegalArgumentException("path is null");

		File f = new File(path);
        return ensureDirectory(f);

	}

    public static boolean  ensureDirectory(File path) {

		if (path==null) throw new IllegalArgumentException("path is null");

        if (path.exists())
            return false;
        else
            return path.mkdirs();
    }

	private static boolean deleteFolder(File folder, int level, boolean deleteSelf) {

		if (folder==null) throw new IllegalArgumentException("folder is null");

        boolean deleted = false;

	    File[] files = folder.listFiles();
	    if(files!=null) { //some JVMs return null for empty dirs
	        for(File f: files) {
	            if(f.isDirectory()) {
	                if (deleteFolder(f, level+1, true))
                        deleted = true;
	            } else {
	                f.delete();
                    deleted = true;
	            }
	        }
	    }
	    if (deleteSelf) {
            if (folder.delete()) deleted = true;
        }

        return deleted;

	}
	
	public static boolean deleteFolder(File folder) {
		return deleteFolder(folder, 0, true);
	}	
	
	public static boolean  deleteFolder(File folder, @SuppressWarnings("SameParameterValue") boolean deleteSelf) {
		return deleteFolder(folder, 0, deleteSelf);
	}

	public static void copyFile(File dest, File src) throws IOException {

		if (src==null) throw new IllegalArgumentException("src file is null");
		if (dest==null) throw new IllegalArgumentException("dest file is null");

		writeStreamToStream(new FileOutputStream(dest), new FileInputStream(src), true);
	}

	public static void copyFile(String dest, String src) throws IOException {

		if (src==null) throw new IllegalArgumentException("src path is null");
		if (dest==null) throw new IllegalArgumentException("dest path is null");

		copyFile(new File(dest), new File(src));
	}


}
