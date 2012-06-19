package br.inf.pucrio.codesearcher;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServlet;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public abstract class AbstractIndexAccessServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected final String path = "/Users/eijiadachi/git/CodeSearchEngine/index";

	protected IndexReader openIndexReader()
	{
		try
		{
			File file = new File( path );

			Directory index = FSDirectory.open( file );

			IndexReader reader = IndexReader.open( index );

			return reader;
		}
		catch (IOException e)
		{
			String str = String.format( "Could not open index at path: %s", path );

			throw new IllegalStateException( str, e );
		}
	}
}
