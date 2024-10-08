package assignment06;

public class ConnectionManager {
    private static final int MAX_CONNECTIONS = 5; //固定数组元素数量最大值
    private static int numberOfArray = 0; //数组拥有元素的数量
    private static Connection[] connections = new Connection[MAX_CONNECTIONS]; //元素为Connection对象的固定数组
    
    public static Connection getNewConnection() { //可以间接创建Connection对象
    	if(numberOfArray < MAX_CONNECTIONS) { //数组元素个数未满
        	connections[numberOfArray] = Connection.getNewConnection();
        	numberOfArray++;
        	return connections[numberOfArray-1];
    	}
    	
    	else { //数组元素个数已满
    		System.out.println("The array element has reached its maximum value, unable to create a new element!");
    		
    		return connections[numberOfArray-1];
    	}
    }
    
    public static Connection[] Accessing() { //访问元素为Connection对象的固定数组
    	if(numberOfArray == 0) {
    		System.out.println("There are no elements in the array that can be accessed!");
    		return null; //当ConnectionManager之中不再有对象时，它会返回null引用。
    	}
    	else {
        	System.out.println("The following content is to access all " + numberOfArray + " elements in the array!");
        	for(int i = 0; i < numberOfArray; i++) {
            	connections[i].Accessing(i);
        	}
        	return connections; //当ConnectionManager之中还有对象时，它会返回固定数组的地址。
    	}
    }
    
    public static void main(String[] args) {
    	Accessing();
    	getNewConnection(); //First array element
    	getNewConnection(); //Second array element
    	Accessing();
    	getNewConnection(); //Third array element
    	getNewConnection(); //Fourth array element
    	getNewConnection(); //Fifth array element
    	Accessing();
    	getNewConnection(); //Sixth array element
    }
}
