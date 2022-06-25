package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import oracle.jdbc.pool.OracleDataSource;

public class BaseControl {

	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rset;
	private Button btModSalary;
	static Statement statement = null;
	static ObservableList<String> pinks = FXCollections.observableArrayList();
	static ObservableList<String> company = FXCollections.observableArrayList();
	static ObservableList<String> size = FXCollections.observableArrayList();
	static ObservableList<String> time = FXCollections.observableArrayList();
	static ObservableList<String> order = FXCollections.observableArrayList();

	public static void initialize() {// Инициализация и подключение бд
		String username = "Scate";
		String password = "1234";
		try {

			OracleDataSource ods = new OracleDataSource();
			ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:" + username + "/" + password + "@localhost:1521/orc");
			conn = ods.getConnection();
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("Connect");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public int generaterIdClients() throws SQLException {// Генерация id для клиента
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT ID FROM CLIENT ORDER BY ID";
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

	public int generaterIdCancel() throws SQLException {// Генерация id для отказа
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT ID FROM CANCEL_ORDER ORDER BY ID";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			int count = resultSet.getInt(1);
			return count;
		} catch (SQLException e) {
			return 0;
		}
	}

	public int generaterIdHistory() throws SQLException {// Генерация id для истории
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

	public void addClients(String name, String secondName, int age, long phone, String pass) throws SQLException {// добавлениеклиента

		int idClients = generaterIdClients();

		String query = "INSERT INTO CLIENT(ID,NAME,SECOND_NAME,AGE,PHONE,PASSWORD) VALUES (?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idClients);// ID
		pstmt.setString(2, name);// Name
		pstmt.setString(3, secondName);// SecondName
		pstmt.setInt(4, age);// ID
		pstmt.setLong(5, phone);// Number_phone
		pstmt.setString(6, pass);// pass
		pstmt.executeUpdate();
	}

	public void addOrders(int size, int price, String company, long phone) throws SQLException {// добавление заказа

		int idOrd = generaterIdOrders();

		String queryClient = "SELECT ID FROM CLIENT WHERE PHONE = " + Entry_control.number;
		ResultSet resultSet = statement.executeQuery(queryClient);
		resultSet = statement.executeQuery(queryClient);
		resultSet.last();
		int idClient = resultSet.getInt(1);

		String queryAdres = "SELECT ADDRESS FROM RINK WHERE PHONE = " + phone;
		resultSet = statement.executeQuery(queryAdres);
		resultSet.last();
		String adress = resultSet.getString(1);

		String query = "INSERT INTO ORDERS(ID,ID_CLIENT,SIZE_SHOE,PRICE,COMPANY,ADDRESS,TIME) VALUES (?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idOrd);// ID
		pstmt.setInt(2, idClient);// ID_client
		pstmt.setInt(3, size);// size
		pstmt.setInt(4, price);// PricePH
		pstmt.setString(5, company);// Company
		pstmt.setString(6, adress);// Address
		pstmt.setString(7, "Не выбрано");// Time
		pstmt.executeUpdate();

		int idHist = generaterIdHistory();

		String query2 = "INSERT INTO HISTORY(ID,ID_CLIENT,SIZE_SHOE,PRICE,COMPANY,ADDRESS,TIME) VALUES (?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(query2);
		pstmt.setInt(1, ++idHist);// ID
		pstmt.setInt(2, idClient);// ID_client
		pstmt.setInt(3, size);// size
		pstmt.setInt(4, price);// PricePH
		pstmt.setString(5, company);// Company
		pstmt.setString(6, adress);// Address
		pstmt.setString(7, "Не выбрано");// Time
		pstmt.executeUpdate();
	}

	public String getPass(String phone) throws SQLException {// Проврека пароля

		try {
			String querySelect = "SELECT PASSWORD FROM CLIENT WHERE PHONE = " + "'" + phone + "'";// carCheck
			pstmt = conn.prepareStatement(querySelect);
			rset = pstmt.executeQuery(querySelect);
			rset.next();
			String pass = rset.getString(1);
			return pass;
		} catch (SQLException e) {
			return " ";
		}
	}
	
	public boolean getCheckHpone(String phone) throws SQLException {// Проврека пароля

		try {
			String querySelect = "SELECT PHONE FROM CLIENT WHERE PHONE = " + "'" + phone + "'";// carCheck
			pstmt = conn.prepareStatement(querySelect);
			rset = pstmt.executeQuery(querySelect);
			rset.next();
			String pass = rset.getString(1);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static ObservableList<String> getTime() throws SQLException {// Получение времени
		String query = "SELECT TIME FROM TIME";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next() && time_control.addOne) {
			String trueTime = rset.getString(1);
			time.add(trueTime.substring(0, 16));
		}
		return time;

	}

	public static ObservableList<String> getDescriptions() throws SQLException {// Получение описания
		String query = "SELECT DESCRIPTION,PHONE,ADDRESS FROM RINK";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next() && Info_scates.oneTimes) {
			pinks.add(rset.getString(1) + " Телефон: " + rset.getString(2) + " адрес: " + rset.getString(3));
		}
		return pinks;

	}

	public static ObservableList<String> getSize() throws SQLException {// Получение размера обуви
		String query = "SELECT COMPANY,COLOR,SIZE_SHOE,PRICE FROM SHOE";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next() && Renta_control.addOne) {
			size.add("От компании: " + rset.getString(1) + " цвет: " + rset.getString(2).toLowerCase() + " размер: "
					+ rset.getString(3) + " по цене " + rset.getString(4) + " за час");
		}
		return size;

	}

	public static ObservableList<String> getOrder() throws SQLException {// Получение заказа

		String queryClient = "SELECT ID FROM CLIENT WHERE PHONE = " + Entry_control.number;
		pstmt = conn.prepareStatement(queryClient);
		rset = pstmt.executeQuery(queryClient);
		rset.next();
		int idClient = rset.getInt(1);

		String query = "SELECT ADDRESS,TIME,ID FROM ORDERS WHERE ID_CLIENT = " + idClient;
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next()) {
			order.add("Номер заказа: " + rset.getString(3) + " адрес: " + rset.getString(1) + " время: "
					+ rset.getString(2));
		}
		return order;

	}

	public void updateData(String time) throws SQLException {// Обнволение информации
		int idOrd = generaterIdOrders();
		String queryUpdateVolume = "UPDATE ORDERS SET TIME = " + "'" + time + "'" + " WHERE ID = " + idOrd;// Update
		Statement stm = conn.createStatement();
		stm.executeUpdate(queryUpdateVolume);
		int idHist = generaterIdHistory();
		String queryUpdateVolume2 = "UPDATE HISTORY SET TIME = " + "'" + time + "'" + " WHERE ID = " + idHist;// Update
		Statement stm2 = conn.createStatement();
		stm.executeUpdate(queryUpdateVolume2);

	}

	public String getInfo() throws SQLException {// получение инофрмации

		String queryClient = "SELECT ID FROM CLIENT WHERE PHONE = " + Entry_control.number;
		ResultSet resultSet = statement.executeQuery(queryClient);
		resultSet = statement.executeQuery(queryClient);
		resultSet.last();
		int idClient = resultSet.getInt(1);

		statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		String querySelectInfa = "SELECT ID,ADDRESS,TIME,PRICE,COMPANY,SIZE_SHOE FROM HISTORY WHERE ID_CLIENT ="
				+ idClient;
		rset = pstmt.executeQuery(querySelectInfa);
//		String sizeShoe = resultSet.getString(1);
//		String price = resultSet.getString(2);
//		String company = resultSet.getString(3);
//		String address = resultSet.getString(4);
//		String time = resultSet.getString(5);

		ResultSetMetaData rsmd = pstmt.getMetaData();

		int columnsNumber = rsmd.getColumnCount();

		String info = "";
		System.out.println(resultSet.next());
		while (rset.next()) {
			info += "\n";
			for (int coll = 1; coll <= columnsNumber; coll++) {
				switch (coll) {
				case 1:
					info += "Номер заказа: " + rset.getString(coll);
					info += "\n";
					break;
				case 2:
					info += " адрес: " + rset.getString(coll);
					break;
				case 3:
					info += " дата и время: " + rset.getString(coll);
					break;
				case 4:
					info += " цена за час " + rset.getString(coll);
					break;
				case 5:
					info += "\n";
					info += "компания: " + rset.getString(coll);
					break;
				case 6:
					info += " размер обуви: " + rset.getString(coll);
					break;
				}

			}

		}
		info += "\n";
		return info;
	}

	public void addCancel(int id) throws SQLException {// Добавление отказа
		int idCans = generaterIdCancel();

		String queryClient = "SELECT ID FROM CLIENT WHERE PHONE = " + Entry_control.number;
		ResultSet resultSet = statement.executeQuery(queryClient);
		resultSet = statement.executeQuery(queryClient);
		resultSet.last();
		int idClient = resultSet.getInt(1);

		String query = "INSERT INTO CANCEL_ORDER(ID,ID_ORDER) VALUES (?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idCans);// ID
		pstmt.setInt(2, idClient);// ID_client
		pstmt.executeUpdate();

		String queryDelete = "DELETE FROM ORDERS WHERE ID = " + id;// Delete item
		pstmt = conn.prepareStatement(queryDelete);
		// rset = pstmt.executeQuery(queryDelete);
		pstmt.executeUpdate();

	}

	public String getInfoClient() throws SQLException {// Получение инфомрации

		String querySelect = "SELECT ID FROM CLIENT WHERE PHONE = " + Entry_control.number;// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id_pr = rset.getInt(1);

		String querySelectHistory = "SELECT NAME,SECOND_NAME,AGE,PHONE,PASSWORD FROM CLIENT WHERE ID  = " + id_pr;// idStore
		pstmt = conn.prepareStatement(querySelectHistory);
		rset = pstmt.executeQuery(querySelectHistory);

		String info = "";
		ResultSetMetaData rsmd = pstmt.getMetaData();

		int columnsNumber = rsmd.getColumnCount();

		while (rset.next()) {
			for (int coll = 1; coll <= columnsNumber; coll++) {
				info += rset.getString(coll) + " ";
			}

		}
		return info;
	}

	public void updateData(String newName, String newSecond, long newPhone, String newPass, int newAge)
			throws SQLException {// Изменение информации

		String querySelect = "SELECT ID FROM CLIENT WHERE PHONE = " + Entry_control.number;// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id_pr = rset.getInt(1);

		String queryUpdateVolume = "UPDATE CLIENT SET NAME = " + "'" + newName + "'" + ",SECOND_NAME = " + "'"
				+ newSecond + "'" + ",PHONE = " + newPhone + ",AGE = " + "'" + newAge + "'" + ",PASSWORD = " + "'"
				+ newPass + "'" + " WHERE ID = " + "'" + id_pr + "'";// Update
		Statement stm = conn.createStatement();
		stm.executeUpdate(queryUpdateVolume);

	}

}
