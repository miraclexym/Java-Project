package Gobang;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

class GobangGrid extends JPanel {
	
	private static final long serialVersionUID = 1L; // 串行版本UID
    public static int theMouse_X = 0; // 鼠标位置的X坐标
    public static int theMouse_Y = 0; // 鼠标位置的Y坐标
    
    public GobangGrid() {
    	
        // 设置窗口背景颜色
        setBackground(new Color(205, 133, 63)); // 深棕色
        
        /**
         * 添加鼠标监听器
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 获取鼠标点击位置
                int mouseX = e.getX();
                int mouseY = e.getY();

                // 计算最近的交叉点
                int col = (mouseX - Config.GRID_OFFSET_X + Config.CELL_SIZE / 2) / Config.CELL_SIZE;
                int row = (mouseY - Config.GRID_OFFSET_Y + Config.CELL_SIZE / 2) / Config.CELL_SIZE;
                
                // 防止落棋超界
                if(col > Config.GRID_SIZE - 1)
                	return;
                if(row > Config.GRID_SIZE - 1)
                	return;
                
                // 不能下在同样的位置
                if(GobangControl.Model.getPieceAt(row, col) != Config.EMPTY)
                	return;
                
                // 发送给对方
                GobangControl.sendChess(row, col);
                
                // 重绘面板
                repaint();
                
                // 判断游戏结束
                boolean GameOver = GobangControl.Model.isGameOver(row, col);
                if(GameOver){
                	GobangControl.paintMouse = false;
                	GobangControl.sendWinning();
                }
                
            }
            
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		// 绘制鼠标
        		GobangControl.paintMouse = true;
                // 重绘面板
                repaint();
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent e) {
        		// 绘制鼠标
        		GobangControl.paintMouse = false;
                // 重绘面板
                repaint();
        	}
        });
        
        // 添加鼠标移动监听器
        addMouseMotionListener(new MouseAdapter() {
        	@Override
        	public void mouseMoved(MouseEvent e) {
        		
                // 获取鼠标位置
        		theMouse_X = e.getX();
        		theMouse_Y = e.getY();
                
                // 重绘面板
                repaint(); // 主要是重绘鼠标执棋
        	}
        });
    }
    
    /**
     * 重写绘图函数
     */
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // 启用抗锯齿，线条更平滑
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 绘制五子棋网格
        for (int i = 0; i < Config.GRID_SIZE; i++) {
            int x = Config.GRID_OFFSET_X + i * Config.CELL_SIZE;
            int y = Config.GRID_OFFSET_Y + i * Config.CELL_SIZE;

            // 绘制横线
            g2d.drawLine(Config.GRID_OFFSET_X, y, Config.GRID_END_X, y);

            // 绘制竖线
            g2d.drawLine(x, Config.GRID_OFFSET_Y, x, Config.GRID_END_Y);
        }
        
        // 绘制已下棋的步数
        for (int i = 0; i < GobangControl.Model.getMoveCount(); i++) {
            int row = GobangControl.Model.getReplayArray()[i][0];
            int col = GobangControl.Model.getReplayArray()[i][1];
            int color = GobangControl.Model.getReplayArray()[i][2];

            // 设置颜色
            if (color == Config.BLACK) {
                g2d.setColor(Color.BLACK);
            } else if (color == Config.WHITE) {
                g2d.setColor(Color.WHITE);
            } else
            	continue;

            // 设置位置
            int xPosition = GobangControl.Model.xPosition[row][col];
            int yPosition = GobangControl.Model.yPosition[row][col];

            // 绘制棋子
            g2d.fillOval(xPosition, yPosition, Config.PIECE_SIZE, Config.PIECE_SIZE);
        }
        
        // 游戏结束就不画鼠标了
        if(GobangControl.paintMouse == false)
        	return;
        
        // 绘制鼠标棋子
        if (GobangControl.currentAttribute == "SERVER") {
            g2d.setColor(Color.BLACK);
        } else
            g2d.setColor(Color.WHITE);
        g2d.fillOval(theMouse_X - Config.PIECE_SIZE / 2, theMouse_Y - Config.PIECE_SIZE / 2,
        		Config.PIECE_SIZE, Config.PIECE_SIZE);
    }
    
    // 设置背景颜色
    public void changeBackground(Color color){
    	setBackground(color);
    }
}