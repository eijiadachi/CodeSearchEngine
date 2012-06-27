package br.inf.pucrio.codesearcher.util;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.MultiTermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public final class IndexUtil
{
	private IndexUtil()
	{
		// avoids instantiation of this utility class
		super();
	}

	protected static final String path = "/Users/Eiji/git/CodeSearchEngine/index";

	public static IndexReader openIndexReader()
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

	public static final Version LUCENE_VERSION = Version.LUCENE_33;

	public static IndexWriter openIndexWriter()
	{
		try
		{

			IndexWriter writer = new IndexWriter( FSDirectory.open( new File( path ) ), new IndexWriterConfig(
					LUCENE_VERSION, new WhitespaceAnalyzer( LUCENE_VERSION ) ) );

			return writer;
		}
		catch (IOException e)
		{
			String str = String.format( "Could not open index at path: %s", path );

			throw new IllegalStateException( str, e );
		}

	}

	public static QueryParser buildQueryParser()
	{
		QueryParser parser = new QueryParser( LUCENE_VERSION, "uses", new WhitespaceAnalyzer( LUCENE_VERSION ) );

		parser.setAllowLeadingWildcard( true );
		parser.setLowercaseExpandedTerms( false );
		parser.setMultiTermRewriteMethod( MultiTermQuery.SCORING_BOOLEAN_QUERY_REWRITE );

		return parser;
	}

}
