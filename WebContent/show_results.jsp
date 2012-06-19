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

<title>Result</title>

</head>



<body onload="prettyPrint()">

<div id="result-content">

<form action="Updater.update">

<% 
	final List<Document> documents = (List<Document>) request.getAttribute( "documents" );
	for( final Document document : documents )
	{
		final String methodName = document.get( "methodName");
		final String codeSnippet = document.get( "snippet" );
		final String docId = document.get( "docId" );
		final String feedback = document.get( "feedback" );
		final Feedback documentCurrentFeedback = Feedback.valueOf( feedback );
		%>
		
		<input name="docId" type="hidden" value="<%= docId %>" />
		
		<div class="result-content">
			
			<span class="result-header">Document # <%= docId %></span>
			
			<select name="feedback">
			
			<%
							Feedback[] feedbackOptions = Feedback.values();
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
			
			<div class="snippet">
			<pre class="prettyprint" id="java"><%= codeSnippet %></pre>
			</div>
			
		</div>
		<% 
	}
%>

	<input type="submit" value="Send feedback"/>

</form>

<script type="text/javascript">
     
</script>

</div>
</body>
</html>