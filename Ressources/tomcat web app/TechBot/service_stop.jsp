<%@ page import="webApp.UserWebAction.*"
	import = "webApp.UserWebApp.*" %>
<%
	int service_id = Integer.parseInt(request.getParameter("service_id"));
	webApp.UserWebAction.stop(service_id);
%>

please hold while we stop your service...
<%
	while(webApp.UserWebApp.getServiceState(service_id)){
		try {
            Thread.sleep(500);
         } catch (Exception e) {
            System.out.println(e);
         }
	}
	response.sendRedirect("/TechBot/menu.jsp");

%>