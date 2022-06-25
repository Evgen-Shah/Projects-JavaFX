package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import oracle.jdbc.pool.OracleDataSource;

public class DataBaseController {

	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rset;
	private Button btModSalary;
	static Statement statement = null;
	static ObservableList<String> serviceNames = FXCollections.observableArrayList();
	static ObservableList<String> times = FXCollections.observableArrayList();
	static ObservableList<String> masters = FXCollections.observableArrayList();
	static ObservableList<String> address = FXCollections.observableArrayList();

	public static void initializeDatabase() {// Инициализация и подключение бд
		String username = "WashCar";
		String password = "pass";
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

	public int generaterIdOrders() throws SQLException {// Генерация id для заказа
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT ID FROM ORDERS ORDER BY ID";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			int count = resultSet.getInt(1);
			return count;
		} catch (SQLException e) {
			return 0;
		}
	}

	public int generaterIdHistory() throws SQLException {// Генерация id для исторрии
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT ID FROM HISTORY ORDER BY ID";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			int count = resultSet.getInt(1);
			return count;
		} catch (SQLException e) {
			return 0;
		}
	}

	public void addClients(String name, String secondName, long phone, String carNumber, long pass)
			throws SQLException {// добавлениеклиента

		int idClients = generaterIdClients();

		String query = "INSERT INTO CLIENTS(ID,NAME,SECOND_NAME,PHONE,CAR_NUMBER,PASSWORD) VALUES (?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idClients);// ID
		pstmt.setString(2, name);// Name
		pstmt.setString(3, secondName);// SecondName
		pstmt.setLong(4, phone);// Number_phone
		pstmt.setString(5, carNumber);// Email
		pstmt.setLong(6, pass);// pass
		pstmt.executeUpdate();
	}

	public String checkNum(String num) throws SQLException {// Проверка номера

		try {
			String querySelect = "SELECT PASSWORD FROM CLIENTS WHERE CAR_NUMBER = " + "'" + num + "'";// carCheck
			pstmt = conn.prepareStatement(querySelect);
			rset = pstmt.executeQuery(querySelect);
			rset.next();
			String pass = rset.getString(1);
			return pass;
		} catch (SQLException e) {
			return " ";
		}
	}

	public static ObservableList<String> getNameService() throws SQLException {// Получение имени услуг
		String query = "SELECT NAME_SERVICE FROM SERVICE";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next() && Choise_Driver.oneTimeAddName) {
			serviceNames.add(rset.getString(1));
		}
		return serviceNames;

	}

	public static ObservableList<String> getAddress() throws SQLException {// Получение адреса
		String query = "SELECT ADDRESS FROM ADDRESS";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next() && Choise_Driver.oneTimeAddName) {
			address.add(rset.getString(1));
		}
		return address;

	}

	public static ObservableList<String> getTime() throws SQLException {// Получение времени
		String query = "SELECT TIME FROM FREE_TIME";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next() && Choise_Driver.oneTimeAddName) {
			String trueTime = rset.getString(1);
			times.add(trueTime.substring(0, 16));
		}
		return times;

	}

	public static ObservableList<String> getMasters() throws SQLException {// Получение мастера
		String query = "SELECT NAME,SECOND_NAME FROM WASHMAN";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next() && Choise_Driver.oneTimeAddName) {
			masters.add(rset.getString(1));
		}
		return masters;

	}

	public void addOrders(String service_name, String exatra_servise, String date, int box, String address)
			throws SQLException {// добавлениеOrderds
		int idOrd = generaterIdOrders();
		String querySelect = "SELECT ID FROM CLIENTS WHERE CAR_NUMBER = " + "'" + Entry_pass_driver.numberAuto + "'";// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id_pr = rset.getInt(1);

		String query = "INSERT INTO ORDERS(ID,ID_CLIENTS,SERVICE_NAME,EXTRA_SERVISE,DATE_,BOX_NUM,ADDRESS) VALUES (?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idOrd);// ID
		pstmt.setInt(2, id_pr);// idClients
		pstmt.setString(3, service_name);// Service
		pstmt.setString(4, exatra_servise);// Extra
		pstmt.setString(5, date);// Date
		pstmt.setInt(6, box);// Box
		pstmt.setString(7, address);// Date

		pstmt.executeUpdate();

	}

	public void addHistory(String service_name, String exatra_servise, String date, int box, String master,
			String address) throws SQLException {// добавлениеКОмментариев
		int idHist = generaterIdHistory();
		String querySelect = "SELECT ID FROM CLIENTS WHERE CAR_NUMBER = " + "'" + Entry_pass_driver.numberAuto + "'";// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id_pr = rset.getInt(1);

		String query = "INSERT INTO HISTORY(ID,ID_CLIENTS,BOX,DATA,SERVICE_NAME,EXTRA_SERVISE,NAME_MAST,ADDRESS) VALUES (?,?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idHist);// ID
		pstmt.setInt(2, id_pr);// idClients
		pstmt.setInt(3, box);// Box
		pstmt.setString(4, date);// Date
		pstmt.setString(5, service_name);// Service
		pstmt.setString(6, exatra_servise);// Extra
		pstmt.setString(7, master);// Mastrer
		pstmt.setString(8, address);// Mastrer
		pstmt.executeUpdate();

	}

	public String getInforamtion() throws SQLException {// Получение инфомрации

		String querySelect = "SELECT ID FROM CLIENTS WHERE CAR_NUMBER = " + "'" + Entry_pass_driver.numberAuto + "'";// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id_pr = rset.getInt(1);
		System.out.println(id_pr);

		String querySelectHistory = "SELECT BOX,DATA,SERVICE_NAME,EXTRA_SERVISE,NAME_MAST,ADDRESS FROM HISTORY WHERE ID_CLIENTS = "
				+ "'" + id_pr + "'";// idStore
		pstmt = conn.prepareStatement(querySelectHistory);
		rset = pstmt.executeQuery(querySelectHistory);

		String info = "";
		ResultSetMetaData rsmd = pstmt.getMetaData();

		int columnsNumber = rsmd.getColumnCount();

//		for (int coll = 1; coll <= columnsNumber; coll++) {
//			info += (rsmd.getColumnName(coll) + " | ");
//		}
		info += "\n";
		while (rset.next()) {
			for (int coll = 1; coll <= columnsNumber; coll++) {
				switch (coll) {
				case 1:

					info += "Бокс: " + rset.getString(coll);
					break;
				case 2:
					info += "  Дата: " + rset.getString(coll);
					break;
				case 3:
					info += "\n";
					info += "Сервис: " + rset.getString(coll);
					break;
				case 4:
					info += "  Допл: " + rset.getString(coll);
					break;
				case 5:
					info += "  Имя мастера: " + rset.getString(coll);
					break;
				case 6:
					info += "\n";
					info += "По адресу: " + rset.getString(coll);
					info += "\n\n";
					break;
				}
			}

		}
		info += "\n";
		return info;
	}

	public String getInfoClient() throws SQLException {// Получение инфомрации

		String querySelect = "SELECT ID FROM CLIENTS WHERE CAR_NUMBER = " + "'" + Entry_pass_driver.numberAuto + "'";// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id_pr = rset.getInt(1);
		System.out.println(id_pr);

		String querySelectHistory = "SELECT NAME,SECOND_NAME,PHONE,CAR_NUMBER,PASSWORD FROM CLIENTS WHERE ID  = " + "'"
				+ id_pr + "'";// idStore
		pstmt = conn.prepareStatement(querySelectHistory);
		rset = pstmt.executeQuery(querySelectHistory);

		String info = "";
		ResultSetMetaData rsmd = pstmt.getMetaData();

		int columnsNumber = rsmd.getColumnCount();

//		for (int coll = 1; coll <= columnsNumber; coll++) {
//			info += (rsmd.getColumnName(coll) + " | ");
//		}
		while (rset.next()) {
			for (int coll = 1; coll <= columnsNumber; coll++) {
				info += rset.getString(coll) + " ";
			}

		}
		return info;
	}

	public void updateData(String newName, String newSecond, long newPhone, long newPass, String newAuto)
			throws SQLException {// Получение инфомрации

		String querySelect = "SELECT ID FROM CLIENTS WHERE CAR_NUMBER = " + "'" + Entry_pass_driver.numberAuto + "'";// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id_pr = rset.getInt(1);
		System.out.println(id_pr);

		String queryUpdateVolume = "UPDATE CLIENTS SET NAME = " + "'" + newName + "'" + ",SECOND_NAME = " + "'"
				+ newSecond + "'" + ",PHONE = " + newPhone + ",CAR_NUMBER = " + "'" + newAuto + "'" + ",PASSWORD = "
				+ "'" + newPass + "'" + " WHERE ID = " + "'" + id_pr + "'";// Update
		Statement stm = conn.createStatement();
		stm.executeUpdate(queryUpdateVolume);

	}
}
