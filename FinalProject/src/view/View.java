package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.stage.Stage;
import model.Employee;

public class View extends Application{
	
	private CreateEmployeeTab createTab; 
	private RetrieveEmployeeTab retrieveTab;
	private TableView<Employee> tableView;
	
    @Override
    public void start(Stage primaryStage) {
    	//Stage Info
    	primaryStage.setTitle("XYZ Company Management Software");
    	buildTable();
    	createTab = new CreateEmployeeTab();
    	retrieveTab = new RetrieveEmployeeTab(tableView);
 
    	
    	TabPane layout = new TabPane();
    	layout.getTabs().addAll(createTab, retrieveTab);
    	
		Scene scene = new Scene(layout,1400,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
	public TableView<Employee> getTableView() {
		return tableView;
	}

	public CreateEmployeeTab getCreateTab() {
		return createTab;
	}
	
	
	
	public RetrieveEmployeeTab getRetrieveTab() {
		return retrieveTab;
	}

	private void buildTable()
	{		
			tableView = new TableView<>();
			//creating table columns
			//each column will have a setCellFactory(TextFieldTableCell.forTableColumn()), this will allow us to edit each cell.
			//onEditCommit() is the event where you finally enter the values in
	        TableColumn< Employee, Integer> column1 = new TableColumn<>("ID");
	        column1.setMinWidth(40);
	        column1.setCellValueFactory( new PropertyValueFactory<>("id"));
	        column1.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			tableView.getColumns().add(column1);
	        
	        TableColumn< Employee, String> column2 = new TableColumn<>("First Name");
	        column2.setMinWidth(100);
	        column2.setCellValueFactory( new PropertyValueFactory<>("first_name"));
	        column2.setCellFactory(TextFieldTableCell.forTableColumn());
			tableView.getColumns().add(column2);
			
	        TableColumn< Employee, String> column3 = new TableColumn<>("Last Name");
	        column3.setMinWidth(100);
	        column3.setCellValueFactory( new PropertyValueFactory<>("last_name"));
	        column3.setCellFactory(TextFieldTableCell.forTableColumn());
			tableView.getColumns().add(column3);
			
			TableColumn< Employee, String> column4 = new TableColumn<>("Start Date");
	        column4.setMinWidth(100);
	        column4.setCellValueFactory( new PropertyValueFactory<>("start_date"));
	        column4.setCellFactory(TextFieldTableCell.forTableColumn());
			tableView.getColumns().add(column4);
			
			TableColumn< Employee, Integer> column5 = new TableColumn<>("Start Salary");
	        column5.setMinWidth(100);
	        column5.setCellValueFactory( new PropertyValueFactory<>("start_salary"));
	        column5.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			tableView.getColumns().add(column5);
			
			TableColumn< Employee, String> column6 = new TableColumn<>("Employee Contract Signed");
	        column6.setMinWidth(150);
	        column6.setCellValueFactory( new PropertyValueFactory<>("employee_contract_signed"));
	        column6.setCellFactory(TextFieldTableCell.forTableColumn());
			tableView.getColumns().add(column6);
			
			TableColumn< Employee, String> column7 = new TableColumn<>("Social Security Number");
	        column7.setMinWidth(175);
	        column7.setCellValueFactory( new PropertyValueFactory<>("social_security_number"));
	        column7.setCellFactory(TextFieldTableCell.forTableColumn());
			tableView.getColumns().add(column7);
			
			TableColumn< Employee, String> column8 = new TableColumn<>("Birth Date");
	        column8.setMinWidth(100);
	        column8.setCellValueFactory( new PropertyValueFactory<>("birth_date"));
	        column8.setCellFactory(TextFieldTableCell.forTableColumn());
			tableView.getColumns().add(column8);
			
			TableColumn< Employee, String> column9 = new TableColumn<>("Phone Number");
	        column9.setMinWidth(100);
	        column9.setCellValueFactory( new PropertyValueFactory<>("phone_number"));
	        column9.setCellFactory(TextFieldTableCell.forTableColumn());
			tableView.getColumns().add(column9);
			
			TableColumn< Employee, String> column10 = new TableColumn<>("Emergency Contact");
	        column10.setMinWidth(150);
	        column10.setCellValueFactory( new PropertyValueFactory<>("emergency_contact"));
	        column10.setCellFactory(TextFieldTableCell.forTableColumn());
			tableView.getColumns().add(column10);
			
			TableColumn< Employee, String> column11 = new TableColumn<>("Emergency Number");
	        column11.setMinWidth(150);
	        column11.setCellValueFactory( new PropertyValueFactory<>("emergency_number"));
	        column11.setCellFactory(TextFieldTableCell.forTableColumn());
			tableView.getColumns().add(column11);
	
			tableView.setMaxHeight(500);
			tableView.setEditable(true);
	}
    

}
