package com.extendedencryption.action.executer;

/*
 * Project: Alfresco Encryption Extension Module , part of the Creative Summer
 * This code was developped by a group of 3 students from UET-VNU .
 * License   : GNU General Public License, version 2 (http://www.gnu.org/licenses/gpl-2.0.html)
 */
import com.extendedencryption.util.AES;
import com.extendedencryption.util.Tools;
import java.util.List;

import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;


public class DecryptionActionExecuter extends BaseExecuter {

    public static final String NAME = "decryption-action";
    private NodeService nodeService;

    protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
        super.addParameterDefinitions(paramList);
    }

    public void executeImpl(Action ruleAction, NodeRef actionedUponNodeRef) {
        byte[] key = null;

        String pass = super.getPassword(actionedUponNodeRef);
        String inputPassword = (String) ruleAction.getParameterValue(BaseExecuter.PARAM_PASS);        
        String inputMD5Password = Tools.convertToMD5(inputPassword);

        if (!pass.equals(inputMD5Password)) {
            int i = 0 / 0;
            return ;
        }

        try {
            key = inputMD5Password.getBytes();
        } catch (NullPointerException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        byte[] data = AES.decrypt(super.getNodeContent(actionedUponNodeRef), key);
        super.write(actionedUponNodeRef, data);

        ruleAction.setParameterValue(BaseExecuter.PARAM_ACTIVE, false);
        ruleAction.setParameterValue(BaseExecuter.PARAM_PASS, inputMD5Password);
        
        super.executeImpl(ruleAction, actionedUponNodeRef);
    } // end if isEmpty
}