package com.extendedencryption.action.evaluator;

import javax.faces.context.FacesContext;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.alfresco.web.action.ActionEvaluator;
import org.alfresco.web.bean.repository.Node;
import org.alfresco.web.bean.repository.Repository;
import com.extendedencryption.model.Model;

public class EncryptionDisableEvaluator implements ActionEvaluator {

	private static final long serialVersionUID = 0L;

	/**
	 * @see org.alfresco.web.action.ActionEvaluator#evaluate(org.alfresco.web.bean.repository.Node)
	 */
	public boolean evaluate(Node node) {

		// if the aspect hasn't been added, it cannot be disabled
		
		if (!node.hasAspect(QName.createQName(Model.NAMESPACE_SOMECO_CONTENT_MODEL, Model.ASPECT_SC_ENCRYPT))) {
			return false;
		}
		
		// otherwise check the active property
		FacesContext context = FacesContext.getCurrentInstance();
		
		// check the active property
		NodeRef ref = new NodeRef(Repository.getStoreRef(), node.getId());
		NodeService nodeSvc = Repository.getServiceRegistry(context).getNodeService();
        boolean active = (Boolean)nodeSvc.getProperty(ref, QName.createQName(Model.NAMESPACE_SOMECO_CONTENT_MODEL, Model.PROP_IS_ACTIVE));
		
        return active;
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
