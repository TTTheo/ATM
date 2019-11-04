import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDao implements Dao<Transaction> {
	private Connection conn = null;

	@Override
	public void connect() {
		// TODO Auto-generated method stub

		// auto close connection
		try {
			conn = DriverManager.getConnection(url);
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
	public boolean insert(Transaction transaction) {
		// TODO Auto-generated method stub
		connect();
		try {
			String query = "INSERT INTO Transaction VALUES (\"" + transaction.getTransID() + "\", "
					+ transaction.getDate() + "," + "\"" + transaction.getSenAccount() + "\"," + "\""
					+ transaction.getRecieAccount() + "\",\"" + transaction.getTransaction().getMark() + "\","
					+ transaction.getTransaction().getMoney() + ")";
			Statement st = conn.createStatement();
			st.executeUpdate(query);

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			close();
			return false;
		}
		close();
		return true;
	}

	

	@Override
	public boolean delete(String transid) {
		// TODO Auto-generated method stub
		connect() ;
		try {
			String query = "DELETE FROM Transaction where transid = \"" + transid + "\"" ;
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
	public boolean update(Transaction t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Transaction select(String transid) {
		// TODO Auto-generated method stub
		Transaction transaction = null ;
		connect() ;
		try {
			String query = "SELECT * FROM Transaction where transid = \"" + transid + "\"" ;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				transaction = new Transaction(new Currency(rs.getString("currency"), rs.getDouble("amount")), rs.getDate("date"), null, null, rs.getString("sender"), rs.getString("receiver")) ;
			}
		}catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}finally {
			close() ;
		}
		
		return transaction;
	}

}
