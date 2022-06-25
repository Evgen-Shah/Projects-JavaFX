package com.example.metro;
//Подключаем необходимые библиотеки для работы

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {// Основной метод приложения

    @Override
    public void start(Stage primaryStage) {// Выполнения метода при запуске приложения
        try {
            DataBase_Controller.initialize();// Подключение к базе данных
            Parent root = FXMLLoader.load(Main.class.getResource("MainWindow.fxml"));// Загружаем выбранный fxml
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();// Показываем окно
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch();
    }
}