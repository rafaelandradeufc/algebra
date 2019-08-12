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

public class OpsMatController implements Initializable {

	@FXML
	Button btSomar;
	@FXML
	TextField linhas_2;
	@FXML
	TextField colunas_2;
	@FXML
	Button btLimpar2;
	@FXML
	Button btAdd;
	@FXML
	Button btAdd2;
	@FXML
	AnchorPane pane;
	@FXML
	AnchorPane pane2;
	@FXML
	Button btVoltar;
	@FXML
	TextField linhas;
	@FXML
	TextField colunas;
	@FXML
	Button btLimpar;

	int x1 = 20, y1 = 100, l1, c1;
	int x2 = 20, y2 = 100, l2, c2;

	TextField mat1[][];
	TextField mat2[][];

	Operacoes op = new Operacoes();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btLimpar.setDisable(true);
		for (int i = 0; i < l1; i++) {

			for (int j = 0; j < c1; j++) {

				mat1[i][j] = new TextField();
			}
		}

		btLimpar2.setDisable(true);
		for (int i = 0; i < l2; i++) {

			for (int j = 0; j < c2; j++) {

				mat2[i][j] = new TextField();
			}
		}

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
	protected void somar(ActionEvent event) throws IOException {

		Node node = (Node) event.getSource();

		Stage stage = (Stage) node.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ufc/crateus/fxml/resultados.fxml"));

		Parent root = (Parent) loader.load();
		ResultController control = (ResultController) loader.getController();

		control.l1 = l1;
		control.c1 = c1;
		control.criaMat();

		control.mat1 = desconvert(op.somaMatrizes(convert(mat1, l1, c1), convert(mat2, l2, c2)), l1, c1);

		control.exibirMatz(l1,c1);

		Scene scene = new Scene(root, 1360, 768);
		stage.setScene(scene);
		stage.setTitle("Resultado");
		stage.show();

	}

	@FXML
	protected void sub(ActionEvent event) throws IOException {

		Node node = (Node) event.getSource();

		Stage stage = (Stage) node.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ufc/crateus/fxml/resultados.fxml"));

		Parent root = (Parent) loader.load();
		ResultController control = (ResultController) loader.getController();

		control.l1 = l1;
		control.c1 = c1;
		control.criaMat();
		control.mat1 = mat1;

		control.mat1 = desconvert(op.subtracaoMatrizes(convert(mat1, l1, c1), convert(mat2, l2, c2)), l1, c1);

		control.exibirMatz(l1,c1);

		Scene scene = new Scene(root, 1360, 768);
		stage.setScene(scene);
		stage.setTitle("Resultado");
		stage.show();

	}

	@FXML
	protected void mult(ActionEvent event) throws IOException {

		Node node = (Node) event.getSource();

		Stage stage = (Stage) node.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ufc/crateus/fxml/resultados.fxml"));

		Parent root = (Parent) loader.load();
		ResultController control = (ResultController) loader.getController();

		control.l1 = l1;
		control.c1 = c1;
		control.criaMat();
		control.mat1 = mat1;
		
		control.mat1 = desconvert(op.multiplicarMatrizes(convert(mat1, l1, c1), convert(mat2, l2, c2)), l1, c2);

		control.exibirMatz(l1,c2);

		

		Scene scene = new Scene(root, 1360, 768);
		stage.setScene(scene);
		stage.setTitle("Resultado");
		stage.show();

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
	protected void add2(ActionEvent event) {
		btAdd2.setDisable(true);
		btLimpar2.setDisable(false);

		l2 = Integer.parseInt(linhas_2.getText());
		c2 = Integer.parseInt(colunas_2.getText());

		mat2 = new TextField[l2][c2];

		for (int i = 0; i < l2; i++) {

			for (int j = 0; j < c2; j++) {
				TextField tf2 = new TextField();
				tf2.setPrefHeight(25);
				tf2.setPrefWidth(37);
				tf2.setLayoutX(x2);
				tf2.setLayoutY(y2);

				x2 += 40;

				mat2[i][j] = tf2;

				pane2.getChildren().addAll(tf2);

			}
			x2 = 20;
			y2 += 40;

		}

		x2 = 20;
		y2 = 100;
	}

	@FXML
	protected void limpar(ActionEvent event) {
		btAdd.setDisable(false);
		btLimpar.setDisable(true);

		for (int i = 0; i < l1; i++) {

			for (int j = 0; j < c1; j++) {

				System.out.println(mat1[i][j].getText());
				pane.getChildren().remove(mat1[i][j]);
			}
		}

	}

	@FXML
	protected void limpar2(ActionEvent event) {
		btAdd2.setDisable(false);
		btLimpar2.setDisable(true);

		for (int i = 0; i < l2; i++) {

			for (int j = 0; j < c2; j++) {

				System.out.println(mat2[i][j].getText());
				pane2.getChildren().remove(mat2[i][j]);
			}
		}
	}

}
