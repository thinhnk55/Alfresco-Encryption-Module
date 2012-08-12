package com.extendedencryption.action.executer;

import org.alfresco.service.cmr.repository.NodeRef;

import com.extendedencryption.util.AES;
import com.extendedencryption.util.BaseExecuter;

/*
 * Project: Alfresco Encryption Extension Module , part of the Creative Summer
 * 
 * This code was developped by a group of 3 students from UET-VNU .
 * 
 * 
 * License   : GNU General Public License, version 2 (http://www.gnu.org/licenses/gpl-2.0.html)
 * 
 */

/**
 * decryption data in alfresco G
 * 
 * @since 4.0 
 */

public class DecryptionExecuter extends BaseExecuter {

	/**
	 * Extend class BaseExecuter and Override void action to encrypt data 
	 * 
	 *
	 */

	@Override
	public void action(NodeRef nodeRefer) {
		byte[] data = AES.decrypt(super.getNodeContent(nodeRefer), null);
		super.write(nodeRefer, data);
	}
}
