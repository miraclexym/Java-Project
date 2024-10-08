package GomokuGame;

import java.util.Scanner;

public class View {
	private View() {}
	private static View instance;	//µ¥ÀýÄ£Ê½
	
	public static View getView(){
		if(instance == null)
			instance = new View();
		return instance;
	}
	private static boolean isGameover = false;
	
	public static Scanner scanner = new Scanner(System.in);
    
	public static void getInput(){
		while(true){
			if(!isGameover){
				System.out.println("Please enter the row and the column for the next chess position");
				int row = scanner.nextInt();
				int col = scanner.nextInt();
				Control.putChess(row, col);
			}
			else
				return;
		}
	}
	
	public static void displayBoard() {
		System.out.println("The following is the current situation of the chessboard");
        for (int i = 0; i < Model.WIDTH; i++) {
            for (int j = 0; j < Model.WIDTH; j++) {
            	int color = Model.getChess(i, j);
            	switch(color){
            	case Model.BLACK:
            		System.out.print("¡ñ ");
            		break;
            	case Model.WHITE:
            		System.out.print("¡ð ");
            		break;
            	case Model.SPACE:
            		System.out.print("+ ");
            		break;
            	}
            }
            System.out.println();
        }
    }
	
	public static void celebration(int player){
		isGameover = true;
		if(player == Model.BLACK)
			System.out.println("The game is over, Black Chess wins!");
		else
			System.out.println("The game is over, White Chess wins!");
	}

}
