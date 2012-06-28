package br.inf.pucrio.codesearcher;

public enum Feedback
{

	NONE
	{
		@Override
		public String getComboString()
		{
			return "---";
		}

		@Override
		public String getName()
		{
			return "Not Rated";
		}

		@Override
		public Float getBoostValue()
		{
			return 1.0f;
		}
	},
	FIVE
	{
		@Override
		public String getComboString()
		{
			return "Very Relevant";
		}

		@Override
		public Float getBoostValue()
		{
			return 50f;
		}
	},
	FOUR
	{
		@Override
		public String getComboString()
		{
			return "Relevant";
		}

		@Override
		public Float getBoostValue()
		{
			return 40f;
		}
	},
	THREE
	{
		@Override
		public String getComboString()
		{
			return "Indifferent";
		}

		@Override
		public Float getBoostValue()
		{
			return 30f;
		}
	},
	TWO
	{
		@Override
		public String getComboString()
		{
			return "Irrelevant";
		}

		@Override
		public Float getBoostValue()
		{
			return 0.001f;
		}
	},
	ONE
	{
		@Override
		public String getComboString()
		{
			return "Very Irrelevant";
		}

		@Override
		public Float getBoostValue()
		{
			return 0.000001f;
		}
	},
	;

	public abstract String getComboString();

	public String getName()
	{
		return getComboString();
	}

	public abstract Float getBoostValue();
}
