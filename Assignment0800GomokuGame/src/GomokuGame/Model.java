package GomokuGame;

public class Model {
	private Model() {}
	private static Model instance;	//����ģʽ
	
	public static Model getModel(){
		if(instance == null)
			instance = new Model();
		return instance;
	}
	
	//��Ϸ���ò���
	public static final int BLACK = 1;
	public static final int WHITE = -1;
	public static final int SPACE = 0;
	public static final int WIDTH = 19;
	
	//��Ϸ��������
	private static int[][] data = new int [WIDTH][WIDTH];
	
	//��ǰ������һ������
	public static boolean putChess(int row, int col, int color){
		if(row >= 0 && row < WIDTH && col >= 0 && col < WIDTH && data[row][col] == SPACE){
			data[row][col] = color;
			return true;
		}
		return false;
	}
	
	//��õ�ǰ���µ�������ɫ
	public static int getChess(int row, int col){
		if(row >= 0 && row < WIDTH && col >= 0 && col < WIDTH)
			return data[row][col];
		return SPACE;
	}
	
	//�ж���������Ϸ�Ƿ����
	public static boolean isGameOver(int row, int col) {
        return checkWin(row, col, 1, 0) || checkWin(row, col, 0, 1) || checkWin(row, col, 1, 1) || checkWin(row, col, 1, -1);
    }
	
	//�ж�ĳ��·���Ƿ���Ϸʤ��
	public static boolean checkWin(int row, int col, int dr, int dc) {
        int count = 1;
        int player = data[row][col];

        //������·���Ƿ���Ϸʤ��
        for (int i = 1; i < 5; i++) {
            int nowRow = row - i * dr;
            int nowCol = col - i * dc;
            if (nowRow >= 0 && nowRow < WIDTH && nowCol >= 0 && nowCol < WIDTH && data[nowRow][nowCol] == player) {
                count++;
            }
            else
                break;
        }

        //����Ҳ�·���Ƿ���Ϸʤ��
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
