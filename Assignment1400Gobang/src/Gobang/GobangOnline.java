package Gobang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class GobangOnline {
	
	private BufferedReader inputStream;
	private PrintStream outputStream;
	
	public void listening(){ // 监听函数
		try {
            // 创建服务器套接字，监听在指定端口
            @SuppressWarnings("resource")
            ServerSocket serverSocket = new ServerSocket(GobangControl.serverPortNumber);
            System.out.println("服务器已启动，等待客户端连接...");
            GobangControl.ChatPanel.updateChatLog("服务器已启动，等待客户端连接...");
            // 等待客户端连接
            Socket clientSocket = serverSocket.accept();
            System.out.println("客户端已连接：" + clientSocket.getInetAddress());
            GobangControl.ChatPanel.updateChatLog("客户端已连接：" + clientSocket.getInetAddress());
            // 获取输入输出流
			inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			outputStream = new PrintStream(clientSocket.getOutputStream(), true);
			// 开始读取对方输入
			StartReadThread();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendConnection(){ // 连接函数
		try {
			@SuppressWarnings("resource")
			Socket socket = new Socket(GobangControl.serverIpAddress, GobangControl.serverPortNumber);
        	System.out.println("向服务器发送连接请求...");
            System.out.println("成功连接到服务器：" + socket.getInetAddress());
            GobangControl.ChatPanel.updateChatLog("向服务器发送连接请求...");
            GobangControl.ChatPanel.updateChatLog("成功连接到服务器：\n    " + socket.getInetAddress());
            // 获取输入输出流
			inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outputStream = new PrintStream(socket.getOutputStream(), true);
			// 开始读取对方输入
			StartReadThread();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 开始监听
	public void startListening(){
		new Thread(){
			public void run(){
				listening();
			}
		}.start();
	}
	
	/**
	 * 根据对方消息做出相应反应，然后找Control解决
	 */
	
	private void StartReadThread() {
	    new Thread() {
	        public void run() {
	            while (true) {
	                try {
	                    String line = inputStream.readLine();
	                    if (line.startsWith("下棋：")) {
	                	    String[] param = (line.substring("下棋：".length())).split(",");
	                	    int row = Integer.parseInt(param[0]);
	                	    int col = Integer.parseInt(param[1]);
	                	    GobangControl.receiveChess(row, col);
	                    } else if (line.equals("请求悔棋")) {
	                        GobangControl.receiveRegret();
	                    } else if (line.equals("接受悔棋")) {
	                        GobangControl.receiveAcceptRegret();
	                    } else if (line.equals("拒绝悔棋")) {
	                        GobangControl.receiveRefuseRegret();
	                    } else if (line.equals("请求再来一局")) {
	                        GobangControl.receiveRestart();
	                    } else if (line.equals("接受再来一局")) {
	                        GobangControl.receiveAcceptRestart();
	                    } else if (line.equals("拒绝再来一局")) {
	                        GobangControl.receiveRefuseRestart();
	                    } else if (line.equals("我获胜了")) {
	                        GobangControl.receiveWinning();
	                    } else {
	                    	GobangControl.receiveMessage(line);
	                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }.start();
	}
	
	// 发送消息
	public void sendMessage(String message) {
	    if (outputStream != null) {
	        outputStream.println(message);
	    }
	}
	
	// 发送棋子
	public void sendChess(String chess) {
	    if (outputStream != null) {
	        outputStream.println(chess);
	    }
	}
	
	// 我方请求悔棋
	public void sendRegret() {
	    if (outputStream != null) {
	        outputStream.println("请求悔棋");
	    }
	}

	// 我方接受悔棋
	public void sendAcceptRegret() {
	    if (outputStream != null) {
	        outputStream.println("接受悔棋");
	    }
	}

	// 我方拒绝悔棋
	public void sendRefuseRegret() {
	    if (outputStream != null) {
	        outputStream.println("拒绝悔棋");
	    }
	}

	// 我方请求再来一局
	public void sendRestart() {
	    if (outputStream != null) {
	        outputStream.println("请求再来一局");
	    }
	}

	// 我方接受再来一局
	public void sendAcceptRestart() {
	    if (outputStream != null) {
	        outputStream.println("接受再来一局");
	    }
	}

	// 我方拒绝再来一局
	public void sendRefuseRestart() {
	    if (outputStream != null) {
	        outputStream.println("拒绝再来一局");
	    }
	}

	// 我方获胜
	public void sendWinning() {
	    if (outputStream != null) {
	        outputStream.println("我获胜了");
	    }
	}
}