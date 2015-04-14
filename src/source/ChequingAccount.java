package source;

public class ChequingAccount extends Account
{
	private final static int ACCT_TYPE = 1;
	private double overdraftLimit;
	private final static double INTEREST_RATE = 1.015; // Interest rate of 1.5% for every type of customer holding this account
	private final static double OVERDRAFT_INTEREST_RATE = 1.1295; // Overdraft interest rate of 12.95% for every type of customer holding this account
	
	public ChequingAccount(double acctBalance, double maxWithdrawal, double monthlyFee, int freeTransactionCount, double transactionFee, double overdraftLimit)
	{
		super(ACCT_TYPE, acctBalance, maxWithdrawal, monthlyFee, freeTransactionCount, transactionFee, INTEREST_RATE);
		this.overdraftLimit = overdraftLimit;
	}
	
	/**
	 * We override this method to incorporate transaction fee calculation functionality
	 * if our customer exceed the amount of allocated free transactions, we add transaction fee to
	 * transaction fee owed class attribute
	 * @param amount
	 * @return 
	 */
	
	@Override
	public boolean deposit(double amount)
	{
		
		if(this.getFreeTransactionCount() <= this.getTransactionCount() && this.getFreeTransactionCount() != -1)
		{
			this.setTransactionFeeOwed();
			return super.deposit(amount);
		}
		else
			return super.deposit(amount);
	}
	
	@Override
	public boolean withdraw(double amount)
	{
		if(this.getFreeTransactionCount() <= this.getTransactionCount() && this.getFreeTransactionCount() != -1)
		{
			this.setTransactionFeeOwed();
			return super.withdraw(amount);
		}
		else
			return super.withdraw(amount);
	}
	
	@Override
	public double getTotalOwed()
	{
		return this.getTransactionFeeOwed() + this.getMonthlyFee(); 
	}
	
	@Override
	public double getTotalBalance()
	{
		return this.getAcctBalance() * this.getInterestRate();
	}
	// ACCESSORS
	public double getOverdraftLimit()
	{
		return this.overdraftLimit;
	}
	
	// MUTATORS
	public void setOverdraftLimit(double overdraftLimit)
	{
		this.overdraftLimit = overdraftLimit;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + String.format("Transaction fee owed: %5.2f | Overdraft limit: %7.2f | Interest rate: %7.4f | Overdraft interest rate: %7.4f", this.getTransactionFeeOwed(), this.overdraftLimit, this.getInterestRate() - 1, OVERDRAFT_INTEREST_RATE - 1);
	}
}
