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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Update_options {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> adress;

    @FXML
    private Button Button_reg;

    @FXML
    private TextField Phone_number;

    @FXML
    private PasswordField pass;

    @FXML
    private Button Back;

    @FXML
    private TextField SecondName;

    @FXML
    private TextField Name;
    
    ControleDataBase db = new ControleDataBase();
    
	static ObservableList<String> addressAdd = FXCollections.observableArrayList();

	@FXML
	void initialize() {
		try {
			addAdress();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FXMLLoader loader = new FXMLLoader();

		Back.setOnAction(event -> {// Использование кнопки для входа инструктором
			Back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("StyduMainWindow.fxml"));

			Loader(loader);
		});
		
		
		Button_reg.setOnAction(event -> {// Использование кнопки для входа инструктором
			Button_reg.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("UpdateOk.fxml"));
			try {
				db.updateData(Name.getText(),SecondName.getText(),Long.parseLong(Phone_number.getText()),pass.getText(),getAddress());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Loader(loader);
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
	
	public void addAdress() throws SQLException {// Добавление адресса
		try {
			addressAdd = ControleDataBase.getAddress();
			adress.setItems(addressAdd);
			adress.setValue(addressAdd.get(0));
		} catch (Exception e) {
			adress.setValue("Ничего нет");
		}

	}

	public String getAddress() {// ПОлучение названия услуги
		String adressStr = adress.getSelectionModel().getSelectedItem();
		return adressStr;
	}
}
