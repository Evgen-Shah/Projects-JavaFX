package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class service_driver {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> Service_choise;

	@FXML
	private ComboBox<String> Address;

	@FXML
	private Button back;

	@FXML
	private ComboBox<String> Time;

	@FXML
	private ComboBox<String> Masters;

	@FXML
	private RadioButton hard;

	@FXML
	private Button choise;

	@FXML
	private RadioButton dust;

	@FXML
	private RadioButton water;

	@FXML
	private ToggleGroup group;

	static ObservableList<String> services = FXCollections.observableArrayList();
	static ObservableList<String> times = FXCollections.observableArrayList();
	static ObservableList<String> masters = FXCollections.observableArrayList();
	static ObservableList<String> address = FXCollections.observableArrayList();

	DataBaseController db = new DataBaseController();

	public void addName() throws SQLException {// ���������� ����� 
		try {
			services = DataBaseController.getNameService();
			Service_choise.setItems(services);
			Service_choise.setValue(services.get(0));
		} catch (Exception e) {
			Service_choise.setValue("������ ���");
		}

	}

	public void addTime() throws SQLException {// ����������  �������
		try {
			times = DataBaseController.getTime();
			Time.setItems(times);
			Time.setValue(times.get(0));
		} catch (Exception e) {
			Time.setValue("������ ���");
		}

	}

	public void addMasters() throws SQLException {// ���������� ��������
		try {
			masters = DataBaseController.getMasters();
			Masters.setItems(masters);
			Masters.setValue("���������");
		} catch (Exception e) {
			Masters.setValue("������ ���");
		}

	}

	public void addAdress() throws SQLException {// ���������� �������
		try {
			address = DataBaseController.getAddress();
			Address.setItems(address);
			Address.setValue(address.get(0));
		} catch (Exception e) {
			Address.setValue("������ ���");
		}

	}

	public String getNameService() {// ��������� �������� ������
		String name = Service_choise.getSelectionModel().getSelectedItem();
		return name;
	}

	public String getTime() {// ��������� �������
		String name = Time.getSelectionModel().getSelectedItem();
		return name;
	}

	public String getAddress() {// ��������� ������
		String name = Address.getSelectionModel().getSelectedItem();
		return name;
	}

	public String getMasters() {// ��������� ������ ��������
		String name = Masters.getSelectionModel().getSelectedItem();
		if (name.equals("���������")) {
			Random ran = new Random();
			int index = ran.nextInt(3);
			return masters.get(index);
		}
		return name;
	}

	@FXML
	void initialize() throws SQLException {
		addName();
		addTime();
		addMasters();
		addAdress();
		FXMLLoader loader = new FXMLLoader();
		back.setOnAction(event -> {// ������������� ������ ��� ����
			back.getScene().getWindow().hide();
			Choise_Driver.oneTimeAddName = false;

			loader.setLocation(getClass().getResource("ChoiseWindow.fxml"));

			Loader(loader);
		});

		choise.setOnAction(event -> {// ������������� ������ ��� ������
			Random ran = new Random();
			int box = ran.nextInt(20);
			System.out.println(getMasters());
			choise.getScene().getWindow().hide();
			dust.setToggleGroup(group);
			water.setToggleGroup(group);
			hard.setToggleGroup(group);
			Choise_Driver.oneTimeAddName = false;
			loader.setLocation(getClass().getResource("add_ok.fxml"));
			RadioButton selection = (RadioButton) group.getSelectedToggle();
			Loader(loader);
			try {
				db.addOrders(getNameService(), selection.getText(), getTime(), box, getAddress());
				db.addHistory(getNameService(), selection.getText(), getTime(), box, getMasters(), getAddress());
			} catch (Exception e) {
				try {
					db.addOrders(getNameService(), "������", getTime(), box, getAddress());
					db.addHistory(getNameService(), "������", getTime(), box, getMasters(), getAddress());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

	}

	public void Loader(FXMLLoader loader) {
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
