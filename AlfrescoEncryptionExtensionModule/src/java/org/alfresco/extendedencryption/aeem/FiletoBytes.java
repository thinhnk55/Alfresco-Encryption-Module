package org.alfresco.extendedencryption.aeem;

/*
 * Project: Alfresco Encryption Extension Module , part of the Creative Summer
 * Dự án : Mở rộng module mã hóa cho alfresco , nằm trong mùa hè sáng tạo   
 * 
 * This code was developped by a group of 3 students from UET-VNU .
 * Dự án được phát triển bởi 1 nhóm sinh viên Đại học công nghệ - Đại học Quốc Gia Hà Nội 
 * 
 * License   : GNU General Public License, version 2 (http://www.gnu.org/licenses/gpl-2.0.html)
 * Giấy phép : GNU GPL 2.0 (nguồn : http://www.gnu.org/licenses/gpl-2.0.html )
 */		
	
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FiletoBytes {
		/**
		 * returns a byte array representation of a given file
		 * trả về 1 mảng nhị phân từ 1 file 
		 */

		public static byte [] fileToBytes(File file) throws NullPointerException,IOException{
			if(file == null) throw new NullPointerException("Not Found");
			
			// byte array 
			byte[] bytes=new byte[(int)file.length()];
						
			
			InputStream input=new FileInputStream(file);
			
			//read the bytes
			int iter = 0 , block = 0;
			while ( iter < bytes.length) {
				block = input.read(bytes, iter , bytes.length-iter);
				if ( block >= 0 ) {
					iter += block;
				} else {
					break;					
				}
			}
			
			input.close();
			
			return bytes;
		}
}
