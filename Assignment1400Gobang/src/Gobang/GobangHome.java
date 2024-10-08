package Gobang;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GobangHome extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private BufferedImage backgroundImage;

    public GobangHome() {
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/resource/联机五子棋游戏封面.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } 
        else {
            setBackground(Config.SETTINGS_PANEL_COLOR);
        }
    }
}