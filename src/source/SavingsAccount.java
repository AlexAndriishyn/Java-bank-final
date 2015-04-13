package source;

public class SavingsAccount extends Account
       {
	private final static int ACCT_TYPE = 2;
	private final static double INTEREST_RATE = 0.04; // Interest rate of 4.0% for every type of customer holding this account
	private final static double MAX_WITHDRAWAL = 1000.00;

	public SavingsAccount (double acctBalance, double monthlyFee, int freeTransactionCount, double transactionFee)
	{
		super(ACCT_TYPE, acctBalance, MAX_WITHDRAWAL, monthlyFee, freeTransactionCount, transactionFee, INTEREST_RATE);
	}
	
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
	public String toString()
	{
		return super.toString() + String.format("| Transaction fee owed: %3.2f", this.getTransactionFeeOwed());
	}
}