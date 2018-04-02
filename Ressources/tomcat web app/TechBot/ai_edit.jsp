<%@ page import="webApp.UserWebApp.*" %>

<%
	for(int i : webApp.UserWebApp.getRunningServiceIDsFromAI(request.getParameter("cid")))
		if(webApp.UserWebApp.getServiceState(i))
			response.sendRedirect("/TechBot/ai_manager.jsp");
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
		
		<div id="msg1">
				Edit A.I.<br>
			</div>
			
			<form action="/TechBot/modify_ai.jsp?cid=<%= request.getParameter("cid") %>" method="POST">
				Name : <input type="text" name="name" value="<%= webApp.UserWebApp.getAIName(request.getParameter("cid")) %>"><br>
				<input type="submit" value="confirm">
			</form>

		</div>
		<a href="/TechBot/dashboard.jsp"><input type="button" value="back"></a><br>
	</body>
</html>