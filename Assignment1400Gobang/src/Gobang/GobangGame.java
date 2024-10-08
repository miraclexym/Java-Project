package Gobang;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class GobangGame extends JPanel {

    private static final long serialVersionUID = 1L; // 串行版本UID

    public GobangGame() {// 构造函数
        // 设置边框布局
        setLayout(new BorderLayout());
        // 添加介绍区域
        add(GobangControl.BriefArea, BorderLayout.NORTH); //北侧
        // 添加状态标签
        add(GobangControl.StatusLabel, BorderLayout.SOUTH); // 南侧
        // 添加功能面板
        add(GobangControl.FuntionPanel, BorderLayout.WEST); // 西侧
        // 添加聊天区域
        add(GobangControl.ChatPanel, BorderLayout.EAST); // 东侧
        // 添加棋盘面板
        add(GobangControl.GridPanel, BorderLayout.CENTER); //中间
    }
}