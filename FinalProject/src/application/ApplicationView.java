package application;
	
import java.sql.*;
import java.util.ArrayList;

import Model.Employee;
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
			primaryStage.setTitle("Employee Data");
			
			Label topLabel = new Label();
			topLabel.setStyle("-fx-font: 20 comic_sans;");
			topLabel.setPadding(new Insets(10, 10, 10, 10));
			topLabel.setText("Employee Records");
			
			//Show window
			buildData();
			
			//Text fields
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
			
			//Button
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

	public void buildData() throws SQLException, ClassNotFoundException 
	{
		//SQL Database Connection Parameters
		Connection myConn = null;
		Statement myStat = null;
		ResultSet myRs = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/projectdb";
		String dbUsername = "root";
		String dbPassword = "admin";
		
		try {
			//1. Get a Connection
			myConn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			
			System.out.println("Database Connection successful! \n");
			
			//2. Create a statement
			myStat = myConn.createStatement();
			
			//3. Execute SQL query
			myRs = myStat.executeQuery("select * from employeeTB");
			
			PreparedStatement preparedStatement = myConn.prepareStatement("select * from employeeTb");
			myRs = preparedStatement.executeQuery();
			
			ObservableList dbData = FXCollections.observableArrayList(dbData(myRs));
		
			//Giving readable names to columns
			for(int i = 0; i < myRs.getMetaData().getColumnCount(); i++)
			{
				TableColumn column = new TableColumn<>();
				switch (myRs.getMetaData().getColumnName(i+1))
				{
					case "id":
						column.setText("Employee ID");
						column.setMinWidth(40);
						break;
						
					case "last_name":
						column.setText("Last Name");
						column.setMinWidth(100);
						break;
					
					case "first_name":
						column.setText("First Name");
						column.setMinWidth(100);
						break;
						
					case "start_salary":
						column.setText("Salary");
						column.setMinWidth(100);
						break;
						
					case "employee_contract_signed":
						column.setText("Contract Signed");
						column.setMinWidth(150);
						break;
						
					case "social_security_number":
						column.setText("Social Security Number");
						column.setMinWidth(175);
						break;
						
					case "birth_date":
						column.setText("Birth Date");
						column.setMinWidth(100);
						break;
						
					case "phone_number":
						column.setText("Phone Number");
						column.setMinWidth(100);
						break;
						
					case "emergency_contact":
						column.setText("Emergency Contact");
						column.setMinWidth(150);
						break;
						
					case "emergency_number":
						column.setText("Emergency Number");
						column.setMinWidth(150);
						break;
						
					default:
						column.setText(myRs.getMetaData().getColumnName(i+1));
						column.setMinWidth(100);
						break;
				}
				
				column.setCellValueFactory(new PropertyValueFactory<>(myRs.getMetaData().getColumnName(i+1)));
				tableView.getColumns().add(column);
			}
			
			//Filling up tableView with data
			tableView.setItems(dbData);
			tableView.setMaxHeight(500);
		}
		catch(Exception exc) 
		{
			exc.printStackTrace();
		}
		finally 
		{
			close(myConn, myStat, myRs);
		}
	}
	
	//Extracting data from ResultSet to ArrayList
	private ArrayList dbData(ResultSet resultSet) throws SQLException 
	{
		
		ArrayList<Employee> data = new ArrayList<>();
		while(resultSet.next()) 
		{
			Employee Employee = new Employee();
			//Employee.setId(resultSet.getInt("id"));
			Employee.setFirst_name(resultSet.getString("first_name"));
			Employee.setFirst_name(resultSet.getString("last_name"));
			Employee.setStart_date(resultSet.getString("start_date"));
			Employee.setStart_salary(resultSet.getInt("start_salary"));
			Employee.setEmployee_contract_signed("employee_contract_signed");
			Employee.setSocial_security_number("social_security_number");
			Employee.setBirth_date("birth_date");
			Employee.setPhone_number("phone_number");
			Employee.setEmergency_contact("emergency_contact");
			Employee.setEmergency_number("emergency_number");
			data.add(Employee);
		}
		return data;
	}
	
	public void newEmployee() 
	{
		//Button
		Button addBtn = new Button("Add Employee");
		addBtn.setOnAction(e -> {
			try {
				addData();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
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
	
	//add to database
	public void addData() throws SQLException 
	{
		Employee d1 = new Employee();
		d1.setFirst_name(first_name.getText());
		d1.setLast_name(last_name.getText());
		d1.setStart_date(start_date.getText());
		d1.setStart_salary(Integer.parseInt(start_salary.getText()));
		d1.setEmployee_contract_signed(employee_contract_signed.getText());
		d1.setSocial_security_number(social_security_number.getText());
		d1.setBirth_date(birth_date.getText());
		d1.setPhone_number(phone_number.getText());
		d1.setEmergency_contact(emergency_contact.getText());
		d1.setEmergency_number(emergency_number.getText());
		tableView.getItems().add(d1);
		
		//SQL Database Connection Parameters
		Connection myConn = null;
		Statement myStat = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/projectdb";
		String dbUsername = "root";
		String dbPassword = "admin";
		
		try {
			//1. Get a Connection
			myConn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			
			System.out.println("Database Connection successful! \n");
			
			//2. Create a statement
			
			//3. Execute SQL query
			PreparedStatement preparedStatement = myConn.prepareStatement("insert into employeeTb(first_name, last_name, start_date, start_salary, "
					+ "employee_contract_signed, social_security_number, birth_date, phone_number, emergency_contact, emergency_number) "
					+ "values (?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, first_name.getText());
			preparedStatement.setString(2, last_name.getText());
			preparedStatement.setString(3, start_date.getText());
			preparedStatement.setInt(4, Integer.parseInt(start_salary.getText()));
			preparedStatement.setString(5, employee_contract_signed.getText());
			preparedStatement.setString(6, social_security_number.getText());
			preparedStatement.setString(7, birth_date.getText());
			preparedStatement.setString(8, phone_number.getText());
			preparedStatement.setString(9, emergency_contact.getText());
			preparedStatement.setString(10, emergency_number.getText());
			preparedStatement.executeUpdate();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			close(myConn, myStat, null);
		}
		
		first_name.clear();
		last_name.clear();
		start_date.clear();
		start_salary.clear();
		employee_contract_signed.clear();
		social_security_number.clear();
		birth_date.clear();
		phone_number.clear();
		emergency_contact.clear();
		emergency_number.clear();
	}
	
	HBox updateMenu = new HBox();
	Button findBtn = new Button("Find");
	Button updateBtn = new Button("Update");
	String fir_nam = null;
	String las_nam = null;
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
		
		findBtn.setOnAction(e -> {
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
				
				ObservableList dbData = FXCollections.observableArrayList(dbData(myRs));
				
				tableView.setMaxHeight(50);
				tableView.setItems(dbData);
				layout.getChildren().clear();
				layout.getChildren().addAll(menu, retrieveMenu, tableView);
				
				Statement mystat = myConn.createStatement();
				ResultSet myrs = mystat.executeQuery("select * from employeeTB");
			
				ObservableList dbdata = FXCollections.observableArrayList(dbData(myrs));
				tableView.setItems(dbData);
			}
			catch(Exception exc)
			{
				exc.printStackTrace();
			}
			finally 
			{
				try {
					close(myConn,myStat,myRs);
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
					} catch (SQLException e1) {
					e1.printStackTrace();
				}
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
		String dbUrl = "jdbc:mysql://localhost:3306/projectdb";
		String user = "root";
		String pass = "admin";
		
		retrieveMenu.setPadding(new Insets(10,10,10,10));
		retrieveMenu.setSpacing(10);
		retrieveMenu.getChildren().clear();
		retrieveMenu.getChildren().addAll(rtvAllBtn, first_name, last_name, rtvOneBtn);
		
		layout.getChildren().clear();
		layout.getChildren().addAll(menu, retrieveMenu);
		
		rtvAllBtn.setOnAction(e -> { layout.getChildren().addAll(tableView);});
		
		rtvOneBtn.setOnAction(e -> { 
			
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
				
				ObservableList dbData = FXCollections.observableArrayList(dbData(myRs));
				
				tableView.setMaxHeight(50);
				tableView.setItems(dbData);
				layout.getChildren().clear();
				layout.getChildren().addAll(menu, retrieveMenu, tableView);
				
				Statement mystat = myConn.createStatement();
				ResultSet myrs = mystat.executeQuery("select * from employeeTB");
			
				ObservableList dbdata = FXCollections.observableArrayList(dbData(myrs));
				tableView.setItems(dbData);
			}
			catch(Exception exc)
			{
				exc.printStackTrace();
			}
			finally 
			{
				try {
					close(myConn,myStat,myRs);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
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
				
				ObservableList dbData = FXCollections.observableArrayList(dbData(myRs));
				
				tableView.setMaxHeight(50);
				tableView.setItems(dbData);
				layout.getChildren().clear();
				layout.getChildren().addAll(menu, retrieveMenu, tableView);
				
				Statement mystat = myConn.createStatement();
				ResultSet myrs = mystat.executeQuery("select * from employeeTB");
			
				ObservableList dbdata = FXCollections.observableArrayList(dbData(myrs));
				tableView.setItems(dbData);
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
	
	private static void close(Connection myConn, Statement myStat, ResultSet myRs) throws SQLException 
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