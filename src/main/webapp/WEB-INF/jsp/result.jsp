<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>
	    	<spring:message code="result.title"/>
		</title>
    	<link rel="stylesheet" href="css/style.css" type="text/css"/>
	</head>
	
	<body>
		<div class="Content">
		<h1><spring:message code="result.header"/></h1>
		<spring:eval expression="user.firstName"/> <spring:eval expression="user.surname"/> is stored in the database.<br/>
		Click <a href="user.htm">here</a> to add another user.
		</div>
	</body>
</html>