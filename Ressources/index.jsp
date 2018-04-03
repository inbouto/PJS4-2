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
		<% 	String msg;
				if(request.getParameter("message") != null)
					msg = request.getParameter("message");
				else
					msg="Hello !";%>
		<div id="msg1">
			<%= msg %><br>
		</div>			
			What do you want to do ?<br>
			<a href="fullLaunch.jsp"><button type="button">launch everything !</button></a>
			<a href="fullStop.jsp"><button type="button">stop everything !</button></a>
		</div>
		<div class="tabli-tablo">
			<table>
				
				   <tr>
					   <th>Services</th>
					   <th> Addresse </th>
					   <th> On/Off </th>
					   <th> Etat </th>
				   </tr>
				   <tr>
					   <td> Insert Mail Here </td>
					   <td> Insert Address Here </td>
					   <td> <label class="switch">
							<input type="checkbox">
							<span class="slider"></span>
							</label>
						</td>
					   <td> <div class="led-green"></div> </td>
				   </tr>
				   <tr>
					   <td> Insert Local IHM Here </td>
					   <td> Insert Address Here </td>
					   <td> <label class="switch">
							<input type="checkbox">
							<span class="slider"></span>
							</label>
						</td>
					   <td> <div class="led-red"></div> </td>
				   </tr>
				    <tr>
					   <td> Insert Twitter Here </td>
					   <td> Insert Address Here </td>
					   <td> <label class="switch">
							<input type="checkbox">
							<span class="slider"></span>
							</label>
						</td>
					   <td> <div class="led-green"></div> </td>
				   </tr>
				    
				</table>
		</div>
	
			
			
	</body>

</html>

