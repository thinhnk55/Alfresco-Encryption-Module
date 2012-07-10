<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="/WEB-INF/alfresco.tld" prefix="a" %>
<%@ taglib uri="/WEB-INF/repo.tld" prefix="r" %>
   <f:loadBundle basename="alfresco.extension.webclient" var="msg"/>    

<h:outputText value="#{msg.decipher} : " />
<h:inputSecret value="#{DialogManager.bean.passphrase}" size="50" maxlength="1024" />