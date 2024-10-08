package Gobang;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Dimension;

public class GobangBrief extends JTextArea {
	
	private static final long serialVersionUID = 1L;
	private TitledBorder titledBorder;
	public GobangBrief() {
        super("    五子棋起源于中国，是全国智力运动会竞技项目之一，"
        		+ "是一种两人对弈的纯策略型棋类游戏。双方分别使用黑白两色的棋子，下"
        		+ "在棋盘直线与横线的交叉点上，先形成五子连珠的人获胜。五子棋非常容"
        		+ "易上手，老少皆宜，而且趣味横生，引人入胜，可以增强思维能力，提高"
        		+ "智力。");

        // 设置文本域的尺寸
        setPreferredSize(new Dimension(Config.BRIEF_AREA_WIDTH, Config.BRIEF_AREA_HEIGHT));
        setMaximumSize(new Dimension(Config.BRIEF_AREA_WIDTH, Config.BRIEF_AREA_HEIGHT));
        // 设置文本域的颜色
        setBackground(Config.BRIEF_TEXT_AREA_COLOR);
        // 设置文本域的字体
        setFont(Config.GAME_FONT);
        // 设置文本域自动换行
        setLineWrap(true);
        // 设置文本域不可编辑
        setEditable(false);
        // 设置文本域边框样式
        titledBorder = BorderFactory.createTitledBorder("游戏介绍");
        titledBorder.setTitleFont(Config.GAME_FONT);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(Config.EMPTY_BORDER, titledBorder);
        setBorder(compoundBorder);
    }

	public void changeGameFont() {
        setFont(Config.GAME_FONT);
        titledBorder.setTitleFont(Config.GAME_FONT);
	}
}