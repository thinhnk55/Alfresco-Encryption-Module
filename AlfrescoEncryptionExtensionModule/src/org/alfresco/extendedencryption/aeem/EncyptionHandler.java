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

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.alfresco.web.app.Application;
import org.alfresco.web.bean.actions.handlers.BaseActionHandler;
import org.alfresco.web.bean.wizard.IWizardBean;

public class EncryptionHandler extends BaseActionHandler {

	/** passphrase property name*/
	public static final String PROP_PASSPHRASE = "passphrase";

	/** 
	 * path of the jsp where the user provides the passphrase 
	 * that participates in the content encryption 
	 */

	public static String JSPPATH="/jsp/extension/encryption.jsp";

	public String getJSPPath() {
		return JSPPATH;
	}

	public void prepareForSave(Map<String, Serializable> actionProps,Map<String, Serializable> repoProps) {
		repoProps.put(EncryptionExecuter.PARAM_PASSPHRASE, (String)actionProps.get(PROP_PASSPHRASE));
	}

	public void prepareForEdit(Map<String, Serializable> actionProps,
			Map<String, Serializable> repoProps) {	
		actionProps.put(PROP_PASSPHRASE, (String)repoProps.get(EncryptionExecuter.PARAM_PASSPHRASE));
	}

	public String generateSummary(FacesContext context, IWizardBean wizard,Map<String, Serializable> actionProps) {	
		String ciphers = (String)actionProps.get(PROP_PASSPHRASE);
		if (ciphers == null){
			ciphers = "";
		}    
		return MessageFormat.format(Application.getMessage(context, "content_cipher"),new Object[] {ciphers});
	}
}
