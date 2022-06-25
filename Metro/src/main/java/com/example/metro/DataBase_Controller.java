package com.example.metro;
//Подключаем необходимые библиотеки для работы

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

public class DataBase_Controller {
    //Создаем необходимые для работы базы данных переменные
    private static Connection conn;
    private static PreparedStatement pstmt;
    PreparedStatement pstmtMetro;
    ResultSet rsetMetro;
    private static ResultSet rset;
    private Button btModSalary;
    static Statement statement = null;

    static ObservableList<String> metro = FXCollections.observableArrayList();//Лист с названием метро
    static ObservableList<String> places = FXCollections.observableArrayList();//Лист с названием места

    public static void initialize() {// Инициализация и подключение бд
        String username = "Metro";//Имя подключения и пароль
        String password = "1234";
        try {
            OracleDataSource ods = new OracleDataSource();//Создаем новое подключение
            ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:" + username + "/" + password + "@localhost:1521/orc");//Устанавливаем информацию для подключения
            conn = ods.getConnection();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("Connect");//Если успешное подключение, то пишем об этом
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ObservableList<String> getPlace(String keyword) throws SQLException {// Метод для получение информации о месте и метро, которое находится рядом
        String query = "SELECT NAME,ROAD_TO_METRO,DEFINITION,ID_METRO,TIME_WORK,ADDRESS FROM INTERESTING_PLACE WHERE KEYWORDS LIKE LOWER ('%" + keyword + "%')";
        //Запрос для получения информации из БД
        pstmt = conn.prepareStatement(query);//Выполняем запрос
        rset = pstmt.executeQuery();
        while (rset.next()) {//Проходим по каждой полученной строке
            long id_metro = rset.getLong(4);//ID метро из таблицы с местами
            String queryMetro = "SELECT NAME FROM METRO WHERE ID = " + id_metro;
            pstmtMetro = conn.prepareStatement(queryMetro);
            rsetMetro = pstmtMetro.executeQuery();
            rsetMetro.next();
            String nameMetro = rsetMetro.getString(1);//Получаем название метро

            places.add(rset.getString(1) + " расстояние до метро: " + rset.getLong(2) + " описание: " + rset.getString(3)
                    + " метро: " + nameMetro + " время работы: " + rset.getString(5) + " адрес: " + rset.getString(6));
            //Добавление информации из базы данных
        }

        return places;
    }
}
