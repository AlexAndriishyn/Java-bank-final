package source;

public class CreditAccount extends Account
{
	private final static int ACCT_TYPE = 3; // our account type is 3(credit)
	private static final double TRANSFER_FEE = 25.00; // Fee to be deducted 
	private final double REQUIRED_PAYMENT_COEF = 1.08; // 8% of the total owed
	private double creditAmount; // This variable holds the amount of credit given to a customer
	private double totalOwing; // Total of what is owed
	
	public CreditAccount(double acctBalance, double maxWithdrawal, double monthlyFee, int freeTransactionCount, double interestRate)
	{
		super(ACCT_TYPE, acctBalance, maxWithdrawal, monthlyFee, freeTransactionCount, interestRate);
		this.creditAmount = this.getAcctBalance();
		this.totalOwing = 0.0;
	}
	
	// CUSTOM METHODS
	/**
	 * Since this is a credit account,
	 * we have to keep track of the amount of money we owe to the bank
	 * Thus, we override our withdraw method and adjust the balance owing attribute
	 * every time a withdrawal operation is made.
	 * @param amount
	 * @return true if operation was successful, false otherwise
	 */
	@Override
	public boolean withdraw(double amount)
	{
		if(super.withdraw(amount))
		{
			this.totalOwing += amount * this.getInterestRate();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Here, when we deposit to credit account, we actually pay for what we owe,
	 * thus we have to update our total owing balance
	 * @param amount
	 * @return true if operation was successful, false otherwise
	 */
	@Override
	public boolean deposit(double amount)
	{
		if(super.deposit(amount))
		{
			this.totalOwing -= amount;
			return true;
		}
		else
			return false;
	}
	
	public void calculateAllFees()
	{
		this.getMonthlyFee();
	}
	
	
	// ACCESSORS
	public double getCreditAmount()
	{
		return this.creditAmount;
	}
	
	public double getTotalOwing()
	{
		return this.totalOwing;
	}
	// MUTATORS
	
	@Override
	public String toString()
	{
		return super.toString() + String.format("Credit amount: %20.2f | Balance owing: %15.2f | Interest rate: %8f", this.getCreditAmount(), this.getTotalOwing(), this.getInterestRate());
	}
}
