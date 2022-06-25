package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.pool.OracleDataSource;

public class database {

	private static Connection conn;
	static Statement statement = null;
	public static ObservableList<String> subs = FXCollections.observableArrayList();
	public static ObservableList<String> filmsId = FXCollections.observableArrayList();
	public static ObservableList<String> theme = FXCollections.observableArrayList();
	public static ObservableList<String> filmsName = FXCollections.observableArrayList();
	static ObservableList<String> activesClosed = FXCollections.observableArrayList();
	static ObservableList<String> activesOpen = FXCollections.observableArrayList();
	private static PreparedStatement pstmt;
	private static ResultSet rset;

	public static void initializeDatabase() {// Инициализация и подключение бд
		String username = "Film";
		String password = "1111";
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

	public int generaterIdUsers() throws SQLException {// Генерация id для пользователя
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
	
	public boolean getCheckHpone(String phone) throws SQLException {// Проврека пароля

		try {
			String querySelect = "SELECT PHONE FROM USERS WHERE PHONE = " + "'" + phone + "'";// carCheck
			pstmt = conn.prepareStatement(querySelect);
			rset = pstmt.executeQuery(querySelect);
			rset.next();
			String pass = rset.getString(1);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public int generaterIdCard() throws SQLException {// Генерация id для CARDS
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT ID FROM CARDS ORDER BY ID";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet = statement.executeQuery(query);
			resultSet.last();
			int count = resultSet.getInt(1);
			return count;
		} catch (SQLException e) {
			return 0;
		}
	}

	public static ObservableList<String> getSub() throws SQLException {// Получение
		String query = "SELECT TERM,PRICE FROM SUB";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next()) {
			subs.add(rset.getString(1) + " " + rset.getString(2));
		}
		return subs;
	}

	public static ObservableList<String> getTheme() throws SQLException {// Получение
		String query = "SELECT THEME FROM APPEALS";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next()) {
			theme.add(rset.getString(1));
		}
		return theme;
	}

	public static String getAns(String theme) throws SQLException {// Получение
		String query = "SELECT ANS FROM APPEALS WHERE THEME = " + "'" + theme + "'";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		rset.next();
		String sub = rset.getString(1);
		return sub;
	}

	public static String getNumberCard() throws SQLException {// Получение
		String querySelect = "SELECT ID FROM USERS WHERE PHONE = " + "'" + registration_.numberPhone + "'";//
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id = rset.getInt(1);
		String sub ="";
		try {
			String query = "SELECT NUMBER_CARD FROM CARDS WHERE ID_USERS = " + "'" + id + "'";

			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			rset.next();
			sub = rset.getString(1);
		} catch (SQLException e) {
			return "";
		}
		return sub;
	}

	public static ObservableList<String> getFilmsUsers() throws SQLException {// Получение
		String querySelect = "SELECT ID FROM USERS WHERE PHONE = " + "'" + registration_.numberPhone + "'";//
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id = rset.getInt(1);

		String query = "SELECT ID_FILMS FROM BUYER_FILMS WHERE ID_CLIENTS = " + "'" + id + "'";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next()) {
			filmsId.add(rset.getString(1));
		}

		for (int i = 0; i < filmsId.size(); i++) {
			String query2 = "SELECT NAME FROM FILM WHERE ID = " + "'" + filmsId.get(i) + "'";
			pstmt = conn.prepareStatement(query2);
			rset = pstmt.executeQuery();
			rset.next();
			filmsName.add(rset.getString(1));

		}

		return filmsName;
	}

	public static ObservableList<String> getFilms(String genre, String reit, long price) throws SQLException {// Получение
		String query = "SELECT NAME,PRICE FROM FILM WHERE GENRE = " + "'" + genre + "'" + "AND RAIT = " + "'" + reit
				+ "'" + "AND PRICE <" + price;
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while (rset.next()) {
			subs.add(rset.getString(1) + " цена: " + rset.getString(2));
		}
		return subs;
	}

	public static String getUsersSub() throws SQLException {// Получение
		String querySelect = "SELECT ID FROM USERS WHERE PHONE = " + "'" + registration_.numberPhone + "'";//
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id = rset.getInt(1);

		String query = "SELECT SUB FROM USERS WHERE ID = " + "'" + id + "'";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		rset.next();
		String sub = rset.getString(1);
		return sub;
	}

	public static String getMoney() throws SQLException {// Получение
		String querySelect = "SELECT ID FROM USERS WHERE PHONE = " + "'" + registration_.numberPhone + "'";//
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id = rset.getInt(1);

		String query = "SELECT MONEY FROM USERS WHERE ID = " + "'" + id + "'";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		rset.next();
		String mn = rset.getString(1);
		return mn;
	}

	public void addClients(long phone, String fullName, String pass) throws SQLException {// добавление пользователя
		int idClients = generaterIdUsers();
		String query = "INSERT INTO USERS(ID,PHONE,FULL_NAME,PASSWORD,SUB,MONEY) VALUES (?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, ++idClients);// ID
		pstmt.setLong(2, phone);// PHONE
		pstmt.setString(3, fullName);// FULL_NAME
		pstmt.setString(4, pass);// PASSWORD
		pstmt.setString(5, "0");// SUB
		pstmt.setLong(6, 0);// mn
		pstmt.executeUpdate();
	}

	public void addCard( long balace, String actions) throws SQLException {// добавление пользователя
		String querySelect = "SELECT ID FROM USERS WHERE PHONE = " + "'" + registration_.numberPhone + "'";//
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id = rset.getInt(1);

		long idCard = generaterIdCard();
		String query = "INSERT INTO CARDS(ID,ID_USERS, BALANCE,ACTION) VALUES (?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setLong(1, ++idCard);// ID
		pstmt.setLong(2, id);// FULL_NAME
		pstmt.setLong(3, balace);// balance
		pstmt.setString(4, actions);// action
		pstmt.executeUpdate();
	}

	public void addFilms(String nameFilms) throws SQLException {// добавление пользователя

		String querySelect = "SELECT ID FROM USERS WHERE PHONE = " + "'" + registration_.numberPhone + "'";//
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int idClients = rset.getInt(1);

		String querySelect2 = "SELECT ID FROM FILM WHERE NAME = " + "'" + nameFilms + "'";//
		pstmt = conn.prepareStatement(querySelect2);
		rset = pstmt.executeQuery(querySelect2);
		rset.next();
		int idFilms = rset.getInt(1);

		String query = "INSERT INTO BUYER_FILMS(ID_CLIENTS,ID_FILMS) VALUES (?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, idClients);// cl
		pstmt.setLong(2, idFilms);// fm
		pstmt.executeUpdate();
	}

	public Object checkNumber(String phone) {
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

	public void updateSub(String sub) throws SQLException {
		String querySelect = "SELECT ID FROM USERS WHERE PHONE = " + "'" + registration_.numberPhone + "'";//
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id = rset.getInt(1);

		String queryUpdateVolume = "UPDATE USERS SET SUB = " + "'" + sub + "'" + " WHERE ID = " + "'" + id + "'";//
		Statement stm = conn.createStatement();
		stm.executeUpdate(queryUpdateVolume);

	}

	public void updateMoney(long money) throws SQLException {
		String querySelect = "SELECT ID FROM USERS WHERE PHONE = " + "'" + registration_.numberPhone + "'";//
		pstmt = conn.prepareStatement(querySelect);
		rset = pstmt.executeQuery(querySelect);
		rset.next();
		int id = rset.getInt(1);

		String queryUpdateVolume = "UPDATE USERS SET MONEY = " + "'" + money + "'" + " WHERE ID = " + "'" + id + "'";//
		Statement stm = conn.createStatement();
		stm.executeUpdate(queryUpdateVolume);

	}

}
