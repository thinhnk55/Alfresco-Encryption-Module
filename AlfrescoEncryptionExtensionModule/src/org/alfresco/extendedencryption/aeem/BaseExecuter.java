package org.alfresco.extendedencryption.aeem;

/*
 * Project: Alfresco Encryption Extension Module , part of the Creative Summer
 * Dự án : Mở rộng module mã hóa cho alfresco , nằm trong mùa hè sáng tạo   
 * 
 * This code was developped by a group of 3 students from UET-VNU .
 * Dự án được phát triển bởi 1 nhóm sinh viên Đại học công nghệ - Đại học Quốc Gia Hà Nội 
 * 
 * License   : GNU General Public License, version 2 (http://www.gnu.org/licenses/gpl-2.0.html)
 * Giấy phép : GNU GPL 2.0 (nguồn : http://www.gnu.org/licenses/gpl-2.0.html )
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.action.ParameterDefinitionImpl;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.util.TempFileProvider;
import org.apache.log4j.Logger;

import org.alfresco.webservice.content.Content;
import org.alfresco.webservice.content.ContentServiceSoapBindingStub;
import org.alfresco.webservice.repository.UpdateResult;
import org.alfresco.webservice.types.CML;
import org.alfresco.webservice.types.CMLCreate;
import org.alfresco.webservice.types.ContentFormat;
import org.alfresco.webservice.types.NamedValue;
import org.alfresco.webservice.types.ParentReference;
import org.alfresco.webservice.types.Predicate;
import org.alfresco.webservice.types.Reference;
import org.alfresco.webservice.util.AuthenticationUtils;
import org.alfresco.webservice.util.Constants;
import org.alfresco.webservice.util.ContentUtils;
import org.alfresco.webservice.util.Utils;
import org.alfresco.webservice.util.WebServiceFactory;

/**
 * encryption data in alfresco Mã hóa tài liệu trong alfresco
 * 
 * @since 4.0 phiên bản 4.0 trở lên
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
		return this.contentService();
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
		data = this.converToByteArray(is);

		return data;
	}

	public void write(NodeRef nodeRefer, byte[] data) {
		// Return a file from bytes
		String path;
		File fout = BytestoFile.bytesToFile(data, path);

		// Getting Mimetype of node
		QName PROP_QNAME_CONTENT = QName.createQName(
				"http://www.alfresco.org/model/content/1.0", "content");
		ContentData contentData = (ContentData) this.getNodeService()
				.getProperty(nodeRefer, PROP_QNAME_CONTENT);
		String originalMimeType = contentData.getMimetype();

		ContentService contentService = this.serviceRegistry
				.getContentService();
		ContentWriter contentWriter = contentService.getWriter(nodeRefer,
				ContentModel.PROP_CONTENT, true);

		// write to the file
		contentWriter.setMimetype(originalMimeType);
		contentWriter.putContent(fout);
	}

	public void action(NodeRef nodeRefer) {
	}
}