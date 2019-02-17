package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.entity.Prod;
import cn.tedu.util.DBUtils;

public class ProdDAO {
	/**
	 * 创数据库
	 */
	public void createDataBase(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			System.out.println("正在创建prod库");
			connection = DBUtils.getConnection();
			String sql = "CREATE DATABASE prod CHARSET utf8";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			System.out.println("已创建prod库");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.colse(connection, preparedStatement, null);
		}
	}
	/**
	 * 进数据库
	 */
	public void useDatabase(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			System.out.println("正在进入prod库");
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement("USE prod");
			preparedStatement.execute();
			System.out.println("已进入prod库");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.colse(connection, preparedStatement, null);
		}
	}
	/**
	 * 查找库
	 */
	public void showDataBases(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean check = true;	//默认要创库
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement("SHOW DataBases");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				String tableNameProd = resultSet.getString("Database");
				if("prod".equals(tableNameProd)){
					check = false;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.colse(connection, preparedStatement, resultSet);
			if(check){
				System.out.println("将创建prod库");
				createDataBase();
			}else{
				System.out.println("将进入prod库");
				useDatabase();
			}
		}
	}
	/**
	 * 查找表
	 */
	public void showTables(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean check = true;	//默认要创表
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement("SHOW TABLES");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				String tableNameProd = resultSet.getString("Tables_in_prod");
				if("prod".equals(tableNameProd)){
					check = false;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.colse(connection, preparedStatement, resultSet);
			if(check){
				System.out.println("将创建prod表");
				createTable();
			}
		}
	}
	/**
	 * 创建表
	 */
	public void createTable(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			System.out.println("正在创建prod表");
			connection = DBUtils.getConnection();
			String sql = "CREATE TABLE prod(id int primary key auto_increment, name text, category text, price double(9,1), pnum int, description text) ENGINE=INNODB CHARSET=utf8";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			System.out.println("已创建prod表");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.colse(connection, preparedStatement, null);
		}
	}
	/**
	 * 清空商品数据
	 */
	public void truncate() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement("TRUNCATE TABLE prod");
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.colse(connection, preparedStatement, null);
		}
	}
	/**
	 * 删除商品
	 */
	public void delete(int id) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM prod WHERE id="+id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.colse(connection, preparedStatement, null);
		}
	}
	/**
	 * 查询商品列表
	 */
	public List<Prod> select() throws SQLException{
		List<Prod> prods = new ArrayList<Prod>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM prod");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String category = resultSet.getString("category");
				double price = resultSet.getDouble("price");
				int pnum = resultSet.getInt("pnum");
				String description = resultSet.getString("description");
				Prod prod = new Prod();
				prod.setId(id);
				prod.setName(name);
				prod.setCategory(category);
				prod.setPrice(price);
				prod.setPnum(pnum);
				prod.setDescription(description);
				prods.add(prod);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.colse(connection, preparedStatement, resultSet);
		}
		return prods;
	}
	/**
	 * 修改商品
	 */
	public void update(int id, String name, String category, double price, int pnum, String description) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBUtils.getConnection();
			String sql = "UPDATE prod SET name='"+name+"',category='"+category+"',price="+price+",pnum="+pnum+",description='"+description+"' WHERE id="+id;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.colse(connection, preparedStatement, null);
		}
	}
	/**
	 * 添加商品
	 */
	public void insert(String name, String category, double price, int pnum, String description) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO prod VALUES(null,'"+name+"','"+category+"',"+price+","+pnum+",'"+description+"')");
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.colse(connection, preparedStatement, null);
		}
	}
}
