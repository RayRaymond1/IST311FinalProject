module FinalProject {
	requires javafx.controls;
	requires java.sql;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens Model to javafx.base;
}
