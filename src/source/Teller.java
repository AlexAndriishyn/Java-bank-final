package source;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Teller {

    // arraylist of customers
    private static ArrayList<Customer> customerList = new ArrayList<>();
    private static Scanner keyboard;
    private static Customer tempCust;
    private static ArrayList<Integer> customerSearch = new ArrayList<Integer>();
    private static String input;
    private static CustomerType tempCustType;
    private static AccountType tempAcctType;
    private static Account tempAcct;
private static Account source;
private static Account destination;

    // menu driven interface
    public static void main(String[] args) {
        Teller teller = new Teller();
        keyboard = new Scanner(System.in);
        teller.mainMenu();
    }

    private void mainMenu() {
        try {
            System.out.println("-Main-----------");
            System.out.println("Input a command");
            System.out.println("0. Exit program");
            System.out.println("1. Create a new customer");
            System.out.println("2. Search and manage customer information");
            input = keyboard.next();
            while (Integer.parseInt(input) != 0) {
                switch (Integer.parseInt(input)) {
                    case 1:
                        createCustomer();
                        break;
                    case 2:
                        lookupCustomer();
                        break;
                    default:
                        System.out.println("Please enter a valid command");
                        break;
                }
                input = keyboard.next();
            }

        } catch (InputMismatchException | IndexOutOfBoundsException | NumberFormatException e) {

            System.out.println("Please enter a valid command");
            mainMenu();
        }

        System.out.println("Program ended");
        System.exit(0);
    }

    private void createCustomer() {

        System.out.println("-Customer Creation-----");

        // set up customer information
        System.out.println("Customer Type");
        System.out.println("1. Regular 2. Student 3. Senior 4. Business");
        int custType = keyboard.nextInt();

        tempCustType = CustomerType.values()[custType - 1];

        System.out.println("First Name:");
        String firstName = keyboard.next();
        System.out.println("Last Name:");
        String lastName = keyboard.next();
        System.out.println("Address:");
        keyboard.nextLine();
        String address = keyboard.nextLine();
        System.out.println("SIN:");
        String sin = keyboard.next();

        tempCust = new Customer(firstName, lastName, sin, address, tempCustType.getCustomerType());

        customerList.add(tempCust);
        addAccount(tempCustType);

        mainMenu();
    }

    private void addAccount(CustomerType custType) {
        System.out.println("What account would you like to make?");
        int index = 1;
        if (custType != CustomerType.STUDENT) {
            System.out.print(index + ". Chequing ");
            index++;
        }
        if (custType != CustomerType.BUSINESS) {
            System.out.print(index + ". Savings ");
            index++;
        }
        System.out.print(index + ". Credit ");
        index++;
        if (custType == CustomerType.BUSINESS) {
            System.out.print(index + ". Business");
            index++;
        }
        System.out.println("");
        int accChosen = keyboard.nextInt();
        if (custType == CustomerType.REGULAR) {
            if (accChosen == 1) {

                tempAcctType = AccountType.CHEQUING;
                tempAcct = new ChequingAccount(5000, 2000, 7.99, 25, 0.15, 500);

            } else if (accChosen == 2) {

                tempAcctType = AccountType.SAVINGS;
                tempAcct = new SavingsAccount(5000, 4.99, 10, 0.25);
            } else if (accChosen == 3) {

                tempAcctType = AccountType.CREDIT;
                tempAcct = new CreditAccount(5000, 1000, 7, -1, 0, 1.1495);
            } else {
                System.out.println("Invalid account");
                mainMenu();
            }
        } else if (tempCustType == CustomerType.STUDENT) {
            if (accChosen == 1) {

                tempAcctType = AccountType.SAVINGS;
                tempAcct = new SavingsAccount(5000, 0.0, -1, 0.0);
            } else if (accChosen == 2) {

                tempAcctType = AccountType.CREDIT;
                tempAcct = new CreditAccount(1000, 200, 5, -1, 0, 1.1495);
            } else {
                System.out.println("Invalid account");
                mainMenu();
            }
        } else if (custType == CustomerType.SENIOR) {
            if (accChosen == 1) {
                tempAcctType = AccountType.CHEQUING;
                tempAcct = new ChequingAccount(5000, 2000, 0.0, -1, 0.0, 500);

            } else if (accChosen == 2) {

                tempAcctType = AccountType.SAVINGS;
                tempAcct = new SavingsAccount(5000, 0.0, -1, 0.0);
            } else if (accChosen == 3) {

                tempAcctType = AccountType.CREDIT;
                tempAcct = new CreditAccount(3000, 500, 5, -1, 0, 1.0995);
            } else {
                System.out.println("Invalid account");
                mainMenu();
            }
        } else if (custType == CustomerType.BUSINESS) {
            if (accChosen == 1) {

                tempAcctType = AccountType.CHEQUING;
                tempAcct = new ChequingAccount(5000, 10000, 14.99, -1, 0.0, 500);
            } else if (accChosen == 2) {

                tempAcctType = AccountType.CREDIT;
                tempAcct = new CreditAccount(10000, 3000, 15, -1, 0, 1.1995);
            } else if (accChosen == 3) {

                tempAcctType = AccountType.BUSINESS;
                tempAcct = new CreditAccount(5000, 1000, 8, -1, 0, 1.1495);
            } else {
                System.out.println("Invalid account");
                mainMenu();
            }
        }

        //create the acount
        if (tempCust.createAcct(tempAcct)) {
            System.out.println("Account creation successful.");
        } else {
            System.out.println("Customer already has max limit of accounts.");
        }

    }

    private void lookupCustomer() {

        System.out.println("-Listing Customers------");
        if (customerList.isEmpty()) {
            System.out.println("There are no customers in your application.");
        } else {
            displayCustomerList();
            Customer foundCust = getManageCustomer();
            if (foundCust != null) {
                tempCust = foundCust;
                manageCustomer();
            } else {
                System.out.println("No matching customer found");
            }
        }
        mainMenu();
    }

    private void displayCustomerList() {

        int i = 1;
        for (Customer tempCust : customerList) {
            System.out.println("ID " + i + " " + tempCust.toString());
            i++;
        }

    }

    private Customer getManageCustomer() {
        System.out.println("Please enter the customer's ID");
        int id = keyboard.nextInt();
        return customerList.get(id - 1);

    }

    private void manageCustomer() {
        System.out.println("-Manage " + tempCust.getFirstName() + " " + tempCust.getLastName() + "------");
        // cust.displayCustomerInfo();
        // cust.displayAccountInfo();
        System.out.println("Input a command");
        System.out.println("0. Back to main");
        System.out.println("1. View balance");
        System.out.println("2. Deposit into an account");
        System.out.println("3. Withdraw from an account");
	System.out.println("4. Transfer money");
        System.out.println("5. Add new account");
        System.out.println("6. Edit customer information");
        String input = keyboard.next();
        while (Integer.parseInt(input) != 0) {
            switch (Integer.parseInt(input)) {
                case 1:
                    getAllAccount();
                    manageCustomer();
                    break;
                case 2:
                    chooseAccount(TransactionType.DEPOSIT);
                    break;
                case 3:
                    chooseAccount(TransactionType.WITHDRAWAL);
                    break;
		case 4:
			chooseAccount(TransactionType.TRANSFER);
			break;
                case 5:
                    System.out.println(tempCust.getCustType());
                    addAccount(tempCust.getCustType());
                    manageCustomer();
                    break;
                case 6:
                    editInformation();
                    break;
                default:
                    System.out.println("Please enter a valid command");
                    manageCustomer();
                    break;
            }
            input = keyboard.next();
        }
        mainMenu();
    }

    public void getAllAccount() {
        String accBalance = "";
        String accType = "";
        System.out.println("-Accounts in " + tempCust.getFirstName() + " " + tempCust.getLastName() + "----");

        for (Account tempAccount : tempCust.getAcctList()) 
	{
            // tempAcctType = AccountType.values()[tempAccount.getAcctType() - 1];
            // accBalance += String.format("%s %20s",
            // tempAcctType.toString(), Double.toString(tempAccount.getAcctBalance()));
            System.out.println(tempAccount);
	}
	System.out.printf("Total owed: %.2f%n", tempCust.getTotalOwed());
	System.out.printf("Total balance: %.2f%n", tempCust.getTotalBalance());
    }

    private void chooseAccount(TransactionType transType) {

	int acctChoose = -1;
	double amount = 0.0;
        // Display account info
        // cust.displayAccountInfo();
        getAllAccount();
        
        if (transType == TransactionType.DEPOSIT) 
	{
		System.out.println("Choose account for your " + transType);
		acctChoose = keyboard.nextInt();
		tempAcct = tempCust.getAcctList().get(acctChoose - 1);
		System.out.println("Amount of your " + transType);
		amount = keyboard.nextDouble();
		if(tempAcct.deposit(amount))
		{
			System.out.println("Transaction successful");
		}
		else
		{
			System.out.println("Not enough money/maximum withdrawal amount exceeded");
		}
	} 
	else if (transType == TransactionType.WITHDRAWAL) 
	{
		System.out.println("Choose account for your " + transType);
		acctChoose = keyboard.nextInt();
		tempAcct = tempCust.getAcctList().get(acctChoose - 1);
		System.out.println("Amount of your " + transType);
		amount = keyboard.nextDouble();
		if(tempAcct.withdraw(amount))
		{
			System.out.println("Transaction successful");
		}
		else
		{
			System.out.println("Not enough money/maximum withdrawal amount exceeded");
		}
	} 
	else if (transType == TransactionType.TRANSFER) 
	{
		System.out.println("Choose a source account for your " + transType);
		acctChoose = keyboard.nextInt();
		tempAcct = tempCust.getAcctList().get(acctChoose - 1);
		System.out.println("Amount of your " + transType);
		amount = keyboard.nextDouble();
		if(tempAcct.withdraw(amount)) // Here, we check for the success of withdrawal operation, if `ok`, we can proceed with transfer operation
		{
			System.out.println("Choose a destination account for your " + transType);
			acctChoose = keyboard.nextInt();
			tempAcct = tempCust.getAcctList().get(acctChoose - 1);
			tempAcct.deposit(amount);
			System.out.println("Transaction successful");
		}
		else
		{
			System.out.println("Not enough money/maximum withdrawal amount exceeded");
		}
	}
        manageCustomer();
    }

    private void editInformation() {
        System.out.println("-Edit Information of " + tempCust.getFirstName() + " " + tempCust.getLastName() + "------");
        System.out.println(tempCust);

        // display account info
        // cust.displayCustomerInfo();
        System.out.println("Input a command");
        System.out.println("0. Back to manage customer");
        System.out.println("1. Edit First Name");
        System.out.println("2. Edit Last Name");
        System.out.println("3. Edit Address");
        System.out.println("4. Edit SIN");

        String input = keyboard.next();
        while (Integer.parseInt(input) != 0) {
            switch (Integer.parseInt(input)) {
                case 1:
                    //edit first names  
                    System.out.println("New first name:");
                    String firstName = keyboard.next();
                    tempCust.setFirstName(firstName);
		System.out.println("...ok");
		System.out.println("What else do you want to edit?");
                    break;
                case 2:
                    //edit last name
                    System.out.println("New last name:");
                    String lastName = keyboard.next();
                    tempCust.setLastName(lastName);
		System.out.println("...ok");
		System.out.println("What else do you want to edit?");
                    break;
                case 3:
                    //edit address
                    System.out.println("New address:");
                    keyboard.nextLine();
                    String address = keyboard.nextLine();
                    tempCust.setAddress(address);
		System.out.println("...ok");
		System.out.println("What else do you want to edit?");
                    break;
                case 4:
                    // edit SIN
                    System.out.println("New SIN:");
                    String sin = keyboard.next();
                    tempCust.setSin(sin);
		System.out.println("...ok");
		System.out.println("What else do you want to edit?");
                    break;
                default:
                    System.out.println("Please enter a valid command");
                    break;
            }
            input = keyboard.next();
        }
        manageCustomer();
    }
}