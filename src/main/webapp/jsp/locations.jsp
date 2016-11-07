<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>


	<style>
		tr.alt td {
			background: #ecf6fc;
		}
		tr.over td {
			background: #bcd4ec;
		}
	</style>
	 
	 
	 <head>
		<title>Spring MVC Application</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/screen.css"/>"/>

	
	   	<!-- <script type="text/javascript" src="jquery-1.2.6.js"></script> -->
	   	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.js" type="text/javascript"></script>
		<script type="text/javascript">
	
	    //jQuery - Stripe tables with mouseover highlighting!!!
		$(document).ready(function() {
	
			$(".stripeMe tr").mouseover(function() {
				$(this).addClass("over")});
	
			$(".stripeMe tr").mouseout(function() {
				$(this).removeClass("over")});
				
			$(".stripeMe tr:even").addClass("alt");
		});
	
		</script>
	</head>

	<body> 
		<div id="container">
			<div class="dualbrand">
				<img src="<c:url value="/images/microbrew.jpg"/>"/>
			</div>
			<div id="content">
				<h1>Location</h1>

					Go back to the <a href="<c:url value="/index.html"/>">Main Page</a>

					<h2>List of Locations:</h2>
					<table bgcolor="white" class="stripeMe" border=1 width="500">	
					   <tr>
					       <th bgcolor="#000000"><font face="verdana" size="1" color="#ffffff"><b>Name</b></font></th> 
					       <th bgcolor="#000000"><font face="verdana" size="1" color="#ffffff"><b>Address</b></font></th> 
					       <th bgcolor="#000000"><font face="verdana" size="1" color="#ffffff"><b>Latitude</b></font></th> 
					       <th bgcolor="#000000"><font face="verdana" size="1" color="#ffffff"><b>Longitude</b></font></th> 
					       <th bgcolor="#000000"><font face="verdana" size="1" color="#ffffff"><b>Type</b></font></th> 
					       <th bgcolor="#000000"><font face="verdana" size="1" color="#ffffff"><b>Remove</b></font></th> 
					    </tr>	
						<c:forEach items="${locationList}" var="location"> 
						    <tr>
						       <td><font face="verdana" size="1"><b>
						         <a href="/google-apps/showMapLocation.do?name=${location.name}&latitude=${location.latitude}&longitude=${location.longitude}">${location.name}</a></b></font>
						       </td> 
						       <td><font face="verdana" size="1">${location.address}
						       	</font></td> 
						       <td><font face="verdana" size="1">${location.latitude}
						       	</font></td> 
						       <td><font face="verdana" size="1">${location.longitude}
						       	</font></td> 
						       <td><font face="verdana" size="1">${location.type}</font></td> 
						       <td width="5">
						         <a href="/jpa-spring-locator/removeLocation?id=${location.id}">
						         <img src="<c:url value="/images/remove.png"/>"/></a>
						       </td> 
						    </tr>
						</c:forEach>
					</table>
			</div>
			<div id="aside">
				<p>Add a new Location:</p>  
				<form:form method="POST" modelAttribute="location" action="saveLocation">  
				<table>  
				    <tbody>
				    <tr>  
				        <td><form:label path="name">Name:</form:label></td>  
				        <td><form:input path="name"></form:input></td>  
				    </tr>  
				    <tr>  
				        <td><form:label path="address">Address:</form:label></td>  
				        <td><form:input path="address"></form:input></td>  
				    </tr>  
				    <tr>  
				        <td><form:label path="latitude">Latitude:</form:label></td>  
				        <td><form:input path="latitude"></form:input></td>  
				    </tr>  
				    <tr>  
				        <td><form:label path="longitude">Longitude:</form:label></td>  
				        <td><form:input path="longitude"></form:input></td>  
				    </tr>  
				    <tr>  
				        <td><form:label path="type">Type:</form:label></td>  
				        <td><form:input path="type"></form:input></td>  
				    </tr>  
				    <tr>  
				        <td colspan="2">  
				            <input type="submit" value="Submit"/>  
				        </td>  
				        <td></td>  
				        <td></td>  
				    </tr>  
				</tbody></table>    
				</form:form>  
			</div>
			<div id="footer">
			    <p>
					This project is being run on JBoss 7.1 with SpringMVC and built with Maven.<br />
			    </p>
			</div>
		</div>
	</body>
</html>
