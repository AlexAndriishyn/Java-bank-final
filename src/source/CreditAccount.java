package source;

public class CreditAccount extends Account
{
	private final static int ACCT_TYPE = 3; // our account type is 3(credit)
	private static final double TRANSFER_FEE = 25.00; // Fee to be deducted on transfers
	private final double REQUIRED_PAYMENT_COEF = 0.08; // 8% of the total owed
	private double creditAmount; // This variable holds the amount of credit given to a customer
	private double creditOwing; // Total of what is owed
	private double minCreditPayment;
	
	public CreditAccount(double acctBalance, double maxWithdrawal, double monthlyFee, int freeTransactionCount, double transactionFee, double interestRate)
	{
		super(ACCT_TYPE, acctBalance, maxWithdrawal, monthlyFee, freeTransactionCount, transactionFee, interestRate);
		this.creditAmount = this.getAcctBalance();
		this.creditOwing = 0.0;
		this.minCreditPayment = 0.0;
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
			this.creditOwing += amount * this.getInterestRate();
			this.setTotalOwed(this.creditOwing);
			setMinCreditPayment();
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
			this.creditOwing -= amount;
			this.setTotalOwed(this.creditOwing);
			setMinCreditPayment();
			return true;
		}
		else
			return false;
	}
	
	
	// ACCESSORS
	public double getCreditAmount()
	{
		return this.creditAmount;
	}
	
	public double getCreditOwing()
	{
		return this.creditOwing;
	}
	
	@Override
	public double getTotalOwed()
	{
		return this.getCreditOwing() + this.getTransactionFeeOwed() + this.getMonthlyFee();
	}
	// MUTATORS
	
	/**
	 * This method determines the minimum amount of money that has to be payed monthly
	 */
	public void setMinCreditPayment()
	{
		this.minCreditPayment = this.REQUIRED_PAYMENT_COEF * this.creditOwing;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + String.format("Credit amount: %9.2f | Min credit to pay: %8.2f | Interest rate: %8.4f", this.creditOwing, this.minCreditPayment, this.getInterestRate());
	}
}
