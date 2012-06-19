<%@page import="java.util.Collections"%>
<%@page import="br.inf.pucrio.codesearcher.Feedback"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List" %>
<%@page import="org.apache.lucene.document.Document;" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type="text/javascript" src="js/prettify.js"></script>
<link href="css/prettify.css" rel="stylesheet" type="text/css" />
<link href="css/codeSearcher.css" rel="stylesheet" type="text/css" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title> Error while processing your query.</title>

</head>




<body onload="prettyPrint()">

<div id="help-content">
	<span style="text-align: right;"><a href="index.jsp">Home</a></span>
	<span style="text-align: right;"><a href="help.jsp">Help</a></span>
</div>

<div style="margin-top: 40px; height:70px;">
	
	<div style="width:220px; float:left;">
	
	<span class="logo" title="Coode - A Code Search Engine">
		<a href="index.jsp">
		<span style="color: blue;">C</span>
		<span style="color: red;">o</span>
		<span style="color: #FFCC00;">o</span>
		<span style="color: green;">d</span>
		<span style="color: blue;">e</span>
		</a>
	</span>
	
	<center>
	<span style="color: #DD4B39; font: 20px 'Arial';">Search</span>
	</center>
	</div>
	
	<div style="margin-left:250px;">
		<form method="get" action="Searcher.search">
			<input type="text" name="query" size="150" style="background-color: white;"></input>
			<input type="submit" value="Search" />
		</form>
	</div>
</div>


<div style="margin-top: 20px;">

<div style="width: 220px; float:left; font-family: arial,sans-serif;">

<p><a href="Searcher.search?query=feedback:FIVE">Rating 5 documents.</a></p>

<p><a href="Searcher.search?query=feedback:FOUR">Rating 4 documents.</a></p>

<p><a href="Searcher.search?query=feedback:THREE">Rating 3 documents.</a></p>

<p><a href="Searcher.search?query=feedback:TWO">Rating 2 documents.</a></p>

<p><a href="Searcher.search?query=feedback:ONE">Rating 1 documents.</a></p>

<p><a href="Searcher.search?query=feedback:NONE">Non rated documents.</a></p>

</div>

<div style="margin-top: 10px; margin-left: 250px; width: 80%;">
	
	<%
	final Object o = request.getAttribute( "exception" );

	if( o instanceof Throwable )
	{
		final Throwable e = (Throwable)o;
		
		final String errorMessage = e.getMessage();
		
		StackTraceElement[] stack = e.getStackTrace();
		
		StringBuffer buffer = new StringBuffer();
		for( StackTraceElement element : stack )
		{
			final String elementStr = String.format( "%s<br>", element.toString() );
			buffer.append( elementStr );
		}
%>
	<p>
	An error occurred while performing your search. Please copy the following ERROR MESSAGE and send it to your sysadmin.
	</p>
	
	<p>
	<span class="error_msg"> ERROR MESSAGE: </span>
	</p>
	
	<p>
	<code>
	<%= errorMessage %>
	</code>
	</p>
	
	<div class="stacktrace">
		<code>
			<%= buffer.toString() %>
		</code>
	</div>
<%
	}
%>
	
</div>

</div>

</body>
</html>