package br.inf.pucrio.codesearcher;

import org.apache.lucene.document.Document;

public class DocumentTO
{

	private Integer id;

	private Document document;

	private Feedback feedback;

	public Document getDocument()
	{
		return document;
	}

	public void setDocument(Document document)
	{
		this.document = document;
	}

	public Feedback getFeedback()
	{
		return feedback;
	}

	public void setFeedback(Feedback feedback)
	{
		this.feedback = feedback;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

}
