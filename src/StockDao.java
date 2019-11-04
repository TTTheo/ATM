
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StockDao implements Dao<Stock> {
	private Connection conn = null ;
	@Override
	public boolean insert(Stock stock) {
		// TODO Auto-generated method stub
		connect() ;
		try {
			String query = "INSERT INTO Stock(`company`, `price`) VALUES (\"" + stock.getCompany() + "\", "+  stock.getPrice() + ")" ;
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
	public boolean delete(String company) {
		// TODO Auto-generated method stub
		connect() ;
		try {
			String query = "DELETE FROM Stock where company = \"" + company + "\"" ;
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
	public boolean update(Stock stock) {
		// TODO Auto-generated method stub
		connect() ;
		try {
			String query = "UPDATE Stock SET price = " + stock.getPrice() +" where company = \"" + stock.getCompany() + "\"" ;
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

	
	public List<Stock> selectAll() {
		// TODO Auto-generated method stub
		List<Stock> stocks = new ArrayList<>() ;
		
		connect() ;
		try {
			String query = "SELECT * FROM Stock" ;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				String company = rs.getString("company") ;
				double price = rs.getDouble("price") ;
				stocks.add(new Stock(company,price)) ;
			}
		}catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}finally {
			close() ;
		}
		
		return stocks;
	}
	@Override
	public Stock select(String company) {
		Stock stock = null ;
		connect() ;
		try {
			String query = "SELECT * FROM Stock where company = \"" + company + "\"" ;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				stock = new Stock(rs.getString("company"),rs.getDouble("price")) ;
			}
		}catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}finally {
			close() ;
		}
		
		return stock;
	}
	
	@Override
	public void connect() {
		// TODO Auto-generated method stub
	
    // auto close connection
    try {
    	conn =  DriverManager.getConnection(url, user, password) ;
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
}