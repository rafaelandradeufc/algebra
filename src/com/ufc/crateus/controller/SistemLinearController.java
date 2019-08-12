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

public class SistemLinearController implements Initializable {

	@FXML
	TextField linhas;
	@FXML
	TextField linhas2;
	@FXML
	TextField colunas;
	@FXML
	Button btLimpar2;
	@FXML
	Button btAdd;
	@FXML
	Button btAdd2;
	@FXML
	Button btResult;
	@FXML
	AnchorPane pane;
	@FXML
	AnchorPane pane2;
	@FXML
	Button btVoltar;
	@FXML
	Button btLimpar;

	int x1 = 20, y1 = 100, l1, c1;
	int l2, x2 = 20, y2 = 100;

	TextField mat1[][];
	TextField vet[];

	Operacoes op = new Operacoes();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btLimpar.setDisable(true);
		for (int i = 0; i < l1; i++) {

			for (int j = 0; j < c1; j++) {

				mat1[i][j] = new TextField();
			}
		}

	}

	public double[] convert(TextField mat[], int l) {

		double matd[] = new double[l];

		for (int i = 0; i < l; i++) {

			matd[i] = Double.parseDouble(mat[i].getText());

		}

		return matd;
	}

	public TextField[] desconvert(double mat[], int l) {

		TextField matd[] = new TextField[l];

		for (int i = 0; i < l; i++) {

			TextField tf = new TextField();
			tf.setText(String.valueOf(mat[i]));
			matd[i] = tf;

		}

		return matd;

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
		control.criaVet();

		control.vet = desconvert(op.resolSistemaLinear(convert(mat1, l1, c1), convert(vet, vet.length)), vet.length);
		
		control.labelResult.setText(op.imprimeMatriz(op.getMatResult()));
		control.exibirVet();

		Scene scene = new Scene(root, 1360, 768);
		stage.setScene(scene);
		stage.setTitle("Resultado");
		stage.show();

	}

	@FXML
	protected void add2(ActionEvent event) {
		btAdd2.setDisable(true);
		btLimpar2.setDisable(false);

		l2 = Integer.parseInt(linhas2.getText());

		vet = new TextField[l2];

		for (int i = 0; i < l2; i++) {
			TextField tf = new TextField();
			tf.setPrefHeight(25);
			tf.setPrefWidth(37);
			tf.setLayoutX(x2);
			tf.setLayoutY(y2);

			x2 += 40;

			vet[i] = tf;

			pane2.getChildren().addAll(tf);
		}

		x2 = 20;
		y2 = 100;

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
		stage.setScene(new Scene(fxmlParent, 1360, 768));
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

	@FXML
	protected void limpar2(ActionEvent event) {
		btAdd2.setDisable(false);
		btLimpar2.setDisable(true);

		for (int i = 0; i < l2; i++) {

			pane2.getChildren().remove(vet[i]);
		}

	}

}
