package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Menu_admin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add;

    @FXML
    private Button whos;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    void initialize() {
    	FXMLLoader loader = new FXMLLoader();

		back.setOnAction(event -> {// Назад
			Base.deteleDouplicate();
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Entry_Reg.fxml"));
			Loader(loader);
		});
		
		add.setOnAction(event -> {// Открытие окна с добавление
			Base.deteleDouplicate();
			add.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Add_state.fxml"));
			Loader(loader);
		});
		
		delete.setOnAction(event -> {// Открытие окна с удалением
			Base.deteleDouplicate();
			delete.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Delete_state.fxml"));
			Loader(loader);
		});
		
		whos.setOnAction(event -> {// Кто записался
			Base.deteleDouplicate();
			whos.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Whos_click.fxml"));
			Loader(loader);
		});
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
