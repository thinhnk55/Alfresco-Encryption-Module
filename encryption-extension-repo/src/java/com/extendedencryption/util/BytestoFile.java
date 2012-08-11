package com.extendedencryption.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * Project: Alfresco Encryption Extension Module , part of the Creative Summer
 *
 * 
 * This code was developped by a group of 3 students from UET-VNU .
 * 
 * 
 * License   : GNU General Public License, version 2 (http://www.gnu.org/licenses/gpl-2.0.html)
 * 
 */		

public class BytestoFile {
	/**
	 * returns a file from a byte array
	 * 
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
