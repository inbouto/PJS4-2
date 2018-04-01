<%@ page import="java.util.List.*"
import="webApp.UserWebApp.*" %>

<%!

	private Boolean isOn(int service_id){
		return webApp.UserWebApp.getServiceState(service_id);
	}
	private String getAIName(String cid){
		return webApp.UserWebApp.getAIName(cid);
	}
	private String getAIType(String cid){
		return webApp.UserWebApp.getAIType(cid);
	}
	
	private Boolean isUsed(String cid){
		java.util.List<Integer> serviceIDs = webApp.UserWebApp.getRunningServiceIDsFromAI(cid);
		for(int i : serviceIDs){
			if(isOn(i))
				return true;
		}
		return false;
	}
	
	private String actionDisabled(String cid){
		if(isUsed(cid))
			return "disabled";
		return "";
	}
	
	private String isUsedBy(String cid){
		java.util.List<Integer> serviceIDs;
		String r  = "";
		serviceIDs = webApp.UserWebApp.getRunningServiceIDsFromAI(cid);
		for(int i : serviceIDs){
			r+="<tr>";
			r+= "<td>" + getServiceNameFromId(i) + "</td>";
			r+= "<td><div class='"+ getCorrectLed(i) + "'></div></td>";
			r+= "</tr>";
		}
		return r;
	}
	
	private String getCorrectLed(int service_id){
		if(isOn(service_id))
			return "led-green";
		return "led-red";
	}
	
	private String getServiceNameFromId(int service_id){
		return webApp.UserWebApp.getServiceName(service_id);
	}
%>

<% 
java.util.List<String> CIDs = new java.util.Vector<String>();
CIDs.add("2fbbc6x326-nlc-1922");
CIDs.add("2fc31ex330-nlc-1535");
String buttons = "";
String name;
String type;
String usedBy;
String disabled;

for(String cid : CIDs){
	name = getAIName(cid);
	type = getAIType(cid);
	usedBy = isUsedBy(cid);
	disabled = actionDisabled(cid);
	
	buttons += "<tr>";
	buttons += "<td> " + name + " </td>";
	buttons += "<td> " + type + "</td>";
	buttons += "<td>";
	buttons += "<table>" + usedBy + "</table>";
	buttons += "</td>";
	buttons += "<td>";
	buttons += "<input type='button' value='train'" + disabled +"><br>";
	buttons += "<input type='button' value='edit'" + disabled +"><br>";
	buttons += "<input type='button' value='delete'" + disabled +"><br>";
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
				My A.I.s<br>
			</div>
		<div class="tabli-tablo">
				<table>
					<tr>
						<td>A.I. name</td>
						<td>A.I. type</td>
						<td>Provides for service :</td>
						<td>Action</td>
					</tr>
					
					   <%= buttons %>
						
					</table>
					
				<input type="button" value="Create new A.I."><br>
				<a href="/TechBot/dashboard.jsp"><input type="button" value="back"></a><br>
			</div>

		</div>
	</body>
</html>