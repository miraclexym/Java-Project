package javaPainter;
import java.awt.*;

public class DrawingShape {
	private ShapeType type; // 类型
    private Color color; // 颜色
	private Point startPoint; // 起始点
	
    private Point endPoint; // 结束点
    private boolean isFilled; // 是否填充
    private int stroke; // 画笔粗细
    
    private String text; // 文本内容
    private int  fontSize; // 文本尺寸
    private String fontName; // 文本字体
    private boolean italic; // 是否斜体
    private boolean bold; // 是否粗体
    
	public DrawingShape(ShapeType type, Color color, Point startPoint,
			Point endPoint, boolean isFilled, int stroke) {
		super();
		this.type = type;
		this.color = color;
		this.startPoint = new Point(startPoint.x, startPoint.y);
		this.endPoint = new Point(endPoint.x, endPoint.y);
		this.isFilled = isFilled;
		this.stroke = stroke;
	}
	
	public DrawingShape(ShapeType type, Color color, Point startPoint,
			String text, int fontSize, String fontName, boolean italic, boolean bold) {
		super();
		this.type = type;
		this.color = color;
		this.startPoint = new Point(startPoint.x, startPoint.y);
		this.text = text;
		this.fontSize = fontSize;
		this.fontName = fontName;
		this.italic = italic;
		this.bold = bold;
	}
	
	public DrawingShape(DrawingShape theSelectShape) {
		super();
		this.type = ShapeType.Rectangle;
		this.color = Color.RED;
		this.startPoint = new Point(theSelectShape.startPoint.x, theSelectShape.startPoint.y);
		this.endPoint = new Point(theSelectShape.endPoint.x, theSelectShape.endPoint.y);
		this.isFilled = false;
		this.stroke = theSelectShape.stroke;
	}
	
	public ShapeType getType() {
		return type;
	}

	public void setType(ShapeType type) {
		this.type = type;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint.x = startPoint.x;
		this.startPoint.y = startPoint.y;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint.x = endPoint.x;
		this.endPoint.y = endPoint.y;
		if(type == ShapeType.Circle){
			int absx = Math.abs(endPoint.x - startPoint.x);
			int absy = Math.abs(endPoint.y - startPoint.y);
			int width = Math.max(absx, absy);
			this.endPoint = new Point(this.startPoint.x + width, this.startPoint.y + width);
		}
	}

	public boolean isFilled() {
		return isFilled;
	}

	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	public int getStroke() {
		return stroke;
	}

	public void setStroke(int stroke) {
		this.stroke = stroke;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public boolean isItalic() {
		return italic;
	}

	public void setItalic(boolean italic) {
		this.italic = italic;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public int getStartX() {
		return startPoint.x;
	}

	public int getStartY() {
		return startPoint.y;
	}

	public int getEndX() {
		return endPoint.x;
	}

	public int getEndY() {
		return endPoint.y;
	}
	
	public int getWidth() {
		int absx = Math.abs(endPoint.x - startPoint.x);
		return absx;
	}

	public int getHeight() {
		int absy = Math.abs(endPoint.y - startPoint.y);
		return absy;
	}
	
	public boolean isInGraph(Point point){
		boolean isInGraph = false;
		switch(type){
	    case Rectangle:
	    	// 判断点是否在矩形内部
	    	return (point.x > getStartX() && point.x < getEndX() && point.y > getStartY() && point.y < getEndY());
	    case Oval:
	        // 计算椭圆的中心点坐标
	        double h = startPoint.x + getWidth() / 2.0;
	        double k = startPoint.y + getHeight() / 2.0;
	        // 计算长轴和短轴
	        double a = getWidth() / 2.0;
	        double b = getHeight() / 2.0;
	        // 判断点是否在椭圆内部
	        return ((Math.pow(point.x - h, 2) / Math.pow(a, 2)) + (Math.pow(point.y - k, 2) / Math.pow(b, 2))) <= 1;
	    case Circle:
	    	// 计算圆的中心点坐标
	    	double Circlea = (startPoint.x + endPoint.x) / 2.0;
	    	double Circleb = (startPoint.y + endPoint.y) / 2.0;
	    	// 判断点是否在圆内部
	    	return (Math.pow(point.x - Circlea, 2) + Math.pow(point.y - Circleb, 2) < Math.pow(getWidth(), 2));
	    case Text:
	    	// 判断点是否在矩形内部
	    	return (point.x > getStartX() && point.x < (getStartX() + 350) && point.y > (getStartY() - 10) && point.y < (getStartY() + 10));
		default:
			break;
		}
		return isInGraph;
	}
}