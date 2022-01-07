package application;
	
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Main extends Application {
	
	private static MediaPlayer player; 
    @Override
    public void start(Stage primaryStage) throws Exception{
    	   Media media = null;
           try {
             media = new Media(getClass().getResource("/music/chill.mp3").toURI().toString());
           } catch (URISyntaxException e) {
             e.printStackTrace();
           } 
           
        Parent root = FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        primaryStage.setTitle("Todo List");
        primaryStage.setScene(new Scene(root, 900, 500));
        
        player = new MediaPlayer(media);
	    player.play();
	    
        primaryStage.show();
        
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
