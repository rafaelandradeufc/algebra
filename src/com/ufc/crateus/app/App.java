package com.ufc.crateus.app;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		URL arquivoFXML = getClass().getResource("/com/ufc/crateus/fxml/inicial.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
		
		stage.setMaximized(true);
		stage.setScene(new Scene(fxmlParent));
		stage.setTitle("Menu principal");
		stage.show();
	}

}
