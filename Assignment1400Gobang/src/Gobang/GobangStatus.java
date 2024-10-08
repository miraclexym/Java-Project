package Gobang;

import javax.swing.JLabel;

public class GobangStatus extends JLabel {
	
	private static final long serialVersionUID = 1L;

	public GobangStatus() {
        super("    游戏状态：未联机，单机游戏。"); // 设置内容
        setBackground(Config.STATUS_LABEL_COLOR); // 设置颜色
        setFont(Config.GAME_FONT); // 设置字体
        setOpaque(true);  // 设置为不透明
    }

	public void changeGameFont() {
        setFont(Config.GAME_FONT); // 设置字体
	}
	
	public void setText(String text) {
        super.setText(text); // 设置字体
	}
}