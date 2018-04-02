<%@ page import="java.util.List.*"
import="webApp.UserWebApp.*" %>

<%!
//This will eventually depends on the logged in user
private java.util.List<String> getAIs(){
	return webApp.UserWebApp.getAIs();
}

private String getAIName(String cid){
	return webApp.UserWebApp.getAIName(cid);
}

private String getAIOptions(){
	String r = "";
	for(String cid : getAIs()){
		r += "<option value='" + cid + "'>" + getAIName(cid) + "</option>";
	}
	return r;
}

private java.util.List<String> getPlatformNames(){
	return webApp.UserWebApp.getPlatformNames();
}

private String getPlatformOptions(){
	String r = "";
	for(String name : getPlatformNames()){
		r += "<option>" + name;
	}
	return r;
}

%>

<%
	String available_AIs = getAIOptions();
	String available_service_types = getPlatformOptions();
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
				New service<br>
			</div>
		<form action="generate_service.jsp">
			Name <input type="text" name="name"><br>
			Type <select name="type" size="1">
			<%= available_service_types %>
			</select><br>
			A.I. <select name="ai" size="1">
			<%= available_AIs %>
			</select><br>
			Credentials (These are used to access the account on which you wish to run the bot on the platform you selected)<br>
			login <input type="text" name="login"> password <input type="password" name="password"><br>
			<input type="submit" value="create">
		</form>
<br>
				<a href="/TechBot/dashboard.jsp"><input type="button" value="back"></a><br>
		</div>
	</body>
</html>