package Gobang;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GobangSetting extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel musicStatusLabel;
    private JButton stopMusicButton;
    private JButton playMusicButton;
    
    private JLabel fontLabel;
    private JComboBox<String> fontComboBox;

    private JLabel musicLabel;
    private JComboBox<String> musicComboBox;

    private JLabel languageLabel;
    private JComboBox<String> languageComboBox;

    private JLabel bgColorLabel;
    private JComboBox<String> bgColorComboBox;

    private JLabel chessLabel;
    private JButton chessButton;
    
    private JPanel suspendPanel = new JPanel();
    private JPanel choicePanel = new JPanel();
    private JPanel fontPanel = new JPanel();
    private JPanel languagePanel = new JPanel();
    private JPanel bgColorPanel = new JPanel();
    private JPanel chessPanel = new JPanel();
    
    public GobangSetting() {
        setTitle("游戏设置"); // 框架标题
        setSize(600, 800); // 框架尺寸
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 默认关闭
        setLocationRelativeTo(null); // 位置居中
        initializeComponents(); // 初始化组件
        setupLayout(); // 设置布局
        addListeners(); // 添加监听
        setVisible(false); // 使之隐藏
        setAlwaysOnTop(true); // 位于顶层
        // 设置鼠标形状为手形
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void initializeComponents() {
    	
        musicStatusLabel = new JLabel("背景音乐状态：暂停");
        stopMusicButton = new JButton("暂停");
        playMusicButton = new JButton("播放");
        musicLabel = new JLabel("选择背景音乐:");
        String[] musicOptions = {"Five Hundred Miles", "卡农", "路过人间", "云烟成雨", "这世界那么多人"};
        musicComboBox = new JComboBox<>(musicOptions);
        fontLabel = new JLabel("选择游戏字体:");
        String[] fontOptions = {"楷体", "仿宋", "等线", "隶书", "黑体", "宋体", "华文彩云", "华文新魏", "华文琥珀", "华文行楷", "方正姚体", "方正粗黑宋简体"};
        fontComboBox = new JComboBox<>(fontOptions);
        languageLabel = new JLabel("选择游戏语言:");
        String[] languageOptions = {"中文", "英语", "俄语", "德语", "法语"};
        languageComboBox = new JComboBox<>(languageOptions);
        bgColorLabel = new JLabel("选择背景颜色:");
        String[] bgColorOptions = {"深棕色", "淡棕色", "深橙色", "淡橙色"};
        bgColorComboBox = new JComboBox<>(bgColorOptions);
        chessLabel = new JLabel("当前执棋颜色：黑色");
        chessButton = new JButton("切换");
        
        changeGameFont();
    }

    private void setupLayout() {
        
        setLayout(new GridLayout(6, 1));
        
        suspendPanel.add(musicStatusLabel);
        suspendPanel.add(stopMusicButton);
        suspendPanel.add(playMusicButton);

        choicePanel.add(musicLabel);
        choicePanel.add(musicComboBox);

        fontPanel.add(fontLabel);
        fontPanel.add(fontComboBox);

        languagePanel.add(languageLabel);
        languagePanel.add(languageComboBox);

        bgColorPanel.add(bgColorLabel);
        bgColorPanel.add(bgColorComboBox);

        chessPanel.add(chessLabel);
        chessPanel.add(chessButton);
        
        
        add(suspendPanel);
        add(choicePanel);
        add(fontPanel);
        add(languagePanel);
        add(bgColorPanel);
        add(chessPanel);
    }
    
    private void addListeners(){
    	
        /**
         * 音乐控制
         */
        
    	stopMusicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GobangControl.stopBackgroundMusic();
            }
        });  
    	
    	playMusicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GobangControl.playbackgroundMusic();
            }
        });
    	
    	/**
    	 * 音乐选择
    	 */
    	
    	musicComboBox.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent event) {
				@SuppressWarnings("unchecked")
				JComboBox<String> source = (JComboBox<String>) event.getSource();
				String selectedOption = (String) source.getSelectedItem();
				GobangControl.changeMusic(selectedOption);
			}
        });
    	
    	/**
    	 * 字体设置
    	 */
    	
    	fontComboBox.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent event) {
				@SuppressWarnings("unchecked")
				JComboBox<String> source = (JComboBox<String>) event.getSource();
				String selectedOption = (String) source.getSelectedItem();
				GobangControl.changeGameFont(selectedOption);
			}
        });
    	
    	/**
    	 * 颜色设置
    	 */
    	
    	bgColorComboBox.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent event) {
				@SuppressWarnings("unchecked")
				JComboBox<String> source = (JComboBox<String>) event.getSource();
				String selectedOption = (String) source.getSelectedItem();
				Color goalColor = new Color(205, 133, 63);
				switch (selectedOption) {
			    case "深棕色":
			        goalColor = new Color(205, 133, 63); // 深棕色
			        break;
			    case "淡棕色":
			        goalColor = new Color(225, 165, 95); // 淡棕色
			        break;
			    case "深橙色":
			        goalColor = new Color(255, 140, 0); // 深橙色
			        break;
			    case "淡橙色":
			        goalColor = new Color(255, 200, 0); // 淡橙色
			        break;
			}

				GobangControl.changeBackgroundColor(goalColor);
			}
        });
    	
        /**
         * 执棋设置
         */
        
    	chessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GobangControl.changeChessColor();
            }
        });
    }
    
    private void styleSettingsPanel(JPanel panel, String title) {
        panel.setBackground(Config.SETTINGS_PANEL_COLOR);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(title);
        titledBorder.setTitleFont(Config.GAME_FONT);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(Config.EMPTY_BORDER, titledBorder);
        panel.setBorder(compoundBorder);
    }
    
    public void changeChessColor(String Color){
    	chessLabel.setText("当前执棋颜色：" + Color);
    }

	public void changeMusicStatus(String status) {
		musicStatusLabel.setText("背景音乐状态：" + status);
	}

	public void changeGameFont() {
        musicStatusLabel.setFont(Config.GAME_FONT);
        stopMusicButton.setFont(Config.GAME_FONT);
        playMusicButton.setFont(Config.GAME_FONT);
        musicLabel.setFont(Config.GAME_FONT);
        musicComboBox.setFont(Config.GAME_FONT);
        fontLabel.setFont(Config.GAME_FONT);
        fontComboBox.setFont(Config.GAME_FONT);
        languageLabel.setFont(Config.GAME_FONT);
        languageComboBox.setFont(Config.GAME_FONT);
        bgColorLabel.setFont(Config.GAME_FONT);
        bgColorComboBox.setFont(Config.GAME_FONT);
        chessLabel.setFont(Config.GAME_FONT);
        chessButton.setFont(Config.GAME_FONT);
        
        styleSettingsPanel(suspendPanel, "音乐控制");
        styleSettingsPanel(choicePanel, "音乐选择");
        styleSettingsPanel(fontPanel, "字体设置");
        styleSettingsPanel(languagePanel, "语言设置");
        styleSettingsPanel(bgColorPanel, "颜色设置");
        styleSettingsPanel(chessPanel, "执棋设置");
	}
}