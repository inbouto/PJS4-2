<%
	String available_AIs = "<option>aaaa<option>bbbb";
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
				New service<br>
			</div>
		<form action="generate_service.jsp">
			Name <input type="text" name="name"><br>
			Type <select name="type" size="1">
			<option>E-mail
			<option>server-side U.I.
			</select><br>
			A.I. <select name="ai" size="1">
			<%= available_AIs %>
			</select><br>
			Credentials :<br>
			login <input type="text" name="login"> password <input type="password" name="password"><br>
			<input type="submit" value="create">
		</form>

		</div>
	</body>
</html>