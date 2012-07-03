<%@page import="java.util.Collections"%>
<%@page import="br.inf.pucrio.codesearcher.Feedback"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List" %>
<%@page import="org.apache.lucene.document.Document;" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type="text/javascript" src="js/jquery.js"></script> 

<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>

<script type="text/javascript" src="dwr/interface/FeedbackUpdater.js"></script>

<script type="text/javascript" src="js/prettify.js"></script>
<script type="text/javascript" src="js/update_feedback.js"></script>
<link href="css/prettify.css" rel="stylesheet" type="text/css" />
<link href="css/codeSearcher.css" rel="stylesheet" type="text/css" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Detailed View</title>

</head>
<body onload="prettyPrint()">


<div id="help-content">
	<span style="text-align: right;"><a href="index.jsp">Home</a></span>
	|
	<span style="text-align: right;"><a href="Configurator.config">Config</a></span>
	|
	<span style="text-align: right;"><a href="help.jsp">Help</a></span>
</div>

<div class="logoDiv" style="margin-top: 40px; height:70px;">
	
	<div class="logoDiv" style="width:220px; float:left;">
	
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
<p><a href="Searcher.search?query=feedback:<%=feedbackName%>" ><%= feedbackString %> documents.</a></p>	
<%
	}
%>

</div>

<div style="margin-top: 10px; margin-left: 250px; width: 80%;">

<%
	final String methodName = (String) request.getAttribute( "methodName");
	final String codeSnippet = (String) request.getAttribute( "snippet" );
	final String docId = (String) request.getAttribute( "docId" );
	final String feedback = (String) request.getAttribute( "feedback" );
	final Feedback documentCurrentFeedback = Feedback.valueOf( feedback );
%>

	<span class="result">Visão completa do método:</span><span class="query"><%= methodName %></span>

	<form action="Updater.update">		
	
	
		<input name="docId" type="hidden" value="<%= docId %>" />

		<div class="result-content">
			<span class="result-header">Document # <%= docId %></span>
			<select docId="<%= docId %>" class="feedback_select">
			<%
			for(Feedback feedbackOption : feedbackOptions) 
			{
				final Object value = feedbackOption.getComboString();
				final String valueStr = feedbackOption.toString();
				if( feedbackOption.equals( documentCurrentFeedback ))
				{
			%>
				<option selected="selected" value="<%= valueStr %>"><%= value %></option>
			<%
				}
				else
				{
			%>
				<option value="<%= valueStr %>"><%= value %></option>
			<%	
				}
			}// for 
			%>
			</select>
			
			<br>
			
			<span class="method-name"> <%= methodName %></span>
			
			<div class="snippet-detailed">
			<pre class="prettyprint" id="java"><%= codeSnippet %></pre>
			</div>
			
		</div>

</form>
		
</div>

</div>

</body>
</html>