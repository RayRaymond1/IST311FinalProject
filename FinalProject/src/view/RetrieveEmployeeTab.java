package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Employee;

public class RetrieveEmployeeTab extends Tab {
	private TableView<Employee> tableView;
	private HBox retrieveMenu, controlsContainer;
	private Label controls;
	private VBox content;
	private Button retrieveAllButton, retrieveOneButton;
	private TextField id, first_name, last_name;
	
	public RetrieveEmployeeTab(TableView<Employee> tableView){
		super("View Employee Records");
		setClosable(false);
		
		//Initialize all the textfields, the boxes need to display onto the UI, and the buttons to run functions
		//and a label to detail the controls of this tab view.
		this.tableView = tableView;
		
		retrieveAllButton = new Button("Retrieve All Records");
		retrieveOneButton = new Button("Search for Record");
		
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
		
		retrieveMenu = new HBox();
		retrieveMenu.getChildren().addAll(id, first_name, last_name, retrieveOneButton, retrieveAllButton);
		retrieveMenu.setPadding(new Insets(10,10,10,10));
		retrieveMenu.setAlignment(Pos.CENTER);
		
		controls = new Label("Click any cell to edit. Right Click to Delete.");
		
		controlsContainer = new HBox();
		controlsContainer.setAlignment(Pos.CENTER);
		controlsContainer.getChildren().addAll(controls);
		
		content = new VBox();
		content.getChildren().addAll(retrieveMenu, controlsContainer, tableView);
		setContent(content);
	}

	//getters and setters for the controller to grab whats needed
	public TableView<Employee> getTableView() {
		return tableView;
	}
	
	public Button getRetrieveAllButton() {
		return retrieveAllButton;
	}

	public Button getRetrieveOneButton() {
		return retrieveOneButton;
	}

	public TextField getFirst_name() {
		return first_name;
	}

	public TextField getLast_name() {
		return last_name;
	}

	public TextField getID() {
		return id;
	}
	
}
