package com.extendedencryption.action.executer;

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