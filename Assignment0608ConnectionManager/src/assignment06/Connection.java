package assignment06;

class Connection {
    private Connection() {} //不能直接创建Connection对象
    
    public static Connection getNewConnection() { //可以间接创建Connection对象
        return new Connection();
    }
    
    public void Accessing(int i){ //Connection的访问函数
    	System.out.println("The class object Connection with label " + i + " has been accessed!");
    }
}
