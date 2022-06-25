package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EntryStudy {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Entry;

    @FXML
    private TextField phone;

    @FXML
    private PasswordField pass;

    @FXML
    private Button Back;
    
    @FXML
    private Text passWrong;
    
    ControleDataBase db = new ControleDataBase();

	@FXML
	void initialize() {
		passWrong.setVisible(false);
		FXMLLoader loader = new FXMLLoader();

		Back.setOnAction(event -> {// Использование кнопки для входа инструктором
			Back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Study.fxml"));

			Loader(loader);
		});
		
		Entry.setOnAction(event -> {// Использование кнопки для входа инструктором
			try {
				ControleDataBase.phone = Long.parseLong(phone.getText());
				String passInBase = db.checkNum(phone.getText());
				String passApp = pass.getText();
				if (passApp.equals(passInBase)) {
					Entry.getScene().getWindow().hide();
					loader.setLocation(getClass().getResource("StyduMainWindow.fxml"));
					Loader(loader);
				} else {
					passWrong.setVisible(true);
				}
			} catch (NumberFormatException | SQLException e) {

				e.printStackTrace();
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