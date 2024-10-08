package assignment04;
import java.util.Arrays;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class VampireNumbers {
    private static int num = 0;

	public static void main(String[] args) {
    	findVampireNumbers();
    	System.out.println("The number of comparisons is " + num);
    }
    public static void findVampireNumbers() {
        for(int x = 11; x < 100; x++)
        	for(int y = Math.max(1000/x,x); y < Math.min(100 , 9999/x); y++){
        		num++;
        		check(x , y);
        	}
    }
    public static void check(int x, int y) {
    	int z = x * y;
    	if(z % 100 == 0) return;
		int a[]={x / 10, x % 10, y / 10, y % 10},b[]={z / 1000, (z / 100) % 10, (z / 10) % 10, z % 10};
		Arrays.sort(a);
		Arrays.sort(b);
		if (Arrays.equals(a,b)) {
            System.out.println(z + " = " + x + " ¡Á " + y);
        }
    }
}
