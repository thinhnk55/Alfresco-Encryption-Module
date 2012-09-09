package test.com.alfresco.dataencryptingtest;

import static org.junit.Assert.*;
import org.junit.Test;
import com.extendedencryption.util.*;
import java.io.File;

/**
 * TODO Test encrypt files
 * Run the test will crack the file so need run decrypt test to recover
 *
 * @author khanhthinh.
 *
 */
public class TestEncryptThenDecrypt {
	/**
	 * TODO test Bytes to File
	 *
	 */
	@Test	
	public void test() {
		testEncryptThenDecrypt();
	}
	
	/**
	 * TODO Encrypt then decrypt file
	 *
	 */
	public void testEncryptThenDecrypt(){
		//input files
		String currentDir = System.getProperty("user.dir");
		System.out.println("Current folder "+ currentDir);
		File dirTestFiles = new File(currentDir+File.separator+"testfiles");
		assertNotNull(dirTestFiles);
		File[] dirContent = dirTestFiles.listFiles();
		assertNotNull(dirContent);
		//encrypt then decrypt files
		for (File file : dirContent){// File objects
			byte[] bytes = null;
			try{
				bytes = FiletoBytes.fileToBytes(file);
				String filePath = file.getAbsolutePath();
				String key = "PasswordOrKeyMustBeSecret";
				bytes = AES.encrypt(bytes, key.getBytes());
				bytes = AES.decrypt(bytes, key.getBytes());
				file = BytestoFile.bytesToFile(bytes, filePath);
			}catch(Exception e){
				fail("Exception");
			}
		}
	}
}

