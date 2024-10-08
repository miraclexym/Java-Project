package Gobang;

import javax.sound.sampled.*;

public class GobangMusic implements Runnable {
    private static SourceDataLine sourceDataLine;
    private Thread musicThread;
    private volatile boolean running;

    public GobangMusic() {}

    @Override
    public void run() {
        try {
            // 打开音频文件
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(GobangMusic.class.getResourceAsStream(GobangControl.musicPath));

            // 获取音频格式
            AudioFormat audioFormat = audioInputStream.getFormat();

            // 创建数据行信息对象
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);

            // 打开数据行，获取源数据行对象
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(audioFormat);

            // 开始播放音乐
            sourceDataLine.start();

            int bytesRead;
            byte[] audioData = new byte[1024];

            // 读取音频数据并写入数据行
            while ((bytesRead = audioInputStream.read(audioData, 0, audioData.length)) != -1 && running) {
                if (bytesRead >= 0) {
                    sourceDataLine.write(audioData, 0, bytesRead);
                }
            }

            // 关闭数据行
            sourceDataLine.drain();
            sourceDataLine.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void playMusic(String filePath) {
        running = true;
        musicThread = new Thread(this);
        musicThread.start();
    }

    public void stopMusic() {
        running = false;
    }

    public void pauseMusic() {
        if (sourceDataLine != null) {
            sourceDataLine.stop();
        }
    }

    public void resumeMusic() {
        if (sourceDataLine != null) {
            sourceDataLine.start();
        }
    }
}