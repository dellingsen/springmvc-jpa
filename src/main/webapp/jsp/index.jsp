<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

	<head>
		<title>Spring MVC Application</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/screen.css"/>"/>
	</head>

	<body>
		<div id="container">
			<div class="dualbrand">
				<img src="<c:url value="/images/microbrew.jpg"/>"/>
			</div>
			<div id="content">
				<h1>Location</h1>

				<div>
					<p>You can now browse through local locations you have created.</p>
				</div>

				<h2>Hi ${user.userName}, welcome to the Spring MVC site!</h2>
				<h2>Browse local locations <a href="<c:url value="/lookup-locations.html"/>">locations</a></h2>
				
				<table class="simpletablestyle">
					<tr>
						<td>
						</td>
					</tr>
				</table>
				
				<h2><a href="<c:url value="/logout"/>">Logout</a></h2>
			</div>
			<div id="aside">
				<p>Learn more about Micro locations in your area...</p>
				<ul>
				</ul>
				<p>Find a location near you.</p>
				<ul>
				</ul>
			</div>
			<div id="footer">
			    <p>
					This project is being run on JBoss 7.1 with SpringMVC and build with Maven.<br />
			    </p>
			</div>
		</div>
	</body>
</html>
