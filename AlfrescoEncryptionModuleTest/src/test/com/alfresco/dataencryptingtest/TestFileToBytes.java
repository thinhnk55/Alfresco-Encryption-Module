package test.com.alfresco.dataencryptingtest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.extendedencryption.util.*;

import java.io.File;

/**
 * TODO Test File to Bytes
 *
 * @author khanhthinh.
 *
 */
public class TestFileToBytes {
	/**
	 * TODO test File to Bytes
	 *
	 */
	@Test	
	public void test() {
		testFileToBytes();
	}
	
	/**
	 * TODO convert File to Bytes
	 *
	 */
	public void testFileToBytes(){
		//input files
		String currentDir = System.getProperty("user.dir");
		System.out.println("Current folder "+ currentDir);
		File dirTestFiles = new File(currentDir+File.separator+"testfiles");
		assertNotNull(dirTestFiles);
		File[] dirContent = dirTestFiles.listFiles();
		assertNotNull(dirContent);
		
		// if byte == null the test fails
		for (File file : dirContent){// File objects
			byte[] bytes = null;
			try{
				bytes = FiletoBytes.fileToBytes(file);
			}catch(Exception e){
				fail("Exception");
			}
			assertNotNull(bytes);
			}
		}
}

