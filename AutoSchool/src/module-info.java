module AutoSchool {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	requires ojdbc8;
	
	opens application to javafx.graphics, javafx.fxml;
}
