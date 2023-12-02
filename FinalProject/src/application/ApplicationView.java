package application;
	
import java.sql.*;
import java.util.ArrayList;

import Controller.MainController;
import Model.Employee;
import Model.EmployeeModel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ApplicationView extends Application 
{
	public final static String DB_URL = "jdbc:mysql://localhost:3306/projectdb";
	public final static String DB_USERNAME = "root";
	public final static String DB_PASSWORD = "admin";
	
	private MainController controller = new MainController();
	private EmployeeModel model = new EmployeeModel();
	
	private TextField id, first_name, last_name, start_date, start_salary, employee_contract_signed, 
	social_security_number, birth_date, phone_number, 
	 emergency_contact, emergency_number;
	private TableView<Employee> tableView = new TableView<>();
	
	//Layout Pane
	private VBox layout = new VBox();
	private VBox menuLayout = new VBox();
	
	// HBox
	private HBox add1 = new HBox();
	private HBox add2 = new HBox();
	private HBox menu = new HBox();
	
	
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage)throws SQLException, ClassNotFoundException 
	{
			primaryStage.setTitle("XYZ Company Management Software");
			
			Label topLabel = new Label();
			topLabel.setStyle("-fx-font: 20 comic_sans;");
			topLabel.setPadding(new Insets(10, 10, 10, 10));
			topLabel.setText("Employee Records");
			
			//building the table's columns
			buildTable();
			
			//Text fields that we will reuse for each function
			id = new TextField();
			id.setPromptText("Id");
			id.setMaxWidth(30);
			
			first_name = new TextField();
			first_name.setPromptText("First Name");
			first_name.setMinWidth(100);
			first_name.setMaxWidth(150);
			
			last_name = new TextField();
			last_name.setPromptText("Last Name");
			last_name.setMinWidth(100);
			last_name.setMaxWidth(150);
			
			start_date = new TextField();
			start_date.setPromptText("Start Date");
			start_date.setMinWidth(100);
			
			start_salary = new TextField();
			start_salary.setPromptText("Start Salary");
			start_salary.setMinWidth(100);
			
			employee_contract_signed = new TextField();
			employee_contract_signed.setPromptText("Contract Signed");
			employee_contract_signed.setMinWidth(100);
			
			social_security_number = new TextField();
			social_security_number.setPromptText("Social Security Number");
			social_security_number.setMinWidth(100);
			
			birth_date = new TextField();
			birth_date.setPromptText("Birth Date");
			birth_date.setMinWidth(100);
			
			phone_number = new TextField();
			phone_number.setPromptText("Phone Number");
			phone_number.setMinWidth(100);
			
			emergency_contact = new TextField();
			emergency_contact.setPromptText("Emergency Contact");
			emergency_contact.setMinWidth(100);
			
			emergency_number = new TextField();
			emergency_number.setPromptText("Emergency Number");
			emergency_number.setMinWidth(100);
			
			//Each of these buttons will create the view needed for each CRUD feature
			Button nwEmpBtn = new Button("New Employee");
			nwEmpBtn.setOnAction(e -> { newEmployee();});
			
			Button updEmpBtn = new Button("Update Employee");
			updEmpBtn.setOnAction(e -> { try {
				updateEmployee();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}});
			
			Button rtvEmpBtn = new Button("Retrieve Employee");
			rtvEmpBtn.setOnAction(e -> { try {
				retrieveEmployee();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}});
			
			Button delEmpBtn = new Button("Delete Employee");
			delEmpBtn.setOnAction(e -> { try {
				deleteEmployee();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}});
			
			menu.setPadding(new Insets(10,10,10,10));
			menu.setSpacing(10);
			menu.getChildren().addAll(nwEmpBtn, rtvEmpBtn, updEmpBtn, delEmpBtn);
			
			
			layout.getChildren().addAll(menu);
					
			Scene scene = new Scene(layout,1400,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
	}

	public void buildTable()
	{		
			//creating table columns
	        TableColumn< Employee, String> column1 = new TableColumn<>("ID");
	        column1.setMinWidth(40);
	        column1.setCellValueFactory( new PropertyValueFactory<>("id"));
			tableView.getColumns().add(column1);
	        
	        TableColumn< Employee, String> column2 = new TableColumn<>("First Name");
	        column2.setMinWidth(100);
	        column2.setCellValueFactory( new PropertyValueFactory<>("first_name"));
			tableView.getColumns().add(column2);
			
	        TableColumn< Employee, String> column3 = new TableColumn<>("Last Name");
	        column3.setMinWidth(100);
	        column3.setCellValueFactory( new PropertyValueFactory<>("last_name"));
			tableView.getColumns().add(column3);
			
			TableColumn< Employee, String> column4 = new TableColumn<>("Start Date");
	        column4.setMinWidth(100);
	        column4.setCellValueFactory( new PropertyValueFactory<>("start_date"));
			tableView.getColumns().add(column4);
			
			TableColumn< Employee, Integer> column5 = new TableColumn<>("Start Salary");
	        column5.setMinWidth(100);
	        column5.setCellValueFactory( new PropertyValueFactory<>("start_salary"));
			tableView.getColumns().add(column5);
			
			TableColumn< Employee, String> column6 = new TableColumn<>("Employee Contract Signed");
	        column6.setMinWidth(150);
	        column6.setCellValueFactory( new PropertyValueFactory<>("employee_contract_signed"));
			tableView.getColumns().add(column6);
			
			TableColumn< Employee, String> column7 = new TableColumn<>("Social Security Number");
	        column7.setMinWidth(175);
	        column7.setCellValueFactory( new PropertyValueFactory<>("social_security_number"));
			tableView.getColumns().add(column7);
			
			TableColumn< Employee, String> column8 = new TableColumn<>("Birth Date");
	        column8.setMinWidth(100);
	        column8.setCellValueFactory( new PropertyValueFactory<>("birth_date"));
			tableView.getColumns().add(column8);
			
			TableColumn< Employee, String> column9 = new TableColumn<>("Phone Number");
	        column9.setMinWidth(100);
	        column9.setCellValueFactory( new PropertyValueFactory<>("phone_number"));
			tableView.getColumns().add(column9);
			
			TableColumn< Employee, String> column10 = new TableColumn<>("Emergency Contact");
	        column10.setMinWidth(150);
	        column10.setCellValueFactory( new PropertyValueFactory<>("emergency_contact"));
			tableView.getColumns().add(column10);
			
			TableColumn< Employee, String> column11 = new TableColumn<>("Emergency Number");
	        column11.setMinWidth(150);
	        column11.setCellValueFactory( new PropertyValueFactory<>("emergency_number"));
			tableView.getColumns().add(column11);
	
			
			//Filling up tableView with data
			tableView.setItems(model.getData());
			tableView.setMaxHeight(500);
	}
	
	public void newEmployee() 
	{
		//Button
		Button addBtn = new Button("Add Employee");
		addBtn.setOnAction(e -> {

				//grabbing all the info needed for an employee object from the text fields, we send it to the controller to process
				Employee newEmployee = new Employee(first_name.getText(), last_name.getText(),
						start_date.getText(), Integer.parseInt(start_salary.getText()), employee_contract_signed.getText(),
						social_security_number.getText(), birth_date.getText(), phone_number.getText(), emergency_contact.getText(),
						emergency_number.getText());
				
				tableView.getItems().add(newEmployee);
				controller.addEmployee(newEmployee);

		});
		//code needed to create the view needed to add the employee data.
		add1.setPadding(new Insets(10,10,10,10));
		add1.setSpacing(10);
		add1.getChildren().clear();
		add1.getChildren().addAll(first_name,last_name, start_date, start_salary, employee_contract_signed);
		
		add2.setPadding(new Insets(10,10,10,10));
		add2.setSpacing(10);
		add2.getChildren().clear();
		add2.getChildren().addAll(social_security_number, birth_date, phone_number, 
				 emergency_contact, emergency_number, addBtn);
		
		layout.getChildren().clear();
		layout.getChildren().addAll(menu,add1,add2);
	}
	
	
	HBox updateMenu = new HBox();
	Button findBtn = new Button("Find");
	Button updateBtn = new Button("Update");
	String fir_nam = null;
	String las_nam = null;
	
	//Broken
	public void updateEmployee() throws SQLException
	{
		String dbUrl = "jdbc:mysql://localhost:3306/projectdb";
		String user = "root";
		String pass = "admin";
		
		updateMenu.setPadding(new Insets(10,10,10,10));
		updateMenu.setSpacing(10);
		updateMenu.getChildren().clear();
		updateMenu.getChildren().addAll(findBtn, first_name, last_name);
		
		layout.getChildren().clear();
		layout.getChildren().addAll(menu, updateMenu);
		
		//findBtn.setOnAction(e -> model.searchDateWithFullName(first_name.getText(), last_name.getText()));
		findBtn.setOnAction(e -> {
			//Connection myConn = null;
			//CallableStatement myStat = null;
			//ResultSet myRs = null;
			try
			{
				//1. Get a connection to database
				//myConn = DriverManager.getConnection(dbUrl, user, pass);
				
				//String fir_nam = first_name.getText();
				//String las_nam = last_name.getText();
				
				// Prepare the stored procedure call
				//myStat = myConn.prepareCall("{call get_employee(?,?) }");
				
				// Set the Parameters
				//myStat.setString(1, fir_nam);
				//myStat.setString(2, las_nam);
				//myStat.executeQuery();
				
				//Get the result set
				//myRs = myStat.getResultSet();
				
				//ObservableList dbData = FXCollections.observableArrayList(dbData(myRs));
				tableView.setItems(model.searchDateWithFullName(first_name.getText(), last_name.getText()));
				tableView.setMaxHeight(50);
				//tableView.setItems(dbData);
				layout.getChildren().clear();
				layout.getChildren().addAll(menu, retrieveMenu, tableView);
				
				//Statement mystat = myConn.createStatement();
				//ResultSet myrs = mystat.executeQuery("select * from employeeTB");
			
				//ObservableList dbdata = FXCollections.observableArrayList(dbData(myrs));
				//tableView.setItems(dbData);
			}
			catch(Exception exc)
			{
				exc.printStackTrace();
			}
			finally 
			{

					//close(myConn,myStat,myRs);
					HBox add1 = new HBox();
					HBox add2 = new HBox();
					VBox menu = new VBox();
					
					add1.setPadding(new Insets(10,10,10,10));
					add1.setSpacing(10);
					add1.getChildren().clear();
					add1.getChildren().addAll(first_name,last_name, start_date, start_salary, employee_contract_signed);
					
					add2.setPadding(new Insets(10,10,10,10));
					add2.setSpacing(10);
					add2.getChildren().clear();
					add2.getChildren().addAll(social_security_number, birth_date, phone_number, 
							 emergency_contact, emergency_number);
					
					menu.getChildren().addAll(add1, add2);
					updateMenu.getChildren().addAll(menu, updateBtn);
			}
		});
		
		updateBtn.setOnAction(e -> {
			
			Connection myConn = null;
			PreparedStatement myStat = null;
			ResultSet myRs = null;
			try {
				
				//1. Get a connection to database
				myConn = DriverManager.getConnection(dbUrl, user, pass);
				
				fir_nam = first_name.getText();
				las_nam = last_name.getText();
				
				myStat = myConn.prepareStatement("UPDATE employeeTb SET(first_name, last_name, start_date, start_salary, "
						+ "employee_contract_signed, social_security_number, birth_date, phone_number, emergency_contact, emergency_number) "
						+ "values (?,?,?,?,?,?,?,?,?,?) WHERE first_name=? and last_name=?");
				myStat.setString(1, first_name.getText());
				myStat.setString(2, last_name.getText());
				myStat.setString(3, start_date.getText());
				myStat.setInt(4, Integer.parseInt(start_salary.getText()));
				myStat.setString(5, employee_contract_signed.getText());
				myStat.setString(6, social_security_number.getText());
				myStat.setString(7, birth_date.getText());
				myStat.setString(8, phone_number.getText());
				myStat.setString(9, emergency_contact.getText());
				myStat.setString(10, emergency_number.getText());
				myStat.setString(11, fir_nam);
				myStat.setString(12, las_nam);
				myStat.executeUpdate();
				
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			finally {
				try {
					close(myConn, myStat, myRs);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	//HBox
	HBox retrieveMenu = new HBox();
	
	//Button
	Button rtvOneBtn = new Button("Search");
	Button rtvAllBtn = new Button("Retrieve All");
	public void retrieveEmployee() throws SQLException
	{
		retrieveMenu.setPadding(new Insets(10,10,10,10));
		retrieveMenu.setSpacing(10);
		retrieveMenu.getChildren().clear();
		retrieveMenu.getChildren().addAll(rtvAllBtn, first_name, last_name, rtvOneBtn);
		
		layout.getChildren().clear();
		layout.getChildren().addAll(menu, retrieveMenu);

		rtvAllBtn.setOnAction(e -> {
			tableView.setItems(model.updateAndDisplayAllRecords());
			layout.getChildren().addAll(tableView);
		});

		rtvOneBtn.setOnAction(e -> {
			tableView.setMaxHeight(50);
			//tableView.setItems();
			layout.getChildren().clear();
			layout.getChildren().addAll(menu, retrieveMenu, tableView);
		});
	}
	
	//HBox
	HBox deleteMenu = new HBox();
		
	//Button
	Button srchBtn = new Button("Find");
	Button delBtn = new Button("Delete");
	
	public void deleteEmployee() throws SQLException
	{	
		String dbUrl = "jdbc:mysql://localhost:3306/projectdb";
		String user = "root";
		String pass = "admin";
		
		deleteMenu.setPadding(new Insets(10,10,10,10));
		deleteMenu.setSpacing(10);
		deleteMenu.getChildren().clear();
		deleteMenu.getChildren().addAll(srchBtn, first_name, last_name);
		
		layout.getChildren().clear();
		layout.getChildren().addAll(menu, deleteMenu);
		
		srchBtn.setOnAction(e -> { 
			
			Connection myConn = null;
			CallableStatement myStat = null;
			ResultSet myRs = null;
			try
			{
				//1. Get a connection to database
				myConn = DriverManager.getConnection(dbUrl, user, pass);
				
				String fir_nam = first_name.getText();
				String las_nam = last_name.getText();
				
				// Prepare the stored procedure call
				myStat = myConn.prepareCall("{call get_employee(?,?) }");
				
				// Set the Parameters
				myStat.setString(1, fir_nam);
				myStat.setString(2, las_nam);
				myStat.executeQuery();
				
				//Get the result set
				myRs = myStat.getResultSet();
				
				//ObservableList dbData = FXCollections.observableArrayList(dbData(myRs));
				
				tableView.setMaxHeight(50);
				//tableView.setItems(dbData);
				layout.getChildren().clear();
				layout.getChildren().addAll(menu, retrieveMenu, tableView);
				
				Statement mystat = myConn.createStatement();
				ResultSet myrs = mystat.executeQuery("select * from employeeTB");
			
				//ObservableList dbdata = FXCollections.observableArrayList(dbData(myrs));
				//tableView.setItems(dbData);
			}
			catch(Exception exc)
			{
				exc.printStackTrace();
			}
			finally 
			{
				try {
					deleteMenu.getChildren().add(delBtn);
					close(myConn,myStat,myRs);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		delBtn.setOnAction(e -> { 
			Connection myConn = null;
			PreparedStatement myStat = null;
			ResultSet myRs = null;
			try {
				
				//1. Get a connection to database
				myConn = DriverManager.getConnection(dbUrl, user, pass);
				
				ObservableList<Employee> Selected_field, All_field;
				All_field = tableView.getItems();
				Selected_field = tableView.getSelectionModel().getSelectedItems();
				Selected_field.forEach( All_field :: remove);
				
				fir_nam = first_name.getText();
				las_nam = last_name.getText();
				
				myStat = myConn.prepareStatement("DELETE from employeeTB where first_name=? and last_name=?");
				
				myStat.setString(1, fir_nam);
				myStat.setString(2, las_nam);
				myRs = myStat.executeQuery();
				
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			finally {
				try {
					close(myConn, myStat, myRs);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private static void close(Connection myConn, PreparedStatement myStat, ResultSet myRs) throws SQLException 
	{
		if(myRs != null)
		{
			myRs.close();
		}
		
		if(myStat != null)
		{
			myStat.close();
		}
		
		if(myConn != null)
		{
			myConn.close();
		}
	}
}