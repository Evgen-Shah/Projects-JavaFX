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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Admin_login_class {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField password;

	@FXML
	private Button back;

	@FXML
	private TextField id;

	@FXML
	private Button enter;
	
    @FXML
    private Text error;

	Base bs = new Base();

	@FXML
	void initialize() {
		error.setVisible(false);
		FXMLLoader loader = new FXMLLoader();

		enter.setOnAction(event -> {// Действие при нажатии кнопки enter
			try {
				if(bs.checkId(id.getText()).equals(password.getText())) { //Проверяем ввод данных
					enter.getScene().getWindow().hide();
					loader.setLocation(getClass().getResource("Menu_admin.fxml"));
					LoaderFXML(loader);
				}
				else {// Если неправильно, то пишем ошибку
					error.setVisible(true);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		back.setOnAction(event -> {// Дейсвтие при нажатии кнопки back
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("Login_window.fxml"));
			LoaderFXML(loader);

		});
	}

	public void LoaderFXML(FXMLLoader loader) {
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
