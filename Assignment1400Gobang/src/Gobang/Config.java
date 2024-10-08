package Gobang;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Config { // 游戏配置
	/**
	 * 界面尺寸
	 */
	public static final int GAME_HOME_WIDTH = 900; // 游戏封面的宽度
	public static final int GAME_HOME_HEIGHT = 450; // 游戏封面的高度
    public static final int GRID_OFFSET_X = 50; // 左侧空白的宽度
    public static final int GRID_OFFSET_Y = 40; // 上侧空白的宽度
    public static final int GRID_SIZE = 15; // 棋盘网格的尺寸
    public static final int CELL_SIZE = 50; // 单个方格的大小
    public static final int PIECE_SIZE = CELL_SIZE; // 单个棋子的大小
    public static final int GRID_WIDTH = (GRID_SIZE - 1) * CELL_SIZE; // 棋盘网格的宽度
    public static final int GRID_END_X = GRID_OFFSET_X + GRID_WIDTH;  // 网格终点的坐标
    public static final int GRID_END_Y = GRID_OFFSET_Y + GRID_WIDTH;  // 网格终点的坐标
	public static final int DIALOG_WIDTH = 1700; // 设置对话框宽度
	public static final int DIALOG_HEIGHT = 800; // 设置对话框高度
    /**
     * 文本尺寸
     */
    public static final int BRIEF_AREA_WIDTH = 1000; // 介绍文本域宽度
    public static final int BRIEF_AREA_HEIGHT = 140; // 介绍文本域高度
    public static final int TERM_AREA_WIDTH = 530; // 术语文本域宽度
    public static final int TERM_AREA_HEIGHT = 600; // 术语文本域高度
    public static final int ONLINE_AREA_WIDTH = 480; // 联机文本域宽度
    public static final int ONLINE_AREA_HEIGHT = 40; // 联机文本域高度
    public static final int CHAT_AREA_WIDTH = 600; // 聊天文本域宽度
    public static final int CHAT_AREA_HEIGHT = 200; // 聊天文本域高度
	/**
	 * 设置颜色
	 */
    public static final Color BACKGROUND_COLOR = new Color(240, 240, 255); // 淡蓝色背景
    public static final Color BRIEF_TEXT_AREA_COLOR = new Color(255, 255, 153); // 淡黄色介绍文本域
    public static final Color TERM_TEXT_AREA_COLOR = new Color(255, 182, 193);  // 淡粉色术语文本域
    public static final Color STATUS_LABEL_COLOR = new Color(240, 240, 255); // 淡蓝色游戏状态标签
    public static final Color SETTINGS_PANEL_COLOR = new Color(173, 216, 230); // 淡蓝色设置面板
    public static final Color ONLINE_TEXT_FIELD_COLOR = new Color(144, 238, 144); // 淡绿色联机文本框
    public static final Color CHAT_TEXT_AREA_COLOR = new Color(192, 192, 192); // 淡灰色聊天文本域
    public static final Color BUTTON_PANEL_COLOR = new Color(173, 216, 230); // 淡蓝色按钮面板
	/**
	 * 棋子颜色
	 */
    public static final int BLACK = -1; // 棋子颜色的黑色
    public static final int WHITE = 1; // 棋子颜色的白色
    public static final int EMPTY = 0; // 棋子颜色的空子
    public static final int SERVER_CHESS_COLOR = -1; // 服务器棋子颜色
    public static final int CLIENT_CHESS_COLOR = 1; // 客户端棋子颜色
    /**
     * 音乐状态
     */
    public static final int MUSIC_PLAYING = 1; // 音乐处于播放中
    public static final int MUSIC_SUSPEND = -1; // 音乐处于暂停中
    /**
     * 边框样式
     */
    public static final Border EMPTY_BORDER = new EmptyBorder(10, 10, 10, 10); // 设置边框的样式
    /**
     * 游戏字体
     */
    public static Font GAME_FONT = new Font("楷体", Font.PLAIN, 30); // 设置游戏的字体
}