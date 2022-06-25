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

public class Order_controller {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> Masters;

	@FXML
	private ComboBox<String> Address;

	@FXML
	private Button back;

	@FXML
	private ComboBox<String> Time;

	@FXML
	private Button choise;

	static ObservableList<String> time = FXCollections.observableArrayList();
	static ObservableList<String> coach = FXCollections.observableArrayList();
	static ObservableList<String> address = FXCollections.observableArrayList();

	ControleDataBase db = new ControleDataBase();

	@FXML
	void initialize() throws SQLException {
			addTime();
			addCoach();
			addAdress();

		FXMLLoader loader = new FXMLLoader();

		back.setOnAction(event -> {// ������������� ������ ��� ����� ������������
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("StyduMainWindow.fxml"));

			Loader(loader);
			Controller_Study.oneTime = false;
		});

		choise.setOnAction(event -> {// ������������� ������ ��� ����� ������������
			db.addOrder(getTime(), getCoach(0), getCoach(1), getAddress());
			db.addHist(getTime(), getCoach(0), getCoach(1), getAddress());
			choise.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("SuccesAdd.fxml"));
			Loader(loader);
			Controller_Study.oneTime = false;

		});

	}

	public void addTime() throws SQLException {// ���������� �������
		try {
			time = ControleDataBase.getTime();
			Time.setItems(time);
			Time.setValue(time.get(0));
		} catch (Exception e) {
			Time.setValue("������ ���");
		}

	}

	public void addCoach() throws SQLException {// ���������� �������
		try {
			coach = ControleDataBase.getCoach();
			Masters.setItems(coach);
			Masters.setValue(coach.get(0));
		} catch (Exception e) {
			Masters.setValue("������ ���");
		}

	}

	public void addAdress() throws SQLException {// ���������� �������
		try {
			address = ControleDataBase.getAddressTrein();
			Address.setItems(address);
			Address.setValue(address.get(0));
		} catch (Exception e) {
			Address.setValue("������ ���");
		}

	}

	public String getTime() {// ��������� �������� ������
		String adress = Time.getSelectionModel().getSelectedItem();
		return adress;
	}

	public String getCoach(int id) {// ��������� �������� ������
		String adress = Masters.getSelectionModel().getSelectedItem();
		String[] adressSpl = adress.split(",");
		return adressSpl[id];
	}

	public String getAddress() {// ��������� �������� ������
		String adress = Address.getSelectionModel().getSelectedItem();
		return adress;
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
