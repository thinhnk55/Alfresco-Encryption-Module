package org.alfresco.extendedencryption.aeem;

import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.repository.NodeRef;

public class EnableWebFlag extends SetWebFlag {
	@Override
	protected void executeImpl(Action action, NodeRef actionedUponNodeRef) {
		action.setParameterValue(SetWebFlag.PARAM_ACTIVE, true);
		super.executeImpl(action, actionedUponNodeRef);
	}
}
