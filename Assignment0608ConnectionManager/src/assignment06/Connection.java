package assignment06;

class Connection {
    private Connection() {} //����ֱ�Ӵ���Connection����
    
    public static Connection getNewConnection() { //���Լ�Ӵ���Connection����
        return new Connection();
    }
    
    public void Accessing(int i){ //Connection�ķ��ʺ���
    	System.out.println("The class object Connection with label " + i + " has been accessed!");
    }
}
