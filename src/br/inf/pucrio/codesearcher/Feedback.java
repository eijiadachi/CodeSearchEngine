package br.inf.pucrio.codesearcher;

public enum Feedback
{
	NONE
	{
		private float boostValue = 1.0f;

		@Override
		public Float getBoostValue()
		{
			return boostValue;
		}

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
		public void setBoostValue(Float boostValue)
		{
			this.boostValue = boostValue;
		}

	},
	FIVE
	{
		private float boostValue = 50.0f;

		@Override
		public Float getBoostValue()
		{
			return boostValue;
		}

		@Override
		public String getComboString()
		{
			return "Very Relevant";
		}

		@Override
		public void setBoostValue(Float boostValue)
		{
			this.boostValue = boostValue;
		}
	},
	FOUR
	{
		private float boostValue = 40.0f;

		@Override
		public Float getBoostValue()
		{
			return boostValue;
		}

		@Override
		public String getComboString()
		{
			return "Relevant";
		}

		@Override
		public void setBoostValue(Float boostValue)
		{
			this.boostValue = boostValue;
		}
	},

	THREE
	{
		private float boostValue = 30.0f;

		@Override
		public Float getBoostValue()
		{
			return boostValue;
		}

		@Override
		public String getComboString()
		{
			return "Indifferent";
		}

		@Override
		public void setBoostValue(Float boostValue)
		{
			this.boostValue = boostValue;
		}
	},
	TWO
	{
		private float boostValue = 0.001f;

		@Override
		public Float getBoostValue()
		{
			return boostValue;
		}

		@Override
		public String getComboString()
		{
			return "Irrelevant";
		}

		@Override
		public void setBoostValue(Float boostValue)
		{
			this.boostValue = boostValue;
		}
	},
	ONE
	{
		private float boostValue = 0.00001f;

		@Override
		public Float getBoostValue()
		{
			return boostValue;
		}

		@Override
		public String getComboString()
		{
			return "Very Irrelevant";
		}

		@Override
		public void setBoostValue(Float boostValue)
		{
			this.boostValue = boostValue;
		}
	},
	;

	public abstract Float getBoostValue();

	public abstract String getComboString();

	public String getName()
	{
		return getComboString();
	}

	public abstract void setBoostValue(final Float boostValue);
}
