import java.util.List;

public interface Dao<T> {
	final String url = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9310493" ;
	final String user = "sql9310493" ;
	final String password = "3uLEtkth5y" ;
	void connect() ;
	void close() ;
	boolean insert(T t) ;
	boolean delete(String s) ;
	boolean update(T t) ;
	T select(String s) ;
}
