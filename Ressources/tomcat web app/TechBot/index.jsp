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

private String getCorrectButtonText(int service_id){
	if(isOn(service_id))
		return "Stop";
	return "Launch";
}

private String getServiceName(int service_id){
	return webApp.UserWebApp.getServiceName(service_id);
}

private String getServiceInfo(int service_id){
	return webApp.UserWebApp.getServiceInfo(service_id);
}

private String getLink(int service_id){
	if(isOn(service_id))
		return "/TechBot/service_stop.jsp?service_id=" + service_id;
	return "/TechBot/service_launch.jsp?service_id=" + service_id;
}
%>

<%
int nbService = getUserServiceAmounts();
String buttons = "";
String name;
String info;
String led;
String buttonText;
String link;

for(int i = 1; i <= nbService; ++i){
	name = getServiceName(i);
	info = getServiceInfo(i);
	led = getCorrectLed(i);
	buttonText = getCorrectButtonText(i);
	link = getLink(i);
	
	buttons += "<tr>";
	buttons += "<td> " + name + " </td>";
	buttons += "<td> " + info + " </td>";
	buttons += "<td><div class='"+ led + "'></div></td>";
	buttons += "<td>";
	buttons += "<a href='" + getLink(i) + "'><input type='button' value='" + buttonText + "'>";
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
		<div class="contenu">
		
		<div id="msg1">
			Hello !<br>
		</div>			
			What do you want to do ?<br>

		</div>
		<div class="tabli-tablo">
			<table>
				<tr>
					<td>Service</td>
					<td>Information</td>
					<td>State</td>
					<td>Action</td>
				</tr>
				
				   <%= buttons %>
				    
				</table>
		</div>
	
			
			
	</body>

</html>