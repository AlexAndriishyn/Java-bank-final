package source;

public enum TransactionType
{
	WITHDRAWAL(1),
         DEPOSIT(2),
	TRANSFER(3);
	
	private int type;
		
	private TransactionType(int type)
	{
		this.type = type;
	}
		
	public int getTransactionType ()
	{
		return this.type;
	}
}
