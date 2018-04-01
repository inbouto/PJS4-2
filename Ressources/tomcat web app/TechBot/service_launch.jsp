<%@ page import="webApp.UserWebAction.*" %>
<%
	int service_id = Integer.parseInt(request.getParameter("service_id"));
	webApp.UserWebAction.launch(Integer.parseInt(request.getParameter("service_id")));
	
%>

please hold while we launch your service...
<%
	while(!webApp.UserWebApp.getServiceState(service_id)){
		try {
            Thread.sleep(500);
         } catch (Exception e) {
            System.out.println(e);
         }
	}
	response.sendRedirect("/TechBot/dashboard.jsp");

%>