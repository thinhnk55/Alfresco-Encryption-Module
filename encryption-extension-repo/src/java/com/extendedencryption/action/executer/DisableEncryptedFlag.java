package com.extendedencryption.action.executer;

/*
 * Project: Alfresco Encryption Extension Module , part of the Creative Summer
 * License   : GNU General Public License, version 2 (http://www.gnu.org/licenses/gpl-2.0.html)
 */

import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.repository.NodeRef;

public class DisableEncryptedFlag extends SetEncryptedFlag {
	@Override
	protected void executeImpl(Action action, NodeRef actionedUponNodeRef) {
		action.setParameterValue(SetEncryptedFlag.PARAM_ACTIVE, false);
		super.executeImpl(action, actionedUponNodeRef);
	}
}
