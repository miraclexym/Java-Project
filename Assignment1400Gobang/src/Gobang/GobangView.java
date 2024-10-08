package Gobang;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GobangView extends JFrame {

    private static final long serialVersionUID = 1L;

    private CardLayout cardLayout = new CardLayout(); // 卡片布局
    private JPanel mainPanel = new JPanel((new BorderLayout())); // 主页面板
    private JPanel cardPanel = new JPanel(cardLayout); // 卡片面板
    private JPanel buttonPanel = new JPanel(); // 按钮面板
    
	private JButton startButton = new JButton("开始游戏");
	private JButton homeButton = new JButton("返回主页");
	private JButton settingsButton = new JButton("游戏设置");
	private JButton modeButton = new JButton("游戏模式");
	private JButton exitButton = new JButton("退出游戏");
	private JButton aboutButton = new JButton("关于游戏");
	private JButton designButton = new JButton("游戏设计");
	
    public GobangView() {
    	
        // 设置对话框文本字体
        UIManager.put("OptionPane.messageFont", Config.GAME_FONT);
        UIManager.put("OptionPane.titleFont", Config.GAME_FONT);
        UIManager.put("Button.font", Config.GAME_FONT);
    	
		// 设置窗口标题
		setTitle("五子棋游戏");
        // 设置窗口尺寸
        setSize(Config.GAME_HOME_WIDTH, Config.GAME_HOME_HEIGHT);
        // 设置窗口尺寸
    	setExtendedState(JFrame.MAXIMIZED_BOTH);
        // 窗口关闭设置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口居中
        setLocationRelativeTo(null);
        // 设置鼠标形状
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // 设置窗口可视
        setVisible(true);
        // 更改游戏字体
        changeGameFont();
        
        // 设置卡片布局
        cardPanel.add(GobangControl.HomePage, "HomePage");
        cardPanel.add(GobangControl.GamePage, "GamePage");
        
        // 设置按钮布局
        buttonPanel.add(startButton);
        buttonPanel.add(homeButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(modeButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(aboutButton);
        buttonPanel.add(designButton);
        
        // 设置主页布局
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(cardPanel, BorderLayout.CENTER);

        add(mainPanel); // 添加面板
        setVisible(true); // 设置可视
        
        
        // 增加按钮监听
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "GamePage");
            }
        });
        
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "HomePage");
            }
        });
        
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (GobangControl.SettingFrame.isVisible()) {
            	    GobangControl.SettingFrame.setVisible(false);
            	} else {
            	    GobangControl.SettingFrame.setVisible(true);
            	}
            }
        });
    	
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);;
            }
        });
        
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
		        String aboutGame = 
		        		"关于游戏：\n\n" +
		    	        "游戏作者：徐亚民\n" +
    	    	        "作者学号：2212032\n" +
    	    	        "作者学院：计算机学院\n" +
    	    	        "作者专业：计算机科学与技术\n" +
    	    	        "开始时间：2023年12月18日晚上    \n" +
    	    	        "截止时间：2023年12月23日晚上    \n" +
    	    	        "历时天数：五天\n" +
    	    	        "耗费时长：三十余小时\n" +
    	    	        "总代码量：1830行";
		        // 弹出对话框显示关于游戏
		        JOptionPane.showMessageDialog(null, aboutGame, "游戏提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        designButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建一个图像Icon
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resource/联机五子棋游戏设计.png"));

                // 创建一个JLabel用于显示图像
                JLabel imageLabel = new JLabel(imageIcon);

                // 创建一个JPanel，并将JLabel添加到其中
                JPanel panel = new JPanel();
                panel.add(imageLabel);

                // 创建一个JScrollPane，滚动面板，将游戏设计添加到其中
                JScrollPane scrollPane = new JScrollPane(panel);
                scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                
                // 设置JPanel的首选大小
                scrollPane.setPreferredSize(new Dimension(Config.DIALOG_WIDTH, Config.DIALOG_HEIGHT));
                scrollPane.setMaximumSize(new Dimension(Config.DIALOG_WIDTH, Config.DIALOG_HEIGHT));
                
                // 弹出对话框显示图像
                JOptionPane.showMessageDialog(null, scrollPane, "游戏设计", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

	public void changeGameFont() {
		startButton.setFont(Config.GAME_FONT);
		homeButton.setFont(Config.GAME_FONT);
		settingsButton.setFont(Config.GAME_FONT);
		modeButton.setFont(Config.GAME_FONT);
		exitButton.setFont(Config.GAME_FONT);
		aboutButton.setFont(Config.GAME_FONT);
		designButton.setFont(Config.GAME_FONT);
	}
}