package StoreAdministrator;

public class CD {
	// 成员变量
    String discName;// 磁盘名称
    int purchasePrice;// 磁盘进价
    int sellingPrice;// 磁盘售价
    int rentalPrice;// 单日租价
    int soldQuantity;// 已售出数量
    int rentedQuantity;// 已租出数量
    int remainingQuantity;// 剩余的数量

    // 构造函数
    public CD(String discName, int purchasePrice, int sellingPrice, int rentalPrice) {
        this.discName = discName;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.rentalPrice = rentalPrice;
        this.soldQuantity = 0;
        this.rentedQuantity = 0;
        this.remainingQuantity = 0;
    }
    
    // 展示磁盘
    public void displayCD() {
	    System.out.println(" CD 名称: " + this.discName 
	    		+ " CD 进价: " + this.purchasePrice + " CD 售价: " + this.sellingPrice + " CD 租价: " + this.rentalPrice 
	    		+ " CD 已售出: " + this.soldQuantity + " CD 已租出: " + this.rentedQuantity + " CD 剩余量: " + this.remainingQuantity);
    }
}
