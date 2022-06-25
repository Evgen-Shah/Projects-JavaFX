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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Add_state {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button add;

	@FXML
	private TextField name;

	@FXML
	private Button back;

	@FXML
	private ChoiceBox <String> state;
	
	Base base = new Base();
	
    ObservableList<String> states = FXCollections.observableArrayList();

	@FXML
	void initialize() throws SQLException {//Метод, который вызывается при прогрузки визуального окна
		GetName();
		
		FXMLLoader loader = new FXMLLoader();

		add.setOnAction(event -> {// Нажатие кнопки добавить
			//Enroll.states.add(name.getText() + " : " +Entry_Reg.adminName);
			try {
				base.addIntership(name.getText());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			states.removeAll(states);
			add.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Add_state.fxml"));
			Loader(loader);
		});

		back.setOnAction(event -> {// Назад
			states.removeAll(states);
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Menu_admin.fxml"));
			Loader(loader);
		});
	}
	
    void GetName() throws SQLException {// Получения имени работодателя 
    	states = Base.getInterForCompany();
       	
    	if(!states.isEmpty()) {//Проверка на пустой лист
    		state.setItems(states);
    		state.setValue(states.get(0));
		}
    }
	public void Loader (FXMLLoader loader) {
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
