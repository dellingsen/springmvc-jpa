<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

	<head>
		<title>Login</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="/jpa-spring-locator/css/screen.css"/>
	</head>

	<body>
		<div id="container">
			<div id="content">

				<h1>Login Page</h1>


				<form:form method="POST" modelAttribute="user" action="login">  
					<h2>Member Login</h2>
					<p>Please log in with your username to use the application</p>
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
						</tbody>
					</table>
					<table>
						<tr>
							<td>
								<input type="submit" value="Login" class="register"/>
							</td>
						</tr>
						<tr>
							<td>
								<p>Don't have an account? <a href="<c:url value="/createUser"/>">Sign-up now</a></p>
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
