
import java.awt.EventQueue;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Main {
	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	private static ArrayList<Manager> managers = new ArrayList<Manager>();
	private static ArrayList<Income> incomes = new ArrayList<Income>();
	private static ArrayList<Loan> loans = new ArrayList<Loan>();
	private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();

	/*
	 * public static void init(){ customers.add(new
	 * Customer("Bob","bob","00000","1234567892")); customers.add(new
	 * Customer("Mary","ma","00000","7894561230")); managers.add(new
	 * Manager("Lisa","lisa","11111","1452368959"));
	 * 
	 * customers.get(0).createChecking(new Checking("000000000001","123456",new
	 * Balance())); customers.get(0).addLoan(new Loan(new
	 * Currency("Dollar",100),(float)0.1,6,"John"));
	 * customers.get(0).addTransaction(new Transaction(new
	 * Currency("Dollar",100),new
	 * Date(),customers.get(0).getUser(),customers.get(1).getUser(),"000000000001",
	 * "000000000002")); customers.get(1).createChecking(new
	 * Checking("000000000002","123456",new Balance()));
	 * customers.get(0).createSaving(new Saving("000000000002","123456",new
	 * Balance())); customers.get(1).createSaving(new
	 * Saving("000000000003","123456",new Balance()));
	 * customers.get(0).getSaving().setBalance(new Balance());
	 * customers.get(0).getChecking().getBalance().setDollar(new
	 * Currency("Dollar",100));
	 * customers.get(0).getSaving().getBalance().setDollar(new
	 * Currency("Dollar",100));
	 * 
	 * for(int i=0;i<customers.size();i++){ //add loans' income into the whole
	 * incomes for(int j=0;j<customers.get(i).getLoans().size();j++){
	 * incomes.add(new
	 * Income(customers.get(i).getLoans().get(j).getLoanTotal(),"Loan")); } } }
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test() ;
//		 init();
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					// Conn con=new Conn();
//					// con.getConnection();
//
//					Login login = new Login();
//					login.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
	
	public static void test() {
//		UserDao testcase

		UserDao u = new UserDao();
		User user = u.select("zhjiang");
		System.out.println(user.getName() + " " + user.getPhone());

		// StockDao testcase
		StockDao s = new StockDao();
		Stock stock = s.select("Amazon");
		System.out.println(stock.getPrice());

//		// AccountDao testcase
		AccountDao a = new AccountDao();
		CheckandSave cs = a.select("1");
		
		System.out.println(cs.getMoneypassword() + " " + cs.getBalance().getDollar().getMark() + " "
				+ cs.getBalance().getDollar().getMoney());
		if (cs instanceof Checking) {
			System.out.println("YEAH");
		}
		a.delete("111111");
		CheckandSave newcs = new Checking("111111", "1111", new Balance());
		a.insert(newcs);

//		// TransactionDao testcase
		TransactionDao t = new TransactionDao();
//		t.delete("0");
		Transaction transaction = new Transaction(new Currency("Dollar", 100), new Date(), cs.getAccountNumber(),
				newcs.getAccountNumber(), cs.getAccountNumber());
//		t.insert(transaction) ;
		Transaction trans = t.select("1");
		System.out.println(trans);

		// CustomerStockDao testcase
		CustomerStockDao csDao = new CustomerStockDao();
//		csDao.delete("zhjiang") ;
//		csDao.delete("wx") ;
		csDao.insert("zhjiang", new CustomerStock(stock.getCompany(), stock.getPrice(), 12345)) ;
		csDao.insert("wx", new CustomerStock(stock.getCompany(), stock.getPrice(), 0)) ;

		
		ArrayList<CustomerStock> cstock = csDao.selectByCompany(stock.getCompany()) ;
		for(CustomerStock c : cstock) {
			System.out.println(c.getCompany() + " " + c.getNumofStock());
		}
		
		// LoanDao testcases
		LoanDao ld = new LoanDao() ;
//		ld.insert("zhjiang", new Loan(new Currency("", 0), 0.1, 0, "Soul")) ;
		Loan loan = ld.selectAll("zhjiang").get(0) ;
		System.out.println(loan.getCollateral() + " " + loan.getLoanLength());
		
		// IncomeDao testcases
		IncomeDao ic = new IncomeDao() ;
//		ic.insert(new Income(new Currency("Dollar", 100), "Dedication")) ;
		for(Income i : ic.selectAll()) {
			System.out.println(i.getType() + " " + i.getIncome().getMark() + " " + i.getIncome().getMoney());
		}
		
	}
}
