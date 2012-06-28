package br.inf.pucrio.codesearcher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;

import br.inf.pucrio.codesearcher.util.IndexUtil;

public abstract class AbstractCodeSearcherServlet extends HttpServlet
{

	private String indexPath;

	private void checkIndexPath()
	{
		if (indexPath == null)
		{
			throw new IllegalStateException(
					"Index path is not set. Check web.xml and see if it is set in the servlet context." );
		}
	}

	protected IndexReader openIndexReader()
	{
		checkIndexPath();

		IndexReader indexReader = IndexUtil.openIndexReader( indexPath );

		return indexReader;
	}

	protected IndexWriter openIndexWriter()
	{
		checkIndexPath();

		IndexWriter indexWriter = IndexUtil.openIndexWriter( indexPath );

		return indexWriter;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException
	{
		ServletContext context = getServletContext();

		String parameter = context.getInitParameter( "indexPath" );

		indexPath = parameter;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			performAction( request, response );
		}
		catch (final Throwable e)
		{
			e.printStackTrace();

			request.setAttribute( "exception", e );

			RequestDispatcher dispatcher = request.getRequestDispatcher( "error.jsp" );

			dispatcher.forward( request, response );
		}
	}

	protected abstract void performAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost( request, response );
	}
}
