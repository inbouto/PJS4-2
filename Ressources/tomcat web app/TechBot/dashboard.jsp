<%@ page import="webApp.UserWebApp.*" %>

<%!
private int getUserServiceAmounts(){
	return webApp.UserWebApp.getUserServiceAmounts();
}
private Boolean isOn(int service_id){
	return webApp.UserWebApp.getServiceState(service_id);
}

private String getCorrectLed(int service_id){
	
	if(isOn(service_id))
		return "led-green";
	return "led-red";
}

private String getActionButtonText(int service_id){
	if(isOn(service_id))
		return "Stop";
	return "Launch";
}

private String getServiceName(int service_id){
	return webApp.UserWebApp.getServiceName(service_id);
}

private String getServiceAIName(int service_id){
	return webApp.UserWebApp.getServiceAIName(service_id);
}

private String getServiceUsername(int service_id){
	return webApp.UserWebApp.getServiceUsername(service_id);
}

private String getActionLink(int service_id){
	if(isOn(service_id))
		return "/TechBot/service_stop.jsp?service_id=" + service_id;
	return "/TechBot/service_launch.jsp?service_id=" + service_id;
}

private String getEditLink(int service_id){
	return "/TechBot/service_edit.jsp?service_id=" + service_id;
}

private String getDisabled(int service_id){
	if(isOn(service_id))
		return "disabled";
	return "";
}

%>

<%
int nbService = getUserServiceAmounts();
String buttons = "";
String name;
String aiName;
String led;
String actionButtonText;
String actionLink;
String username;
String editLink;

for(int i = 1; i <= nbService; ++i){
	name = getServiceName(i);
	aiName = getServiceAIName(i);
	led = getCorrectLed(i);
	actionButtonText = getActionButtonText(i);
	actionLink = getActionLink(i);
	username = getServiceUsername(i);
	editLink = getEditLink(i);
	
	buttons += "<tr>";
	buttons += "<td> " + name + " </td>";
	buttons += "<td> " + aiName + " </td>";
	buttons += "<td> " + username + "</td>";
	buttons += "<td><div class='"+ led + "'></div></td>";
	buttons += "<td>";
	buttons += "<a href='" + actionLink + "'><input type='button' value='" + actionButtonText + "'></a><br>";
	buttons += "<a href='" + editLink + "'><input type='button' value='Edit'" + getDisabled(i) + "></a>";
	buttons += "</td>";
	buttons += "</tr>";
}
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
	
		<a href="/TechBot/ai_manager.jsp"><input type="button" value="My A.I.s"></a>
		<div class="contenu">
		
			<div id="msg1">
				Dashboard<br>
			</div>		

		
			<div class="tabli-tablo">
				<table>
					<tr>
						<td>Service</td>
						<td>A.I. Name</td>
						<td>Service credentials</td>
						<td>State</td>
						<td>Action</td>
					</tr>
					
					   <%= buttons %>
						
					</table>
					
				<a href="create_service.jsp"><input type="button" value="Create new service"></a><br>
				<a href="/TechBot/"><input type="button" value="back"></a><br>
			</div>
		</div>
			
	</body>

</html>