<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><c:out value="${category.name}"/></title>
</head>
<body>
<a href="/">Home</a>
	<h1 style="text-align: center;"><c:out value="${category.name}"/></h1>
	
	<div style="display: flex; justify-content: space-evenly;">
	<ul>
		<c:forEach items="${prods }" var="prod">
			<li><c:out value="${prod.name }"/></li>
		</c:forEach>
	</ul>
		<form action="/categories/${category.id}" method="post">
		<input type="hidden" name="_method" value="put">
    <p>
        <label for="products">Name</label>
        <select name="products">
	        <c:forEach items="${products }" var="products">
	        	<option value="${products.id}"><c:out value="${products.name }"/></option>
	        		
	        </c:forEach>
        </select>
    </p>

    <input type="submit" value="Submit"/>
</form>
	
	
	</div>
</body>
</html>