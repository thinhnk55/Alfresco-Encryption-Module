package com.extendedencryption.action.executer;

import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.repository.NodeRef;

public class EnableEncryptedFlag extends SetEncryptedFlag {
	@Override
	protected void executeImpl(Action action, NodeRef actionedUponNodeRef) {		
		action.setParameterValue(SetEncryptedFlag.PARAM_ACTIVE, true);
		super.executeImpl(action, actionedUponNodeRef);
		
		EncryptionExecuter encrypt = new EncryptionExecuter();
		encrypt.setNodeService(super.nodeService);
		encrypt.action(actionedUponNodeRef);
	}
}
