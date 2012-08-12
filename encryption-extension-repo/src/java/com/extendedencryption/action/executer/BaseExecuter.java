package com.extendedencryption.action.executer;

/*
 * Project: Alfresco Encryption Extension Module , part of the Creative Summer
 * This code was developped by a group of 3 students from UET-VNU .
 * License   : GNU General Public License, version 2 (http://www.gnu.org/licenses/gpl-2.0.html)
 */

import java.io.ByteArrayOutputStream;
import java.io.*;
import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.ContentData;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.model.FileInfo;

/**
 * encryption data in alfresco 
 * 
 * @since 4.0
 */

public class BaseExecuter extends ActionExecuterAbstractBase {

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
		
		QName typeQName = serviceRegistry.getNodeService().getType(nodeRef);
		
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
		
		OutputStream outputStream = contentWriter.getContentOutputStream();
		
		// replate the whole file with content
		printWriter.write(data, (int) contentWriter.getSize(), data.length());
	}

	public void action(NodeRef nodeRefer) {
	}
}