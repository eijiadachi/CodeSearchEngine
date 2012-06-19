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

import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiTermQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;

public class Searcher extends AbstractIndexAccessServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String queryStr = request.getParameter( "query" );

		final IndexReader reader = openIndexReader();

		final IndexSearcher searcher = new IndexSearcher( reader );

		QueryParser parser = new QueryParser( Version.LUCENE_33, "uses", new WhitespaceAnalyzer( Version.LUCENE_33 ) );

		parser.setAllowLeadingWildcard( true );
		parser.setLowercaseExpandedTerms( false );
		parser.setMultiTermRewriteMethod( MultiTermQuery.SCORING_BOOLEAN_QUERY_REWRITE );

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

				System.out.println( document.get( "docId" ) );
				System.out.println( searcher.explain( query, docId ) );

				documents.add( document );

				String id = document.get( "docId" );

				map.put( id, document );
			}

			request.setAttribute( "documents", documents );

			HttpSession session = request.getSession();

			session.setAttribute( "documentsMap", map );
		}
		catch (ParseException e)
		{
			dispatcher = request.getRequestDispatcher( "error.jsp" );
		}
		finally
		{
			dispatcher.forward( request, response );
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost( request, response );
	}

}
