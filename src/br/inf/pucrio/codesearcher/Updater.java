package br.inf.pucrio.codesearcher;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

import br.inf.pucrio.codesearcher.util.IndexUtil;

public class Updater extends AbstractCodeSearcherServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IndexWriter writer = IndexUtil.openIndexWriter();

		final String[] docIdArray = request.getParameterValues( "docId" );
		final String[] feedbackArray = request.getParameterValues( "feedback" );

		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		Map<String, Document> map = (Map<String, Document>) session.getAttribute( "documentsMap" );

		int size = docIdArray.length;

		for (int i = 0; i < size; i++)
		{
			String docIdStr = docIdArray[i];

			Document document = map.get( docIdStr );

			String feedbackStr = feedbackArray[i];

			String currentFeedback = document.get( "feedback" );

			if (currentFeedback.equals( feedbackStr ))
			{
				continue;
			}

			Feedback feedback = Feedback.valueOf( feedbackStr );

			Float boost = feedback.getBoostValue();

			document.setBoost( boost );

			document.removeField( "feedback" );

			Field feedbackField = new Field( "feedback", feedbackStr, Store.YES, Index.NOT_ANALYZED );
			feedbackField.setBoost( boost );

			document.add( feedbackField );

			Term term = new Term( "docId", docIdStr );

			writer.updateDocument( term, document );

			writer.commit();
		}

		writer.close();

		RequestDispatcher dispatcher = request.getRequestDispatcher( "index.jsp" );
		dispatcher.forward( request, response );
	}
}
