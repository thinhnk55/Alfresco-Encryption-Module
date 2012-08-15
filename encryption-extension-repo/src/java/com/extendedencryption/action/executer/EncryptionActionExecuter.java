package com.extendedencryption.action.executer;
/*
 * Project: Alfresco Encryption Extension Module , part of the Creative Summer
 * This code was developped by a group of 3 students from UET-VNU .
 * License   : GNU General Public License, version 2 (http://www.gnu.org/licenses/gpl-2.0.html)
 */

import java.util.List;
import com.extendedencryption.util.AES;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;

public class EncryptionActionExecuter extends BaseExecuter {
	
	public static final String NAME = "encryption-action";
	
    private NodeService nodeService;
	
	protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
    }
	
	@Override
	public void action(NodeRef nodeRefer) {
		byte[] data = AES.encrypt(super.getNodeContent(nodeRefer), null);
		super.write(nodeRefer, data);
	}
	
	public void executeImpl(Action ruleAction, NodeRef actionedUponNodeRef) {
		this.action(actionedUponNodeRef);
    } // end if isEmpty
}