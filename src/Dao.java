import java.util.List;

public interface Dao<T> {
	final String url = "jdbc:sqlite:src/ATM.db" ;
	void connect() ;
	void close() ;
	boolean insert(T t) ;
	boolean delete(String s) ;
	boolean update(T t) ;
	T select(String s) ;
}
