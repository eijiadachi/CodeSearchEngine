package br.inf.pucrio.codesearcher;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.lucene.document.Document;

public class Viewer extends AbstractIndexAccessServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String docId = request.getParameter( "docId" );

		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		Map<String, Document> map = (Map<String, Document>) session.getAttribute( "documentsMap" );

		Document document = map.get( docId );

		final String methodName = document.get( "methodName" );
		final String codeSnippet = document.get( "snippet" );
		final String feedback = document.get( "feedback" );

		request.setAttribute( "docId", docId );
		request.setAttribute( "methodName", methodName );
		request.setAttribute( "snippet", codeSnippet );
		request.setAttribute( "feedback", feedback );

		RequestDispatcher dispatcher = request.getRequestDispatcher( "detailed_view.jsp" );

		dispatcher.forward( request, response );
	}
}
