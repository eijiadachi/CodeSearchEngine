<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link href="css/codeSearcher.css" rel="stylesheet" type="text/css" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Code Search Engine</title>
</head>
<body>
	
	<div id="help-content">
		<span style="text-align: right;"><a href="index.jsp">Home</a></span>
		<span style="text-align: right;"><a href="help.jsp">Help</a></span>
	</div>
	
	<div id="search-content">
	
		<center>
			<div>
				<span class="logo" title="Coode - A Code Search Engine">
					<a href="index.jsp">
					<span style="color: blue;">C</span>
					<span style="color: red;">o</span>
					<span style="color: #FFCC00;">o</span>
					<span style="color: green;">d</span>
					<span style="color: blue;">e</span>
					</a>
				</span>
			</div>
		</center>
		
		<center>
			<div id="search-form">
				<form method="post" action="Searcher.search">
					<input type="text" name="query" size="150" style="background-color: white;"></input><br>
					<input type="submit" value="Search" />
				</form>
			</div>
		</center>
	
	</div>	
	
</body>
</html>