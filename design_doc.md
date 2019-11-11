# CS591 Bank ATM - Securities Group Assignment Design Document
Group Members: Yujing Chen, Zehui Jiang, Wenrui Lai, David Shen

## Class Design
* AddStockFrame.java
    * UI for manager to add a stock.
* Balance.java
    * This is the class o f balance in the account. The balance contains three currencies and it can change with the operations related to money. If there are other currencies should be included into the balance, they can also be added here.
* BalanceFrame.java
    * UI for customer to check the balance.
* BuyStockFrame.java
    * UI for customer to buy a stock.
* CheckandSave.java
    * This is super class of Checking and Saving. All the common properties of checking and saving are added here. In the future if they have more common properties, they can be added here.
* Checking.java
    * This class extends CheckandSave class. This is the class of checking account. It inherits the methods of super class and this class can have checking’s own properties. If the checking account have more its own properties, such properties can be added here.
* CheckupFrame.java
    * UI for manager to check up all customers or one specific customer.
* CloseAccountFrame.java
    * UI for customer to close accounts.
* Currency.java
    * This is the class of a single currency that can be stored in the bank. The currency should have its own mark and amount of money. It is a general class because it only have gets and sets methods.
* Customer.java
    * This is for the customer account. It extends User class. A customer now have a checking account, a saving account, loans, transactions and a investment account. If the customers have other properties, these properties can be added here.
* CustomerFrame.java
    * UI for customer to view the operations.
* CustomerStock.java
    * It is for customer's stock. It extends Stock class and have its own property numofStock. If customer's stock have more properties, they can be added here.
* DailyReportFrame.java
    * UI for manager to see daily report about transactions.
* DeleteStockFrame.java
    * UI for manager to delete a stock.
* DepositFrame.java
    * UI for customer to deposit.
* Income.java
    * This is about the income/charge of the bank. Every charge have its own type like loan and withdraw, and also amount of charge. The income should be a single class that it can show to manager more conveniently.
* IncomeFrame.java
    * UI for manager to check all incomes.
* InvestFrame.java
    * UI for customer to manage stocks.
* Investment.java
    * This is the class for customer's investment. It have list of stocks belong to the customer. If customer have other type of investment, they can be added here.
* Loan.java
    * This is the class of customers’ loan. The loan should have its own currency, interest, length of loan and collateral. And because the bank charge the interest of all loans, methods are added to calculate the amount of the interest of the loan according to its currency. If the loan have more properties, they can be added here.
* LoanFrame.java
    * UI for customer to loan.
* Login.java
    * Login UI of the program.
* Main.java
    * This is the entrance of the program.
* ManageStockFrame.java
    * UI for manager to manage stock market.
* Manager.java
    * This is the class of manager. It extends User class. And it is a general class. If the manager have more properties in the future, they can be added here.
* ManagerFrame.java
    * UI for manager to view operations.
* OpenAccount.java
    * UI of opening a new customer account.
* PaymentFrame.java
    * UI of checking the payment of the bank.
* Saving.java
    * This class extends CheckandSave class. This is the class of saving account. It inherits the methods of super class and this class can have saving’s own properties. If the saving account have more properties, such properties can be added here.
* SellStockFrame.java
    * UI for customer to sell stock.
* ShowInfo.java
    * UI for customer to see information after opening new an account.
* Stock.java
    * It is class for a single stock. A sigle stock have price and company name. The stock can be used anywhere being an independent class.
* StockMarketFrame.java
    * UI for customer to see stock market.
* Tool.java
    * It is a class implementing some help functions.
* Transaction.java
    * This is the class of a single transaction.  For customers, they have their own transactions, for managers, they need to see all the transactions. So it should be a single class. In this class, currency, date, the account of sender and receiver should be considered.
* TransactionFrame.java
    * UI for customer to do transactions.
* UpdatePriceFrame.java
    * UI for manager to update stocks' pices.
* User.java
    * This is for the user’s general account, including name, phone, username and password. If the account need more information, it can be put here. Any type of user can extend it.
* WithDrawFrame.java
    * UI for customer to withdraw.

## Database and DAO Class Design
We used a SQLite database to make data persistent and wrote data access object(DAO) classes to pass queries to database.
### ATM.db
Contains 9 tables:
* User
    * This table stores the basic information of a customer: username, name, password and phone number. Username is the primary key. 
* Manager
    * Same structure as User. Manager users has permission to manager window.
* Account
    * This table stores information of an account: account number, the username whom this account belongs to, account type and pin number. Account number is a primary key and username is a foreign key reference to username in User.
* Balance
    * This table stores amount of money of different currencies in an account. Account number the primary key and also foreign key reference to account number in Account.
* Transaction
    * This table stores all transaction histories: transaction ID, date of transaction, sender, receiver, currency type and transaction amount. Transaction ID is the primary key.
* Stock
    * This table stores all the stocks in stock market. There are two columns: company and price. Company is the primary key.
* CustomerStock
    * This table represent a stock owned by a customer. It consists of: username, company, price and share where username and company are the composite primary key because the combination of a customer and a stock is unique and is used to select a customer stock. And username, company and price are foreign keys reference to username in User, company and price in Stock.
* Income
    * This table stores all income for the bank: currency, amount and income type. It’s only accessible for manager uses.
* Loan
    * This table stores loan histories of customers: username, interest, length of loan, collateral and amount of loan. Username is a foreign key reference to username in User.
For all foreign keys, on update and delete it cascades. Because customer should be able to modify their information. And once the username or account number is modified, we need to make sure that all table has the same data. And for primary keys, on conflict it will rollback the operation that caused the conflict.

### DAO classes:
* Dao.java
    * An interface that all other Dao classes implement. It contains a final String url for JDBC purpose and 6 methods: connect, close, insert, select, update and delete. 
        * final String url = "jdbc:sqlite:src/ATM.db" ;
        * void connect() ;
        * void close() ;
        * boolean insert(T t) ;
        * boolean delete(String s) ;
        * boolean update(T t) ;
        * T select(String s) ;
    * It’s a generic interface which allows the operation on every object. 
* Dao classes that implement Dao interface :
    * For most of them, they follow the interface. But for specific classes like CustomerStockDao.java, where composite primary key is used, it’s not possible to select or delete with a single key word. So, select and delete method is overloaded with two-argument methods that allows us to pass in a combination of keywords.
        * AccountDao.java
        * CustomerStockDao.java
        * IncomeDao.java
        * InvestmentDao.java
        * LoanDao.java
        * ManagerDao.java
        * StockDao.java
        * UserDao.java
        * TransactionDao.java
    

## Starting Design and Explanation
We decided to use Yujing Chen's Bank ATM as our starting design. There were numerous reasons for choosing this design. 
(1) We felt that Yujing's design had the best UI out of all possible choices. Her ATM's UI was not only easy to use, but also felt the most fleshed out complete. Specifically, the handling of certain features such as currencies and bank accounts felt well developed and highly scalable. We felt that this would translate well into designing the UI for the stock market, which will potentially have to display many different stocks. 
(2) In addition to the UI, we also liked the decomposition of the Bank ATM's classes. Specifically, the Bank ATM seemed like it was decomposed into many independent and resuseable classes. In other words, the classes were weakly coupled. Having weakly coupled classes makes it easier to split up the work between us. Because the classes have minimal dependencies, we can each work on an independent component of the project without getting blocked, which ultimately results in better teamwork. 
To summarize, we chose this design because of the benefits of the UI and the object design. 

## Evaluation of Starting Design
Overall, we found that our choice of starting design met our expectations but had some roadblocks we were not expecting. One of the benefits of our starting design was that the UI and implementation was well fleshed out. However as a consequence, this also meant that the logic and code was more complex, and required more time to read and understand it. We also found that we had different opinions on specific implementation details and object structure. Overall, our design worked out as planned because... (will finish once project is finished)

