package com.extendedencryption.action.executer;

import java.util.List;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;

public class DecryptionActionExecuter extends ActionExecuterAbstractBase {
	
	public static final String NAME = "decryption-action";
	
    private NodeService nodeService;
	
    public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}
    
	protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
    }
		
	public void executeImpl(Action ruleAction, NodeRef actionedUponNodeRef) {
		DecryptionExecuter decrypt = new DecryptionExecuter();
		decrypt.setNodeService(this.nodeService);
		decrypt.action(actionedUponNodeRef);
    } // end if isEmpty
}
