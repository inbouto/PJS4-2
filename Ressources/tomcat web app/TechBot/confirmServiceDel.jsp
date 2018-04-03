<%@ page import="webApp.UserWebAction.*" %>
<%
	webApp.UserWebAction.deleteService(Integer.parseInt(request.getParameter("service_id")));
	response.sendRedirect("/TechBot/dashboard.jsp");
%>