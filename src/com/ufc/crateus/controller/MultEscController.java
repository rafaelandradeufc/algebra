package com.ufc.crateus.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ufc.crateus.controls.Operacoes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MultEscController implements Initializable{

	@FXML
	AnchorPane pane;
	@FXML
	Button btVoltar;
	@FXML
	Button btAdd;
	@FXML
	Button btLimpar;
	@FXML
	TextField escalar;
	@FXML
	Button btResult;
	@FXML
	TextField linhas;
	@FXML
	TextField colunas;
	
	int x1 = 20, y1 = 100, l1, c1;
	int x2 = 20, y2 = 100;
	
	TextField mat1[][];
	Operacoes op = new Operacoes();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	public double[][] convert(TextField mat[][], int l, int c) {

		double matd[][] = new double[l][c];

		for (int i = 0; i < l; i++) {

			for (int j = 0; j < c; j++) {

				matd[i][j] = Double.parseDouble(mat[i][j].getText());

			}
		}

		return matd;
	}

	public TextField[][] desconvert(double mat[][], int l, int c) {

		TextField matd[][] = new TextField[l][c];

		for (int i = 0; i < l; i++) {

			for (int j = 0; j < c; j++) {

				TextField tf = new TextField();
				tf.setText(String.valueOf(mat[i][j]));
				matd[i][j] = tf;

			}
		}

		return matd;

	}

	
	@FXML
	protected void result(ActionEvent event) throws IOException {
		
		Node node = (Node) event.getSource();

		Stage stage = (Stage) node.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ufc/crateus/fxml/resultados.fxml"));

		Parent root = (Parent) loader.load();
		ResultController control = (ResultController) loader.getController();

		control.l1 = l1;
		control.c1 = c1;
		double esc = Double.parseDouble(escalar.getText());
		
		control.criaMat();
		
		control.mat1 = desconvert(op.multiplicacaoPorEscalar(convert(mat1, l1, c1), esc), l1, c1);

		control.exibirMatz(l1,c1);
		

		Scene scene = new Scene(root, 1360, 768);
		stage.setScene(scene);
		stage.setTitle("Resultado");
		stage.show();
		
	}
	
	@FXML
	protected void add(ActionEvent event) {
		btAdd.setDisable(true);
		btLimpar.setDisable(false);

		l1 = Integer.parseInt(linhas.getText());
		c1 = Integer.parseInt(colunas.getText());

		mat1 = new TextField[l1][c1];

		for (int i = 0; i < l1; i++) {

			for (int j = 0; j < c1; j++) {
				TextField tf = new TextField();
				tf.setPrefHeight(25);
				tf.setPrefWidth(37);
				tf.setLayoutX(x1);
				tf.setLayoutY(y1);

				x1 += 40;

				mat1[i][j] = tf;

				pane.getChildren().addAll(tf);

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
		stage.setScene(new Scene(fxmlParent,1360,768));
		stage.setTitle("Tela principal");
		stage.show();
	}
	
	@FXML
	protected void limpar(ActionEvent event) {
		btAdd.setDisable(false);
		btLimpar.setDisable(true);

		for (int i = 0; i < l1; i++) {

			for (int j = 0; j < c1; j++) {

				pane.getChildren().remove(mat1[i][j]);
			}
		}

	}
	
}
