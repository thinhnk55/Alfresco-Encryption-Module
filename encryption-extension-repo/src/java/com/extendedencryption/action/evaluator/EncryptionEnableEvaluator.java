package com.extendedencryption.action.evaluator;

/*
 * Project: Alfresco Encryption Extension Module , part of the Creative Summer
 * License   : GNU General Public License, version 2 (http://www.gnu.org/licenses/gpl-2.0.html)
 */
 

import javax.faces.context.FacesContext;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.alfresco.web.action.ActionEvaluator;
import org.alfresco.web.bean.repository.Node;
import org.alfresco.web.bean.repository.Repository;

import com.extendedencryption.model.Model;

public class EncryptionEnableEvaluator implements ActionEvaluator {

	private static final long serialVersionUID = 1L;

	/**
	 * @see org.alfresco.web.action.ActionEvaluator#evaluate(org.alfresco.web.bean.repository.Node)
	 */
	public boolean evaluate(Node node) {		
		FacesContext context = FacesContext.getCurrentInstance();

		// check the aspect, then check the active property
		NodeRef ref = new NodeRef(Repository.getStoreRef(), node.getId());
		
		// if the aspect hasn't yet been added, it can be enabled
		if (!node.hasAspect(QName.createQName(Model.NAMESPACE_SOMECO_CONTENT_MODEL, Model.ASPECT_SC_ENCRYPT))) {
			return true;
		}
		
		// check the active property
		NodeService nodeSvc = Repository.getServiceRegistry(context).getNodeService();
        boolean active = (Boolean)nodeSvc.getProperty(ref, QName.createQName(Model.NAMESPACE_SOMECO_CONTENT_MODEL, Model.PROP_IS_ACTIVE));
        
        return !active;
   	}

	public boolean evaluate(Object obj) {
		if (obj instanceof Node) {
			return evaluate((Node)obj);
		} else {
			// if you don't give me a Node, I don't know how to evaluate
			return false;
		}
	}

}
