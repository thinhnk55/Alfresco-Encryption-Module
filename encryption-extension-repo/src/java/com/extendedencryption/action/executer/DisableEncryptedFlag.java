package com.extendedencryption.action.executer;

import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.repository.NodeRef;

public class DisableEncryptedFlag extends SetEncryptedFlag {
	@Override
	protected void executeImpl(Action action, NodeRef actionedUponNodeRef) {
		action.setParameterValue(SetEncryptedFlag.PARAM_ACTIVE, false);
		super.executeImpl(action, actionedUponNodeRef);
	}
}
