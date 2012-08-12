package com.extendedencryption.util;

/*
 * Project: Alfresco Encryption Extension Module , part of the Creative Summer
 * 
 * 
 * This code was developped by a group of 3 students from UET-VNU .
 * 
 * License   : GNU General Public License, version 2 (http://www.gnu.org/licenses/gpl-2.0.html)
 * 
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.ContentData;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;

/**
 * encryption data in alfresco 
 * 
 * @since 4.0
 */

public class BaseExecuter {

	/** node service object */
	public NodeService nodeService;
	/** content service object */
	public ContentService contentService;

	public static int bytesize = 16384;

	/**
	 * set the node's service
	 * 
	 * @param nodeService
	 */

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
		ContentReader contentReader = this.getContentService().getReader(
				nodeRefer, ContentModel.PROP_CONTENT);

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
		// Return a file from bytes
		String path = null;
		File fout = null;
		try {
			fout = BytestoFile.bytesToFile(data, path);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Getting Mimetype of node
		QName PROP_QNAME_CONTENT = QName.createQName(
				"http://www.alfresco.org/model/content/1.0", "content");
		ContentData contentData = (ContentData) this.getNodeService()
				.getProperty(nodeRefer, PROP_QNAME_CONTENT);
		String originalMimeType = contentData.getMimetype();

		ContentService contentService = this.getContentService();
		ContentWriter contentWriter = contentService.getWriter(nodeRefer,
				ContentModel.PROP_CONTENT, true);

		// write to the file
		contentWriter.setMimetype(originalMimeType);
		contentWriter.putContent(fout);
	}

	public void action(NodeRef nodeRefer) {
	}
}