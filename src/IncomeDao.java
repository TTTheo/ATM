import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IncomeDao implements Dao<Income> {

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
	public boolean insert(Income income) {
		// TODO Auto-generated method stub
		connect();
		try {
			String query = "INSERT INTO [Income] VALUES(\"" + income.getIncome().getMark() + "\"," + income.getIncome().getMoney() + ",\"" + income.getType() + "\")"; // need to be completed
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
	public boolean delete(String type) {
		// TODO Auto-generated method stub
		connect();

		close();
		return true;
	}

	@Override
	public boolean update(Income income) {
		// TODO Auto-generated method stub
		// will add it if user info can be changed
		return true;
	}

	@Override
	public Income select(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Income> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Income> incomes = new ArrayList<>();
		connect();
		try {
			String query = "SELECT * FROM [Income]" ;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Income income = new Income(new Currency(rs.getString("currency"), rs.getDouble("amount")), rs.getString("type")) ;
				incomes.add(income) ;
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} finally {
			close();
		}
		return incomes;
	}
}
