package test.com.alfresco.dataencryptingtest;

import static org.junit.Assert.*;
import org.junit.Test;
import com.extendedencryption.util.*;
import java.io.File;

/**
 * TODO Test Decrypt file
 * Run it after run TestEncryption
 * @author khanhthinh.
 *
 */
public class TestDecryption {
	/**
	 * TODO test Bytes to File
	 *
	 */
	@Test	
	public void test() {
		testDecryption();
	}
	
	/**
	 * TODO test decryption
	 *
	 */
	public void testDecryption(){
		//input files
		String currentDir = System.getProperty("user.dir");
		System.out.println("Current folder "+ currentDir);
		File dirTestFiles = new File(currentDir+File.separator+"testfiles");
		assertNotNull(dirTestFiles);
		File[] dirContent = dirTestFiles.listFiles();
		assertNotNull(dirContent);
		//decrypt files
		for (File file : dirContent){// File objects
			byte[] bytes = null;
			try{
				bytes = FiletoBytes.fileToBytes(file);
				String filePath = file.getAbsolutePath();
				String key = "PasswordOrKeyMustBeSecret";
				bytes = AES.decrypt(bytes, key.getBytes());
				file = BytestoFile.bytesToFile(bytes, filePath);
			}catch(Exception e){
				fail("Exception");
			}
		}
	}
}

