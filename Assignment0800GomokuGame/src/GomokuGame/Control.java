package GomokuGame;

public class Control {
	private Control(){}
	private static Control instance;	//单例模式
	
	public static Control getControl(){
		if(instance == null)
			instance = new Control();
		return instance;
	}
	
	private static int player = Model.BLACK;
	
	public static void putChess(int row, int col){
		boolean success = Model.putChess(row, col, player);
		if(success){
			//更新模型
			Model.putChess(row, col, player);
			
			//更新视图
			View.displayBoard();
			
			//判断游戏结束
			boolean isGameover = Model.isGameOver(row, col);
			
			//输出游戏胜利者
			if(isGameover)
				View.celebration(player);
			
			//棋手转变
			player = player == Model.BLACK ? Model.WHITE : Model.BLACK;
		}
	}
	
	public static void main(String[] args){
		View.displayBoard();
		View.getInput();
	}
}
