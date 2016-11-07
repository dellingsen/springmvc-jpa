<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

	<head>
		<title>Create User</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/screen.css"/>"/>
	</head>

	<body>
		<div id="container">
			<div id="content">

				<h1>Add new user</h1>

				<form:form method="POST" modelAttribute="user" action="createUser">  
					<h2>New user information</h2>

					<table>
						<tbody>
						    <tr>  
						        <td><form:label path="userName">Username</form:label></td>  
						        <td><form:input path="userName"></form:input></td>  
						    </tr>  
						    <tr>  
						        <td><form:label path="password">Password</form:label></td>  
						        <td><form:input type="password" path="password"></form:input></td>  
						    </tr>  
						    <tr>  
						        <td><form:label path="firstName">First name</form:label></td>  
						        <td><form:input path="firstName"></form:input></td>  
						    </tr>  
						    <tr>  
						        <td><form:label path="lastName">Last name</form:label></td>  
						        <td><form:input path="lastName"></form:input></td>  
						    </tr>  
						    <tr>  
						        <td><form:label path="email">Email</form:label></td>  
						        <td><form:input path="email"></form:input></td>  
						    </tr>  
						</tbody>
					</table>
					<table>
						<tr>
							<td>
								<input type="submit" value="Create" class="register"/>
							</td>
						</tr>
					</table>
				</form:form>
			</div>
			<div id="footer">
			    <p>
					Copyright 2013<br />
			    </p>
			</div>
		</div>
	</body>
</html>