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
<title>My Bookmarks</title>
</head>
<body>

<h4><html:link action="bookmarks.do?methodToCall=add">Add New Bookmark</html:link></h4>

<table>
	<thead>
		<tr>
			<th>Name</th>
			<th>URL</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
	</thead>
	<tbody>
<c:forEach items="${requestScope.bookmarks}" var="bookmark">
		<tr>
			<td><c:out value="${bookmark.name}"/></td>
			<td><html:link href="${bookmark.url}" target="_blank"><c:out value="${bookmark.url}"/></html:link></td>
			<td><html:link action="bookmarks.do?methodToCall=edit&id=${bookmark.id}">edit</html:link></td>
			<td><html:link action="bookmarks.do?methodToCall=delete&id=${bookmark.id}">delete</html:link></td>
		</tr>
</c:forEach>
	</tbody>
</table>

</body>
</html>