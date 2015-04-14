//@misstiphany

package source;

public class BusinessAccount {
    
    
    private int busAcctNum;
    private double busAcctBalance;
    private final double MAX_WITHDRAWAL = 50000.00;
    private int transactionCount = 0;
    private double overdraftLimit = 10000;
    
    public BusinessAccount ()
    {
        this.setBusAcctNum(); //Assigns an acct Num.
        this.busAcctBalance = 0.00;//initializes the account balance to 0
               
    }
  
    public BusinessAccount(double busAcctBalance)
	{
		this.setBusAcctNum();
		this.busAcctBalance = busAcctBalance;
	}
    
    private void setBusAcctNum()
	{
		this.busAcctNum = Account_Support.getBusAcctNum();
	}
    
    	public boolean Deposit(double amount)
	{
		if (amount > 0.0)
		{
			++this.transactionCount;
			return updateBusAcctBalance(amount);
		}
		else
		{
			return false;
		}
	}
        public boolean Withdraw(double amount)
	{
		if (busAcctBalance >= amount && amount <= MAX_WITHDRAWAL && amount > 0 && amount <= overdraftLimit)
		{
			amount *= -1; 
			++this.transactionCount;
			return updateAcctBalance(amount);
		}
		else
		{
			return false;
		}
	}

           
	private boolean updateAcctBalance(double amount)
	{
		this.busAcctBalance += amount;
		return true;
	}

	
	public int getBusAcctNumber()
	{
		return this.busAcctNum;
	}

	public double getBusAcctBalance()
	{
		return this.busAcctBalance;
	}

	public int getAcctTransactionCount()
	{
		return this.transactionCount;
	}
        
        public boolean addTransaction()
	{
		if (transactionCount > 15)
		{
			//this is what I'm working on now.
		}
		else
		{
			return false;
		}
	}
           /*transaction charges *these are Tony's suggestions***
You need to have a method [addTransaction()] that looks at the current transaction 
count and compares it to the total transactions allowed. If the current count is greater than what is allowed,
you add one to transaction count and then deduct the transaction cost from the balance.
 */
	public double getMaxWithdrawal()
	{
		return MAX_WITHDRAWAL;
