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

public class Cancel_control {

	public static boolean addOneTime = true;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> rentas;

	@FXML
	private Button Cancel;

	@FXML
	private Button back;

	BaseControl bs = new BaseControl();

	static ObservableList<String> order = FXCollections.observableArrayList();

	@FXML
	void initialize() throws SQLException {
		addOrder();
		FXMLLoader loader = new FXMLLoader();
		back.setOnAction(event -> {// Назад
			back.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("Options.fxml"));
			LoaderFXML(loader);
			addOneTime = false;
			try {
				clear();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Cancel.setOnAction(event -> {// Отмена
			Cancel.getScene().getWindow().hide();

			loader.setLocation(getClass().getResource("Options.fxml"));
			LoaderFXML(loader);
			addOneTime = false;
			try {
				String[] number = getTime().split(" ");
				int id = Integer.parseInt(number[2]);
				bs.addCancel(id);
				clear();
			} catch (SQLException | IndexOutOfBoundsException ea) {
				loader.setLocation(getClass().getResource("Options.fxml"));
			}
		});
	}

	public void addOrder() throws SQLException {// Добавление заказа
		try {
			order = BaseControl.getOrder();
			rentas.setItems(order);
			rentas.setValue(order.get(0));
		} catch (Exception e) {
			rentas.setValue("Ничего нет");
		}

	}

	public void clear() throws SQLException {// Очистка
		rentas.getItems().clear();
	}

	public String getTime() {// ПОлучение времени
		String name = rentas.getSelectionModel().getSelectedItem();
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
