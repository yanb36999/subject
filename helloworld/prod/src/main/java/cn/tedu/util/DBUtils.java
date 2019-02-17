package cn.tedu.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class DBUtils {
	private static BasicDataSource basicDataSource;
	static{
		Properties properties = new Properties();
		InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			properties.load(inputStream);
			basicDataSource = new BasicDataSource();
			basicDataSource.setDriverClassName(properties.getProperty("driver"));
			basicDataSource.setUrl(properties.getProperty("url"));
			basicDataSource.setUsername(properties.getProperty("username"));
			basicDataSource.setPassword(properties.getProperty("password"));
			basicDataSource.setInitialSize(Integer.parseInt(properties.getProperty("initSize")));
			basicDataSource.setMaxActive(Integer.parseInt(properties.getProperty("maxSize")));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static Connection getConnection() throws SQLException{
		return basicDataSource.getConnection();
	}
	public static void colse(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
		try {
			if(connection!=null){
				connection.setAutoCommit(true);
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(preparedStatement!=null){
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(resultSet!=null){
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
