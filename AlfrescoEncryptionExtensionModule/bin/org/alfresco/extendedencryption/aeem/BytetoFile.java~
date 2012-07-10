package org.alfresco.extendedencryption.aeem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * Project: Alfresco Encryption Extension Module , part of the Creative Summer
 * Dự án : Mở rộng module mã hóa cho alfresco , dự án của mùa hè sáng tạo   
 * 
 * This code was developped by a group of 3 students from UET-VNU .
 * Được phát triển bởi 1 nhóm 3 sinh viên từ trường đại học công nghệ - đại học quốc gia Hà Nôi 
 * 
 * License   : GNU General Public License, version 2 (http://www.gnu.org/licenses/gpl-2.0.html)
 * Giấy phép : GNU GPL 2.0 (nguồn : http://www.gnu.org/licenses/gpl-2.0.html )
 */		

public class BytestoFile {
	/**
	 * returns a file from a byte array
	 * trả về 1 file từ 1 mảng byte 
	 */

	public static File bytesToFile (byte[] bytes,String path) throws NullPointerException, IOException{
		if(bytes == null) throw new NullPointerException();
		if(path == null) throw new NullPointerException();
		
		File file=new File(path);
		FileOutputStream fout;
		fout = new FileOutputStream(file);

		for (int i = 0; i < bytes.length; i++)
			fout.write(bytes[i]);

		fout.close();
		
		return file;
	}

}
