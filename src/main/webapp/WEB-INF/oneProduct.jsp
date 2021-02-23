<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><c:out value="${product.name}"/></title>
</head>
<body>
<a href="/">Home</a>
	<h1 style="text-align: center;"><c:out value="${product.name}"/></h1>
	<h4><c:out value="${product.description}"/></h4>
	<div style="display: flex; justify-content: space-evenly;">
	<ul>
		<c:forEach items="${cats }" var="cat">
			<li><c:out value="${cat.name }"/></li>
		</c:forEach>
	</ul>
		<form action="/products/${product.id}" method="post">
		<input type="hidden" name="_method" value="put">
    <p>
        <label for="categories">Name</label>
        <select name="categories">
	        <c:forEach items="${categories }" var="cat">
	        	<option value="${cat.id}"><c:out value="${cat.name }"/></option>
	        		
	        </c:forEach>
        </select>
    </p>

    <input type="submit" value="Submit"/>
</form>
	
	
	</div>
</body>
</html>