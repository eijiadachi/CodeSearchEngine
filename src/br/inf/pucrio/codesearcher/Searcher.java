package br.inf.pucrio.codesearcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import br.inf.pucrio.codesearcher.util.IndexUtil;

public class Searcher extends AbstractCodeSearcherServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void performAction(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		String queryStr = request.getParameter( "query" );

		final IndexReader reader = openIndexReader();

		final IndexSearcher searcher = new IndexSearcher( reader );

		QueryParser parser = IndexUtil.buildQueryParser();

		RequestDispatcher dispatcher = request.getRequestDispatcher( "show_results.jsp" );

		Query query;
		try
		{
			query = parser.parse( queryStr );

			TopDocs docs = searcher.search( query, 10 );

			ScoreDoc[] scoreDocs = docs.scoreDocs;

			List<Document> documents = new ArrayList<Document>();

			Map<String, Document> map = new TreeMap<String, Document>();

			for (ScoreDoc scoreDoc : scoreDocs)
			{
				final int docId = scoreDoc.doc;

				final Document document = reader.document( docId );

				documents.add( document );

				String id = document.get( "docId" );

				map.put( id, document );
			}

			request.setAttribute( "documents", documents );
			request.setAttribute( "query", query );

			HttpSession session = request.getSession();

			session.setAttribute( "documentsMap", map );
		}
		catch (Exception e)
		{
			request.setAttribute( "exception", e );

			dispatcher = request.getRequestDispatcher( "error.jsp" );
		}
		finally
		{
			dispatcher.forward( request, response );
		}
	}
}
