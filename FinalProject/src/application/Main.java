package application;

import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.EmployeeModel;
import view.View;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		EmployeeModel model = new EmployeeModel();
		View view = new View();
		view.start(primaryStage);
		MainController controller = new MainController(model, view);
		
	}
}
