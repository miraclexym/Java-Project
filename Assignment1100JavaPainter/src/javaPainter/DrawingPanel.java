package javaPainter;

import javax.swing.*;

import java.awt.*;
import java.util.LinkedList;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L; // 序列化版本号
	
	public DrawingPanel(){
        // 设置光标类型，为十字形
        this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
	}

    @Override //重写paintComponent方法，使得画板每次刷新时可将之前的所有图形重新画出来。
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // 遍历访问所有形状并绘制
        LinkedList<DrawingShape> shapes = JavaPainter.getshapeList();
        for (DrawingShape shape : shapes) {
            drawShape(g, shape);
        }
    }
    
    // 绘制单个形状的方法
    private void drawShape(Graphics g, DrawingShape shape) {
    	// 根据形状的类型调用相应的绘制方法
    	switch (shape.getType()) {
    	    case Brush:
    	        drawLine(g, shape);
    	        break;
    	    case Eraser:
    	    	drawEraser(g, shape);
    	        break;
    	    case Line:
    	        drawLine(g, shape);
    	        break;
    	    case Rectangle:
    	        drawRectangle(g, shape);
    	        break;
    	    case Oval:
    	        drawOval(g, shape);
    	        break;
    	    case Circle:
    	        drawCircle(g, shape);
    	        break;
    	    case Text:
    	        drawText(g, shape);
    	        break;
    	    default:
    	        break;
    	}
    }
    
	// 绘制橡皮擦
    private void drawEraser(Graphics g, DrawingShape shape) {
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.setColor(shape.getColor());
    	g2d.setStroke(new BasicStroke(30, BasicStroke.CAP_SQUARE , BasicStroke.JOIN_BEVEL));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawLine(shape.getStartX(), shape.getStartY(), shape.getEndX(), shape.getEndY());
	}

	// 绘制线条
    private void drawLine(Graphics g, DrawingShape shape) {
    	Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(shape.getColor());
		g2d.setStroke(new BasicStroke(shape.getStroke()));
        g2d.drawLine(shape.getStartX(), shape.getStartY(), shape.getEndX(), shape.getEndY());
    }

    // 绘制矩形
    private void drawRectangle(Graphics g, DrawingShape shape) {
    	Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(shape.getColor());
		g2d.setStroke(new BasicStroke(shape.getStroke()));
        if (shape.isFilled()) {
        	g2d.fillRect(shape.getStartX(), shape.getStartY(), shape.getWidth(), shape.getHeight());
        } else {
        	g2d.drawRect(shape.getStartX(), shape.getStartY(), shape.getWidth(), shape.getHeight());
        }
    }
    
    // 绘制椭圆形
    private void drawOval(Graphics g, DrawingShape shape) {
    	Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(shape.getColor());
		g2d.setStroke(new BasicStroke(shape.getStroke()));
        if (shape.isFilled()) {
        	g2d.fillOval(shape.getStartX(), shape.getStartY(), shape.getWidth(), shape.getHeight());
        } else {
        	g2d.drawOval(shape.getStartX(), shape.getStartY(), shape.getWidth(), shape.getHeight());
        }
    }

    // 绘制圆形
    private void drawCircle(Graphics g, DrawingShape shape) {
    	Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(shape.getColor());
		g2d.setStroke(new BasicStroke(shape.getStroke()));
        if (shape.isFilled()) {
            g2d.fillOval(shape.getStartX(), shape.getStartY(), shape.getWidth(), shape.getHeight());
        } else {
        	g2d.drawOval(shape.getStartX(), shape.getStartY(), shape.getWidth(), shape.getHeight());
        }
    }

    // 绘制文本
    private void drawText(Graphics g, DrawingShape shape) {
    	Graphics2D g2d = (Graphics2D) g;
    	
    	// 设置颜色
		g2d.setPaint(shape.getColor());
		
		// 设置线条
		g2d.setStroke(new BasicStroke(1));
		
		// 设置字体
		Font font = new Font(shape.getFontName(), Font.PLAIN, shape.getFontSize() + 15);

		// 设置粗体
		if (shape.isBold()) {
		    font = font.deriveFont(Font.BOLD);
		}

		// 设置斜体
		if (shape.isItalic()) {
		    font = font.deriveFont(Font.ITALIC);
		}

		g2d.setFont(font);
        
        g2d.drawString(shape.getText(), shape.getStartX(), shape.getStartY() + 11 ); // 修正误差
    }
}
