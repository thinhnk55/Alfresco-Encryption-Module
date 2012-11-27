package com.extendedencryption.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

/*
 * Algorithm for this module is :
 * 
 * 
 * The Advanced Encryption Standard (AES) is a specification for the encryption of data
 * 
 */

public class Tools {
    public static String convertToMD5(String toEnc) {
        MessageDigest mdEnc = null; 
        try {
            mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
        mdEnc.update(toEnc.getBytes(), 0, toEnc.length());
        String md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string 
        return md5;
    }
}
