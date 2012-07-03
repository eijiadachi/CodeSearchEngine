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

<title>Help</title>

</head>

<body>

<div id="help-content">
	<span style="text-align: right;"><a href="index.jsp">Home</a></span>
	|
	<span style="text-align: right;"><a href="Configurator.config">Config</a></span>
	|
	<span style="text-align: right;"><a href="help.jsp">Help</a></span>
</div>

<div class="logoDiv" style="margin-top: 40px; height:70px;">
	
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
	
	<div class="search-header" style="margin-left:250px;">
		<form method="get" action="Searcher.search">
			<input type="text" name="query" style="background-color: white;"></input>
			<input type="submit" value="Search" />
		</form>
	</div>
</div>


<div style="margin-top: 20px;">

<div style="width: 220px; float:left; font-family: arial,sans-serif;">

<%
	final Feedback[] feedbackOptions = Feedback.values();

	for( Feedback feedback : feedbackOptions )
	{
		final String feedbackString = feedback.getName();
		final String feedbackName = feedback.name();
%>
<p><a href="Searcher.search?query=feedback:<%=feedbackName%>"><%= feedbackString %> documents.</a></p>	
<%
	}
%>

</div>

<div style="margin-top: 10px; margin-left: 250px; width: 80%;">
	
	<h1>Allow me to help you</h1>
	
	<p>
	The Coode system is a search engine aimed at searching for code snippets implementing exception handling code in Java.
	
	<p>The Coode system has its own query language, which is based on a boolean model. A valid query string accepted by the Coode system is a boolean expression of terms.
	A term is a pair (field:value), where 'field' indicates the name of a field by which code examples are indexed in the repository of examples and 'value'
	indicates the value that the user wants to search for.
	
	<p>The available fields are:
	<ul>
		<li>calls: a method called by the code snippet stored in the repository</li>
		<li>uses: a type of a variable used by the code snippet stored in the repository</li>
		<li>handles: a type of an exception handled by the code snippet stored in the repository</li>
		<li>feedback: the feedback given by to a code snippet stored in the repository. It might be: NONE, VERY_RELEVANT, RELEVANT, INDIFFERENT, IRRELEVANT and VERY_IRRELEVANT</li>
	</ul>
	
	<p>A boolean expression is a set of terms composed by parenthesis and the following boolean operators:
	<ul>
		<li>AND</li>
		<li>OR</li>
		<li>NOT</li>
	</ul>
	
	<p> The Coode query language also supports the use of wildcards in the 'value' definition. The following queries are valid queries:
	<ul>
		<li>handles:java.io.IOException AND (uses:java.io.File OR uses:java.io.Writer)</li>
		<li>calls:java.io.FileWriter.append AND NOT handles:java.lang.Exception</li>
		<li>(calls:*append OR calls:*printf) AND NOT feedback:*IRRELEVANT</li>
	</ul>
	
</div>

</div>

</body>
</html>