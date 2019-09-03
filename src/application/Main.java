package application;

import java.io.IOException;

import gui.controllers.ViewController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

	private static Stage stage;
	private static Scene login;
	private static Scene main;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent fxmlMain = FXMLLoader.load(getClass().getResource("/gui/view/View.fxml"));
			main = new Scene(fxmlMain);
			stage = primaryStage;
			Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/gui/view/ViewLogin.fxml"));
			login = new Scene(fxmlLogin);
			stage.setScene(login);
			stage.centerOnScreen();
			stage.show();
			closeRequestProgram();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Scene getMain() {
		return main;
	}
	public static Stage getStage() {
		return stage;
	}
	
	public static Scene getScene() {
		return login;
	}
	
	public static void closeRequestProgram() {
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	        	ViewController.getStageCliente().close();
	      		ViewController.getStageFuncionario().close();
	      		ViewController.getStageAgenda().close();
	      		ViewController.getStageSobre().close();
	      		ViewController.getStageCaixa().close();
	          }
	      });
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
