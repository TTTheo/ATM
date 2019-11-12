import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class UserDao implements Dao<Customer> {
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
	public boolean insert(Customer user) {
		// TODO Auto-generated method stub
		connect() ;
		try {
			String query = "INSERT INTO User VALUES (\"" + user.getName() + "\", "+  "\"" + user.getUsername() + "\"," + "\"" + user.getPassword() + "\"," + "\"" + user.getPhone() + "\")" ;
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
			String query = "DELETE FROM User where username = \"" + username + "\"" ;
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
	public boolean update(Customer user) {
		// TODO Auto-generated method stub
		// will add it if user info can be changed
		return true;
	}

	@Override
	public Customer select(String username) {
		// TODO Auto-generated method stub
		Customer user = null ;
		connect() ;
		try {
			String query = "SELECT * FROM User where username = \"" + username + "\"" ;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				user = new Customer(rs.getString("name"),rs.getString("username"), rs.getString("password"), rs.getString("phone")) ;
			}
		}catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}finally {
			close() ;
		}
		
		return user;
	}
	
	public List<Customer> selectAll() {
		// TODO Auto-generated method stub
		List<Customer> users = new ArrayList<>() ;
		
		connect() ;
		try {
			String query = "SELECT * FROM User" ;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				String name = rs.getString("name") ;
				String username=rs.getString("username");
				String password=rs.getString("password");
				String phone=rs.getString("phone");
				users.add(new Customer(name,username,password,phone)) ;
			}
		}catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}finally {
			close() ;
		}
		
		return users;
	}

}
