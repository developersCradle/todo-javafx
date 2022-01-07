package datamodel;




import java.util.Date;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Todo {

	private SimpleBooleanProperty done = new SimpleBooleanProperty(true);
	private SimpleStringProperty task = new SimpleStringProperty("");
    private SimpleObjectProperty<Date> date = new SimpleObjectProperty<Date>();

    public Todo() {
    }

    public Todo(SimpleBooleanProperty done, SimpleStringProperty task, SimpleObjectProperty<Date> date) {
		super();
		this.done = done;
		this.task = task;
		this.date = date;
	}

	public SimpleObjectProperty<Date> dateProperty() {
		return date;
	}

	public void setDate(Date date) {
		this.date.set(date);
	}

	public Date getDate() {
		return date.get();
	}

	
    public String getTask() {
        return task.get();
    }

    public SimpleStringProperty taskProperty() {
        return task;
    }

    public void setTask(String task) {
        this.task.set(task);
    }
    
	public SimpleBooleanProperty doneProperty() {
		return done;
	}

	public void setDone(boolean done) {
		this.done.set(done);
	}

	public boolean getDone() {
		return done.get();
	}

	
    @Override
    public String toString() {
        return "Todo{" +
                "done=" + done +
                ", task=" + task +
                ", date=" + date +
                '}';
    }
}
