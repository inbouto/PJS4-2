<%@ page import="webApp.UserWebApp.*" %>

<%!
public String getServiceName(int service_id){
	return webApp.UserWebApp.getServiceName(service_id);
}
%>

<%
int service_id = Integer.parseInt(request.getParameter("service_id"));

	if(webApp.UserWebApp.getServiceState(service_id))
		response.sendRedirect("/TechBot/dashboard.jsp");


String serviceName = getServiceName(service_id);
%>

<!DOCTYPE html>

<html>
	<head>
	<title>QBoT</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="style/style.css">
		<link href='https://fonts.googleapis.com/css?family=Advent Pro' rel='stylesheet'>
	</head>
	
	<body>
		<div class="contenu">
		
		Are you certain you want to delete the "<%= serviceName %>" service ?<br>
		<a href="/TechBot/confirmServiceDel.jsp?service_id=<%= service_id %>"><input type="button" value="Yes"></a>

		</div>
		<a href="/TechBot/dashboard.jsp"><input type="button" value="back"></a><br>
	</body>
</html>