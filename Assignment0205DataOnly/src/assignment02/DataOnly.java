package assignment02;

public class DataOnly {
	int i;
	double d;
	boolean b;
	
	public DataOnly() {}
	
	public void output(){
		System.out.println("this.i = "+this.i);
		System.out.println("this.d = "+this.d);
		System.out.println("this.b = "+this.b);
	}
	
	public static void main(String[] args) {
		DataOnly data = new DataOnly();
		
		data.i = 47;
		data.d = 1.1;
		data.b = false;
		
		data.output();
	}
}
