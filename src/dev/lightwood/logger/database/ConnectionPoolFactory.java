package dev.lightwood.logger.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class ConnectionPoolFactory {

	private static DataSource dataSource;
	
	public ConnectionPoolFactory(String hostname, String database, String user, String password) {
		MysqlConnectionPoolDataSource pool = new MysqlConnectionPoolDataSource();
		pool.setUrl("jdbc:mysql://"+hostname+":3306/"+database);
		pool.setUser(user);
		pool.setPassword(password);
		dataSource = pool;
		try {
			Statement st = getConnection().createStatement();
			st.executeUpdate("CREATE TABLE IF NOT EXISTS logs (dateInMillis LONGTEXT NOT NULL, user VARCHAR(32), cAddress VARCHAR(255) NOT NULL, type VARCHAR(255) NOT NULL, logMessage LONGTEXT NOT NULL)");
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		return connection;
	}

}
