package com.ufc.crateus.controls;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Operacoes {

	public Operacoes() {
	}

	private double[][] matResult = new double[1000][1000];

	
	
	public double[][] criarMat() {

		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(System.in);

		System.out.println("Digite o número de linhas da matriz:");
		int n = entrada.nextInt();
		System.out.println("Digite o número de colunas da matriz:");
		int m = entrada.nextInt();

		double[][] mat = new double[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.println("Digite um valor na posicao " + "[" + (i + 1) + "]" + "[" + (j + 1) + "]:");
				mat[i][j] = entrada.nextDouble();
			}
		}

		return mat;
	}

	
	/*Nesse metodo é realizada a soma de duas matrizes, onde é percorrido o vetor mat1 e mat2,
	 *  e assim realizado a soma de cada valor correspondente a sua posição nos dois vetores*/
	public double[][] somaMatrizes(double[][] mat1, double[][] mat2) {

		if (mat1.length == mat2.length && mat1[0].length == mat2[0].length) {
			double[][] matf = new double[mat1.length][mat1[0].length];

			for (int i = 0; i < mat1.length; i++) {
				for (int j = 0; j < mat1[0].length; j++) {
					matf[i][j] = mat1[i][j] + mat2[i][j];
				}
			}

			return matf;
		}

		return null;
	}
	/*Nesse metodo é realizada a subtração de duas matrizes, onde é percorrido o vetor mat1 e mat2,
	 *  e assim realizado a subtrção de cada valor correspondente a sua posição nos dois vetores*/
	public double[][] subtracaoMatrizes(double[][] mat1, double[][] mat2) {

		if (mat1.length == mat2.length && mat1[0].length == mat2[0].length) {
			double[][] matf = new double[mat1.length][mat1[0].length];

			for (int i = 0; i < mat1.length; i++) {
				for (int j = 0; j < mat1[0].length; j++) {
					matf[i][j] = mat1[i][j] - mat2[i][j];
				}
			}

			return matf;
		}

		return null;
	}
	
	
	/*Nesse metodo é realizada a multiplicação de duas matrizes, onde é percorrido o vetor mat1 e mat2,
	 *  e assim realizado a multiplicação de cada valor da coluna de uma das matrizes pela linha da outra.
	 *  As posições da matriz mat1 é obtida através da troca da coluna pela linha e a linha pela coluna de mat2  */
	public double[][] multiplicarMatrizes(double[][] mat1, double[][] mat2) {

		if (mat1[0].length == mat2.length) {

			double[][] matf = new double[mat1.length][mat2[0].length];

			for (int i = 0; i < mat1.length; i++) {
				for (int j = 0; j < mat2[0].length; j++) {
					for (int k = 0; k < mat1[0].length; k++) {
						matf[i][j] += (mat1[i][k] * mat2[k][j]);
					}
				}
			}
			return matf;
		}
		return null;
	}
	
/*Este é um método auxiliar para trocar as linhas pelas colunas de uma matriz */
	public double[][] transpostaMatriz(double[][] mat) {

		double[][] matf = new double[mat[0].length][mat.length];

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				matf[j][i] = mat[i][j];
			}
		}

		return matf;
	}

	/*Esse método percorre uma matriz e multiplica cada posição da mesma, por um valor inteiro*/
	public double[][] multiplicacaoPorEscalar(double[][] mat, double k) {

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				mat[i][j] *= k;
			}
		}
		return mat;
	}

	

	/* Método usado: Laplace 
	 * 
	 * Este método percorre uma matriz, realizando a quebra da mesma em matrizes menores e obtendo
	 * o cofator delas ao chegar em matrizes de tamanho 2x2 e assim salvando a soma dos valores de todos os 
	 * determinantes;
	 * 
	 * */
	public double determinanteMatriz(double[][] matriz) {

		double determinante = 0.0;

		if (matriz.length == 1)
			determinante = matriz[0][0];
		else if (matriz.length == 2)
			determinante = matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];

		else {
			double[][] aux;
			int i_aux, j_aux, linha, coluna;

			for (int i = 0; i < matriz.length; i++) {

				if (matriz[0][i] != 0) {
					aux = new double[matriz.length - 1][matriz.length - 1];
					i_aux = 0;
					j_aux = 0;

					for (linha = 1; linha < matriz.length; linha++) {
						for (coluna = 0; coluna < matriz.length; coluna++) {
							if (coluna != i) {
								aux[i_aux][j_aux] = matriz[linha][coluna];
								j_aux++;
							}
						}

						i_aux++;
						j_aux = 0;
					}

					determinante += Math.pow(-1, i) * matriz[0][i] * determinanteMatriz(aux);
				}

			}
		}
		return determinante;
	}

	private double[][] substMat(int i, int j, double[][] matriz) {

		double[][] temp = new double[matriz.length - 1][matriz.length - 1];

		int count1 = 0;
		int count2 = 0;

		for (int k = 0; k < matriz.length; k++) {
			if (k != i) {
				count2 = 0;
				for (int l = 0; l < matriz.length; l++) {
					if (l != j) {
						temp[count1][count2] = matriz[k][l];

						count2++;
					}
				}
				count1++;
			}
		}

		return temp;
	}

	private double[][] adjuntaMatriz(double[][] mat) {

		double[][] tempAdjunta = new double[mat.length][mat.length];

		for (int i = 0; i < tempAdjunta.length; i++) {
			for (int j = 0; j < tempAdjunta.length; j++) {
				double[][] temp = this.substMat(i, j, mat);

				double elementoAdjunto = (Math.pow(-1, i + j) * this.determinanteMatriz(temp));

				tempAdjunta[i][j] = elementoAdjunto;
			}
		}

		return tempAdjunta;
	}
	/*Para calcular a inversa é usado um metodo auxiliar para calcular o determinante e 
	 * logo em seguida é realizado as operações baseadas em cofatores para obter a matriz Adjunta
	 * e logo em seguida, calcular sua transposta e assim é dividido cada um de seus valores pelo determinante
	 * calculado inicialmente.  */
	public double[][] inversaMatriz(double[][] mat) {

		double det = determinanteMatriz(mat);
		double matf[][] = transpostaMatriz(adjuntaMatriz(mat));

		for (int i = 0; i < matf.length; i++) {
			for (int j = 0; j < matf.length; j++) {
				matf[i][j] = matf[i][j] / det;
			}
		}

		return matf;
	}

	/* Método usado: Gauss 
	 * 
	 * Este método realiza o escalonamento de cada linha de uma matriz, onde seus termos independentes 
	 * são dados por um vetor de tamanho igual ao número de linhas da matriz. Cada linha da matriz é percorrida
	 * e relizada operações de divisão com base no pivo mais a direita percorrido.
	 * 
	 * */
	public double[] resolSistemaLinear(double[][] mat, double[] vet) {

		int n = vet.length;

		for (int k = 0; k < n; k++) {

			int max = k;

			for (int i = k + 1; i < n; i++) {
				if (Math.abs(mat[i][k]) > Math.abs(mat[max][k]))
					max = i;
			}

			double[] temp = mat[k];
			mat[k] = mat[max];
			mat[max] = temp;

			double t = vet[k];
			vet[k] = vet[max];
			vet[max] = t;

			for (int i = k + 1; i < n; i++) {

				double fator = mat[i][k] / mat[k][k];
				vet[i] -= fator * vet[k];

				for (int j = k; j < n; j++) {
					mat[i][j] -= fator * mat[k][j];
				}
			}
		}

		int tipoSistema = 0;

		for (int i = 0; i < mat.length; i++) {

			for (int j = 0; j < mat[0].length; j++) {

				if (i == mat.length - 1) {
					tipoSistema += mat[i][j];

				}

			}

		}

		if (tipoSistema == 0 && vet[vet.length-1] == 0) {

			
			JOptionPane.showMessageDialog(null, "Sistema Indeterminado - (Infinitas soluções)!");

		} else if (tipoSistema == 0 && vet[vet.length-1] != 0) {

			JOptionPane.showMessageDialog(null, "Sistema Impossível - (Não tem solução)!");
			
		}

		double[] solution = new double[n];

		for (int i = n - 1; i >= 0; i--) {

			double sum = 0.0;

			for (int j = i + 1; j < n; j++) {
				sum += mat[i][j] * solution[j];
			}

			solution[i] = (vet[i] - sum) / mat[i][i];
		}

		matResult = mat;

		return solution;
	}

	public String imprimeMatriz(double[][] mat) {
		String str = "";

		if (mat == null) {

			str += "Valores/Matrizes incorretos!";

		} else {
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat[0].length; j++) {

					str += "|" + mat[i][j];
				}

				str += "|" + "\n";

			}
		}

		return str;
	}

	public double[][] getMatResult() {
		return matResult;
	}

	public void setMatResult(double[][] matResult) {
		this.matResult = matResult;
	}

}
