package GomokuGame;

public class Model {
	private Model() {}
	private static Model instance;	//单例模式
	
	public static Model getModel(){
		if(instance == null)
			instance = new Model();
		return instance;
	}
	
	//游戏配置参数
	public static final int BLACK = 1;
	public static final int WHITE = -1;
	public static final int SPACE = 0;
	public static final int WIDTH = 19;
	
	//游戏棋盘数组
	private static int[][] data = new int [WIDTH][WIDTH];
	
	//当前棋手下一个棋子
	public static boolean putChess(int row, int col, int color){
		if(row >= 0 && row < WIDTH && col >= 0 && col < WIDTH && data[row][col] == SPACE){
			data[row][col] = color;
			return true;
		}
		return false;
	}
	
	//获得当前所下的棋子颜色
	public static int getChess(int row, int col){
		if(row >= 0 && row < WIDTH && col >= 0 && col < WIDTH)
			return data[row][col];
		return SPACE;
	}
	
	//判断五子棋游戏是否结束
	public static boolean isGameOver(int row, int col) {
        return checkWin(row, col, 1, 0) || checkWin(row, col, 0, 1) || checkWin(row, col, 1, 1) || checkWin(row, col, 1, -1);
    }
	
	//判断某条路径是否游戏胜利
	public static boolean checkWin(int row, int col, int dr, int dc) {
        int count = 1;
        int player = data[row][col];

        //检查左侧路径是否游戏胜利
        for (int i = 1; i < 5; i++) {
            int nowRow = row - i * dr;
            int nowCol = col - i * dc;
            if (nowRow >= 0 && nowRow < WIDTH && nowCol >= 0 && nowCol < WIDTH && data[nowRow][nowCol] == player) {
                count++;
            }
            else
                break;
        }

        //检查右侧路径是否游戏胜利
        for (int i = 1; i < 5; i++) {
            int nowRow = row + i * dr;
            int nowCol = col + i * dc;
            if (nowRow >= 0 && nowRow < WIDTH && nowCol >= 0 && nowCol < WIDTH && data[nowRow][nowCol] == player) {
                count++;
            }
            else
                break;
        }

        return count >= 5;
    }
}
