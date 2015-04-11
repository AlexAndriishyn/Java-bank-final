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
        private int transactionFee;
	private double interestRate;

	/**
	 * Constructor
	 * @param accountType
	 * @param customerType 
	 */
	public Account(int accountType, double maxWithdrawal, double monthlyFee, double interestRate)
	{
		this.setAcctNumber(); // Assigning account number
		this.setAcctType(accountType); // Assigning account type
		this.acctBalance = 0.00;
		this.maxWithdrawal = maxWithdrawal;
		this.monthlyFee = monthlyFee;
		this.interestRate = interestRate;
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
        
	public boolean Deposit(double amount)
	{
		if (amount > 0.0)
		{
			updateTransactionCount();
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
	 */
	public boolean Withdraw(double amount)
	{
		if (acctBalance >= amount && amount <= maxWithdrawal && amount > 0)
		{
			amount *= -1; // Multiply by negative -1 to extract the amount of withdraw from account balance
			updateTransactionCount();
			return updateAcctBalance(amount);
		}
		else
		{
			return false;
		}
	}
        private void updateTransactionCount(){
            ++this.transactionCount;
            if(this.transactionCount > this.freeTransactionCount){
                this.acctBalance -=this.transactionFee;
            }
        }
	/**
	 * Updates the account balance with the specified amount
	 * @param amount
	 */
	private boolean updateAcctBalance(double amount)
	{
		this.acctBalance += amount;
		return true;
	}
	
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
	
	public void setInterestRate(double interestRate)
	{
		this.interestRate = interestRate;
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

	public int getAcctTransactionCount()
	{
		return this.transactionCount;
	}

	public int getFreeTransactionCount()
	{
		return this.freeTransactionCount;
	}
	
	public double getInterestRate()
	{
		return this.interestRate;
	}

	@Override
	public String toString()
	{
		return String.format("Account# %17d | Balance: %20.2f | Transactions: %11d |",
				this.getAcctNumber(), this.getAcctBalance(), this.getAcctTransactionCount());
	}
}