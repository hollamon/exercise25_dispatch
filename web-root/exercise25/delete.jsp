<%@ page language="java" import="edu.arizona.training.bo.Bookmark" 
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean-el.tld" prefix="bean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Bookmark</title>
</head>
<body>

<h2>Are you sure you want to delete this bookmark?</h2>
<p>
	Name: <c:out value="${bookmarkToDelete.name}"/><br />
	URL: <c:out value="${bookmarkToDelete.url}"/>
</p>

<html:form action="bookmarks.do?methodToCall=save">
<html:hidden property="id" value="${bookmarkToDelete.id}"/>
<html:hidden property="action" value="delete"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:link action="bookmarks.do">No</html:link>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:submit value="Yes" />
</html:form>

</body>
</html>