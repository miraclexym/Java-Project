package StoreAdministrator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreAdministrator {
	// 成员变量
    int storeFunds;// 商店资金
    int storeProfit;// 所获利润
    private static ArrayList<Bill> bills = new ArrayList<>();// 商店账单
    private static Map<String, CD> cds = new HashMap<>();// 磁盘库存
    private static Map<String, Member> members = new HashMap<>();// 会员记录
    public static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    
    // 构造函数
    public StoreAdministrator() {
        this.storeFunds = 100000; // 初始资金10万块钱
        this.storeProfit = 0;
    }

    // 展示账单
    public void displayBills() {
    	if(!bills.isEmpty()){
        	System.out.println("\n以下为所有的账单记录。");
        	for (Bill bill : bills){
        		bill.displayBill(); // 访问到这个账单
        	}
    	}
    	else{
    		System.out.println("\n账单记录为空。");
    	}
    }

    // 展示磁盘
    public void displayCDs() {
    	if(!cds.isEmpty()){
        	System.out.println("\n以下为所有的磁盘记录。");
        	for (Map.Entry<String, CD> entry : cds.entrySet()) {
        	    CD cd = entry.getValue(); // 访问到这个磁盘
        	    cd.displayCD();
        	}
    	}
    	else{
    		System.out.println("\n磁盘记录为空。");
    	}
    }

    // 展示会员
    public void displayMembers() {
    	if(!members.isEmpty()){
        	System.out.println("\n以下为所有的会员记录。");
            for (Map.Entry<String, Member> entry : members.entrySet()) {
            	Member member = entry.getValue(); // 访问到这个会员
            	member.displayMember();
            }
    	}
    	else{
    		System.out.println("\n会员记录为空。");
    	}
    }
    
    // 展示所有统计
    public void displayAll(){
    	System.out.println("\n商店资金为: " + this.storeFunds);
    	System.out.println("所获利润为: " + this.storeProfit);
    	this.displayBills();// 展示账单
    	this.displayCDs();// 展示磁盘
    	this.displayMembers();// 展示会员
    }
    
    // 添加会员
    public void addMember() throws IOException {
    	System.out.print("\n请输入会员姓名: ");
        String memberName = stdin.readLine();
    	System.out.print("请输入电话号码: ");
        String phoneNumber = stdin.readLine();
        Member member = new Member(memberName, phoneNumber);
        members.put(memberName, member);

        System.out.println("会员添加成功，会员名称为: " + memberName + " 电话号码为: " + phoneNumber);
    }

    // 删除会员
    public void deleteMember() throws IOException {
    	System.out.print("\n请输入要删除的会员名称: ");
        String memberName = stdin.readLine();

        if (members.containsKey(memberName)) {
            members.remove(memberName);
            System.out.println("会员删除成功。");
        } else {
            System.out.println("找不到该会员。");
        }
    }

    // 增加磁盘种类
    public void addCD() throws IOException {
    	System.out.print("\n请输入磁盘名称: ");
        String discName = stdin.readLine();
    	System.out.print("请输入磁盘进价: ");
        int purchasePrice = Integer.parseInt(stdin.readLine());
    	System.out.print("请输入磁盘售价: ");
        int sellingPrice = Integer.parseInt(stdin.readLine());
    	System.out.print("请输入磁盘租价: ");
        int rentalPrice = Integer.parseInt(stdin.readLine());
        CD cd = new CD(discName, purchasePrice, sellingPrice, rentalPrice);
        cds.put(discName, cd);

        System.out.println("磁盘添加成功，磁盘名称为: " + discName 
        		+ " 进价为: " + purchasePrice + " 售价为: " + sellingPrice + " 租价为: " + rentalPrice);
    }

    // 进货磁盘
    public void purchaseCDs() throws IOException {
    	System.out.print("\n请输入进货日期: ");
        String purchaseDate = stdin.readLine();
        
        String discName;
        while(true){
        	System.out.print("请输入磁盘名称: ");
            discName = stdin.readLine();
            if (!cds.containsKey(discName)){
                System.out.println("找不到该磁盘，请重新输入");
            }
            else
            	break;
        }
        
    	System.out.print("请输入磁盘数量: ");
        int quantity = Integer.parseInt(stdin.readLine());
        
        CD cd = cds.get(discName);
        cd.remainingQuantity += quantity;// 更新磁盘库存
        
        this.storeFunds -= cd.purchasePrice * quantity;// 更新商店资金
        this.storeProfit = this.storeFunds - 100000;// 更新商店利润
        
        Bill bill = new Bill(TransactionType.PURCHASE, purchaseDate, "supplier", discName, quantity, 0);
        bills.add(bill);// 更新账单
    }

    // 销售磁盘
    public void sellCDs() throws IOException {
    	System.out.print("\n请输入销售日期: ");
    	String saleDate = stdin.readLine();
    	
        String discName;
        while(true){
        	System.out.print("请输入磁盘名称: ");
            discName = stdin.readLine();
            if (!cds.containsKey(discName)){
                System.out.println("找不到该磁盘，请重新输入");
            }
            else
            	break;
        }
        
    	System.out.print("请输入磁盘数量: ");
    	int quantity = Integer.parseInt(stdin.readLine());
    	
        String memberName;
        while(true){
        	System.out.print("请输入会员名称: ");
        	memberName = stdin.readLine();
            if (!members.containsKey(memberName)){
                System.out.println("找不到该会员，请重新输入");
            }
            else
            	break;
        }
    	
        CD cd = cds.get(discName);
        cd.remainingQuantity -= quantity;// 更新磁盘库存
        cd.soldQuantity += quantity;// 更新磁盘已售出数量
        
        Member member =members.get(memberName);
        member.purchasedQuantity += quantity;// 更新会员已购买数量
        member.totalSpentAmount += cd.sellingPrice * quantity;// 更新会员总消费金额
        
        this.storeFunds += cd.sellingPrice * quantity;// 更新商店资金
        this.storeProfit = this.storeFunds - 100000;// 更新商店利润
        
        Bill bill = new Bill(TransactionType.SALE, saleDate, memberName, discName, quantity, 0);
        bills.add(bill);// 更新账单
    }

    // 出租磁盘
    public void rentCDs() throws IOException {
    	System.out.print("\n请输入出租日期: ");
    	String rentalDate = stdin.readLine();
    	
        String discName;
        while(true){
        	System.out.print("请输入磁盘名称: ");
            discName = stdin.readLine();
            if (!cds.containsKey(discName)){
                System.out.println("找不到该磁盘，请重新输入");
            }
            else
            	break;
        }
        
    	System.out.print("请输入磁盘数量: ");
    	int quantity = Integer.parseInt(stdin.readLine());
    	
        String memberName;
        while(true){
        	System.out.print("请输入会员名称: ");
        	memberName = stdin.readLine();
            if (!members.containsKey(memberName)){
                System.out.println("找不到该会员，请重新输入");
            }
            else
            	break;
        }
    	
    	System.out.print("请输入出租天数: ");
    	int rentalDays = Integer.parseInt(stdin.readLine());
    	
        CD cd = cds.get(discName);
        cd.remainingQuantity -= quantity;// 更新磁盘库存
        cd.rentedQuantity += quantity;// 更新磁盘已租出数量
        
        Member member =members.get(memberName);
        member.rentedQuantity += quantity;// 更新会员已租磁盘数量
        member.totalSpentAmount += cd.rentalPrice * quantity * rentalDays;// 更新会员总消费金额
        
        this.storeFunds += cd.rentalPrice * quantity * rentalDays;// 更新商店资金
        this.storeProfit = this.storeFunds - 100000;// 更新商店利润
        
        Bill bill = new Bill(TransactionType.RENTAL, rentalDate, memberName, discName, quantity, rentalDays);
        bills.add(bill);// 更新账单
    }

    // 归还磁盘
    public void receiveReturnedCDs() throws IOException {
    	
        String memberName;
        while(true){
        	System.out.print("请输入会员名称: ");
        	memberName = stdin.readLine();
            if (!members.containsKey(memberName)){
                System.out.println("找不到该会员，请重新输入");
            }
            else
            	break;
        }
    	
        String discName;
        while(true){
        	System.out.print("请输入磁盘名称: ");
            discName = stdin.readLine();
            if (!cds.containsKey(discName)){
                System.out.println("找不到该磁盘，请重新输入");
            }
            else
            	break;
        }
        
    	System.out.print("请输入磁盘数量: ");
    	int quantity = Integer.parseInt(stdin.readLine());
    	
        CD cd = cds.get(discName);
        cd.remainingQuantity += quantity;// 更新磁盘库存
        cd.rentedQuantity -= quantity;// 更新磁盘已租出数量
        
        Member member =members.get(memberName);
        member.rentedQuantity -= quantity;// 更新会员已租磁盘数量
    }
    
    public static void main(String[] args) {
        StoreAdministrator storeAdmin = new StoreAdministrator();

        while (true) {
            System.out.println("\n1. 展示所有统计 2. 添加会员 3. 删除会员 4. 增加磁盘种类 "
            		+ "5. 进货磁盘 6. 销售磁盘 7. 出租磁盘 8. 归还磁盘 0. 退出程序");
            System.out.print("请选择一个操作: ");
            try {
                int choice = Integer.parseInt(StoreAdministrator.stdin.readLine());

                switch (choice) {
                    case 1:
                        storeAdmin.displayAll();
                        break;
                    case 2:
                        storeAdmin.addMember();
                        break;
                    case 3:
                        storeAdmin.deleteMember();
                        break;
                    case 4:
                        storeAdmin.addCD();
                        break;
                    case 5:
                        storeAdmin.purchaseCDs();
                        break;
                    case 6:
                        storeAdmin.sellCDs();
                        break;
                    case 7:
                        storeAdmin.rentCDs();
                        break;
                    case 8:
                        storeAdmin.receiveReturnedCDs();
                        break;
                    case 0:
                        System.out.println("退出程序");
                        System.exit(0);
                    default:
                        System.out.println("检测到无效输入，请进行有效输入！");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
        }
    }
}