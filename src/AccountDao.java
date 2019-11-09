import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class AccountDao implements Dao<CheckandSave>{

	private Connection conn = null ;
	@Override
	public void connect() {
		// TODO Auto-generated method stub
	
    // auto close connection
    try {
    	conn =  DriverManager.getConnection(url) ;
        if (conn != null) {
            System.out.println("Connected to the database!");
        } else {
            System.out.println("Failed to make connection!");
        }

    } catch (SQLException e) {
        System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
    }
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		try {
			conn.close();
			System.out.println("Connection closed!");
		} catch (SQLException e) {
			System.out.println("Failed to close connection!");
		}
	}

	@Override
	public boolean insert(CheckandSave cs) {
		// TODO Auto-generated method stub
		//nothing to do
		return true;
	}
	
	public boolean insert(CheckandSave cs,String username) {
		// TODO Auto-generated method stub
		Balance balance = cs.getBalance() ;
		int type = -1 ;
		if (cs instanceof Checking) {
			type = 0 ;
			
		}
		if(cs instanceof Saving) {
			type = 1 ;
		}
		connect() ;
		try {
			
			String query = "INSERT INTO Account VALUES(\"" + username + "\",\"" + cs.getMoneypassword() + "\",\"" + cs.getAccountNumber() + "\"," + type + ")" ;
			String query2 = "INSERT INTO Balance VALUES(\"" + cs.getAccountNumber() + "\"," + balance.getDollar().getMoney() + "," + balance.getRMB().getMoney() + "," + balance.getEuro().getMoney() + ")" ;
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			st.executeUpdate(query2) ;
			
		}catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			close() ;
			return false ;
		}
		close() ;
		return true;
	}
	

	@Override
	public boolean delete(String accountnumber) {
		// TODO Auto-generated method stub
		connect() ;
		try {
			String query = "DELETE FROM Account where accountnumber = \"" + accountnumber + "\"" ;
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			
		}catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			close() ;
			return false ;
		}
		close() ;
		return true;
	}

	@Override
	public boolean update(CheckandSave cs) {
		
		// TODO Auto-generated method stub
		Balance balance = cs.getBalance() ;
		connect() ;
		try {
			String query = "UPDATE Balance SET Dollar = " + balance.getDollar().getMoney() + "," + "RMB = " + balance.getRMB().getMoney() + "," + "Euro = " + balance.getEuro().getMoney() + " WHERE accountnumber =\"" + cs.getAccountNumber() + "\"" ;
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			
		}catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			close() ;
			return false ;
		}
		close() ;
		return true;
	}

	@Override
	public CheckandSave select(String accountnumber) {
		// TODO Auto-generated method stub
		CheckandSave cs = null ;
		connect() ;
		try {
			String query = "SELECT name, password, phone, username, accountnumber, moneypassword, Type, Dollar,RMB, Euro FROM User NATURAL JOIN Account NATURAL JOIN Balance WHERE accountnumber = \"" + accountnumber + "\"" ;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				int type = rs.getInt("type") ;
				Balance balance = new Balance() ;
				balance.setDollar(new Currency("Dollar",rs.getDouble("Dollar")));
				balance.setRMB(new Currency("RMB", rs.getDouble("RMB")));
				balance.setEuro(new Currency("Euro", rs.getDouble("Euro")));
				if(type == 0) {
					cs = new Checking(rs.getString("accountnumber"), rs.getString("moneypassword"), balance) ;
				}
				if(type == 1) {
					cs = new Saving(rs.getString("accountnumber"), rs.getString("moneypassword"), balance) ;
				}
			}
		}catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}finally {
			close() ;
		}
		
		return cs;
	}
	
	public CheckandSave selectSaving(String username) {
		// TODO Auto-generated method stub
		CheckandSave cs = null ;
		connect() ;
		try {
			String query = "SELECT name, password, phone, username, accountnumber, moneypassword, Type, Dollar,RMB, Euro FROM User NATURAL JOIN Account NATURAL JOIN Balance WHERE username = \"" + username + "\""+"and type = \"" + 1 + "\"";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				int type = rs.getInt("Type") ;
				User account = new User(rs.getString("name"),rs.getString("username"), rs.getString("password"), rs.getString("phone")) ;
				Balance balance = new Balance() ;
				balance.setDollar(new Currency("Dollar",rs.getDouble("Dollar")));
				balance.setRMB(new Currency("RMB", rs.getDouble("RMB")));
				balance.setEuro(new Currency("Euro", rs.getDouble("Euro")));
				if(type == 0) {
					cs = new Checking(rs.getString("accountnumber"), rs.getString("moneypassword"), balance) ;
				}
				if(type == 1) {
					cs = new Saving(rs.getString("accountnumber"), rs.getString("moneypassword"), balance) ;
				}
			}
		}catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}finally {
			close() ;
		}
		
		return cs;
	}
	
	public CheckandSave selectChecking(String username) {
		// TODO Auto-generated method stub
		CheckandSave cs = null ;
		connect() ;
		try {
			String query = "SELECT name, password, phone, username, accountnumber, moneypassword, Type, Dollar,RMB, Euro FROM User NATURAL JOIN Account NATURAL JOIN Balance WHERE username = \"" + username + "\""+"and type = \"" + 2 + "\"";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				int type = rs.getInt("Type") ;
				User account = new User(rs.getString("name"),rs.getString("username"), rs.getString("password"), rs.getString("phone")) ;
				Balance balance = new Balance() ;
				balance.setDollar(new Currency("Dollar",rs.getDouble("Dollar")));
				balance.setRMB(new Currency("RMB", rs.getDouble("RMB")));
				balance.setEuro(new Currency("Euro", rs.getDouble("Euro")));
				if(type == 0) {
					cs = new Checking(rs.getString("accountnumber"), rs.getString("moneypassword"), balance) ;
				}
				if(type == 1) {
					cs = new Saving(rs.getString("accountnumber"), rs.getString("moneypassword"), balance) ;
				}
			}
		}catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}finally {
			close() ;
		}
		
		return cs;
	}
	
	public List<CheckandSave> selectAllSaving() {
		// TODO Auto-generated method stub
		List<CheckandSave> cs = null ;
		connect() ;
		try {
			String query = "SELECT name, password, phone, username, accountnumber, moneypassword, Type, Dollar,RMB, Euro FROM User NATURAL JOIN Account NATURAL JOIN Balance" ;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				int type = rs.getInt("Type") ;
				User account = new User(rs.getString("name"),rs.getString("username"), rs.getString("password"), rs.getString("phone")) ;
				Balance balance = new Balance() ;
				balance.setDollar(new Currency("Dollar",rs.getDouble("Dollar")));
				balance.setRMB(new Currency("RMB", rs.getDouble("RMB")));
				balance.setEuro(new Currency("Euro", rs.getDouble("Euro")));
				if(type == 1) {
					CheckandSave cas = new Saving(rs.getString("accountnumber"), rs.getString("moneypassword"), balance) ;
					cs.add(cas);
				}
			}
		}catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}finally {
			close() ;
		}
		
		return cs;
	}

}
