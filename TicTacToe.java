import java.util.Scanner;

class TicTacToe{

	
	public static void main(String[] args) {
		Field field = new Field();
		
		Scanner scanner = new Scanner(System.in);
		int zug;
		int count = 1;
		field.initField();

		while (count <= 10) {
			System.out.println("##########################");
			System.out.println("Zug Nr. " + count);
			if (count % 2 == 0) {
				System.out.println("Spieler X ist an der Reihe.");
			} else {
				System.out.println("Spieler O ist an der Reihe.");
			}
			System.out.println("Aktuelles Spielfeld:");
			field.printField();
			System.out.println("Wähle ein freies Feld:");

			if (field.isGameWon()) {
				System.out.println("###########################");
				System.out.println("#########Spielende#########");
				System.out.println("###########################");
				field.printField();
				System.out.println("Sieger: Spieler " + field.getWinner());
				System.out.println("###########################");
				return;
			} else {
				try {
					zug = scanner.nextInt();
					if (zug > 0 && zug <= 9) {

						if (field.isPlayPossible(zug)) {
							if (count % 2 == 0) {
								field.set('X', zug);
							} else {
								field.set('O', zug);
							}
							count++;
						} else {
							System.out.println("##########################");
							System.out.println("Ungültiger Spielzug, bitte nur Zahlen von 1 bis 9 wählen.");
						}
					} else {
						System.out.println("##########################");
						System.out.println("Ungültiger Spielzug, bitte nur Zahlen von 1 bis 9 wählen.");
					}

				} catch (java.util.InputMismatchException e) {
					System.out.println("Ungültiger Spielzug, bitte nur Zahlen von 1 bis 9 wählen.");
					scanner.nextLine();
				}
			}
			if(count == 10 && !field.isGameWon()) {
				System.out.println("###########################");
				System.out.println("#########Spielende#########");
				System.out.println("###########################");
				field.printField();
				System.out.println("###########################");
				System.out.println("#######Unentschieden#######");
				System.out.println("###########################");
				return;
			}
		}

			
	}
}

class Field{

	private char[][] field;
	private char winner;

	private int[][] playToCoords(int zug) {

		int[][] coords = new int[1][2];


		switch (zug) {
			case 1:
				coords[0][0] = 1;
				coords[0][1] = 0;
				break;
			case 2:
				coords[0][0] = 5;
				coords[0][1] = 0;
				break;
			case 3:
				coords[0][0] = 9;
				coords[0][1] = 0;
				break;
			case 4:
				coords[0][0] = 1;
				coords[0][1] = 2;
				break;
			case 5:
				coords[0][0] = 5;
				coords[0][1] = 2;
				break;
			case 6:
				coords[0][0] = 9;
				coords[0][1] = 2;
				break;
			case 7:
				coords[0][0] = 1;
				coords[0][1] = 4;
				break;
			case 8:
				coords[0][0] = 5;
				coords[0][1] = 4;
				break;
			case 9:
				coords[0][0] = 9;
				coords[0][1] = 4;
				break;
		}
		return coords;
	}

	public void initField(){
		/*
		 *	j
		 * i  012345678910
		 * 	0  1 | 2 | 3
		 * 	1 ---+---+---
		 * 	2  4 | 5 | 6
		 * 	3 ---+---+---
		 * 	4  7 | 8 | 9
		 *     
		 *	4 = [1][2]
		 *	8 = [5][4]
		*/

		field = new char[11][5];
	
		for(int i = 0; i < field.length; i++){
			for(int j = 0; j < field[0].length; j++) {
				field[i][j] = ' ';
			}
		}

		for(int i = 0; i < field[0].length; i++) {
			field[3][i] = '|';
			field[7][i] = '|';
		}

		for(int i = 0; i < field.length; i++) {
			field[i][1] = '-';
			field[i][3] = '-';
		}

		field[3][1] = '+';
		field[3][3] = '+';
		field[7][1] = '+';
		field[7][3] = '+';

		field[1][0] = '1';
		field[5][0] = '2';
		field[9][0] = '3';
		field[1][2] = '4';
		field[5][2] = '5';
		field[9][2] = '6';
		field[1][4] = '7';
		field[5][4] = '8';
		field[9][4] = '9';
	}

	public boolean isGameWon(){
		if(field[1][0] == field[5][0] && field[1][0]== field[9][0]) {
			winner = field[1][0];
			return true;
		}
		if(field[1][2] == field[5][2] && field[1][2]== field[9][2]) {
			winner = field[1][2];
			return true;
		}
		if(field[1][4] == field[5][4] && field[1][4]== field[9][4]) {
			winner = field[1][4];
			return true;
		}
		if(field[1][0] == field[1][2] && field[1][0]== field[1][4]) {
			winner = field[1][0];
			return true;
		}
		if(field[5][0] == field[5][2] && field[5][0]== field[5][4]) {
			winner = field[5][0];
			return true;
		}
		if(field[9][0] == field[9][2] && field[9][0]== field[9][4]) {
			winner = field[9][0];
			return true;
		}
		if(field[1][0] == field[5][2] && field[1][0]== field[9][4]) {
			winner = field[1][0];
			return true;
		}
		if(field[9][0] == field[5][2] && field[9][0]== field[1][4]) {
			winner = field[9][0];
			return true;
		}
		return false;
	}

	public char getWinner(){
		return winner;
	}

	public void printField() {
		for(int i = 0; i < field[0].length; i++) {
			for(int j = 0; j<field.length; j++) {
				System.out.print(field[j][i]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public void set(char spieler, int zug){
		int[][] coords = this.playToCoords(zug);
		field[coords[0][0]][coords[0][1]] = spieler;
	}

	public boolean isPlayPossible(int zug){
		int[][] coords = this.playToCoords(zug);

		if(field[coords[0][0]][coords[0][1]] == 'O' ||field[coords[0][0]][coords[0][1]] == 'X') {
			 return false;
		} else {
			 return true;
		}
	}
}