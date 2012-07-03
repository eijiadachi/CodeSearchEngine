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

<%
	final Object query = request.getAttribute( "query" );
	final String titleMsg = String.format( "%s - Pesquisa Coode", query );
%>

<title><%= titleMsg %></title>
</head>




<body onload="prettyPrint()">

<div class="wait-div"></div>

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
			<input type="text" name="query" size="150" style="background-color: white;"></input>
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
	final List<Document> documents = (List<Document>) request.getAttribute( "documents" );

	if( documents.isEmpty() )
	{
	%>
		<p>
		Your query <span class="query">"<%= query %>"</span> did not match any document.
		</p>
		<p>
		Suggestions:
		<ul>
			<li>Make sure all keywords are spelled correctly.</li>
			<li>Try different keywords.</li>
			<li>Try more generic keywords.</li>
			<li>Try using less keywords.</li>			
		</ul>
		</p>
		<p>
		Please go to the <span style="text-align: right;"><a href="help.jsp">Help</a></span> section if you still have any doubt about the queries syntax.
		</p>
	<%
	}
	else
	{
	%>
		<span class="result"> Resultados para:</span> <span class="query"><%= query %></span>
	<%
		for( final Document document : documents )
		{
			final String methodName = document.get( "methodName");
			final String codeSnippet = document.get( "snippet" );
			final String docId = document.get( "docId" );
			final String feedback = document.get( "feedback" );
			final Feedback documentCurrentFeedback = Feedback.valueOf( feedback );
	%>
	<form id="show-results-form" action="Updater.update">		
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
			
			<span class="method-name"> <a title="Click here to see this method in more detail." href="<%= String.format("Viewer.detail?docId=%s", docId) %>"><%= methodName %></a></span>
			
			<div class="snippet">
			<pre class="prettyprint" id="java"><%= codeSnippet %></pre>
			</div>
			
		</div>
		<% 
		}// for
	}// else 
	%>

</form>
</div>

</div>

</body>
</html>