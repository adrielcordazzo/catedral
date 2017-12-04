<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Users List</title>
</head>

<body>
	
	<div id="referencia">
	<div rv-value="idade">xxxx</div>
	<input type="text" name="idade" rv-value="idade">
	<input type="text" name="nome" rv-value="nome">
	<select name="brasil" rv-value="brasil">
		<option value="1">1s</option>
		<option value="2">2s</option>
	</select>
	</div>


</body>
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/DataBind.js"></script>
<script src="${pageContext.request.contextPath}/assets/require.js" data-main="assets/main"></script>

</html>