package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Employee;

public class CreateEmployeeTab extends Tab {
	
	private TextField id, first_name, last_name, start_date, start_salary, employee_contract_signed, 
	social_security_number, birth_date, phone_number, 
	 emergency_contact, emergency_number;
	
	private Button addButton;
	
	private HBox row1, row2;
	
	private VBox content;
	
	public CreateEmployeeTab() {
		super("Create Employee Record");
		setClosable(false);
		
		//Initialize all text fields, the stack pane, and add button
		content = new VBox();
		
		addButton = new Button("Add");
		
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
		
		
		
		//Add the textboxes to 2 Hboxes, set them onto a VBox, display that
		row1 = new HBox();
		row1.setPadding(new Insets(10,10,10,10));
		row1.setSpacing(10);
		row1.getChildren().addAll(first_name,last_name, start_date, start_salary, employee_contract_signed);
		
		row2 = new HBox();
		row2.setPadding(new Insets(10,10,10,10));
		row2.setSpacing(10);
		row2.getChildren().addAll(social_security_number, birth_date, phone_number, emergency_contact, emergency_number, addButton);
		
		content.getChildren().addAll(row1, row2);
		setContent(content);
	}

	//I want a getter to a button so I can add the listeners with the controller class
	public Button getAddButton() {
		return addButton;
	}
	
	private void clearAllFields() {
		id.clear();
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
	
	public Employee createEmployeeFromFields() {
		Employee newEmployee = new Employee(first_name.getText(), last_name.getText(), start_date.getText(),
				Integer.parseInt(start_salary.getText()), employee_contract_signed.getText(), social_security_number.getText(),
				birth_date.getText(), phone_number.getText(), emergency_contact.getText(), emergency_number.getText());
		
		clearAllFields();
		
		return newEmployee;
	}
	
	
	
	

}
