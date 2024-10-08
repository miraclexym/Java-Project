package assignment06;

public class ConnectionManager {
    private static final int MAX_CONNECTIONS = 5; //�̶�����Ԫ���������ֵ
    private static int numberOfArray = 0; //����ӵ��Ԫ�ص�����
    private static Connection[] connections = new Connection[MAX_CONNECTIONS]; //Ԫ��ΪConnection����Ĺ̶�����
    
    public static Connection getNewConnection() { //���Լ�Ӵ���Connection����
    	if(numberOfArray < MAX_CONNECTIONS) { //����Ԫ�ظ���δ��
        	connections[numberOfArray] = Connection.getNewConnection();
        	numberOfArray++;
        	return connections[numberOfArray-1];
    	}
    	
    	else { //����Ԫ�ظ�������
    		System.out.println("The array element has reached its maximum value, unable to create a new element!");
    		
    		return connections[numberOfArray-1];
    	}
    }
    
    public static Connection[] Accessing() { //����Ԫ��ΪConnection����Ĺ̶�����
    	if(numberOfArray == 0) {
    		System.out.println("There are no elements in the array that can be accessed!");
    		return null; //��ConnectionManager֮�в����ж���ʱ�����᷵��null���á�
    	}
    	else {
        	System.out.println("The following content is to access all " + numberOfArray + " elements in the array!");
        	for(int i = 0; i < numberOfArray; i++) {
            	connections[i].Accessing(i);
        	}
        	return connections; //��ConnectionManager֮�л��ж���ʱ�����᷵�ع̶�����ĵ�ַ��
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
