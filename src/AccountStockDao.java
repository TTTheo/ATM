import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AccountStockDao implements Dao<CustomerStock>{
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
	
	public boolean insert(CustomerStock custock) {
		// TODO Auto-generated method stub
		connect() ;
		try {
			String query = "INSERT INTO User VALUES ";  //need to be completed
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
	
	public boolean insert(String acountnumber,CustomerStock custock) {
		// TODO Auto-generated method stub
		connect() ;
		try {
			String query = "INSERT INTO User VALUES ";  //need to be completed
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
	public boolean delete(String acountnumber) {
		// TODO Auto-generated method stub
		connect() ;
		
		close() ;
		return true;
	}
	

	public boolean delete(String acountnumber,CustomerStock custock) {
		// TODO Auto-generated method stub
		connect() ;
		
		close() ;
		return true;
	}

	@Override
	public boolean update(CustomerStock custock) {
		// TODO Auto-generated method stub
		
		
		
		return true;
	}

	@Override
	public CustomerStock select(String company) {
		// TODO Auto-generated method stub
		connect() ;
		CustomerStock custock=null;
		
		
		
		return custock;
	}
	
	public List<CustomerStock> selectList(String company) {
		// TODO Auto-generated method stub
		connect() ;
		List<CustomerStock> custock=new ArrayList<CustomerStock>();
		
		
		
		return custock;
	}
	
	public List<CustomerStock> selectListofAccount(String accountNumber) {
		// TODO Auto-generated method stub
		connect() ;
		List<CustomerStock> custock=new ArrayList<CustomerStock>();
		
		
		
		return custock;
	}
	
	public List<CustomerStock> selectAll() {
		// TODO Auto-generated method stub
		List<CustomerStock> stocks = new ArrayList<>() ;
		
		connect() ;
		try {
			String query = "SELECT * FROM User" ;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				
			}
		}catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}finally {
			close() ;
		}
		
		return stocks;
	}


}
