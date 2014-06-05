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
<title>Add Bookmark</title>
</head>
<body>

<html:errors/>

<html:form action="bookmarks.do?methodToCall=save" focus="name">
<html:hidden property="action" value="add"/>
<table>
	<tbody>
		<tr>
			<td>Name:</td>
			<td><html:text property="name" /> <html:errors property="name"/></td>
		</tr>
		<tr>
			<td>URL:</td>
			<td><html:text property="url" /> <html:errors property="url"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><html:submit value="Add" /></td>
		</tr>
	</tbody>
</table>
</html:form>

</body>
</html>