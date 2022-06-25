package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class Info_scates {

	public static boolean oneTimes = true;
	static ObservableList<String> timesPinks = FXCollections.observableArrayList();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button back;

	@FXML
	private Button choise;

	@FXML
	private ComboBox<String> descr;

	BaseControl bs = new BaseControl();

	@FXML
	void initialize() {
		FXMLLoader loader = new FXMLLoader();
		try {
			addTime();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		choise.setOnAction(event -> {// Выбор
			choise.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Renta.fxml"));
			LoaderFXML(loader);
			oneTimes = false;
			Renta_control.addOne = true;
			setter();
		});

		back.setOnAction(event -> {// Назад
			back.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("Options.fxml"));
			LoaderFXML(loader);
			oneTimes = false;
		});

	}

	public void addTime() throws SQLException {// Добавление времени
		try {
			timesPinks = BaseControl.getDescriptions();
			descr.setItems(timesPinks);
			descr.setValue(timesPinks.get(0));
		} catch (Exception e) {
			descr.setValue("Ничего нет");
		}

	}

	String name;
	static String[] phone;

	public void setter() {
		name = descr.getSelectionModel().getSelectedItem();
		System.out.println(name);
		phone = name.split(" ");
		System.out.println(phone[3]);
	}

	public static long getPhone() {// ПОлучение времени
		return Long.parseLong(phone[3]);
	}

	public void LoaderFXML(FXMLLoader loader) {
		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Parent root = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}
}
