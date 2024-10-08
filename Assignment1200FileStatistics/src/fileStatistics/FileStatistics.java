package fileStatistics;

import java.io.File;

class Statistic {
    int fileCount = 0;
    int folderCount = 0;
    long totalSize = 0;
    
	public Statistic() {
		this.fileCount = 0;
		this.folderCount = 0;
		this.totalSize = 0;
	}
	
	public void count(Statistic statistic){
		this.fileCount += statistic.fileCount;
		this.folderCount += statistic.folderCount;
		this.totalSize += statistic.totalSize;
	}
}

public class FileStatistics {

    public static void main(String[] args) {
        // 指定要遍历的文件夹路径
        String folderPath = "E:\\个人资料\\课程资料\\Java语言与应用";
        
        // 创建File对象，表示要统计的文件夹
        File folder = new File(folderPath);
        
        // 检查文件夹是否存在
        if (folder.exists()) {
            // 调用统计方法
            performFileStatistics(folder);
        } else {
            System.out.println("指定的文件夹不存在。");
        }
    }

    private static Statistic performFileStatistics(File folder) {
    	
    	Statistic statistic = new Statistic();

        // 获取文件夹下的所有文件和子文件夹
        File[] files = folder.listFiles();

        System.out.println("\n" + folder.getAbsolutePath() + "    文件夹中的统计结果：*********************\n");
        
        // 遍历文件夹下的所有文件和子文件夹
        for (File file : files) {
            if (file.isFile()) {
                // 如果是文件，增加文件计数和累加文件大小
            	statistic.fileCount++;
            	statistic.totalSize += file.length();
                System.out.println("文件名称：" + file.getName() + "    文件大小：" + formatSize(file.length()));
            } else if (file.isDirectory()) {
                // 如果是文件夹，增加文件夹计数，递归调用统计方法
            	statistic.folderCount++;
                Statistic newone = performFileStatistics(file);
                statistic.count(newone);
            }
        }

        System.out.println(folder.getAbsolutePath() + "    所有文件数目: " + statistic.fileCount);
        System.out.println(folder.getAbsolutePath() + "    所有文件夹数目: " + statistic.folderCount);
        System.out.println(folder.getAbsolutePath() + "    所有文件大小总和: " + formatSize(statistic.totalSize) + "\n");
        
        return statistic;
    }

    private static String formatSize(long size) {
        // 格式化文件大小，转换为KB、MB或GB
        if (size < 1024) {
            return size + " B";
        } else if (size < 1024 * 1024) {
            return size / 1024 + " KB";
        } else if (size < 1024 * 1024 * 1024) {
            return size / (1024 * 1024) + " MB";
        } else {
            return size / (1024 * 1024 * 1024) + " GB";
        }
    }
}
