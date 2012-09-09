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
public class TestEncryption {
	/**
	 * TODO test encryption
	 *
	 */
	@Test	
	public void test() {
		testEncryption();
	}
	
	/**
	 * TODO test encryption
	 *
	 */
	public void testEncryption(){
		//input files
		String currentDir = System.getProperty("user.dir");
		System.out.println("Current folder "+ currentDir);
		File dirTestFiles = new File(currentDir+File.separator+"testfiles");
		assertNotNull(dirTestFiles);
		File[] dirContent = dirTestFiles.listFiles();
		assertNotNull(dirContent);
		//enrypt files
		for (File file : dirContent){// File objects
			byte[] bytes = null;
			try{
				bytes = FiletoBytes.fileToBytes(file);
				String filePath = file.getAbsolutePath();
				String key = "PasswordOrKeyMustBeSecret";
				bytes = AES.encrypt(bytes, key.getBytes());
				file = BytestoFile.bytesToFile(bytes, filePath);
			}catch(Exception e){
				fail("Exception");
			}
		}
	}
}

