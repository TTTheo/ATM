import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CustomerStockDao implements Dao<CustomerStock>{
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
	public boolean insert(CustomerStock custock) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean insert(String username, CustomerStock custock) {
		// TODO Auto-generated method stub
		connect() ;
		try {
			String query = "INSERT INTO AccountStock VALUES(\"" + username + "\"," + "\"" + custock.getCompany() + "\"," + custock.getPrice() + "," + custock.getNumofStock() + ")";  //need to be completed
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
	public boolean delete(String username) {
		// TODO Auto-generated method stub
		connect() ;
		try {
			String query = "DELETE * FROM AccountStock where username = \"" + username + "\"" ;
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
	public boolean update(CustomerStock customerStock) {
		return true ;
	}
	@Override
	public CustomerStock select(String username) {
		return null ;
	}
	
	public CustomerStock select(String username, String company) {
		CustomerStock customerStock = null;

		connect();
		try {
			String query = "SELECT * FROM AccountStock where company = \"" + company + "\" and username = \"" + username + "\"";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				customerStock = new CustomerStock(company, rs.getDouble("company"), rs.getInt("share")) ;
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} finally {
			close();
		}
		return customerStock ;
	}
	
	public ArrayList<CustomerStock> selectByCompany(String company){
		ArrayList<CustomerStock> customerStocks = new ArrayList<>();

		connect();
		try {
			String query = "SELECT * FROM AccountStock where company = \"" + company + "\"";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				CustomerStock customerStock = new CustomerStock(company, rs.getDouble("price"), rs.getInt("share")) ;
				customerStocks.add(customerStock) ;
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} finally {
			close();
		}
		return customerStocks ;
	}
	
	public ArrayList<CustomerStock> selectByUsername(String username){
		ArrayList<CustomerStock> customerStocks = new ArrayList<>();

		connect();
		try {
			String query = "SELECT * FROM AccountStock where username = \"" + username + "\"";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				CustomerStock customerStock = new CustomerStock(rs.getString("company"), rs.getDouble("price"), rs.getInt("share")) ;
				customerStocks.add(customerStock) ;
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} finally {
			close();
		}
		return customerStocks ;
	}
}
