package com.ufc.crateus.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InicialController implements Initializable {

	@FXML
	Pane pane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}

	@FXML
	protected void opsMat(ActionEvent event) throws IOException {

		Node node = (Node) event.getSource();

		Stage stage = (Stage) node.getScene().getWindow();
		URL arquivoFXML = getClass().getResource("/com/ufc/crateus/fxml/opsMat.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

		stage.setMaximized(true);
		stage.setScene(new Scene(fxmlParent,1360,700));
		stage.setTitle("Soma de Matrizes");
		stage.show();
	}

	

	

	@FXML
	protected void multEsc(ActionEvent event) throws IOException {
		Node node = (Node) event.getSource();

		Stage stage = (Stage) node.getScene().getWindow();
		URL arquivoFXML = getClass().getResource("/com/ufc/crateus/fxml/multEsc.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

		stage.setMaximized(true);
		stage.setScene(new Scene(fxmlParent,1360,700));
		stage.setTitle("Multiplicação por Escalar");
		stage.show();

	}



	@FXML
	protected void detinvtranMat(ActionEvent event) throws IOException {
		Node node = (Node) event.getSource();

		Stage stage = (Stage) node.getScene().getWindow();
		URL arquivoFXML = getClass().getResource("/com/ufc/crateus/fxml/detinvtranMat.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

		stage.setMaximized(true);
		stage.setScene(new Scene(fxmlParent,1360,700));
		stage.setTitle("Determinante");
		stage.show();

	}



	@FXML
	protected void sistemLinear(ActionEvent event) throws IOException {
		Node node = (Node) event.getSource();

		Stage stage = (Stage) node.getScene().getWindow();
		URL arquivoFXML = getClass().getResource("/com/ufc/crateus/fxml/sistemLinear.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

		stage.setMaximized(true);
		stage.setScene(new Scene(fxmlParent,1360,700));
		stage.setTitle("Resolução de Sitema Linear");
		stage.show();

	}
}
