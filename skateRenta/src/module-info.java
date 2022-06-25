module skateRenta {
	requires javafx.controls;
	requires javafx.fxml;
	requires ojdbc8;
	requires java.sql;
	requires javafx.graphics;
	requires java.xml.crypto;
	
	opens application to javafx.graphics, javafx.fxml;
}
