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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ResultController implements Initializable {

	@FXML
	AnchorPane pane;
	@FXML
	Button btVoltar;
	@FXML
	Label labelResult;

	int l1, c1, x1 = 20, y1 = 100;

	TextField mat1[][];
	TextField vet[];

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void criaMat() {

		mat1 = new TextField[l1][c1];

	}

	public void criaVet() {

		vet = new TextField[l1];

	}

	public void exibirVet() {

		for (int i = 0; i < l1; i++) {

			TextField lb = new TextField();
			lb.setPrefHeight(25);
			lb.setPrefWidth(37);
			lb.setLayoutX(x1);
			lb.setLayoutY(y1);

			x1 += 40;

			lb.setText(vet[i].getText());
			lb.setEditable(false);

			pane.getChildren().addAll(lb);

		}

		x1 = 20;
		y1 = 100;

	}

	public void exibirMatz(int l1, int c1) {

		for (int i = 0; i < l1; i++) {

			for (int j = 0; j < c1; j++) {
				TextField lb = new TextField();
				lb.setPrefHeight(25);
				lb.setPrefWidth(37);
				lb.setLayoutX(x1);
				lb.setLayoutY(y1);

				x1 += 40;

				lb.setText(mat1[i][j].getText());
				lb.setEditable(false);

				pane.getChildren().addAll(lb);

			}
			x1 = 20;
			y1 += 40;

		}

		x1 = 20;
		y1 = 100;

	}

	@FXML
	protected void voltar(ActionEvent event) throws IOException {

		Node node = (Node) event.getSource();

		Stage stage = (Stage) node.getScene().getWindow();
		URL arquivoFXML = getClass().getResource("/com/ufc/crateus/fxml/inicial.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

		stage.setMaximized(true);
		stage.setScene(new Scene(fxmlParent, 1360, 768));
		stage.setTitle("Tela principal");
		stage.show();
	}

}
