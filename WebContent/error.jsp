<%@page import="java.util.*"%>
<%@page import="java.util.Map.Entry"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	Map<String, String[]> map = request.getParameterMap();
	
	Set<Entry<String, String[]>> entrySet = map.entrySet();
	
	for( Entry<String, String[]> entry : entrySet )
	{
		String key = entry.getKey();
		String[] value = entry.getValue();
	%>
		<span> <%= key %> </span><br>
	<% 
		for(String str : value )
		{
			%>
			<span> <%= str %> </span><br>
			<%	
		}
	}
%>

</body>
</html>