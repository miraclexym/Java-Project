package StoreAdministrator;

public class Bill {
    // 成员变量
    TransactionType transactionType;// 交易类型
    String transactionDate;// 交易时间
    String transactionParty;// 交易对象
    String discName;// 磁盘名称
    int quantity;// 磁盘数量
    int rentalDays;// 租用天数

    // 构造函数
    public Bill(TransactionType transactionType, String transactionDate, String transactionParty
    		, String discName, int quantity, int rentalDays) {
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.transactionParty = transactionParty;
        this.discName = discName;
        this.quantity = quantity;
        this.rentalDays = rentalDays;
    }
    
    // 展示账单
    public void displayBill() {
        if (this.transactionType != TransactionType.RENTAL){
            System.out.println(" 交易类型: " + this.transactionType + " 交易时间: " + this.transactionDate 
            		+ " 交易对象: " + this.transactionParty + " 磁盘名称: " + this.discName + " 磁盘数量: " + this.quantity);
            return;
        }
        
        System.out.println(" 交易类型: " + this.transactionType + " 交易时间: " + this.transactionDate 
        		+ " 交易对象: " + this.transactionParty + " 磁盘名称: " + this.discName 
        		+ " 磁盘数量: " + this.quantity + " 租用天数: " + this.rentalDays);
    }
}
