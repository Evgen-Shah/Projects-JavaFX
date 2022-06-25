package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Autorization_ {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField pass;

	@FXML
	private TextField login;

	@FXML
	private Button enter;

	@FXML
	private Button back;

	@FXML
	private Text Error;

	database base = new database();

	@FXML
	void initialize() {// ����� ��� ������������� ����������� ���� 
		pin_cord_card_.moneys = 800;
		FXMLLoader loader = new FXMLLoader();
		Error.setText("");

		enter.setOnAction(event -> {// �������� ��� ������� ������ �����
			if (base.checkNumber(login.getText()).equals(pass.getText())) {//�������� ������ � ������ ��� �����
				registration_.numberPhone = Long.parseLong(login.getText());
				enter.getScene().getWindow().hide();
				loader.setLocation(getClass().getResource("choise_action.fxml"));
				LoaderFXML(loader);
			}
			else {
				Error.setText("�������� ������!");
			}

		});

		back.setOnAction(event -> {// �������� ��� ������� ������ back
			back.getScene().getWindow().hide();
			loader.setLocation(getClass().getResource("LK.fxml"));
			LoaderFXML(loader);
		});
	}



	public void LoaderFXML(FXMLLoader loader) {// ����� ��� ��������� ����
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


