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

public class Renta_control {

	BaseControl bs = new BaseControl();

	public static boolean addOne = true;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

//	@FXML
//	private ComboBox<String> Company;
//
//	@FXML
//	private ComboBox<String> Address;

	@FXML
	private ComboBox<String> Size;

//	@FXML
//	private ComboBox<String> Color;

	@FXML
	private Button back;

	@FXML
	private Button reg;

	Info_scates is = new Info_scates();

	static ObservableList<String> timesPinks = FXCollections.observableArrayList();

	@FXML
	void initialize() throws SQLException {
		addTime();
		FXMLLoader loader = new FXMLLoader();
		back.setOnAction(event -> {// Назад
			back.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("information_scate.fxml"));
			LoaderFXML(loader);
			addOne = false;

		});

		reg.setOnAction(event -> {// Регистрация заказа
			try {
				String[] elements = getTime().split(" ");
				reg.getScene().getWindow().hide();
				bs.addOrders(Integer.parseInt(elements[6]), Integer.parseInt(elements[9]), elements[2],
						Info_scates.getPhone());
				
				loader.setLocation(getClass().getResource("TimeFree.fxml"));
				LoaderFXML(loader);
				addOne = false;
				time_control.addOne = true;

			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
	}

	public void addTime() throws SQLException {// Добавление времени
		try {
			timesPinks = BaseControl.getSize();
			Size.setItems(timesPinks);
			Size.setValue(timesPinks.get(0));
		} catch (Exception e) {
			Size.setValue("Ничего нет");
		}

	}

	public String getTime() {// ПОлучение времени
		String name = Size.getSelectionModel().getSelectedItem();
		return name;

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
