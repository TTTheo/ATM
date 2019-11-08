import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class InvestmentDao implements Dao<Investment>{
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
	public boolean insert(Investment invest) {
		// TODO Auto-generated method stub
		connect() ;
		
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
	public boolean update(Investment invest) {
		// TODO Auto-generated method stub
		// will add it if user info can be changed
		return true;
	}

	@Override
	public Investment select(String username) {
		// TODO Auto-generated method stub
		Investment invest = null ;
		connect() ;
		
		
		return invest;
	}
	
	public List<Investment> selectAll() {
		// TODO Auto-generated method stub
		List<Investment> invest = new ArrayList<>() ;
		
		connect() ;
		
		
		return invest;
	}

}
