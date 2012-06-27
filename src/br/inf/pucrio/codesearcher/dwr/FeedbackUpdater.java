package br.inf.pucrio.codesearcher.dwr;

import org.apache.lucene.index.IndexReader;

import br.inf.pucrio.codesearcher.util.IndexUtil;

public class FeedbackUpdater
{
	public boolean updateFeedback(final String docId, final String newFeedback)
	{
		IndexReader reader = IndexUtil.openIndexReader();

		return false;
	}
}
