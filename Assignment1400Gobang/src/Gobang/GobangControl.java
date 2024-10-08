package Gobang;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class GobangControl {
	
	/**
	 * 
	 * ***********************主函数***********************
	 * 
	 */
	
	public static void main(String[] args) {}
	
	/**
	 * 
	 * ***********************静态框架***********************
	 * 
	 */
	public static GobangMusic Music = new GobangMusic(); // 此为音乐
	public static GobangOnline Online = new GobangOnline(); // 此为联机
	public static GobangSetting SettingFrame = new GobangSetting(); // 此为设置
	
	public static GobangBrief BriefArea = new GobangBrief(); // 界面上侧
	public static GobangStatus StatusLabel = new GobangStatus(); // 界面下侧
	public static GobangFunction FuntionPanel = new GobangFunction(); // 界面左侧
	public static GobangChat ChatPanel = new GobangChat(); // 界面右侧
	public static GobangGrid GridPanel = new GobangGrid(); // 界面中心
	
	public static GobangHome HomePage = new GobangHome(); // 游戏封面
	public static GobangGame GamePage = new GobangGame(); // 游戏界面
	public static GobangView View = new GobangView(); // 游戏视图
	public static GobangModel Model = new GobangModel(); // 游戏模型
	
	/**
	 * 
	 * ***********************静态变量***********************
	 * 
	 */
	
	public static boolean paintMouse = false; // 绘制鼠标标识符
	public static String currentAttribute = "SERVER"; // 当前程序的属性
	public static String musicPath = "/resource/Five Hundred Miles.wav"; // 播放音乐的路径
    public static int musicStatus = Config.MUSIC_PLAYING; // 当前的音乐状态
	public static String serverIpAddress = "localhost"; //服务器IP地址
	public static int serverPortNumber = 12345; // 服务器端口号
	
	/**
	 * 
	 * ***********************静态函数***********************
	 * 
	 */
    
    public static void changeChessColor() { // 更改棋子颜色
    	if(currentAttribute == "SERVER") {
    		currentAttribute = "CLIENT";
        	SettingFrame.changeChessColor("白色");
    	}
    	else {
    		currentAttribute = "SERVER";
        	SettingFrame.changeChessColor("黑色");
    	}
    }
    
    public static void changeBackgroundColor(Color color) { // 更改背景颜色
    	GridPanel.changeBackground(color);
    }
	
	public static void playbackgroundMusic() { // 播放背景音乐
		Music.playMusic(musicPath);
		musicStatus = Config.MUSIC_PLAYING;
		SettingFrame.changeMusicStatus("播放");
	}
	
	public static void stopBackgroundMusic() { // 更改音乐状态
    	if(musicStatus == Config.MUSIC_PLAYING) {
    		musicStatus = Config.MUSIC_SUSPEND;
    		SettingFrame.changeMusicStatus("暂停");
    		Music.pauseMusic();
    	}
    	else {
    		musicStatus = Config.MUSIC_PLAYING;
    		SettingFrame.changeMusicStatus("播放");
    		Music.resumeMusic();
    	}
	}

	public static void changeMusic(String selectedOption) { // 更改背景音乐
		musicPath = "/resource/" + selectedOption + ".wav";
	}

	public static void changeGameFont(String selectedOption) { // 更改游戏字体
		Config.GAME_FONT = new Font(selectedOption, Font.PLAIN, 30);
		
        // 设置对话框文本字体
        UIManager.put("OptionPane.messageFont", Config.GAME_FONT);
        UIManager.put("OptionPane.titleFont", Config.GAME_FONT);
        UIManager.put("Button.font", Config.GAME_FONT);
		
		BriefArea.changeGameFont();
		StatusLabel.changeGameFont();
		FuntionPanel.changeGameFont();
		ChatPanel.changeGameFont();
		
		View.changeGameFont();
		
		SettingFrame.changeGameFont();
	}
	
	public static void startListening() { // 开始监听
		Online.startListening();
		currentAttribute = "SERVER";
		StatusLabel.setText("    游戏状态：已联机，联机游戏。");
	}
	
	public static void sendConnection() { // 发送连接
		Online.sendConnection();
		currentAttribute = "CLIENT";
		StatusLabel.setText("    游戏状态：已联机，联机游戏。");
	}
	
	public static void moveForward() { // 前进一步
		Model.moveForward();
    	GridPanel.repaint();
	}
	
	public static void moveBackward() { //后退一步
		Model.moveBackward();
    	GridPanel.repaint();
	}
	
	public static void regret(){ // 悔棋
		Model.regret();
    	GridPanel.repaint();
	}
	
	public static void playAnotherRound(){ // 再来一局
    	Model.clearBoard();
    	GridPanel.repaint();
	}
	
	public static void receiveMessage(String message) { // 对方：消息
		System.out.println("对方：" + message);
        ChatPanel.updateChatLog("对方：" + message);
        JOptionPane.showMessageDialog(null, "对方：" + message, "游戏提示", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void receiveChess(int row, int col){ // 对方：下棋
		System.out.println("对方：" + "下棋：" + row + "," + col);
        ChatPanel.updateChatLog("对方：" + "下棋：" + row + "," + col);
		int chessColor;
		if(currentAttribute != "SERVER")
			chessColor = Config.SERVER_CHESS_COLOR;
		else
			chessColor = Config.CLIENT_CHESS_COLOR;
        Model.setPieceAt(row, col, chessColor);
        GridPanel.repaint();
	}
	
	public static void receiveRegret() { // 对方：请求悔棋
        System.out.println("对方：请求悔棋");
        ChatPanel.updateChatLog("对方：请求悔棋");
        // 对话框的选项
        Object[] options = {"同意", "拒绝"};
        // 显示对话框
        int result = JOptionPane.showOptionDialog(
                null,
                "对方请求悔棋，您是否同意？",
                "游戏提示",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,  // 不使用自定义图标
                options, // 按钮选项
                options[1]); // 默认选中的按钮
        // 处理用户选择的结果
        if (result == JOptionPane.YES_OPTION) {
        	sendAcceptRegret();
        } else {
        	sendRefuseRegret();
        }
	}
	
	public static void receiveAcceptRegret() { // 对方：接受悔棋
        System.out.println("对方：接受悔棋");
        ChatPanel.updateChatLog("对方：接受悔棋");
        JOptionPane.showMessageDialog(null, "对方接受悔棋！", "游戏提示", JOptionPane.INFORMATION_MESSAGE);
    	regret();
	}
	
	public static void receiveRefuseRegret() { // 对方：拒绝悔棋
        System.out.println("对方：拒绝悔棋");
        ChatPanel.updateChatLog("对方：拒绝悔棋");
        JOptionPane.showMessageDialog(null, "对方拒绝悔棋！", "游戏提示", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void receiveRestart() { // 对方：请求再来一局
        System.out.println("对方：请求再来一局");
        ChatPanel.updateChatLog("对方：请求再来一局");
        // 对话框的选项
        Object[] options = {"同意", "拒绝"};
        // 显示对话框
        int result = JOptionPane.showOptionDialog(
                null,
                "对方请求再来一局，您是否同意？",
                "游戏提示",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,  // 不使用自定义图标
                options, // 按钮选项
                options[1]); // 默认选中的按钮
        // 处理用户选择的结果
        if (result == JOptionPane.YES_OPTION) {
        	sendAcceptRestart();
        } else {
        	sendRefuseRestart();
        }
	}
	
	public static void receiveAcceptRestart() { // 对方：接受再来一局
        System.out.println("对方：接受再来一局");
        ChatPanel.updateChatLog("对方：接受再来一局");
        JOptionPane.showMessageDialog(null, "对方接受再来一局！", "游戏提示", JOptionPane.INFORMATION_MESSAGE);
        playAnotherRound();
	}
	
	public static void receiveRefuseRestart() { // 对方：拒绝再来一局
        System.out.println("对方：拒绝再来一局");
        ChatPanel.updateChatLog("对方：拒绝再来一局");
        JOptionPane.showMessageDialog(null, "对方拒绝再来一局！", "游戏提示", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void sendMessage() { // 我方：消息
		String message = ChatPanel.getChatTextArea().getText();
		System.out.println("我方：" + message);
        ChatPanel.updateChatLog("我方：" + message);
		Online.sendMessage(message);
	}
	
	public static void sendChess(int row, int col){ // 我方：下棋
		System.out.println("我方：" + "下棋：" + row + "," + col);
        ChatPanel.updateChatLog("我方：" + "下棋：" + row + "," + col);
		Online.sendChess("下棋：" + row + "," + col);
		int chessColor;
		if(currentAttribute == "SERVER")
			chessColor = Config.SERVER_CHESS_COLOR;
		else
			chessColor = Config.CLIENT_CHESS_COLOR;
        Model.setPieceAt(row, col, chessColor);
        GridPanel.repaint();
	}
	
	public static void sendRegret() { // 我方：请求悔棋
        System.out.println("我方：请求悔棋");
        ChatPanel.updateChatLog("我方：请求悔棋");
        Online.sendRegret();
	}
	
	public static void sendAcceptRegret() { // 我方：接受悔棋
        System.out.println("我方：接受悔棋");
        ChatPanel.updateChatLog("我方：接受悔棋");
        Online.sendAcceptRegret();
    	regret();
	}
	
	public static void sendRefuseRegret() { // 我方：拒绝悔棋
        System.out.println("我方：拒绝悔棋");
        ChatPanel.updateChatLog("我方：拒绝悔棋");
        Online.sendRefuseRegret();
	}
	
	public static void sendRestart() { // 我方：请求再来一局
        System.out.println("我方：请求再来一局");
        ChatPanel.updateChatLog("我方：请求再来一局");
        Online.sendRestart();
	}
	
	public static void sendAcceptRestart() { // 我方：接受再来一局
        System.out.println("我方：接受再来一局");
        ChatPanel.updateChatLog("我方：接受再来一局");
        Online.sendAcceptRestart();
        playAnotherRound();
	}
	
	public static void sendRefuseRestart() { // 我方：拒绝再来一局
        System.out.println("我方：拒绝再来一局");
        ChatPanel.updateChatLog("我方：拒绝再来一局");
        Online.sendRefuseRestart();
	}

	public static void sendWinning() { // 我方：获得胜利
		System.out.println("您已形成五子连珠，恭喜您游戏胜利！");
        String GameOverMessage = "您已形成五子连珠，恭喜您游戏胜利！";
        ChatPanel.updateChatLog("您已形成五子连珠，恭喜您游戏胜利！");
        JOptionPane.showMessageDialog(null, GameOverMessage, "游戏提示", JOptionPane.INFORMATION_MESSAGE);
        Online.sendWinning();
	}

	public static void receiveWinning() { // 对方：获得胜利
		System.out.println("对方已形成五子连珠，很遗憾游戏失败！");
        String GameOverMessage = "对方已形成五子连珠，很遗憾游戏失败！";
        ChatPanel.updateChatLog("对方已形成五子连珠，很遗憾游戏失败！");
        JOptionPane.showMessageDialog(null, GameOverMessage, "游戏提示", JOptionPane.INFORMATION_MESSAGE);
	}
}