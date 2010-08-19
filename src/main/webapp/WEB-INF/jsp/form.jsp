<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>

	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>
	    	<spring:message code="form.title"/>
		</title>
    	<link rel="stylesheet" href="css/style.css" type="text/css"/>
	</head>

	<body>
		<div class="Content">
			<h1><spring:message code="form.header"/></h1>
			<form:form method="post" action="user.htm" commandName="user">
				<table border="0" cellpadding="4" cellspacing="0">
					<tr>
						<td><spring:message code="form.name"/></td> <td><form:input path="firstName" size="20" maxlength="50" /></td> <td><form:errors cssClass="error" path="firstName" /></td>
					</tr> 
					<tr>
						<td><spring:message code="form.surname"/></td> <td><form:input path="surname" size="20" maxlength="50" /></td> <td><form:errors cssClass="error" path="surname" /></td>
					</tr>
				</table>
				<div class="FormButtons">
					<input type="submit" value="<spring:message code="form.submit"/>" />
				</div>
			</form:form>
		</div>
	</body>
</html>