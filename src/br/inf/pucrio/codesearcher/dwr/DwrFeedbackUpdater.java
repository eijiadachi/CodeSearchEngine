package br.inf.pucrio.codesearcher.dwr;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import br.inf.pucrio.codesearcher.Feedback;
import br.inf.pucrio.codesearcher.util.IndexUtil;

public class DwrFeedbackUpdater
{
	private HttpSession getSession()
	{
		WebContext context = WebContextFactory.get();

		HttpSession session = context.getSession();

		return session;
	}

	private String getIndexPath()
	{
		WebContext context = WebContextFactory.get();

		ServletContext servletContext = context.getServletContext();

		String indexPath = servletContext.getInitParameter( "indexPath" );

		return indexPath;
	}

	private Map<String, Document> getDocumentsMapFromSession()
	{
		HttpSession session = getSession();

		@SuppressWarnings("unchecked")
		Map<String, Document> map = (Map<String, Document>) session.getAttribute( "documentsMap" );

		return map;
	}

	public boolean updateFeedback(final String docId, final String newFeedback)
	{
		Map<String, Document> map = getDocumentsMapFromSession();

		Document document = map.get( docId );

		Feedback feedback = Feedback.valueOf( newFeedback );

		Float boost = feedback.getBoostValue();

		document.setBoost( boost );

		document.removeField( "feedback" );

		Field feedbackField = new Field( "feedback", newFeedback, Store.YES, Index.NOT_ANALYZED );
		feedbackField.setBoost( boost );

		document.add( feedbackField );

		Term term = new Term( "docId", docId );

		String indexPath = getIndexPath();

		IndexWriter writer = IndexUtil.openIndexWriter( indexPath );
		try
		{
			writer.updateDocument( term, document );

			writer.commit();

			writer.close();

			return true;
		}
		catch (IOException e)
		{
			return false;
		}

	}
}
