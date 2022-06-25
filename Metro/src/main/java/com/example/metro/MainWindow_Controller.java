package com.example.metro;

//Подключаем необходимые библиотеки для работы

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainWindow_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Accordion places;// Панель для добавления

    @FXML
    private Text error;// Текст с ошибкой

    @FXML
    private Button find;// Кнопка для поиска

    @FXML
    private TextField keyword;// Введенный текст в поле с ключевым словом

    static ObservableList<String> place = FXCollections.observableArrayList();// Лист с местами и информацией

    DataBase_Controller db = new DataBase_Controller();// Создание нового объекта класса с базой данных

    static ObservableList<String> metros = FXCollections.observableArrayList();// Лист с названием метро

    @FXML
    void initialize() {// Метод при инициализации класса
        error.setVisible(false);// Устанавалием текст с ошибкой невидимым
        find.setOnAction(event -> {// Дейсвтие при нажатии кнопки
            if (places.getPanes().size() > 0) {// Проверка на наличие уже созданных панелей, если они есть, то удаляем их
                places.getPanes().clear();
                place.removeAll(place);
            }
            try {
                place = db.getPlace(keyword.getText());// Получаем информацию из юазы данных
                int sizeArray = place.size();// Количесвто полученной информации
                if (!keyword.getText().equals("") && place.size() != 0 && keyword.getText().length() >= 4) {// Проверка для ввода
                    error.setVisible(false);
                    for (int i = 0; i < sizeArray; i++) {// Цикл для создания панелей с информацией
                        TitledPane t = new TitledPane();
                        String namePlace = new String(place.get(i).split(" расстояние до метро: ")[0]);// Получаем название места
                        String definition = new String(db.getPlace(keyword.getText()).get(i).split(" описание: ")[1].split(" метро: ")[0]);
                        // Получаем описание места
                        String nameMetroDef = place.get(i).split(" метро: ")[2].split(" время работы: ")[0];// Получаем название метро
                        String road = new String(place.get(i).split(" расстояние до метро: ")[1].split(" описание: ")[0]);
                        // Получаем расстояние до места от метро
                        String time = new String(place.get(i).split(" время работы: ")[1].split(" адрес: ")[0]);// Получаем время работы
                        String address = new String(place.get(i).split(" адрес: ")[1]);// Получаем адрес места
                        Text def = new Text();// Создаем текст
                        t.setPrefWidth(places.getWidth());
                        t.setText(namePlace);// Устанавливаем название метса
                        def.setText("Краткое описание:  " + definition + "\n\n" + "Находится на расстоянии " + road + " метров от станции метро " + nameMetroDef +
                                "\n\n" + time + "\n\n" + "Адрес: " + address);// Устанавливаем всю необходимую информацию о месте
                        def.setWrappingWidth(480);// Устанавливаем ширину текста
                        t.setContent(def);// Устанавливаем текст в панели
                        VBox box1 = new VBox(15);
                        box1.setPadding(new Insets(10));
                        box1.getChildren().addAll(t);// Добавляем текст в контейнер
                        TitledPane nameMetro = new TitledPane(place.get(i).split(" метро: ")[2].split(" время работы: ")[0], box1);
                        // Устанавливаем название метро
                        places.getPanes().add(nameMetro);// Создаем панель со свей информацией
                    }
                } else {
                    place.removeAll(place);// Удаляем уже использованную информацию
                    error.setVisible(true);
                }
            } catch (SQLException E) {
                E.printStackTrace();
            }

        });


    }

}
