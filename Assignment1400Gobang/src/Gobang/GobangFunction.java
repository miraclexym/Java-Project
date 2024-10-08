package Gobang;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GobangFunction extends JPanel {
	
	private static final long serialVersionUID = 1L; // 串行版本UID
	
	private JTextArea termArea = new JTextArea(); // 创建术语区域
	private TitledBorder termBorder; // 创建术语边框
	
	private JPanel onlinePanel = new JPanel(); // 创建联机面板
	private TitledBorder middleOnlineBorder; // 创建联机边框
    private JTextField ipTextField = new JTextField("服务器IP地址...（默认localhost）"); // 服务器IP地址
    private JTextField portTextField = new JTextField("服务器端口号...（默认12345）"); // 服务器端口号
    
    private JPanel buttonPanel = new JPanel();// 创建按钮面板
    private JButton ruleButton = new JButton("游戏规则");
    private JButton onlineButton = new JButton("联机策略");
    private JButton listenButton = new JButton("开始监听");
    private JButton connectButton = new JButton("发送连接");
    private JButton undoButton = new JButton("请求悔棋");
    private JButton rematchButton = new JButton("再来一局");
    private JButton forwardButton = new JButton("前进一步");
    private JButton backButton = new JButton("后退一步");
    
    public GobangFunction() {
    	
    	/**
    	 * 游戏术语区域
    	 */
    	
    	termArea.setText(
    	        "    〖黑方〗执黑棋一方的简称。\n" +
    	        "    〖白方〗执白棋一方的简称。\n" +
    	        "    〖胜局〗有一方获胜的对局。\n" +
    	        "    〖和局〗分不出胜负的对局。\n" +
    	        "    〖终局〗下棋双方对局结束。\n" +
    	        "    〖复盘〗本盘对局全部再现。\n" +
    	        "    〖悔棋〗撤销上一步的操作。\n" +
    	        "    〖重来〗重新开始新的对局。");
        // 设置文本域的尺寸
    	termArea.setPreferredSize(new Dimension(Config.TERM_AREA_WIDTH, Config.TERM_AREA_HEIGHT));
    	termArea.setMaximumSize(new Dimension(Config.TERM_AREA_WIDTH, Config.TERM_AREA_HEIGHT));
        // 设置文本域的颜色
    	termArea.setBackground(Config.TERM_TEXT_AREA_COLOR);
        // 设置文本域的字体
    	termArea.setFont(Config.GAME_FONT);
        // 设置文本域自动换行
    	termArea.setLineWrap(true);
        // 设置文本域不可编辑
    	termArea.setEditable(false);
        // 设置文本域边框样式
    	termBorder = BorderFactory.createTitledBorder("游戏术语");
    	termBorder.setTitleFont(Config.GAME_FONT);
    	termBorder.setTitlePosition(TitledBorder.TOP);
    	termBorder.setBorder(new LineBorder(Color.BLACK));
        CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(Config.EMPTY_BORDER, termBorder);
        termArea.setBorder(compoundBorder);
    	
        
        /**
         * 联机面板
         */
        
        ipTextField.setBackground(Config.ONLINE_TEXT_FIELD_COLOR); // 背景颜色
        ipTextField.setFont(Config.GAME_FONT); // 设置字体
        ipTextField.setPreferredSize(new Dimension(Config.ONLINE_AREA_WIDTH, Config.ONLINE_AREA_HEIGHT));
        ipTextField.setMaximumSize(new Dimension(Config.ONLINE_AREA_WIDTH, Config.ONLINE_AREA_HEIGHT));
        
        portTextField.setBackground(Config.ONLINE_TEXT_FIELD_COLOR); // 背景颜色
        portTextField.setFont(Config.GAME_FONT); // 设置字体
        portTextField.setPreferredSize(new Dimension(Config.ONLINE_AREA_WIDTH, Config.ONLINE_AREA_HEIGHT));
        portTextField.setMaximumSize(new Dimension(Config.ONLINE_AREA_WIDTH, Config.ONLINE_AREA_HEIGHT));
        
        onlinePanel.setBackground(Config.ONLINE_TEXT_FIELD_COLOR); // 背景颜色
        onlinePanel.setLayout(new BoxLayout(onlinePanel, BoxLayout.Y_AXIS)); // 设置布局
        onlinePanel.add(ipTextField); // 增加组件
        onlinePanel.add(portTextField); // 增加组件
        middleOnlineBorder = BorderFactory.createTitledBorder("连机面板"); // 设置标题
        middleOnlineBorder.setTitleFont(Config.GAME_FONT); // 标题字体
        middleOnlineBorder.setTitlePosition(TitledBorder.TOP); // 标题位置
        middleOnlineBorder.setBorder(new LineBorder(Color.BLACK)); // 边框颜色
        CompoundBorder innerOnlineBorder = BorderFactory.createCompoundBorder(middleOnlineBorder, Config.EMPTY_BORDER);
        CompoundBorder outerOnlineBorder = BorderFactory.createCompoundBorder(Config.EMPTY_BORDER, innerOnlineBorder);
        onlinePanel.setBorder(outerOnlineBorder);
        
        /**
         * 功能按钮板
         */
        

        // 设置边框样式
        buttonPanel.setBorder(Config.EMPTY_BORDER);
        // 设置背景颜色
        buttonPanel.setBackground(Config.BUTTON_PANEL_COLOR);
        // 设置网格布局，几行几列
        buttonPanel.setLayout(new GridLayout(4, 2, 10, 10)); // 10, 10是行列之间的水平和垂直间距
        
        // 设置字体样式
        ruleButton.setFont(Config.GAME_FONT);
        onlineButton.setFont(Config.GAME_FONT);
        listenButton.setFont(Config.GAME_FONT);
        connectButton.setFont(Config.GAME_FONT);
        undoButton.setFont(Config.GAME_FONT);
        rematchButton.setFont(Config.GAME_FONT);
        forwardButton.setFont(Config.GAME_FONT);
        backButton.setFont(Config.GAME_FONT);
        
        // 将按钮添加到按钮面板
        buttonPanel.add(ruleButton);
        buttonPanel.add(onlineButton);
        buttonPanel.add(listenButton);
        buttonPanel.add(connectButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(rematchButton);
        buttonPanel.add(forwardButton);
        buttonPanel.add(backButton);
        
        /**
         * 游戏规则监听器
         */
        
        ruleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gameRules = 
                		"游戏规则：\n\n" +
                        "    (1) 对局双方各执一色棋子。\n" +
                        "    (2) 空棋盘开局。\n" +
                        "    (3) 黑先、白后，交替下子，每次只能下一子。\n" +
                        "    (4) 棋子下在棋盘的空白点上，棋子下定后不得移动或拿走。  \n" +
                        "    (5) 黑方的第一枚棋子必须下在天元点上，即中心交叉点。\n\n" +
                        "特殊规定：\n\n" +
                        "    执行黑方指定开局、三手可交换、五手两打的规定。\n" +
                        "    整个对局过程中黑方有禁手，白方无禁手。\n" +
                        "    黑方禁手有三三禁手、四四禁手和长连禁手三种。\n\n";

                // 弹出对话框显示游戏规则
                JOptionPane.showMessageDialog(null, gameRules, "游戏提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        /**
         * 联机策略监听器
         */
        
        onlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String onlineStrategy = 
                    "联机策略：\n" +
                    "    〖网络连接〗确保两台电脑能够相互访问，可以通过局域网或      \n"
                    + "者互联网进行连接。\n" +
                    "    〖修改IP地址〗在客户端程序中，输入服务器所在电脑的IP地    \n"
                    + "址，如果在同一台电脑上测试，可以设置“localhost”。\n" +
                    "    〖运行服务器程序〗在服务器端执行开始监听的操作，该操作    \n"
                    + "将会在指定的端口上监听客户端的连接。\n" +
                    "    〖运行客户端程序〗在客户端执行发送连接的操作，客户端程    \n"
                    + "序将尝试连接到指定的服务器IP地址和端口。\n" +
                    "    〖进行联机游戏〗连接成功后，客户端与服务器可以进行联机    \n"
                    + "游戏娱乐。";

                // 弹出对话框显示联机策略术语
                JOptionPane.showMessageDialog(null, onlineStrategy, "游戏提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        /**
         * 开始监听按钮监听器
         */
        
        listenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GobangControl.startListening();
            }
        });

        /**
         * 发送连接按钮监听器
         */
        
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GobangControl.sendConnection();
            }
        });

        /**
         * 请求悔棋按钮监听器
         */
        
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GobangControl.sendRegret();
            }
        });

        /**
         * 再来一局按钮监听器
         */
        
        rematchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GobangControl.sendRestart();
            }
        });
        
        /**
         * 前进一步按钮监听器
         */
        
        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GobangControl.moveForward();
            }
        });

        /**
         * 后退一步按钮监听器
         */
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GobangControl.moveBackward();
            }
        });
        
        /**
         * 设置功能面板布局为盒式垂直布局
         */

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // 把组件添加到功能面板里面
        add(termArea);
        add(onlinePanel); // 联机面板
        add(buttonPanel); // 功能按钮板
    }

	public void changeGameFont() {
		// 术语字体
		termArea.setFont(Config.GAME_FONT);
		termBorder.setTitleFont(Config.GAME_FONT);
		// 联机字体
        ipTextField.setFont(Config.GAME_FONT);
        portTextField.setFont(Config.GAME_FONT);
        middleOnlineBorder.setTitleFont(Config.GAME_FONT);
        // 按钮字体
        ruleButton.setFont(Config.GAME_FONT);
        onlineButton.setFont(Config.GAME_FONT);
        listenButton.setFont(Config.GAME_FONT);
        connectButton.setFont(Config.GAME_FONT);
        undoButton.setFont(Config.GAME_FONT);
        rematchButton.setFont(Config.GAME_FONT);
        forwardButton.setFont(Config.GAME_FONT);
        backButton.setFont(Config.GAME_FONT);
	}
}