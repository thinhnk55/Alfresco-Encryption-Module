package com.extendedencryption.action.executer;

/*
 * Project: Alfresco Encryption Extension Module , part of the Creative Summer
 * This code was developped by a group of 3 students from UET-VNU .
 * License   : GNU General Public License, version 2 (http://www.gnu.org/licenses/gpl-2.0.html)
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.extendedencryption.util.AES;
import com.extendedencryption.util.FiletoBytes;

import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;

public class DecryptionActionExecuter extends BaseExecuter {
	
	public static final String NAME = "decryption-action";
	
    private NodeService nodeService;
    
	protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
    }	
	
	public void executeImpl(Action ruleAction, NodeRef actionedUponNodeRef) {
		byte[] key = null;
		try {
			String pass = "test";
		    key = pass.getBytes();
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		byte[] data = AES.decrypt(super.getNodeContent(actionedUponNodeRef), key);
		super.write(actionedUponNodeRef, data);
		
		ruleAction.setParameterValue(BaseExecuter.PARAM_ACTIVE, false);
		super.executeImpl(ruleAction, actionedUponNodeRef);		
    } // end if isEmpty
}