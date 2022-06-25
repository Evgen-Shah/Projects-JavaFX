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
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Whos_click {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button add;

	@FXML
	private Button yep;

	@FXML
	private Button nope;

	boolean yesOrNo = false;

	@FXML
	private ChoiceBox<String> Whos;

	public static ObservableList<String> states = FXCollections.observableArrayList();

	public static ObservableList<String> statesOrg = FXCollections.observableArrayList();

	@FXML
	private Button back;

	Base base = new Base();

	accs ac = new accs();

	@FXML
	void initialize() throws SQLException {
		FXMLLoader loader = new FXMLLoader();

		GetName();
		yep.setOnAction(event -> {// Назад
			String choise = Whos.getSelectionModel().getSelectedItem();
			try {
				base.addAnser("Принят ", choise.split(" компетенции: ")[0].split(" стажировка: ")[1],
						choise.split(" телефон: ")[1]);
				base.deleteBidForCompany(choise.split(" компетенции: ")[0].split(" стажировка: ")[1],
						choise.split(" телефон: ")[1]);
			} catch (Exception e) {
				try {
					base.updateStatus("Принят ", choise.split(" статус: ")[0].split(" стажировка: ")[1]);
				} catch (SQLException e1) {

				}
			}
			states.removeAll(states);
			yep.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Whos_click.fxml"));
			Loader(loader);

		});

		nope.setOnAction(event -> {// Назад
			String choise = Whos.getSelectionModel().getSelectedItem();
			try {
				base.addAnser("Не принят ", choise.split(" компетенции: ")[0].split(" стажировка: ")[1],
						choise.split(" телефон: ")[1]);
				base.deleteBidForCompany(choise.split(" компетенции: ")[0].split(" стажировка: ")[1],
						choise.split(" телефон: ")[1]);
			} catch (Exception e) {
				try {
					base.updateStatus("Не принят  ", choise.split(" статус: ")[0].split(" стажировка: ")[1]);

				} catch (SQLException e1) {

				}
			}
			states.removeAll(states);
			statesOrg.removeAll(statesOrg);
			Base.deteleDouplicate();
			nope.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Whos_click.fxml"));
			Loader(loader);
		});

		back.setOnAction(event -> {// Назад
			Base.deteleDouplicate();
			statesOrg.removeAll(statesOrg);
			states.removeAll(states);
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Menu_admin.fxml"));
			Loader(loader);
		});
	}

	void GetName() throws SQLException {// Получения имени работодателя
		try {
			System.out.println(states);
			states = Base.getBid();
			statesOrg = Base.getAnsCompany();
			states.addAll(statesOrg);
			if (!states.isEmpty() && !statesOrg.isEmpty()) {// Проверка на пустой лист
				Whos.setItems(states);
				Whos.setValue(states.get(0));
			}
		} catch (SQLException e) {
			try {
				states.removeAll(states);
				states = Base.getBid();
				if (!states.isEmpty()) {// Проверка на пустой лист
					Whos.setItems(states);
					Whos.setValue(states.get(0));
				}
			} catch (SQLException e1) {
				try {
					statesOrg.removeAll(statesOrg);
					statesOrg = Base.getAnsCompany();
					if (!statesOrg.isEmpty()) {// Проверка на пустой лист
						Whos.setItems(statesOrg);
						Whos.setValue(statesOrg.get(0));
					}
				} catch (SQLException e2) {
					
					Whos.setValue("Ничего нет!");
				}
			}
		}
	}

	public void Loader(FXMLLoader loader) {
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Parent root = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}
}
