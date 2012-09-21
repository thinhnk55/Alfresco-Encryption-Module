package test.com.alfresco.dataencryptingtest;

/*
 * Project: Alfresco Encryption Extension Module , part of the Creative Summer
 * License   : GNU General Public License, version 2 (http://www.gnu.org/licenses/gpl-2.0.html)
 */

import static org.junit.Assert.*;
import org.junit.Test;
import com.extendedencryption.util.*;
import java.io.File;

/**
 * TODO Test Bytes to File
 *
 * @author khanhthinh.
 *
 */
public class TestBytesToFile {
	/**
	 * TODO test Bytes to File
	 *
	 */
	@Test	
	public void test() {
		testFileToBytes();
	}
	
	/**
	 * TODO test Bytes to File
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
				String filePath = file.getAbsolutePath();
				file = null;
				file = BytestoFile.bytesToFile(bytes, filePath);
			}catch(Exception e){
				fail("Exception");
			}
			assertNotNull(file);
			}
		}
}

