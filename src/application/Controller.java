package application;

import datamodel.Todo;
import datamodel.TodoData;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class Controller {

	
    @FXML
    private BorderPane mainPanel;

    @FXML
    private TableView<Todo> todoTable;

    private TodoData data;
    
    
    public void initialize() {
        data = new TodoData();
        data.loadContacts();
    	todoTable.setItems(data.getTodos());
    }

    
    }
