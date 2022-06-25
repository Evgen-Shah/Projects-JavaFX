Autopackage application;

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

public class ControleDataBase {

	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rset;
	private Button btModSalary;
	static Statement statement = null;

	public static long phone;

	static ObservableList<String> address = FXCollections.observableArrayList();
	static ObservableList<String> time = FXCollections.observableArrayList();
	static ObservableList<String> coach = FXCollections.observableArrayList();
	static ObservableList<String> addressTrein = FXCollections.observableArrayList();
	static ObservableList<String> ordersCoach = FXCollections.observableArrayList();

	public static void initializeDatabase() {// Инициализация и подключение бд
		String username = "auto";
		String password = "1234";
		try {
			OracleDataSource ods = new OracleDataSource();
			ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:" + username + "/" + password + "@localhost:1521/xe");
			conn = ods.getConnection();
			System.out.println("Connection success");
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	public String getInfoAuto() throws SQLException {// Получение адреса

		String query = "SELECT ID_SCHOOL FROM STUDY WHERE PHONE = " + "'" + phone + "'";
		ResultSet resultSet = statement.executeQuery(query);
		resultSet = statement.executeQuery(query);
		resultSet.last();
		int idSc = resultSet.getInt(1);

		String querys = "SELECT PHONE_ADM,ADDRESS FROM INFO_AUTO WHERE ID = " + "'" + idSc + "'";
		ResultSet resultSetS = statement.executeQuery(querys);
		resultSetS = statement.executeQuery(querys);
		resultSetS.last();
		String info = resultSetS.getString(1) + "," + resultSetS.getString(2);

		return info;
	}

	public int generaterIdStudy() throws SQLException {// Генерация id для клиента
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT ID FROM STUDY ORDER BY ID";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			int count = resultSet.getInt(1);
			return count;
		} catch (SQLException e) {
			return 0;
		}
	}

	public int generaterIdOrder() throws SQLException {// Генерация id для клиента
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT ID FROM ORDER_ST ORDER BY ID";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			int count = resultSet.getInt(1);
			return count;
		} catch (SQLException e) {
			return 0;
		}
	}

	public static ObservableList<String> getAddress() throws SQLException {// Получение адреса
		String query = "SELECT ADDRESS FROM INFO_AUTO";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next() && StyduWindow_controll.oneTimeAddName) {
			address.add(rset.getString(1));
		}
		return address;
	}

	public static ObservableList<String> getTime() throws SQLException {// Получение адреса
		String query = "SELECT TIME_TREIN FROM TIME";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next() && Controller_Study.oneTime) {
			time.add(rset.getString(1));
		}
		return time;
	}

	public static ObservableList<String> getCoach() throws SQLException {// Получение адреса
		String query = "SELECT NAME , SECOND_NAME FROM COACH";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next() && Controller_Study.oneTime) {
			coach.add(rset.getString(1) + "," + rset.getString(2));
		}
		return coach;
	}

	public static ObservableList<String> getAddressTrein() throws SQLException {// Получение адреса
		String query = "SELECT ADDRESS FROM ADDRESS_TREINING";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next() && Controller_Study.oneTime) {
			addressTrein.add(rset.getString(1));
		}
		return addressTrein;
	}

	public String checkNum(String num) throws SQLException {// Проверка номера

		try {
			String querySelect = "SELECT PASSWORD FROM STUDY WHERE PHONE = " + "'" + num + "'";// carCheck
			pstmt = conn.prepareStatement(querySelect);
			rset = pstmt.executeQuery(querySelect);
			rset.next();
			String pass = rset.getString(1);
			return pass;
		} catch (SQLException e) {
			return " ";
		}
	}

	public static ObservableList<String> getOrders() throws SQLException {// Получение адреса

		String querySelect = "SELECT SECOND_NAME FROM COACH WHERE PHONE = " + phone;// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		String id_coach = rset.getString(1);

		String query = "SELECT TIME_ORDER,SECOND_NAME_ST,ADRESS FROM ORDER_ST WHERE SECOND_NAME_COACH =" + "'"
				+ id_coach + "'";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next() && Controller_coach.annOneTime) {
			ordersCoach.add(rset.getString(1) + " " + rset.getString(2) + " " + rset.getString(3));
		}
		return ordersCoach;
	}

	public String checkNumCoach(String num) throws SQLException {// Проверка номера

		try {
			String querySelect = "SELECT PASSWORD FROM COACH WHERE PHONE = " + "'" + num + "'";// carCheck
			pstmt = conn.prepareStatement(querySelect);
			rset = pstmt.executeQuery(querySelect);
			rset.next();
			String pass = rset.getString(1);
			return pass;
		} catch (SQLException e) {
			return " ";
		}
	}

	public long getIdSt() {

		String querySelectStoreForPr = "SELECT ID FROM STUDY WHERE PHONE = " + "'" + phone + "'";// idStore
		try {
			pstmt = conn.prepareStatement(querySelectStoreForPr);

			rset = pstmt.executeQuery(querySelectStoreForPr);
			rset.next();
			long IdP = rset.getInt("ID");
			return IdP;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}

	}

	public void addStudy(String name, String secondName, String pass, long phone, String address) throws SQLException {// добавлениеклиента

		System.out.println(address);
		String querySelectStoreForPr = "SELECT ID FROM INFO_AUTO WHERE ADDRESS = " + "'" + address + "'";// idStore
		pstmt = conn.prepareStatement(querySelectStoreForPr);
		rset = pstmt.executeQuery(querySelectStoreForPr);
		rset.next();
		int IdP = rset.getInt("ID");

		int idClients = generaterIdStudy();
		String query = "INSERT INTO STUDY(ID,NAME,SECOND_NAME,PASSWORD,ID_SCHOOL,PHONE) VALUES (?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idClients);// ID
		pstmt.setString(2, name);// Name
		pstmt.setString(3, secondName);// SecondName
		pstmt.setString(4, pass);// Number_phone
		pstmt.setInt(5, IdP);// Email
		pstmt.setLong(6, phone);// pass
		pstmt.executeUpdate();
	}

	public void addOrder(String time, String coachName, String coachSecondName, String address) {// добавлениеклиента
		System.out.println(phone);
		System.out.println(address);
		try {
			long id_St = getIdSt();
			String querySelectStoreForSt = "SELECT SECOND_NAME FROM STUDY WHERE ID = " + "'" + id_St + "'";
			pstmt = conn.prepareStatement(querySelectStoreForSt);

			rset = pstmt.executeQuery(querySelectStoreForSt);
			rset.next();
			String secSt = rset.getString(1);

			int idOrd = generaterIdOrder();

			String query = "INSERT INTO ORDER_ST(ID,TIME_ORDER,SECOND_NAME_COACH,ADRESS,SECOND_NAME_ST) VALUES (?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ++idOrd);// ID
			pstmt.setString(2, time);// Name
			pstmt.setString(3, coachSecondName);// SecondName
			pstmt.setString(4, address);// SecondName
			pstmt.setString(5, secSt);// SecondName
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addHist(String time, String coachName, String coachSecondName, String address) {// добавлениеклиента
		System.out.println(phone);
		System.out.println(address);
		try {
			long id_St = getIdSt();
			String querySelectStoreForSt = "SELECT SECOND_NAME FROM STUDY WHERE ID = " + "'" + id_St + "'";
			pstmt = conn.prepareStatement(querySelectStoreForSt);

			rset = pstmt.executeQuery(querySelectStoreForSt);
			rset.next();
			String secSt = rset.getString(1);

			int idOrd = generaterIdOrder();

			String query = "INSERT INTO HISTORY(ID,TIME_ORDER,SECOND_NAME_COACH,ADRESS,SECOND_NAME_ST) VALUES (?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ++idOrd);// ID
			pstmt.setString(2, time);// Name
			pstmt.setString(3, coachSecondName);// SecondName
			pstmt.setString(4, address);// SecondName
			pstmt.setString(5, secSt);// SecondName
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getInfoClient() throws SQLException {// Получение инфомрации

		ObservableList<String> coachInfo = FXCollections.observableArrayList();

		String querySelect = "SELECT SECOND_NAME FROM STUDY WHERE PHONE = " + phone;// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		String secName = rset.getString(1);

//		String querySelectHistory = "SELECT SECOND_NAME_COACH FROM ORDER_ST WHERE SECOND_NAME_ST  = " + secName;
//		pstmt = conn.prepareStatement(querySelectHistory);
//		rset = pstmt.executeQuery(querySelectHistory);
//		rset.next();
//		String Seccoach = rset.getString(1);

//		String query = "SELECT NAME ,SECOND_NAME FROM COACH WHERE ID = " + "'" + id_coach + "'";
//		pstmt = conn.prepareStatement(query);
//		rset = pstmt.executeQuery();
//		rset.next();
//		String coach = rset.getString(1) + " , " + rset.getString(2);

		String querySelectInfo = "SELECT TIME_ORDER, ADRESS,SECOND_NAME_COACH FROM HISTORY WHERE SECOND_NAME_ST = "
				+ "'" + secName + "'";// idStore
		pstmt = conn.prepareStatement(querySelectInfo);
		rset = pstmt.executeQuery(querySelectInfo);

		String info = "";
		ResultSetMetaData rsmd = pstmt.getMetaData();

		int columnsNumber = rsmd.getColumnCount();

		while (rset.next()) {
			info += "Время: " + rset.getString(1) + ",";
			info += " адресс: " + rset.getString(2) + ",";
			info += " инструктор: " + rset.getString(3) + "\n";

		}
		return info;
	}

	public String getInfoOrder() throws SQLException {// Получение инфомрации

		ObservableList<String> stInfo = FXCollections.observableArrayList();

		String querySelect = "SELECT SECOND_NAME FROM COACH WHERE PHONE = " + phone;// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		String id_coach = rset.getString(1);

		String querySelectInfo = "SELECT TIME_ORDER, ADRESS,SECOND_NAME_ST FROM ORDER_ST WHERE SECOND_NAME_COACH  = "
				+ "'" + id_coach + "'";// idStore
		pstmt = conn.prepareStatement(querySelectInfo);
		rset = pstmt.executeQuery(querySelectInfo);

		String info = "";
		ResultSetMetaData rsmd = pstmt.getMetaData();

		int columnsNumber = rsmd.getColumnCount();

		while (rset.next()) {
			info += "Время: " + rset.getString(1) + ",";
			info += " адресс: " + rset.getString(2) + ",";
			info += " фамилия: " + rset.getString(3) + "\n";

		}
		return info;
	}

	public void updateData(String newName, String newSecond, long newPhone, String newPass, String newSchool)
			throws SQLException {// Изменение информации

		String querySelect = "SELECT ID FROM STUDY WHERE PHONE = " + phone;// idStore
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id_st = rset.getInt(1);

		System.out.println(address);
		String querySelectStoreForPr = "SELECT ID FROM INFO_AUTO WHERE ADDRESS = " + "'" + newSchool + "'";// idStore
		pstmt = conn.prepareStatement(querySelectStoreForPr);
		rset = pstmt.executeQuery(querySelectStoreForPr);
		rset.next();
		int IdP = rset.getInt("ID");

		String queryUpdateVolume = "UPDATE STUDY SET NAME = " + "'" + newName + "'" + ",SECOND_NAME = " + "'"
				+ newSecond + "'" + ",PHONE = " + newPhone + ",ID_SCHOOL = " + "'" + IdP + "'" + ",PASSWORD = " + "'"
				+ newPass + "'" + " WHERE ID = " + "'" + id_st + "'";// Update
		Statement stm = conn.createStatement();
		stm.executeUpdate(queryUpdateVolume);

	}

	public void cansel(String sec_n) throws SQLException {// Добавление отказа
		String queryDelete = "DELETE FROM ORDER_ST WHERE SECOND_NAME_ST = " + "'" + sec_n + "'";// Delete item
		pstmt = conn.prepareStatement(queryDelete);
		// rset = pstmt.executeQuery(queryDelete);
		pstmt.executeUpdate();

	}

}
