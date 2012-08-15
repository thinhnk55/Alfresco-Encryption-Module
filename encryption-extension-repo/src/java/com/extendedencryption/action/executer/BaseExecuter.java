package com.extendedencryption.action.executer;

/*
 * Project: Alfresco Encryption Extension Module , part of the Creative Summer
 * This code was developped by a group of 3 students from UET-VNU .
 * License   : GNU General Public License, version 2 (http://www.gnu.org/licenses/gpl-2.0.html)
 */

import java.io.*;
import java.util.List;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.ContentData;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.alfresco.repo.action.ParameterDefinitionImpl;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;


import com.extendedencryption.model.Model;

/**
 * encryption data in alfresco 
 * 
 * @since 4.0
 */

public class BaseExecuter extends ActionExecuterAbstractBase {

	public final static String NAME = "new-base-executer";	
	public final static String PARAM_ACTIVE = "active";
	
	
	/** node service object */
	public NodeService nodeService;
	/** content service object */
	public ContentService contentService;
	public ContentReader actionedUponContentReader;

	public static int bytesize = 16384;
	protected ServiceRegistry serviceRegistry;
	
	/**
	 * set the node's service
	 * 
	 * @param nodeService
	 */
	public BaseExecuter() {
	}
	
	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }
	
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public NodeService getNodeService() {
		return this.nodeService;
	}

	/**
	 * set the content's service
	 * 
	 * @param nodeService
	 */

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public ContentService getContentService() {
		return this.getContentService();
	}

	/**
	 * Convert a inputstram to byte array
	 */

	public byte[] converToByteArray(InputStream is) throws IOException {

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[bytesize];

		while ((nRead = is.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}

		buffer.flush();

		return buffer.toByteArray();
	}

	/**
	 * Reading a note content and return byte array
	 * 
	 * @param nodeRefer
	 * @return byte array
	 */
	 
	public byte[] getNodeContent(NodeRef nodeRefer) {
		byte[] data = new byte[bytesize];

		// Reading the node content
		ContentReader contentReader = serviceRegistry.getContentService().getReader(
				nodeRefer, ContentModel.PROP_CONTENT);
				
		actionedUponContentReader = contentReader;
		InputStream is = contentReader.getContentInputStream();
	
		// Conver input stream to bytes
		try {
			data = this.converToByteArray(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}

	public void write(NodeRef nodeRefer, byte[] data) {
		// Getting Mimetype of node
		QName PROP_QNAME_CONTENT = QName.createQName(
				"http://www.alfresco.org/model/content/1.0", "content");
		ContentData contentData = (ContentData) serviceRegistry.getNodeService()
				.getProperty(nodeRefer, PROP_QNAME_CONTENT);
		String originalMimeType = contentData.getMimetype();

		ContentService contentService = serviceRegistry.getContentService();
		ContentWriter contentWriter = contentService.getWriter(nodeRefer,
				ContentModel.PROP_CONTENT, true);
		
		contentWriter.setMimetype(originalMimeType);
		contentWriter.setEncoding(actionedUponContentReader.getEncoding());
		
		contentWriter.putContent(new ByteArrayInputStream(data));
		
/*		OutputStream outputStream = contentWriter.getContentOutputStream();
		
		// replate the whole file with content
		
		try {
			outputStream.write(data, (int) contentWriter.getSize(), data.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}

	@Override
	protected void executeImpl(Action action, NodeRef actionedUponNodeRef) {	
		
		// Set active encrypted flag 
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
	
	protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
		paramList.add(
		         new ParameterDefinitionImpl(               // Create a new parameter definition to add to the list
		            PARAM_ACTIVE,                           // The name used to identify the parameter
		            DataTypeDefinition.BOOLEAN,             // The parameter value type
		            false,                                  // Indicates whether the parameter is mandatory
		            getParamDisplayLabel(PARAM_ACTIVE)));   // The parameters display label
	}	
}