<%@ page import="webApp.UserWebAction.*" %>

<%
	webApp.UserWebAction.setAIName(request.getParameter("cid"), request.getParameter("name"));
	response.sendRedirect("/TechBot/ai_manager.jsp");
%>