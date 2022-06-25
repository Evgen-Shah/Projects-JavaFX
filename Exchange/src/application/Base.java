package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import oracle.jdbc.pool.OracleDataSource;

public class Base {
	private static Connection conn;
	static Statement statement = null;
	static ObservableList<String> serviceNames = FXCollections.observableArrayList();
	private static ResultSet rset;
	static ObservableList<String> times = FXCollections.observableArrayList();
	static ObservableList<String> masters = FXCollections.observableArrayList();
	static ObservableList<String> actives = FXCollections.observableArrayList();
	static ObservableList<String> activesClosed = FXCollections.observableArrayList();
	static ObservableList<String> activesOpen = FXCollections.observableArrayList();
	private static PreparedStatement pstmt;

	public static void initializeDatabase() {// нициализация и подключение бд
		
		String username = "Exchange";
		String password = "1234";
		try {
			OracleDataSource ods = new OracleDataSource();
			ods = new OracleDataSource();
		
			ods.setURL("jdbc:oracle:thin:" + username + "/" + password + "@localhost:1521/orc");
			conn = ods.getConnection();
			System.out.println("Connection success");
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public int generaterIdClients() throws SQLException {// Генерация id для клиента
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT ID FROM CLIENTS ORDER BY ID";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			int count = resultSet.getInt(1);
			return count;
		} catch (SQLException e) {
			return 0;
		}
	}

	public int generaterIdOpen() throws SQLException {// Генерация id для клиента
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT ID FROM INFO_OPEN_POSITION ORDER BY ID";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			int id = resultSet.getInt(1);
			return id;
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public int generaterIdClosed() throws SQLException {// Генерация id для клиента
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT ID FROM INFO_CLOSED_POSITION ORDER BY ID";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			int id = resultSet.getInt(1);
			return id;
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public int generaterIdActives () throws SQLException {// Генерация id для ACTIVES
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT ID FROM ACTIVES ORDER BY ID";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			int id = resultSet.getInt(1);
			return id;
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public String checkNumber(String num) throws SQLException {// Проверка номера

		try {
			String querySelect = "SELECT PASSWORD FROM CLIENTS WHERE PHONE = " + "'" + num + "'";// carCheck
			pstmt = conn.prepareStatement(querySelect);
			rset = pstmt.executeQuery(querySelect);
			rset.next();
			String pass = rset.getString(1);
			return pass;
		} catch (SQLException e) {
			return " ";
		}
	}
	
	public String checkId(String id) throws SQLException {// Проверка номера
		try {
			String querySelect = "SELECT PASSWORD FROM ADMIN_INFO WHERE ID = " + "'" + id + "'";// carCheck
			pstmt = conn.prepareStatement(querySelect);
			rset = pstmt.executeQuery(querySelect);
			rset.next();
			String pass = rset.getString(1);
			return pass;
		} catch (SQLException e) {
			return " ";
		}
	}
	
	public static ObservableList<String> getHistoryAddDelete() throws SQLException {// Получение активов
		String query = "SELECT NAME,ACTIONS FROM HISTORY_ADD_DELETE";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next()) {
			actives.add(rset.getString(1) + " " + rset.getString(2));
		}
		return actives;

	}
	
	public String getName(String num) throws SQLException {

		try {
			String querySelect = "SELECT FULL_NAME FROM CLIENTS WHERE PHONE = " + "'" + num + "'";// carCheck
			pstmt = conn.prepareStatement(querySelect);
			rset = pstmt.executeQuery(querySelect);
			rset.next();
			String pass = rset.getString(1);
			return pass;
		} catch (SQLException e) {
			return " ";
		}
	}
	
	public String getCountActives(String num) throws SQLException {

		try {
			String querySelect = "SELECT COUNT FROM INFO_OPEN_POSITION WHERE PHONE = " + "'" + num + "'";// carCheck
			pstmt = conn.prepareStatement(querySelect);
			rset = pstmt.executeQuery(querySelect);
			rset.next();
			String pass = rset.getString(1);
			return pass;
		} catch (SQLException e) {
			return " ";
		}
	}
	
	
	public static ObservableList<String> getActives() throws SQLException {// Получение активов
		String query = "SELECT NAME,PRICE FROM ACTIVES";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next()) {
			actives.add(rset.getString(1) + " " + rset.getString(2));
		}
		return actives;

	}
	
	public static void remove()  {// Получение активов
		actives.removeAll(actives);
	}
	
	public static String getBalance() throws SQLException {// Получение активов
		String query = "SELECT BALANCE FROM CLIENTS WHERE PHONE = " + "'" + Login_window_class.phoneNumber + "'";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		rset.next();
		return rset.getString(1);

	}
	
	public static ObservableList<String> getActivesClosed() throws SQLException {// Получение активов
		String querySelect = "SELECT ID FROM CLIENTS WHERE PHONE = " + "'" + Login_window_class.phoneNumber + "'";// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id_pr = rset.getInt(1);
		
		String query = "SELECT ACTIVES,COUNT,PRICE,OPERATION,DATE_CLOSED FROM INFO_CLOSED_POSITION WHERE ID_CLIENTS = " + "'" + id_pr + "'";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next()) {
			activesClosed.add("Актив: " + rset.getString(1) + " количество: " + rset.getString(2) + " цена: " + rset.getString(3) + " операция: " + rset.getString(4) + " дата: " + rset.getTimestamp(5) );
		}
		return activesClosed;

	}
	
	public static ObservableList<String> getActivesOpen() throws SQLException {// Получение активов
		String querySelect = "SELECT ID FROM CLIENTS WHERE PHONE = " + "'" + Login_window_class.phoneNumber + "'";// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id_pr = rset.getInt(1);
		
		String query = "SELECT ACTIVES,COUNT,PRICE,OPERATION,DATE_ADD FROM INFO_OPEN_POSITION WHERE ID_CLIENTS = " + "'" + id_pr + "'";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next()) {
			activesOpen.add("Актив: " + rset.getString(1) + " количество: " + rset.getString(2) + " цена: " + rset.getString(3) + " операция: " + rset.getString(4) + " дата: " + rset.getTimestamp(5));
		}
		return activesOpen;

	}
	
	public static void removeOpen()  {// Получение активов
		activesOpen.removeAll(activesOpen);
	}
	
	public static void removeClosed()  {// Получение активов
		activesClosed.removeAll(activesClosed);
	}
	
	public void addChangeActives(String name, String actions)
			throws SQLException {// добавление клиента
		int idClients = generaterIdActives();
		String query = "INSERT INTO HISTORY_ADD_DELETE(ID,NAME,ACTIONS) VALUES (?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idClients);// ID
		pstmt.setString(2, name);// Name
		pstmt.setString(3, actions);// date
		pstmt.executeUpdate();
	}
	
	public void addActives(String name, long price)
			throws SQLException {// добавление клиента
		int idClients = generaterIdActives();
		String query = "INSERT INTO ACTIVES(ID,NAME,PRICE) VALUES (?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idClients);// ID
		pstmt.setString(2, name);// Name
		pstmt.setLong(3, price);// date
		pstmt.executeUpdate();
	}
	
	
	public void addClients(String fullName, String date, long phone, long balance, String pass)
			throws SQLException {// добавление клиента
		int idClients = generaterIdClients();
		String query = "INSERT INTO CLIENTS(ID,FULL_NAME,B_DATE,PHONE,BALANCE,PASSWORD) VALUES (?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idClients);// ID
		pstmt.setString(2, fullName);// Name
		pstmt.setString(3, date);// date
		pstmt.setLong(4, phone);// phone
		pstmt.setLong(5, balance);// balance
		pstmt.setString(6, pass);// pass
		pstmt.executeUpdate();
	}
	
	public void openActives(String actives, long count, long price, String operation)
			throws SQLException {// добавление актива
		String querySelect = "SELECT ID FROM CLIENTS WHERE PHONE = " + "'" + Login_window_class.phoneNumber + "'";// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id_pr = rset.getInt(1);
		
		int idClients = generaterIdOpen();
		String query = "INSERT INTO INFO_OPEN_POSITION(ID,ID_CLIENTS,ACTIVES,COUNT,PRICE,OPERATION,DATE_ADD) VALUES (?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idClients);// ID
		pstmt.setLong(2, id_pr);// ID_CLIENTS
		pstmt.setString(3,actives );// ACTIVES
		pstmt.setLong(4, count);// COUNT
		pstmt.setLong(5, price);// PRICE
		pstmt.setString(6, operation);// PRICE
		pstmt.setDate(7, new java.sql.Date(System.currentTimeMillis()));// date
		pstmt.executeUpdate();
	}
	
	public void closedActives(String actives, long count, long price, String operation)
			throws SQLException {// добавление актива
		String querySelect = "SELECT ID FROM CLIENTS WHERE PHONE = " + "'" + Login_window_class.phoneNumber + "'";// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id_pr = rset.getInt(1);
		
		int idClients = generaterIdClosed();
		String query = "INSERT INTO INFO_CLOSED_POSITION(ID,ID_CLIENTS,ACTIVES,COUNT,PRICE,OPERATION,DATE_CLOSED) VALUES (?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idClients);// ID
		pstmt.setLong(2, id_pr);// ID_CLIENTS
		pstmt.setString(3,actives );// ACTIVES
		pstmt.setLong(4, count);// COUNT
		pstmt.setLong(5, price);// PRICE
		pstmt.setString(6, operation);// PRICE
		pstmt.setDate(7, new java.sql.Date(System.currentTimeMillis()));// date
		pstmt.executeUpdate();
	}
	
	public void updateBalanse(long newBalance)
			throws SQLException {
		String querySelect = "SELECT ID FROM CLIENTS WHERE PHONE = " + "'" + Login_window_class.phoneNumber + "'";// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id_pr = rset.getInt(1);
		System.out.println(id_pr);

		String queryUpdateVolume = "UPDATE CLIENTS SET BALANCE = " + "'" + newBalance + "'" + " WHERE ID = " + "'" + id_pr + "'" ;// Update
		Statement stm = conn.createStatement();
		stm.executeUpdate(queryUpdateVolume);

	}
	
	public void cancel(String name) throws SQLException {// Добавление отказа
		String queryDelete = "DELETE FROM ACTIVES WHERE NAME = " + "'" + name + "'";// Delete item
		pstmt = conn.prepareStatement(queryDelete);
		pstmt.executeUpdate();

	}
	
	public void deletePos(String name) throws SQLException {// Добавление отказа
		String querySelect = "SELECT ID FROM CLIENTS WHERE PHONE = " + "'" + Login_window_class.phoneNumber + "'";// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id_pr = rset.getInt(1);
		
		
		String queryDelete = "DELETE FROM INFO_OPEN_POSITION WHERE ACTIVES = " + "'" + name + "'"  +" AND ID_CLIENTS = "+"'" + id_pr +"'";// Delete item
		pstmt = conn.prepareStatement(queryDelete);
		pstmt.executeUpdate();

	}

//	public void updateCount(long newCount)
//			throws SQLException {
//		String querySelect = "SELECT ID FROM CLIENTS WHERE PHONE = " + "'" + Login_window_class.phoneNumber + "'";// idStore
//		pstmt = conn.prepareStatement(querySelect);
//		rset = pstmt.executeQuery(querySelect);
//		rset.next();
//		int id_pr = rset.getInt(1);
//		System.out.println(id_pr);
//
//		String queryUpdateVolume = "UPDATE CLIENTS SET BALANCE = " + "'" + newBalance + "'" + " WHERE ID = " + "'" + id_pr + "'" ;// Update
//		Statement stm = conn.createStatement();
//		stm.executeUpdate(queryUpdateVolume);
//
//	}
}
