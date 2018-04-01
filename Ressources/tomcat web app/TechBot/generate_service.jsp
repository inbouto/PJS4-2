<%@ page import="webApp.UserWebAction.*" %>
<%
	
	webApp.UserWebAction.createService(request.getParameter("name"), request.getParameter("type"), request.getParameter("ai"), request.getParameter("login"), request.getParameter("password"));
	

%>