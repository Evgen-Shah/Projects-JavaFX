package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import oracle.jdbc.pool.OracleDataSource;

public class Base {

	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rset;

	static Statement statement = null;

	static ObservableList<String> inteship = FXCollections.observableArrayList();
	static ObservableList<String> ans = FXCollections.observableArrayList();
	static ObservableList<String> ans2 = FXCollections.observableArrayList();

	public static void initializeDatabase() {// Инициализация и подключение бд
		String username = "Intership";
		String password = "1111";
		try {

			OracleDataSource ods = new OracleDataSource();
			ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:" + username + "/" + password + "@localhost:1521/orc");
			conn = ods.getConnection();
			System.out.println("Connection");
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public int generaterIduser() throws SQLException {// Генерация id для клиента
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT ID FROM USERS ORDER BY ID";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			int count = resultSet.getInt(1);
			return count;
		} catch (SQLException e) {
			return 0;
		}
	}

	public int generaterIdAns() throws SQLException {// Генерация id для клиента
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT ID FROM ANS ORDER BY ID";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			int count = resultSet.getInt(1);
			return count;
		} catch (SQLException e) {
			return 0;
		}
	}

	public int generaterIdIntership() throws SQLException {// Генерация id для клиента
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT ID FROM INTERSHIP ORDER BY ID";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			int id = resultSet.getInt(1);
			return id;
		} catch (SQLException e) {
			return 0;
		}
	}

	public int generaterIdBid() throws SQLException {// Генерация id для клиента
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT ID FROM BID ORDER BY ID";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			int count = resultSet.getInt(1);
			return count;
		} catch (SQLException e) {
			return 0;
		}
	}
	

	public int generaterIdCanc() throws SQLException {// Генерация id для клиента
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT ID FROM CANCEL ORDER BY ID";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			int count = resultSet.getInt(1);
			return count;
		} catch (SQLException e) {
			return 0;
		}
	}


	public Object checkPass(String phone) {
		try {
			String querySelect = "SELECT PASSWORD FROM USERS WHERE PHONE = " + "'" + phone + "'";//
			pstmt = conn.prepareStatement(querySelect);
			rset = pstmt.executeQuery(querySelect);
			rset.next();
			String pass = rset.getString(1);
			return pass;
		} catch (SQLException e) {
			return " ";
		}
	}

	public Object checkCompany(String company) {
		try {
			String querySelect = "SELECT PASS FROM COMPANY WHERE LOGIN = " + "'" + company + "'";//
			pstmt = conn.prepareStatement(querySelect);
			rset = pstmt.executeQuery(querySelect);
			rset.next();
			String pass = rset.getString(1);
			return pass;
		} catch (SQLException e) {
			return " ";
		}
	}

	public static ObservableList<String> getInter() throws SQLException {// Получение
		String query = "SELECT NAME,COMPANY FROM INTERNSHIP";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next()) {
			inteship.add(rset.getString(1) + " : " + rset.getString(2));
		}
		return inteship;
	}

	public static ObservableList<String> getAnsCompany() throws SQLException {// Получение
		String query = "SELECT ID_USERS FROM ANS WHERE COMPANY = " + "'" + Entry_Reg.adminName + "'";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		rset.next();
		int id = rset.getInt(1);

		String query2 = "SELECT FULLNAME FROM USERS WHERE ID = " + "'" + id + "'";
		PreparedStatement pstmt2 = conn.prepareStatement(query2);
		ResultSet rset2 = pstmt2.executeQuery();
		rset2.next();
		String name = rset2.getString(1);

		String query3 = "SELECT INTERSHIP,ANS FROM ANS WHERE COMPANY = " + "'" + Entry_Reg.adminName + "'";
		pstmt = conn.prepareStatement(query3);
		rset = pstmt.executeQuery();
		while (rset.next()) {
			ans.add(name + " стажировка: " + rset.getString(1) + " статус: " + rset.getString(2));
		}
		return ans;
	}
	
	public static String checkStatus(String intership) throws SQLException {// Получение
		String stst;
		String query = "SELECT ID FROM USERS WHERE PHONE = " + "'" + Registration.phones + "'";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		rset.next();
		int id = rset.getInt(1);

		String query3 = "SELECT ANS FROM ANS WHERE ID_USERS = " + "'" + id + "'" + "AND INTERSHIP =" +"'"+ intership + "'" ;
		pstmt = conn.prepareStatement(query3);
		rset = pstmt.executeQuery();
		rset.next();
		stst = rset.getString(1);
		
		return stst;
	}

	public static ObservableList<String> getAnsUser() throws SQLException {// Получение
		String query = "SELECT ID FROM USERS WHERE PHONE = " + "'" + Registration.phones + "'";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		rset.next();
		int id = rset.getInt(1);

		String query3 = "SELECT INTERSHIP,COMPANY,ANS FROM ANS WHERE ID_USERS = " + "'" + id + "'";
		pstmt = conn.prepareStatement(query3);
		rset = pstmt.executeQuery();
		
		while (rset.next()) {
			ans2.add(" стажировка: " + rset.getString(1) + " компания: " + rset.getString(2));
		}
		return ans2;
	}

	
	public static ObservableList<String> getInterUser() throws SQLException {// Получение
		String querySelect = "SELECT ID FROM USERS WHERE PHONE = " + "'" + Registration.phones + "'";//
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id = rset.getInt(1);

		String query = "SELECT INTERSHIP,COMPANY FROM BID WHERE ID_USERS = " + "'" + id + "'";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next()) {
			inteship.add(" стажировка: " + rset.getString(1) + " компания: " + rset.getString(2));
		}
		return inteship;
	}

	public static ObservableList<String> getBid() throws SQLException {// Получение
		String query = "SELECT PHONE_USER FROM BID WHERE COMPANY = " + "'" + Entry_Reg.adminName + "'";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		rset.next();
		long phoneU = rset.getLong(1);

		String query2 = "SELECT FULLNAME FROM USERS WHERE PHONE = " + "'" + phoneU + "'";
		PreparedStatement pstmt2 = conn.prepareStatement(query2);
		ResultSet rset2 = pstmt2.executeQuery();
		rset2.next();
		String name = rset2.getString(1);

		String query3 = "SELECT INTERSHIP,COMPET,PHONE_USER FROM BID WHERE COMPANY = " + "'" + Entry_Reg.adminName
				+ "'";
		pstmt = conn.prepareStatement(query3);
		rset = pstmt.executeQuery();

		while (rset.next()) {
			inteship.add(name + " стажировка: " + rset.getString(1) + " компетенции: " + rset.getString(2)
					+ " телефон: " + rset.getLong(3));
		}
		return inteship;
	}

	public static ObservableList<String> getInterForCompany() throws SQLException {// Получение
		String query = "SELECT NAME FROM INTERNSHIP WHERE COMPANY = " + "'" + Entry_Reg.adminName + "'";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next()) {
			inteship.add(rset.getString(1));
		}
		return inteship;
	}

	

	public static void exp() throws SQLException {// Получение
		String query = "SELECT NAME FROM test WHERE COMPANY = " + "'" + Entry_Reg.adminName + "'";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		rset.next();

	}
	
	public static void deteleDouplicate() {
		inteship.removeAll(inteship);
	}

	public void addClients(String fullName, long phone, String bith, String pass) throws SQLException {// добавление
																										// пользователя
		int idClients = generaterIduser();
		String query = "INSERT INTO USERS(ID,FULLNAME,PHONE,BIRH,PASSWORD) VALUES (?,?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idClients);// ID
		pstmt.setString(2, fullName);// FULL_NAME
		pstmt.setLong(3, phone);// phone
		pstmt.setString(4, bith);// birth
		pstmt.setString(5, pass);// pass
		pstmt.executeUpdate();
	}

	public void addBid(String intership, String company, String compet) throws SQLException {// добавление пользователя
		String querySelect = "SELECT ID FROM USERS WHERE PHONE = " + "'" + Registration.phones + "'";//
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id = rset.getInt(1);

		int idBid = generaterIdBid();
		String query = "INSERT INTO BID(ID,ID_USERS,INTERSHIP,COMPANY,COMPET,PHONE_USER) VALUES (?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idBid);// ID
		pstmt.setInt(2, id);// FULL_NAME
		pstmt.setString(3, intership);// phone
		pstmt.setString(4, company);// birth
		pstmt.setString(5, compet);// pass
		pstmt.setString(6, Registration.phones);// PHONE
		pstmt.executeUpdate();
	}

	public void addIntership(String intership) throws SQLException {// добавление пользователя

		int idInter = generaterIdIntership();
		String query = "INSERT INTO INTERNSHIP (ID,NAME,COMPANY) VALUES (?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idInter);// ID
		pstmt.setString(2, intership);// FULL_NAME
		pstmt.setString(3, Entry_Reg.adminName);// phone

		pstmt.executeUpdate();
	}
	
	public void addCancel(String intership, String company) throws SQLException {// добавление пользователя
		String querySelect = "SELECT ID FROM USERS WHERE PHONE = " + "'" + Registration.phones + "'";//
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id = rset.getInt(1);

		int idInter = generaterIdCanc();
		String query = "INSERT INTO CANCEL (ID,ID_USERS,INTERSHIP,COMPANY) VALUES (?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idInter);// ID
		pstmt.setLong(2, id);// FULL_NAME
		pstmt.setString(3, intership);// phone
		pstmt.setString(4, company);// phone

		pstmt.executeUpdate();
	}

	public void addAnser(String ans, String intership, String phone) throws SQLException {// добавление пользователя

		String querySelect = "SELECT ID FROM USERS WHERE PHONE = " + "'" + phone + "'";//
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id = rset.getInt(1);

		int idAns = generaterIdAns();
		String query = "INSERT INTO ANS (ID,INTERSHIP,ID_USERS,ANS,COMPANY) VALUES (?,?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idAns);// ID
		pstmt.setString(2, intership);// FULL_NAME
		pstmt.setInt(3, id);// phone
		pstmt.setString(4, ans);// ANS
		pstmt.setString(5, Entry_Reg.adminName);// ANS

		pstmt.executeUpdate();
	}

	public void deleteBid(String choise) throws SQLException {// Удаление
		String querySelect = "SELECT ID FROM USERS WHERE PHONE = " + "'" + Registration.phones + "'";//
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id = rset.getInt(1);

		String queryDelete = "DELETE FROM BID WHERE ID_USERS = " + "'" + id + "'" + " AND " + "INTERSHIP = " + "'"
				+ choise + "'";// Delete item
		pstmt = conn.prepareStatement(queryDelete);
		pstmt.executeUpdate();

	}

	public void deleteBidForCompany(String choise, String phone) throws SQLException {// Удаление

		String queryDelete = "DELETE FROM BID WHERE INTERSHIP = " + "'" + choise + "'" + " AND " + "PHONE_USER = " + "'"
				+ phone + "'";// Delete item
		pstmt = conn.prepareStatement(queryDelete);
		pstmt.executeUpdate();

	}

	public void deletInter(String choise) throws SQLException {// Удаление
		String querySelect = "SELECT ID FROM COMPANY WHERE LOGIN = " + "'" + Entry_Reg.adminName + "'";//
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id = rset.getInt(1);

		String queryDelete = "DELETE FROM INTERNSHIP WHERE COMPANY = " + "'" + Entry_Reg.adminName + "'" + " AND "
				+ "NAME = " + "'" + choise + "'";// Delete item
		pstmt = conn.prepareStatement(queryDelete);
		pstmt.executeUpdate();

	}

	public void updateStatus(String status, String choise) throws SQLException {
		String querySelect = "SELECT ID FROM ANS WHERE COMPANY = " + "'" + Entry_Reg.adminName + "'" + " AND "
				+ "INTERSHIP = " + "'" + choise + "'";//
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id = rset.getInt(1);

		String queryUpdateVolume = "UPDATE ANS SET ANS = " + "'" + status + "'" + " WHERE ID = " + "'" + id + "'";//
		Statement stm = conn.createStatement();
		stm.executeUpdate(queryUpdateVolume);

	}
}
