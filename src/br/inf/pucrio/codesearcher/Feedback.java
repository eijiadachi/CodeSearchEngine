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
		public Float getBoostValue()
		{
			return 1.0f;
		}
	},
	ONE
	{
		@Override
		public String getComboString()
		{
			return "1";
		}

		@Override
		public Float getBoostValue()
		{
			return 10f;
		}
	},
	TWO
	{
		@Override
		public String getComboString()
		{
			return "2";
		}

		@Override
		public Float getBoostValue()
		{
			return 20f;
		}
	},
	THREE
	{
		@Override
		public String getComboString()
		{
			return "3";
		}

		@Override
		public Float getBoostValue()
		{

			return 30f;
		}
	},
	FOUR
	{
		@Override
		public String getComboString()
		{
			return "4";
		}

		@Override
		public Float getBoostValue()
		{
			return 40f;
		}
	},
	FIVE
	{
		@Override
		public String getComboString()
		{
			return "5";
		}

		@Override
		public Float getBoostValue()
		{
			return 50f;
		}
	};

	public abstract String getComboString();

	public abstract Float getBoostValue();
}
