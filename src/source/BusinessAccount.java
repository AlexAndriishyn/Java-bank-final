package source;

public class BusinessAccount extends Account {

	private final static int ACCT_TYPE = 4;
	private double busAcctBalance;
	private final static double MAX_WITHDRAWAL = 50000.00;
	private double overdraftLimit = 10000;
	private final static double MONTHLY_FEE = 25.0;
	private final double interestRate;

	public BusinessAccount(double acctBalance, int freeTransactionCount, double transactionFee)
	{
		super(ACCT_TYPE, acctBalance, MAX_WITHDRAWAL, MONTHLY_FEE, freeTransactionCount, transactionFee);
	}
}    



/*
public BusinessAccount()
	{
		double transactionFee = 0.00;//initializing to zero
                int transactionCount = 0;
                
		//transaction fee schedule
		if(transactionCount <= 15)//if under 15 transactions, no charge
			transactionFee = transactionFee + 0.00;//and no fee
                else if(transactionCount >= 15 && (depositAmount >= 1.00 && depositAmount <= 999.00))//if transactions are greater than 15 and between 1-999
			transactionFee = transactionFee + 0.50;//charge is 0.50
		else if(transactionCount >= 15 && (depositAmount >= 1000.00 && depositAmount <= 4999.00))//if transactions are greater than 15 and between 1000-4999
			transactionFee = transactionFee + 1.50;//charge is 1.50
		else if(transactionCount >= 15 && (depositAmount >= 5000.00 && depositAmount <= 9999.00))//if transactions are greater than 15 and between 5000-9999
			transactionFee = transactionFee + 3.50;//charge is 3.50
		else if(transactionCount >= 15 && (depositAmount >= 10000.00 && depositAmount <= 49999.00))//if transactions are greater than 15 and between 10000-49999
			transactionFee = transactionFee + 5.50;//charge is 5.50
                
                transactionCount = transactionCount + 1;
                ++transactionFee;
                
		this.updateBusAcctBalance(depositAmount);//not too sure how to add the fee to the balance
                
        
		
                if(depositAmount  >= 1.00 && depositAmount <= 2999.00)//interest rate calculated on deposits between $1-2999
			interestRate = depositAmount * 0.01;
                else if(depositAmount  >= 3000.00 && depositAmount <= 4999.00)//interest rate calculated on deposits between $3000-4999
			interestRate = depositAmount * 0.02;
		else if(depositAmount  >= 5000.00 && depositAmount <= 9999.00)//interest rate calculated on deposits between $5000-9999
			interestRate = depositAmount * 0.03;
		else if(depositAmount  >= 10000.00 && depositAmount <= 39999.00)//interest rate calculated on deposits between $10000-39999
			interestRate = depositAmount * 0.04;
		else if(depositAmount  >= 40000.00 && depositAmount <= 99999.00)//interest rate calculated on deposits between $40000-99999
			interestRate = depositAmount * 0.05;
                else if(depositAmount  >= 100000.00)//interest rate calculated on deposits beyond 100000
			interestRate = depositAmount * 0.06;
                
                if (depositAmount >= 100000)
                    MONTHLY_FEE = 0.00;
*/
