<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products and Category</title>
</head>
<body>
<h1 style="text-align: center;"><a href="/products/new">New Product</a> || <a href="/categories/new">New Category</a></h1>
<div style="display: flex; justify-content: space-evenly; size: 20px;">
	<ul>
		<c:forEach items="${prods}" var="prod">
			<li><a href="/products/${prod.id }"><c:out value="${prod.name }"/></a></li>
		</c:forEach>
	</ul>
	<ul>
		<c:forEach items="${cats}" var="cat">
			<li><a href="/categories/${cat.id }"><c:out value="${cat.name }"/></a></li>
		</c:forEach>
	</ul>
</div>
</body>
</html>