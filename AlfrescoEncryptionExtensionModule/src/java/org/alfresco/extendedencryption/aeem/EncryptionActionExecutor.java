package org.alfresco.extendedencryption.aeem;

import java.util.List;

import org.alfresco.repo.action.ParameterDefinitionImpl;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.model.FileNotFoundException;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.service.namespace.QNamePattern;


public class EncryptionActionExecutor extends ActionExecuterAbstractBase {
	
	public static final String NAME = "action";
	
    private NodeService nodeService;
	
    public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}
    
	protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
        paramList.add(
        	new ParameterDefinitionImpl(
        								DataTypeDefinition.NODE_REF,
        								true,
        								);
    }
	
	
	public void executeImpl(Action ruleAction, NodeRef actionedUponNodeRef) {
		EncryptionExecutor encryp = new EncryptionExecutor();
		encryp.setNodeService(this.nodeService);
		encryp.action(actionedUponNodeRef);
    } // end if isEmpty
}
