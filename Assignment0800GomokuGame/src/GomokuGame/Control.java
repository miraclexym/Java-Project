package GomokuGame;

public class Control {
	private Control(){}
	private static Control instance;	//����ģʽ
	
	public static Control getControl(){
		if(instance == null)
			instance = new Control();
		return instance;
	}
	
	private static int player = Model.BLACK;
	
	public static void putChess(int row, int col){
		boolean success = Model.putChess(row, col, player);
		if(success){
			//����ģ��
			Model.putChess(row, col, player);
			
			//������ͼ
			View.displayBoard();
			
			//�ж���Ϸ����
			boolean isGameover = Model.isGameOver(row, col);
			
			//�����Ϸʤ����
			if(isGameover)
				View.celebration(player);
			
			//����ת��
			player = player == Model.BLACK ? Model.WHITE : Model.BLACK;
		}
	}
	
	public static void main(String[] args){
		View.displayBoard();
		View.getInput();
	}
}
