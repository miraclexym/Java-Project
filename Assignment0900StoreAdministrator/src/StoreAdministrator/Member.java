package StoreAdministrator;

public class Member {
	// 成员变量
    String memberName;// 会员名称
    String phoneNumber;// 电话号码
    int purchasedQuantity;// 购买磁盘数量
    int rentedQuantity;// 租用磁盘数量
    int totalSpentAmount;// 总消费金额

    // 构造函数
    public Member(String memberName, String phoneNumber) {
        this.memberName = memberName;
        this.phoneNumber = phoneNumber;
        this.purchasedQuantity = 0;
        this.rentedQuantity = 0;
        this.totalSpentAmount = 0;
    }
    
    // 展示会员
    public void displayMember() {
        System.out.println(" 会员名称: " + this.memberName 
        		+ " 电话号码: " + this.phoneNumber + " 购买磁盘数量: " + this.purchasedQuantity 
        		+ " 租用磁盘数量: " + this.rentedQuantity + " 总消费金额: " + this.totalSpentAmount);
    }
}
