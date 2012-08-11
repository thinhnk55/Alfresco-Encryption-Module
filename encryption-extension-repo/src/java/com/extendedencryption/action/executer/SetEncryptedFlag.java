package com.extendedencryption.action.executer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.alfresco.repo.action.ParameterDefinitionImpl;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import com.extendedencryption.model.Model;

public class SetEncryptedFlag extends ActionExecuterAbstractBase {
	
	public final static String NAME = "set-encrypted-flag";
	public final static String PARAM_ACTIVE = "active";
	
	/** The NodeService to be used by the bean */
	protected NodeService nodeService;
		
	@Override
	protected void executeImpl(Action action, NodeRef actionedUponNodeRef) {
			
		Boolean activeFlag = (Boolean)action.getParameterValue(PARAM_ACTIVE);

		if (activeFlag == null) activeFlag = true;
			
					
		// set the sc:isActive property to true
		Map<QName, Serializable> properties = nodeService.getProperties(actionedUponNodeRef);
		properties.put(QName.createQName(Model.NAMESPACE_SOMECO_CONTENT_MODEL, Model.PROP_IS_ACTIVE), activeFlag);		  
					
		// if the aspect has already been added, set the properties
		if (nodeService.hasAspect(actionedUponNodeRef, QName.createQName(Model.NAMESPACE_SOMECO_CONTENT_MODEL,Model.ASPECT_SC_ENCRYPT))) {			
			nodeService.setProperties(actionedUponNodeRef, properties);
		} else {
			// otherwise, add the aspect and set the properties			
			nodeService.addAspect(actionedUponNodeRef, QName.createQName(Model.NAMESPACE_SOMECO_CONTENT_MODEL, Model.ASPECT_SC_ENCRYPT), properties);
		}                  				
	}

	@Override
	protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
		paramList.add(
		         new ParameterDefinitionImpl(               // Create a new parameter definition to add to the list
		            PARAM_ACTIVE,                           // The name used to identify the parameter
		            DataTypeDefinition.BOOLEAN,             // The parameter value type
		            false,                                  // Indicates whether the parameter is mandatory
		            getParamDisplayLabel(PARAM_ACTIVE)));   // The parameters display label
		
	}

	/**
	* @param nodeService The NodeService to set.
	*/
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}
