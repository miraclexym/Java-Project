package Gobang;

import java.util.Random;

public class GobangModel {
    protected int[][] xPosition = new int[Config.GRID_SIZE][Config.GRID_SIZE];
    protected int[][] yPosition = new int[Config.GRID_SIZE][Config.GRID_SIZE];
	protected int[][] chessColor = new int[Config.GRID_SIZE][Config.GRID_SIZE];
    protected int[][] replayArray = new int [Config.GRID_SIZE * Config.GRID_SIZE][3]; // 复盘数组，存储每一步的棋子位置和颜色
    protected int moveCount = 0; // 记录已下棋的数量
    
    // 构造函数
    public GobangModel() {
    	calculateCoordinate(); // 计算坐标
        initializeBoard(); // 初始化棋盘
    }
    
    // 计算坐标
    private void calculateCoordinate(){
        for (int row = 0; row < Config.GRID_SIZE; row++) {
            for (int col = 0; col < Config.GRID_SIZE; col++) {
                xPosition[row][col] = Config.GRID_OFFSET_X + col * Config.CELL_SIZE - Config.PIECE_SIZE / 2;
                yPosition[row][col] = Config.GRID_OFFSET_Y + row * Config.CELL_SIZE - Config.PIECE_SIZE / 2;
            }
        }
    }
    
    // 初始化棋盘
    private void initializeBoard() {
    	
    	// 棋盘数组
        for (int i = 0; i < Config.GRID_SIZE; i++) {
            for (int j = 0; j < Config.GRID_SIZE; j++) {
            	chessColor[i][j] = Config.EMPTY;
            }
        }
        
        // 复盘数组
        for (int i = 0; i < moveCount; i++) {
        	replayArray[i][2] = Config.EMPTY;
        }
        
        // 随机落子
        for(int i = 0; i < 0; i++) {
        	RandomChess();
        }
    }
    
    // 随机落子
    private void RandomChess() {
        Random random = new Random();
        setPieceAt(random.nextInt(Config.GRID_SIZE), random.nextInt(Config.GRID_SIZE), Config.WHITE);
        setPieceAt(random.nextInt(Config.GRID_SIZE), random.nextInt(Config.GRID_SIZE), Config.BLACK);
    }
    
    // 获得棋子颜色（绘图）
    public int getPieceAt(int row, int col) {
        return chessColor[row][col];
    }
    
    // 设置棋子颜色（下棋）
    public void setPieceAt(int row, int col, int player) {
        chessColor[row][col] = player;
        // 记录当前步的信息到复盘数组
        replayArray[moveCount][0] = row;
        replayArray[moveCount][1] = col;
        replayArray[moveCount][2] = player;
        moveCount++;
    }
    
    // 清空棋盘
    public void clearBoard() {
        initializeBoard();
    }
    
	// 判断五子棋游戏是否结束
	public boolean isGameOver(int row, int col) {
        return checkWin(row, col, 1, 0) || checkWin(row, col, 0, 1) || checkWin(row, col, 1, 1) || checkWin(row, col, 1, -1);
    }
	
	// 判断某条路径是否游戏胜利
	private boolean checkWin(int row, int col, int dr, int dc) {
        int count = 1;
        int player = chessColor[row][col];
        
        //检查左侧路径是否游戏胜利
        for (int i = 1; i < 5; i++) {
            int nowRow = row - i * dr;
            int nowCol = col - i * dc;
            if (nowRow >= 0 && nowRow < Config.GRID_SIZE && nowCol >= 0 && nowCol < Config.GRID_SIZE && chessColor[nowRow][nowCol] == player) {
                count++;
            }
            else
                break;
        }
        
        //检查右侧路径是否游戏胜利
        for (int i = 1; i < 5; i++) {
            int nowRow = row + i * dr;
            int nowCol = col + i * dc;
            if (nowRow >= 0 && nowRow < Config.GRID_SIZE && nowCol >= 0 && nowCol < Config.GRID_SIZE && chessColor[nowRow][nowCol] == player) {
                count++;
            }
            else
                break;
        }
        
        return count >= 5;
    }
	
    // 获取复盘数组
    public int[][] getReplayArray() {
        return replayArray;
    }

    // 获取已下棋数量
    public int getMoveCount() {
        return moveCount;
    }

    // 悔棋
	public void regret() {
		moveCount--;
        replayArray[moveCount][2] = Config.EMPTY;
        chessColor[replayArray[moveCount][0]][replayArray[moveCount][1]] = Config.EMPTY;
		moveCount--;
        replayArray[moveCount][2] = Config.EMPTY;
        chessColor[replayArray[moveCount][0]][replayArray[moveCount][1]] = Config.EMPTY;
	}

	public void moveForward() {
		moveCount++;
	}

	public void moveBackward() {
		moveCount--;
	}
}