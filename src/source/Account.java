package source;

public abstract class Account
{
	private int acctNumber;
	private AccountType acctType;
	private double acctBalance;
	private double maxWithdrawal;
	private double monthlyFee;
	private int transactionCount = 0;
	private int freeTransactionCount;
	private double transactionFee;
	private double transactionFeeOwed;
	private double interestRate;
	private double totalOwed;
	private double totalGained;

	/**
	 * CONSTRUCTOR
	 * @param ACCT_TYPE
	 * @param acctBalance
	 * @param maxWithdrawal
	 * @param monthlyFee
	 * @param freeTransactionCount
	 * @param interestRate 
	 */
	public Account(int ACCT_TYPE, double acctBalance, double maxWithdrawal, double monthlyFee, int freeTransactionCount, double transactionFee, double interestRate)
	{
		this.setAcctNumber(); // Assigning account number
		this.setAcctType(ACCT_TYPE); // Assigning account type
		this.acctBalance = acctBalance;
		this.maxWithdrawal = maxWithdrawal;
		this.monthlyFee = monthlyFee;
		this.freeTransactionCount = freeTransactionCount;
		this.interestRate = interestRate;
		this.transactionFee = transactionFee;
		this.transactionFeeOwed = 0.0;
		this.totalGained = 0.0;
		this.totalOwed = 0.0;
	}
	
	/**
	 * retrieves account number from account support class
	 * and assigns it to the new account
	 */
	private void setAcctNumber()
	{
		this.acctNumber = Account_Support.getAcctNumber();
	}
	
	/**
	 * Sets account type
	 * @param type 
	 */
	private void setAcctType(int type)
	{
		if(type == 1)
			this.acctType = AccountType.CHEQUING;
		else if(type == 2)
			this.acctType = AccountType.SAVINGS;
		else if(type == 3)
			this.acctType = AccountType.CREDIT;
		else if(type == 4)
			this.acctType = AccountType.BUSINESS;
	}
	
	/**
	 * Adds a specified amount of currency to the account balance
	 * @param amount
	 * @return true if transaction was successful, false otherwise
	 */
	public boolean deposit(double amount)
	{
		if (amount > 0.0)
		{
			++this.transactionCount;
			return updateAcctBalance(amount);
		}
		else
		{
			return false;
		}
	}

	/**
	 * Withdraws a specified amount of currency from the account balance
	 * @param amount
	 * @return 
	 */
	public boolean withdraw(double amount)
	{
		if (acctBalance >= amount && amount <= maxWithdrawal && amount > 0)
		{
			amount *= -1; // Multiply by negative -1 to extract the amount of withdraw from account balance
			++this.transactionCount;
			return updateAcctBalance(amount);
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Updates the account balance with the specified amount
	 * @param amount
	 * @return
	 */
	public boolean updateAcctBalance(double amount)
	{
		this.acctBalance += amount;
		return true;
	}

	public boolean calculateInterest()
	{
		return updateAcctBalance(this.acctBalance * this.interestRate);
	}
	
	// MUTATORS
	public void setMaxWithdrawal(double maxWithdrawal)
	{
		this.maxWithdrawal = maxWithdrawal;
	}
	
	public void setMonthlyFee(double monthlyFee)
	{
		this.monthlyFee = monthlyFee;
	}
	
	public void setFreeTransactionCount(int freeTransactionCount)
	{
		this.freeTransactionCount = freeTransactionCount;
	}
	
	public void setTransactionFee(double transactionFee)
	{
		this.transactionFee = transactionFee;
	}
	
	public void setTransactionFeeOwed()
	{
		this.transactionFeeOwed += this.transactionFee;
	}
	
	public void setInterestRate(double interestRate)
	{
		this.interestRate = interestRate;
	}
	
	public void setTotalOwed(double amount)
	{
		this.totalOwed = amount;
	}
	
	// ACCESSORS
	public int getAcctNumber()
	{
		return this.acctNumber;
	}
	
	// this method will return the corresponding account type NUMBER(1,2,3 or 4)
	public int getAcctType()
	{
		return this.acctType.getAccountType();
	}
	
	// this method will return the corresponding account type DESCRIPTION
	public AccountType getAcctTypeDesc()
	{
		return this.acctType;
	}
	
	public double getAcctBalance()
	{
		return this.acctBalance;
	}
	
	public double getMaxWithdrawal()
	{
		return this.maxWithdrawal;
	}
	
	public double getMonthlyFee()
	{
		return this.monthlyFee;
	}

	public int getTransactionCount()
	{
		return this.transactionCount;
	}

	public int getFreeTransactionCount()
	{
		return this.freeTransactionCount;
	}
	
	public double getTransactionFee()
	{
		return this.transactionFee;
	}
	
	public double getTransactionFeeOwed()
	{
		return this.transactionFeeOwed;
	}
	
	public double getInterestRate()
	{
		return this.interestRate;
	}
	
	public abstract double getTotalOwed(); // this method will calculate all monthly fees, credit interest and transaction fees for every account
	public abstract double getTotalBalance(); // this method will calculate the total amount of money we have on balance for every account + our interest rate on those accounts

	@Override
	public String toString()
	{
		return String.format("Account# %3d | Type: %8s | Balance: %9.2f | Max. withdrawal: %7.2f |Transactions: %3d |",
				this.getAcctNumber(), this.getAcctTypeDesc(), this.getAcctBalance(), this.getMaxWithdrawal(), this.getTransactionCount());
	}
}
