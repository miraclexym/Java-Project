package javaPainter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

enum Tool {Brush, Eraser, Line, Rectangle, Oval, Circle, Text, Select, Delete, Clear}
enum ShapeType {Brush, Eraser, Line, Rectangle, Oval, Circle, Text}

public class JavaPainter extends JFrame implements MouseListener, MouseMotionListener {
	
	// 定义一些常量与变量
	private static final long serialVersionUID = 1L; // 序列化版本号
    private static final int DEFAULT_WIDTH = 2000; // 界面宽度
    private static final int DEFAULT_HEIGHT = 1000; // 界面高度
    public static LinkedList<DrawingShape> shapeList; // 绘制图形清单
    private int stroke = 1; // 画笔粗细，用于绘图
    private int  fontSize; // 文本尺寸，用于绘图
    private String fontName; // 文本字体，用于绘图
    private boolean italic; // 是否斜体，用于绘图
    private boolean bold; // 是否粗体，用于绘图
    private boolean isfilled; // 是否填充，用于绘图
    private int ShapeNumber; // 记录图形数量，用于绘图
    private DrawingShape theLastShape; // 访问最后一个图形，用于拖动操作
    private DrawingShape theSelectShape; // 访问选中的图形，用于修改指定图形
    private DrawingShape theadditionalShape; // 附加图形
	private Point currentStartPoint = new Point(0,0); // 当前起始点，用于绘图
    private Point currentEndPoint = new Point(0,0); // 当前终止点，用于绘图
    private Color brushColor = Color.BLACK; // 画笔颜色，用于绘图
    private Color backgrandColor = Color.WHITE; // 画笔颜色，用于绘图
    Font myKaiTi30Font = new Font("楷体", Font.PLAIN, 30); // 创建字体，用于绘图
	
	// 调色板（上侧位置）
	private JPanel palettePanel;
	
	private JLabel strokeSpinnerLabel; // 画笔粗细下拉列表标签
    private JLabel backgrandColorLabel; // 背景颜色下拉列表标签
    private JLabel brushColorLabel; // 画笔颜色下拉列表标签
    private JLabel textFieldLabel; // 待插入文本域提示标签
	private JLabel fontNameLabel; // 字体名称下拉列表标签
	private JLabel fontSizeLabel; // 字体字号下拉列表标签
    
    private JSpinner strokeSpinner; // 画笔粗细下拉列表
    private JComboBox<String> backgrandColorComboBox; // 背景颜色下拉列表
    private JComboBox<String> brushColorComboBox; // 画笔颜色下拉列表
    private JTextField textField; // 待插入文本域
    private JComboBox<String> fontNameComboBox; // 字体名称下拉列表
    private JSpinner fontSizeSpinner; // 字体字号下拉列表
    private JCheckBox italiccheckBox; // 斜体复选框
    private JCheckBox boldcheckBox; // 粗体复选框
    private JCheckBox fillcheckBox; // 填充复选框
    
    private SpinnerModel strokeSpinnerModel;// 创建JSpinner
    private SpinnerModel fontSizeSpinnerModel;// 创建JSpinner
    
    // 状态栏（下侧位置）
    private JLabel statusBar; // 界面下方状态栏
    private String toolText = "当前所选工具为：画笔，", motionText = "", locationText = ""; // 提示语标签内容文本
    
    // 工具栏（左侧位置）
    private JToolBar toolBar; // 工具栏
    private ButtonGroup buttonGroup; // 按钮组
    private Tool currentTool = Tool.Brush; // 当前工具，默认为画笔
    
    // 绘图面板（中心位置）
    private DrawingPanel drawingPanel; // 绘图面板
    
    // 构造函数
    public JavaPainter() {
    	
    	// 基本配置
        setTitle("Java Painter"); // 设置窗口标题
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT); // 设置窗口尺寸
        // 设置窗口为最大化
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认关闭操作
        setLocationRelativeTo(null); // 设置窗口在屏幕中心
        
        // 调色板（上侧位置）
        palettePanel = new JPanel(new FlowLayout());
        palettePanel.setBackground(new Color(195, 195, 195));
        
    	fontNameLabel = new JLabel(); // 初始化
    	fontSizeLabel = new JLabel(); // 初始化
        strokeSpinnerLabel = new JLabel(); // 初始化
        backgrandColorLabel = new JLabel(); // 初始化
        brushColorLabel = new JLabel(); // 初始化
        textFieldLabel = new JLabel(); // 初始化
        
    	fontNameLabel.setText("字体"); // 设置文本
    	fontSizeLabel.setText("字号"); // 设置文本
        strokeSpinnerLabel.setText("画笔粗细"); // 设置文本
        backgrandColorLabel.setText("背景颜色"); // 设置文本
        brushColorLabel.setText("画笔颜色"); // 设置文本
        textFieldLabel.setText("插入文本"); // 设置文本
        
    	fontNameLabel.setFont(myKaiTi30Font); // 设置字体
    	fontSizeLabel.setFont(myKaiTi30Font); // 设置字体
        strokeSpinnerLabel.setFont(myKaiTi30Font); // 设置字体
        backgrandColorLabel.setFont(myKaiTi30Font); // 设置字体
        brushColorLabel.setFont(myKaiTi30Font); // 设置字体
        textFieldLabel.setFont(myKaiTi30Font); // 设置字体
        
        strokeSpinnerModel = new SpinnerNumberModel(1, 1, 20, 1); // 初始值，最小值，最大值，步进
        strokeSpinner = new JSpinner(strokeSpinnerModel);
        backgrandColorComboBox = new JComboBox<>(new String[]{"白色", "黑色", "红色", "橙色", "黄色", "绿色", "蓝色", "青色", "紫色"}); // 初始化
        brushColorComboBox = new JComboBox<>(new String[]{"黑色", "白色", "红色", "橙色", "黄色", "绿色", "蓝色", "青色", "紫色"}); // 初始化
        textField = new JTextField(); // 初始化
        String[] fontNames = {
                "仿宋", "华文中宋", "华文仿宋", "华文宋体", "华文彩云", "华文新魏",
                "华文楷体", "华文琥珀", "华文细黑", "华文行楷", "华文隶书", "宋体", "幼圆",
                "微软雅黑", "微软雅黑 Light", "新宋体", "方正姚体", "方正粗黑宋简体",
                "方正舒体", "楷体", "爱奇艺黑体", "爱奇艺黑体 Black", "爱奇艺黑体 Medium",
                "等线", "等线 Light", "隶书", "黑体"
            };
        fontNameComboBox = new JComboBox<>(fontNames); // 初始化
        fontSizeSpinnerModel = new SpinnerNumberModel(10, 10, 70, 5); // 初始值，最小值，最大值，步进
        fontSizeSpinner = new JSpinner(fontSizeSpinnerModel); // 初始化
        
        strokeSpinner.setFont(myKaiTi30Font); // 设置字体以及文本
        backgrandColorComboBox.setFont(myKaiTi30Font); // 设置字体以及文本
        brushColorComboBox.setFont(myKaiTi30Font); // 设置字体以及文本
        
        textField.setText("请在工具栏文本框中输入文本"); // 设置文本
        textField.setFont(myKaiTi30Font); // 设置字体
        Dimension textFieldSize = new Dimension(400, 45); // 设置一个尺寸
        textField.setPreferredSize(textFieldSize); // 设置文本域尺寸
        
        fontNameComboBox.setFont(myKaiTi30Font); // 设置字体以及文本
        fontSizeSpinner.setFont(myKaiTi30Font); // 设置字体以及文本
        
        italiccheckBox = new JCheckBox("斜体");
        italiccheckBox.setFont(myKaiTi30Font); // 设置字体以及文本
        italiccheckBox.setBackground(new Color(195, 195, 195));
        
        boldcheckBox = new JCheckBox("粗体");
        boldcheckBox.setFont(myKaiTi30Font); // 设置字体以及文本
        boldcheckBox.setBackground(new Color(195, 195, 195));
        
        fillcheckBox = new JCheckBox("填充");
        fillcheckBox.setFont(myKaiTi30Font); // 设置字体以及文本
        fillcheckBox.setBackground(new Color(195, 195, 195));
        
        palettePanel.add(strokeSpinnerLabel); // 将画笔粗细下拉列表标签加入调色板
        palettePanel.add(strokeSpinner); // 将画笔粗细下拉列表加入调色板
        palettePanel.add(backgrandColorLabel); // 将背景颜色下拉列表标签加入调色板
        palettePanel.add(backgrandColorComboBox); // 将背景颜色下拉列表加入调色板
        palettePanel.add(brushColorLabel); // 将画笔颜色下拉列表标签加入调色板
        palettePanel.add(brushColorComboBox); // 将画笔颜色下拉列表加入调色板
        palettePanel.add(textFieldLabel); // 将待插入文本域提示标签加入调色板
        palettePanel.add(textField); // 将待插入文本域加入调色板
        palettePanel.add(fontNameLabel); // 将字体名称下拉列表标签加入调色板
        palettePanel.add(fontNameComboBox); // 将字体名称下拉列表加入调色板
        palettePanel.add(fontSizeLabel); // 将字号下拉列表标签加入调色板
        palettePanel.add(fontSizeSpinner); // 将字号下拉列表加入调色板
        palettePanel.add(italiccheckBox); // 将斜体复选框加入调色板
        palettePanel.add(boldcheckBox); // 将斜体复选框加入调色板
        palettePanel.add(fillcheckBox); // 将填充复选框加入调色板
        
        fillcheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                	isfilled = true;
                } else {
                	isfilled = false;
                }
            }
        });
        
        boldcheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                	bold = true;
                } else {
                	bold = false;
                }
            }
        });
        
        italiccheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                	italic = true;
                } else {
                	italic = false;
                }
            }
        });
        
        // 添加ChangeListener监听器
        strokeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newValue = (int) strokeSpinner.getValue();
                stroke = newValue;
            }
        });
        
        // 添加ChangeListener监听器
        fontSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newValue = (int) fontSizeSpinner.getValue();
                fontSize = newValue;
            }
        });
        
        // 字体名称下拉列表监听器
        fontNameComboBox.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent event) {
				@SuppressWarnings("unchecked")
				JComboBox<String> source = (JComboBox<String>) event.getSource();
				String selectedOption = (String) source.getSelectedItem();
				fontName = selectedOption;
			}
        });
        
        // 背景颜色下拉列表监听器
        backgrandColorComboBox.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent event) {
				@SuppressWarnings("unchecked")
				JComboBox<String> source = (JComboBox<String>) event.getSource();
				String selectedOption = (String) source.getSelectedItem();
				Color goalColor;
				switch (selectedOption) {
				    case "黑色":
				        goalColor = Color.BLACK;
				        break;
				    case "白色":
				    	goalColor = Color.WHITE;
				        break;
				    case "红色":
				    	goalColor = Color.RED;
				        break;
				    case "橙色":
				    	goalColor = Color.ORANGE;
				        break;
				    case "黄色":
				    	goalColor = Color.YELLOW;
				        break;
				    case "绿色":
				    	goalColor = Color.GREEN;
				        break;
				    case "蓝色":
				    	goalColor = Color.BLUE;
				        break;
				    case "青色":
				    	goalColor = Color.CYAN;
				        break;
				    case "紫色":
				    	goalColor = Color.MAGENTA;
				        break;
				    default:
				    	goalColor = Color.CYAN;
				        break;
				}
				backgrandColor = goalColor;
				drawingPanel.setBackground(backgrandColor); // 设置绘图面板颜色
			}
        });
        
        // 画笔颜色下拉列表监听器
        brushColorComboBox.addItemListener(new ItemListener(){
    		@Override
    		public void itemStateChanged(ItemEvent event) {
    			@SuppressWarnings("unchecked")
				JComboBox<String> source = (JComboBox<String>) event.getSource();
    			String selectedOption = (String) source.getSelectedItem();
    			Color goalColor;
    			switch (selectedOption) {
    			    case "黑色":
    			        goalColor = Color.BLACK;
    			        break;
    			    case "白色":
    			    	goalColor = Color.WHITE;
    			        break;
    			    case "红色":
    			    	goalColor = Color.RED;
    			        break;
    			    case "橙色":
    			    	goalColor = Color.ORANGE;
    			        break;
    			    case "黄色":
    			    	goalColor = Color.YELLOW;
    			        break;
    			    case "绿色":
    			    	goalColor = Color.GREEN;
    			        break;
    			    case "蓝色":
    			    	goalColor = Color.BLUE;
    			        break;
    			    case "青色":
    			    	goalColor = Color.CYAN;
    			        break;
    			    case "紫色":
    			    	goalColor = Color.MAGENTA;
    			        break;
    			    default:
    			    	goalColor = Color.CYAN;
    			        break;
    			}
    			brushColor = goalColor;
    		}
        });
        
        // 状态栏（下侧位置）
        statusBar = new JLabel();
        statusBar.setFont(myKaiTi30Font); // 设置字体
        statusBar.setOpaque(true); // 设置不透明
        statusBar.setBackground(new Color(195, 195, 195)); // 设置状态栏背景颜色
        
        // 工具栏（左侧位置）
        toolBar = new JToolBar(); // 初始化
        toolBar.setBackground(new Color(195, 195, 195)); // 设置状态栏背景颜色
        buttonGroup = new ButtonGroup(); // 新建按钮组
        String[] buttonCommands = {"Brush", "Eraser", "Line", "Rectangle", "Oval", "Circle", "Text", "Select", "Delete", "Clear"};
        for (String label : buttonCommands) {
            JButton button = new JButton();
            try{
                // 加载图像
                String path = "/image/" + label + ".png";
                Image image = new ImageIcon(getClass().getResource(path)).getImage();
                // 加载图标
                ImageIcon icon = new ImageIcon(image);
                // 设置按钮图标
                button.setIcon(icon);
            } catch (Exception e) {
            	JOptionPane.showMessageDialog(null, "自定义光标异常");
            }
/*            // 设置按钮文本
            button.setText(label);
            button.setFont(myKaiTi30Font);*/
        	// 设置按钮上的命令
            button.setActionCommand(label);
            // 设置被选中的按钮颜色为浅蓝色
            if(label.equals("Brush"))
                button.setBackground(new Color(0, 255, 255));
            // 设置按钮最大尺寸和首选尺寸，以确保它们相同
            Dimension buttonSize = new Dimension(50, 50);
            button.setMaximumSize(buttonSize);
            button.setPreferredSize(buttonSize);
            // 增加监听器，同时增加按钮
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    // 根据命令判断是哪一个按钮并调色
                	String command0 = event.getActionCommand();
                    switch (command0) {
                    case "Brush":
                    case "Eraser":
                    case "Text":
                    case "Rectangle":
                    case "Circle":
                    case "Oval":
                    case "Line":
                    case "Select":
                        Component[] components = toolBar.getComponents();
                        for (Component component : components) {
                        	JButton button = (JButton) component;
                            button.setBackground(UIManager.getColor("Button.background"));
                    	}
                        // 设置被选中的按钮颜色为浅蓝色
                        JButton source = (JButton) event.getSource();
                        source.setBackground(new Color(0, 255, 255));
                    default:
                        break;
                    }
                    // 根据命令判断是哪一个按钮并做出相应举措
                    String command = event.getActionCommand();
                    switch (command) {
                        case "Brush":
                            currentTool = Tool.Brush;
                            drawingPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                            toolText = "当前所选工具为：画笔，";
                            statusBar.setText(toolText + motionText + locationText);
                            break;
                        case "Eraser":
                            currentTool = Tool.Eraser;
                            drawingPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                            toolText = "当前所选工具为：橡皮擦，";
                            try {
                                // 定义鼠标进入画板时的样式
                                String url = "/image/cursor.png"; // 储存鼠标橡皮擦图片的位置
                                Toolkit tk = Toolkit.getDefaultToolkit();
                                Image image = new ImageIcon(getClass().getResource(url)).getImage();
                                Cursor cursor = tk.createCustomCursor(image, new Point(10, 10), "norm");
                                drawingPanel.setCursor(cursor); //设置鼠标橡皮擦样式
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "自定义光标异常");
                            }
                            statusBar.setText(toolText + motionText + locationText);
                            break;
                        case "Text":
                            currentTool = Tool.Text;
                            drawingPanel.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                            toolText = "当前所选工具为：文本框，";
                            statusBar.setText(toolText + motionText + locationText);
                            break;
                        case "Rectangle":
                            currentTool = Tool.Rectangle;
                            drawingPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                            toolText = "当前所选工具为：矩形，";
                            statusBar.setText(toolText + motionText + locationText);
                            break;
                        case "Circle":
                            currentTool = Tool.Circle;
                            drawingPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                            toolText = "当前所选工具为：圆形，";
                            statusBar.setText(toolText + motionText + locationText);
                            break;
                        case "Oval":
                            currentTool = Tool.Oval;
                            drawingPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                            toolText = "当前所选工具为：椭圆形，";
                            statusBar.setText(toolText + motionText + locationText);
                            break;
                        case "Line":
                            currentTool = Tool.Line;
                            drawingPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                            toolText = "当前所选工具为：线条，";
                            statusBar.setText(toolText + motionText + locationText);
                            break;
                        case "Select":
                            currentTool = Tool.Select;
                            drawingPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            toolText = "当前所选工具为：选择，";
                            statusBar.setText(toolText + motionText + locationText);
                            // 遍历访问所有形状并绘制
                            /*LinkedList<DrawingShape> shapes = getshapeList();
                            for (DrawingShape shape : shapes) {
                            	System.out.println("Type - shape: " + shape.getType());
                                System.out.println("Start Point - X: " + shape.getStartX() + ", Y: " + shape.getStartY());
                                System.out.println("End Point - X: " + shape.getEndX() + ", Y: " + shape.getEndY());
                            }*/
                            break;
                        case "Delete":
                        	if(theSelectShape != null)
                        	{
                                removeShape(theSelectShape);
                                removeShape(theadditionalShape);
                				drawingPanel.repaint();
                            }
                        	else
                        		statusBar.setText("请选择一个图形");
                            break;
                        case "Clear":
                        	claerShape();
            				drawingPanel.repaint();
                            break;
                    }
                }
            });
            // 将按钮添加到按钮组和工具栏
            buttonGroup.add(button);
            toolBar.add(button);
        }

        // 绘图面板（中心位置）
        drawingPanel = new DrawingPanel();
        drawingPanel.addMouseListener(this); // 添加鼠标监听器
        drawingPanel.addMouseMotionListener(this); // 添加鼠标监听器
        drawingPanel.setBackground(Color.WHITE); // 设置绘图面板颜色
        
        // 设置JFrame的布局
        setLayout(new BorderLayout());
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.Y_AXIS));
        add(palettePanel, BorderLayout.NORTH);
        add(statusBar, BorderLayout.SOUTH);
        add(toolBar, BorderLayout.WEST);
        add(drawingPanel, BorderLayout.CENTER);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int saved = 0;
				if (saved  == 0) {
                    int n = JOptionPane.showConfirmDialog(null, "您还没保存，确定要退出？", "提示", JOptionPane.OK_CANCEL_OPTION);
                    if (n == 0) {
                        System.exit(0);
                    }
                }
                if (saved == 1) {
                    System.exit(0);
                }
            }
        });
        
    	shapeList = new LinkedList<DrawingShape>();
        
    	
        // 菜单条
        JMenuBar jMenuBar = new JMenuBar();
        // 实例化菜单对象
        // 定义文件、设置、帮助菜单
        JMenu fileMenu = new JMenu("文件");
        fileMenu.setFont(myKaiTi30Font);
        JMenu setMenu = new JMenu("设置");
        setMenu.setFont(myKaiTi30Font);
        JMenu helpMenu = new JMenu("帮助");
        helpMenu.setFont(myKaiTi30Font);
        // 实例化菜单项,并通过ImageIcon对象添加图片 定义文件菜单的菜单项
        JMenuItem fileItemNew = new JMenuItem("新建", new ImageIcon(getClass().getResource("/image/new.png")));
        fileItemNew.setFont(myKaiTi30Font);
        JMenuItem fileItemOpen = new JMenuItem("打开", new ImageIcon(getClass().getResource("/image/open.png")));
        fileItemOpen.setFont(myKaiTi30Font);
        JMenuItem fileItemSave = new JMenuItem("保存", new ImageIcon(getClass().getResource("/image/save.png")));
        fileItemSave.setFont(myKaiTi30Font);
        JMenuItem fileItemExit = new JMenuItem("退出", new ImageIcon(getClass().getResource("/image/exit.png")));
        fileItemExit.setFont(myKaiTi30Font);
        // 定设置菜单的菜单项
        JMenuItem setItemColor = new JMenuItem("颜色", new ImageIcon(getClass().getResource("/image/color.png")));
        setItemColor.setFont(myKaiTi30Font);
        JMenuItem setItemUndo = new JMenuItem("撤销", new ImageIcon(getClass().getResource("/image/undo.png")));
        setItemUndo.setFont(myKaiTi30Font);
        JMenuItem helpItemUse = new JMenuItem("使用手册");
        helpItemUse.setFont(myKaiTi30Font);
        JMenuItem helpItemInfo = new JMenuItem("关于画图");
        helpItemInfo.setFont(myKaiTi30Font);
        // 设置 UIManager，将字体应用到 JOptionPane 的消息文本
        UIManager.put("OptionPane.messageFont", myKaiTi30Font);
        UIManager.put("OptionPane.titleFont", myKaiTi30Font);
        UIManager.put("Button.font", myKaiTi30Font);
        helpItemInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                "" + "关于画图\n" 
                + "作者: 徐亚民\n" 
                + "班级: 计算机科学与技术2班\n"
                + "学号: 2212032\n" 
                + "邮箱: 2212032@mail.nankai.edu.cn\n"
                + "图标绘制工具: PPT\n",
                	"关于画图", JOptionPane.PLAIN_MESSAGE);
            }
        });
        helpItemUse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null, 
            	"" 
            	+ "######################\r\n" 
            	+ "##画图软件使用说明书##\r\n"
                + "######################\r\n" 
            	+ "本软件可以实现以下功能：\r\n" 
                + "（1）在画板上绘制直线、矩形、椭圆、圆等图形\r\n"
                + "（2）依据鼠标轨迹绘制曲线、橡皮擦\r\n"
                + "（3）设置画笔粗细和背景、画笔的颜色\r\n" 
                + "（4）设置文本的字体、字号、斜体、粗体\r\n" 
                + "（5）选择图形、删除图形、填充图形\r\n"
                + "（6）文件的新建、打开、保存、退出\r\n"
                + "（7）清空画板\r\n",
                	"使用说明", JOptionPane.PLAIN_MESSAGE);
            }
        });
        helpMenu.add(helpItemUse);
        helpMenu.add(helpItemInfo);
        // 设置快捷键
        fileItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        fileItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        fileItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        fileItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        setItemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        
        // 添加菜单项到菜单
        fileMenu.add(fileItemNew);
        fileMenu.add(fileItemOpen);
        fileMenu.add(fileItemSave);
        fileMenu.add(fileItemExit);
        setMenu.add(setItemColor);
        setMenu.add(setItemUndo);
        
        // 添加菜单到菜单条
        jMenuBar.add(fileMenu);
        jMenuBar.add(setMenu);
        jMenuBar.add(helpMenu);
        // 添加菜单条
        setJMenuBar(jMenuBar);
    	
    	
        setVisible(true); // 设置窗口显示可见
    }
    
	@Override  //  鼠标移动事件
	public void mouseMoved(MouseEvent e) {
        // 改变状态栏
		motionText = "在绘图面板中，鼠标被移动";
		locationText = "，移动时鼠标位置：（" + e.getX() + "，" + e.getY() +"）";
		statusBar.setText(toolText + motionText + locationText);
	}
	
	@Override  //  鼠标点击事件
	public void mouseClicked(MouseEvent e) {
		currentStartPoint.setLocation(e.getX(), e.getY()); // 记录鼠标开始位置
        // 改变状态栏
		motionText = "在绘图面板中，鼠标被单击";
		locationText = "，单击时鼠标位置：（" + e.getX() + "，" + e.getY() +"）";
		// 增添图形
		if(currentTool == Tool.Text) {
			addShape(new DrawingShape(ShapeType.Text, brushColor, 
					currentStartPoint, textField.getText(), fontSize, fontName, italic, bold));
			drawingPanel.repaint();
		}
		else if(currentTool == Tool.Select){
			DrawingShape[] shapeArray = new DrawingShape[ShapeNumber];
			int mycount = 0;
	        LinkedList<DrawingShape> shapes = JavaPainter.getshapeList();
	        for (DrawingShape shape : shapes) {
	        	shapeArray[mycount++] = shape;
	        }
	        mycount--;
	        for( ; mycount >= 0; mycount--){
	        	if(shapeArray[mycount].isInGraph(currentStartPoint)){
	        		theSelectShape = shapeArray[mycount];
	        		if(theadditionalShape != null)
		        		removeShape(theadditionalShape);
	        		theadditionalShape = new DrawingShape(theSelectShape);
	        		addShape(theadditionalShape);
	    			drawingPanel.repaint();
		            // 改变状态栏
		    		motionText = "成功选中图形";
		    		locationText = "，选中时鼠标位置：（" + e.getX() + "，" + e.getY() +"）";
		    		break;
	    		}
	        }
			
		}
		statusBar.setText(toolText + motionText + locationText);
	}
	
	@Override  //  鼠标进入事件
	public void mouseEntered(MouseEvent e) {
        // 改变状态栏
		motionText = "鼠标进入绘图面板";
		locationText = "，进入时鼠标位置：（" + e.getX() + "，" + e.getY() +"）";
		statusBar.setText(toolText + motionText + locationText);
	}

	@Override  //  鼠标离开事件
	public void mouseExited(MouseEvent e) {
        // 改变状态栏
		motionText = "鼠标离开绘图面板";
		locationText = "，离开时鼠标位置：（" + e.getX() + "，" + e.getY() +"）";
		statusBar.setText(toolText + motionText + locationText);
	}

	@Override  //  鼠标按下事件
	public void mousePressed(MouseEvent e) {
		currentStartPoint.setLocation(e.getX(), e.getY()); // 记录鼠标开始位置
        // 改变状态栏
		motionText = "在绘图面板中，鼠标被按下";
		locationText = "，按下时鼠标位置：（" + e.getX() + "，" + e.getY() +"）";
		statusBar.setText(toolText + motionText + locationText);
		// 增添图形  // 鼠标被按下时先创建一个图形，随着拖动和释放，修改图形的第二个点即可。
		switch (currentTool) {
	    case Line:
	        addShape(new DrawingShape(ShapeType.Line, brushColor, currentStartPoint, currentStartPoint, isfilled, stroke));
	        drawingPanel.repaint();
	        break;
	    case Rectangle:
	        addShape(new DrawingShape(ShapeType.Rectangle, brushColor, currentStartPoint, currentStartPoint, isfilled, stroke));
	        drawingPanel.repaint();
	        break;
	    case Oval:
	        addShape(new DrawingShape(ShapeType.Oval, brushColor, currentStartPoint, currentStartPoint, isfilled, stroke));
	        drawingPanel.repaint();
	        break;
	    case Circle:
	        addShape(new DrawingShape(ShapeType.Circle, brushColor, currentStartPoint, currentStartPoint, isfilled, stroke));
	        drawingPanel.repaint();
	        break;
		case Brush:
	        addShape(new DrawingShape(ShapeType.Brush, brushColor, currentStartPoint, currentStartPoint, false, stroke));
			drawingPanel.repaint();
			break;
		case Eraser:
	        addShape(new DrawingShape(ShapeType.Eraser, backgrandColor, currentStartPoint, currentStartPoint, false, stroke));
			drawingPanel.repaint();
			break;
	    default:
	        break;
	}
	}
	
	@Override  //  鼠标拖动事件
	public void mouseDragged(MouseEvent e) {
		currentEndPoint.setLocation(e.getX(), e.getY());
        // 改变状态栏
		motionText = "在绘图面板中，鼠标被拖动";
		locationText = "，拖动时鼠标位置：（" + e.getX() + "，" + e.getY() +"）";
		statusBar.setText(toolText + motionText + locationText);
		// 增添图形
		switch(currentTool) {
		case Line:
			theLastShape.setEndPoint(currentEndPoint);
			drawingPanel.repaint();
			break;
		case Rectangle:
		case Circle:
		case Oval:
			theLastShape.setStartPoint(new Point(Math.min(currentStartPoint.x, currentEndPoint.x), 
					Math.min(currentStartPoint.y, currentEndPoint.y)));
			theLastShape.setEndPoint(new Point(Math.max(currentStartPoint.x, currentEndPoint.x), 
					Math.max(currentStartPoint.y, currentEndPoint.y)));
			drawingPanel.repaint();
			break;
		case Brush:
			theLastShape.setEndPoint(currentEndPoint);
	        addShape(new DrawingShape(ShapeType.Brush, brushColor, currentEndPoint, currentEndPoint, false, stroke));
			drawingPanel.repaint();
			break;
		case Eraser:
			theLastShape.setEndPoint(currentEndPoint);
	        addShape(new DrawingShape(ShapeType.Eraser, backgrandColor, currentEndPoint, currentEndPoint, false, stroke));
			drawingPanel.repaint();
			break;
		default:
			break;
	}
	}
	
	@Override  //  鼠标释放事件
	public void mouseReleased(MouseEvent e) {
		currentEndPoint.setLocation(e.getX(), e.getY());
        // 改变状态栏
		motionText = "在绘图面板中，鼠标被释放";
		locationText = "，释放时鼠标位置：（" + e.getX() + "，" + e.getY() +"）";
		statusBar.setText(toolText + motionText + locationText);
		// 增添图形
		switch(currentTool) {
		case Line:
			theLastShape.setEndPoint(currentEndPoint);
			drawingPanel.repaint();
			break;
		case Rectangle:
		case Circle:
		case Oval:
			theLastShape.setStartPoint(new Point(Math.min(currentStartPoint.x, currentEndPoint.x), 
					Math.min(currentStartPoint.y, currentEndPoint.y)));
			theLastShape.setEndPoint(new Point(Math.max(currentStartPoint.x, currentEndPoint.x), 
					Math.max(currentStartPoint.y, currentEndPoint.y)));
			drawingPanel.repaint();
			break;
		case Brush:
			theLastShape.setEndPoint(currentEndPoint);
	        addShape(new DrawingShape(ShapeType.Brush, brushColor, currentEndPoint, currentEndPoint, false, stroke));
			drawingPanel.repaint();
			break;
		case Eraser:
			theLastShape.setEndPoint(currentEndPoint);
	        addShape(new DrawingShape(ShapeType.Eraser, backgrandColor, currentEndPoint, currentEndPoint, false, stroke));
			drawingPanel.repaint();
			break;
		default:
			break;
	}
	}

	// 获取形状清单
    public static LinkedList<DrawingShape> getshapeList() {
        return shapeList;
    }
    
    // 添加绘画图形
    public void addShape(DrawingShape shape) {
        shapeList.add(shape);
        theLastShape = shape;
        ShapeNumber++;
    }

    // 删除绘画图形
    public void removeShape(DrawingShape shape) {
        shapeList.remove(shape);
    }
    
    // 清空绘画图形
    public void claerShape() {
        shapeList.clear();
    }
    
    // 主函数
    public static void main(String[] args) {
        new JavaPainter();
    }
}
