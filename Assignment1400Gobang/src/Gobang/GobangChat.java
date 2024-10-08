package Gobang;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

public class GobangChat extends JPanel {

	private static final long serialVersionUID = 1L;

	private TitledBorder recordBorder;
    private JTextArea recordTextArea = new JTextArea();;
    private JScrollPane scrollPane = new JScrollPane(recordTextArea);
    
	private TitledBorder chatBorder;
    private JTextArea chatTextArea;
    
    private JButton sendButton = new JButton("发送消息");

    public GobangChat() {
    	
    	/**
    	 * 聊天记录
    	 */
    	
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // 滑轮垂直滑动
        recordTextArea.setBackground(Config.CHAT_TEXT_AREA_COLOR); // 背景颜色
        scrollPane.setBackground(Config.CHAT_TEXT_AREA_COLOR); // 背景颜色
        recordTextArea.setLineWrap(true); // 自动换行
    	recordTextArea.setEditable(false); // 不可编辑
    	
    	recordBorder = BorderFactory.createTitledBorder("聊天记录"); // 标题名称
    	recordBorder.setTitlePosition(TitledBorder.TOP); // 标题在顶部
    	recordBorder.setBorder(new LineBorder(Color.BLACK)); // 边框颜色黑色
        CompoundBorder innerRecordBorder = BorderFactory.createCompoundBorder(recordBorder, Config.EMPTY_BORDER); // 内层边框
        CompoundBorder outerRecordBorder = BorderFactory.createCompoundBorder(Config.EMPTY_BORDER, innerRecordBorder); // 外层边框
        scrollPane.setBorder(outerRecordBorder); // 设置边界
    	
        /**
         * 聊天区域
         */
        
        chatTextArea = new JTextArea("请在此输入想发送的文本...");
        chatTextArea.setPreferredSize(new Dimension(Config.CHAT_AREA_WIDTH, Config.CHAT_AREA_HEIGHT));
        chatTextArea.setMaximumSize(new Dimension(Config.CHAT_AREA_WIDTH, Config.CHAT_AREA_HEIGHT));
        chatTextArea.setBackground(Config.CHAT_TEXT_AREA_COLOR);
        chatTextArea.setLineWrap(true);
        chatTextArea.setEditable(true);

        chatBorder = BorderFactory.createTitledBorder("聊天区域");
        chatBorder.setTitlePosition(TitledBorder.TOP);
        chatBorder.setBorder(new LineBorder(Color.BLACK));
        CompoundBorder innerChatBorder = BorderFactory.createCompoundBorder(chatBorder, Config.EMPTY_BORDER);
        CompoundBorder outerChatBorder = BorderFactory.createCompoundBorder(Config.EMPTY_BORDER, innerChatBorder);
        chatTextArea.setBorder(outerChatBorder);
        
        /**
         * 增加监听器
         */
        
        chatTextArea.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                repaint();
            }

            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                repaint();
            }
        });
        
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GobangControl.sendMessage();
            	chatTextArea.setText("");
            }
        });
        
        /**
         * 设置布局
         */
        
        setBackground(Config.CHAT_TEXT_AREA_COLOR);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(scrollPane);
        add(chatTextArea);
        add(sendButton);
        
        /**
         * 设置字体
         */
        changeGameFont();
    }
    
    // 更新消息记录
    public void updateChatLog(String message) {
    	recordTextArea.append(message + "\n");
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMaximum()); // 滑轮在底部
    }
    
    // 获得发送文本
	public JTextArea getChatTextArea() {
		return chatTextArea;
	}
	
	// 设置游戏字体
	public void changeGameFont() {
        recordTextArea.setFont(Config.GAME_FONT);
        recordBorder.setTitleFont(Config.GAME_FONT);
        chatTextArea.setFont(Config.GAME_FONT);
        chatBorder.setTitleFont(Config.GAME_FONT);
        sendButton.setFont(Config.GAME_FONT);
	}
}