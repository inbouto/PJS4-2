<% 	String msg;
	if(request.getParameter("message") != null)
		msg = request.getParameter("message");
	else
		msg="Hello !";%>

<%= msg %><br>
what do you want to do ?<br>
<a href="fullLaunch.jsp"><button type="button">launch everything !</button></a>
<a href="fullStop.jsp"><button type="button">stop everything !</button></a>


