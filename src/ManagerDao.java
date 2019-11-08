import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ManagerDao implements Dao<Manager>{
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
	
	public boolean insert(Manager manager) {
		// TODO Auto-generated method stub
		connect() ;
		try {
			String query = "INSERT INTO User VALUES ";   //need to be completed
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
		
		close() ;
		return true;
	}

	@Override
	public boolean update(Manager manager) {
		// TODO Auto-generated method stub
		// will add it if user info can be changed
		return true;
	}

	@Override
	public Manager select(String username) {
		// TODO Auto-generated method stub
		Manager manager = null ;
		connect() ;
		
		
		return manager;
	}
	
	public List<Manager> selectAll() {
		// TODO Auto-generated method stub
		List<Manager> managers = new ArrayList<>() ;
		
		connect() ;
		
		
		return managers;
	}
}
