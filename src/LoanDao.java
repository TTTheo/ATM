import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoanDao implements Dao<Loan> {
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
	public boolean insert(Loan loan) {
		// TODO Auto-generated method stub
		return true;
	}
	public boolean insert(String username, Loan loan) {
		connect() ;
		try {
			String query = "INSERT INTO [Loan] VALUES(\"" + username + "\","  + loan.getIntesest() + "," + loan.getLoanLength() + ",\"" + loan.getCollateral() + "\"," + loan.getLoan().getMoney() + ")";  //need to be completed
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
	public boolean delete(String type) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean update(Loan loan) {
		// TODO Auto-generated method stub
		// will add it if user info can be changed
		return true;
	}

	@Override
	public Loan select(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Loan> selectAll(String username) {
		// TODO Auto-generated method stub
		ArrayList<Loan> loans = new ArrayList<>();

		connect();
		try {
			String query = "SELECT * FROM [Loan] where username = \"" + username + "\"";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Loan loan = new Loan(new Currency("", rs.getDouble("amount")), rs.getDouble("interest"), rs.getInt("loanlength"), rs.getString("collateral")) ;
				loans.add(loan) ;
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} finally {
			close();
		}
		return loans ;
	}
}
