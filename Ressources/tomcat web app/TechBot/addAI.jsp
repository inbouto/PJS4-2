<%@ page import="java.util.List.*"
import="webApp.UserWebApp.*" %>

<%!
private String getTypeOptions(){
	java.util.List<String> types = getTypes();
	String r = "";
	for(String type : types){
		r += "<option>" + type;
	}
	return r;
}

private java.util.List<String> getTypes(){
	return webApp.UserWebApp.getAITypes();
}
%>

<%
String typeOptions = getTypeOptions();
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
		
		<form>
			<select>
			<%= typeOptions %>
			</select><br><br>
			training file (.csv) <input type="file" name="training data">
		</form>

		</div>
		<a href="/TechBot/dashboard.jsp"><input type="button" value="confirm"></a><br>
		<a href="/TechBot/dashboard.jsp"><input type="button" value="back"></a><br>
	</body>
</html>