package StoreAdministrator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreAdministrator {
	// ��Ա����
    int storeFunds;// �̵��ʽ�
    int storeProfit;// ��������
    private static ArrayList<Bill> bills = new ArrayList<>();// �̵��˵�
    private static Map<String, CD> cds = new HashMap<>();// ���̿��
    private static Map<String, Member> members = new HashMap<>();// ��Ա��¼
    public static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    
    // ���캯��
    public StoreAdministrator() {
        this.storeFunds = 100000; // ��ʼ�ʽ�10���Ǯ
        this.storeProfit = 0;
    }

    // չʾ�˵�
    public void displayBills() {
    	if(!bills.isEmpty()){
        	System.out.println("\n����Ϊ���е��˵���¼��");
        	for (Bill bill : bills){
        		bill.displayBill(); // ���ʵ�����˵�
        	}
    	}
    	else{
    		System.out.println("\n�˵���¼Ϊ�ա�");
    	}
    }

    // չʾ����
    public void displayCDs() {
    	if(!cds.isEmpty()){
        	System.out.println("\n����Ϊ���еĴ��̼�¼��");
        	for (Map.Entry<String, CD> entry : cds.entrySet()) {
        	    CD cd = entry.getValue(); // ���ʵ��������
        	    cd.displayCD();
        	}
    	}
    	else{
    		System.out.println("\n���̼�¼Ϊ�ա�");
    	}
    }

    // չʾ��Ա
    public void displayMembers() {
    	if(!members.isEmpty()){
        	System.out.println("\n����Ϊ���еĻ�Ա��¼��");
            for (Map.Entry<String, Member> entry : members.entrySet()) {
            	Member member = entry.getValue(); // ���ʵ������Ա
            	member.displayMember();
            }
    	}
    	else{
    		System.out.println("\n��Ա��¼Ϊ�ա�");
    	}
    }
    
    // չʾ����ͳ��
    public void displayAll(){
    	System.out.println("\n�̵��ʽ�Ϊ: " + this.storeFunds);
    	System.out.println("��������Ϊ: " + this.storeProfit);
    	this.displayBills();// չʾ�˵�
    	this.displayCDs();// չʾ����
    	this.displayMembers();// չʾ��Ա
    }
    
    // ��ӻ�Ա
    public void addMember() throws IOException {
    	System.out.print("\n�������Ա����: ");
        String memberName = stdin.readLine();
    	System.out.print("������绰����: ");
        String phoneNumber = stdin.readLine();
        Member member = new Member(memberName, phoneNumber);
        members.put(memberName, member);

        System.out.println("��Ա��ӳɹ�����Ա����Ϊ: " + memberName + " �绰����Ϊ: " + phoneNumber);
    }

    // ɾ����Ա
    public void deleteMember() throws IOException {
    	System.out.print("\n������Ҫɾ���Ļ�Ա����: ");
        String memberName = stdin.readLine();

        if (members.containsKey(memberName)) {
            members.remove(memberName);
            System.out.println("��Աɾ���ɹ���");
        } else {
            System.out.println("�Ҳ����û�Ա��");
        }
    }

    // ���Ӵ�������
    public void addCD() throws IOException {
    	System.out.print("\n�������������: ");
        String discName = stdin.readLine();
    	System.out.print("��������̽���: ");
        int purchasePrice = Integer.parseInt(stdin.readLine());
    	System.out.print("����������ۼ�: ");
        int sellingPrice = Integer.parseInt(stdin.readLine());
    	System.out.print("������������: ");
        int rentalPrice = Integer.parseInt(stdin.readLine());
        CD cd = new CD(discName, purchasePrice, sellingPrice, rentalPrice);
        cds.put(discName, cd);

        System.out.println("������ӳɹ�����������Ϊ: " + discName 
        		+ " ����Ϊ: " + purchasePrice + " �ۼ�Ϊ: " + sellingPrice + " ���Ϊ: " + rentalPrice);
    }

    // ��������
    public void purchaseCDs() throws IOException {
    	System.out.print("\n�������������: ");
        String purchaseDate = stdin.readLine();
        
        String discName;
        while(true){
        	System.out.print("�������������: ");
            discName = stdin.readLine();
            if (!cds.containsKey(discName)){
                System.out.println("�Ҳ����ô��̣�����������");
            }
            else
            	break;
        }
        
    	System.out.print("�������������: ");
        int quantity = Integer.parseInt(stdin.readLine());
        
        CD cd = cds.get(discName);
        cd.remainingQuantity += quantity;// ���´��̿��
        
        this.storeFunds -= cd.purchasePrice * quantity;// �����̵��ʽ�
        this.storeProfit = this.storeFunds - 100000;// �����̵�����
        
        Bill bill = new Bill(TransactionType.PURCHASE, purchaseDate, "supplier", discName, quantity, 0);
        bills.add(bill);// �����˵�
    }

    // ���۴���
    public void sellCDs() throws IOException {
    	System.out.print("\n��������������: ");
    	String saleDate = stdin.readLine();
    	
        String discName;
        while(true){
        	System.out.print("�������������: ");
            discName = stdin.readLine();
            if (!cds.containsKey(discName)){
                System.out.println("�Ҳ����ô��̣�����������");
            }
            else
            	break;
        }
        
    	System.out.print("�������������: ");
    	int quantity = Integer.parseInt(stdin.readLine());
    	
        String memberName;
        while(true){
        	System.out.print("�������Ա����: ");
        	memberName = stdin.readLine();
            if (!members.containsKey(memberName)){
                System.out.println("�Ҳ����û�Ա������������");
            }
            else
            	break;
        }
    	
        CD cd = cds.get(discName);
        cd.remainingQuantity -= quantity;// ���´��̿��
        cd.soldQuantity += quantity;// ���´������۳�����
        
        Member member =members.get(memberName);
        member.purchasedQuantity += quantity;// ���»�Ա�ѹ�������
        member.totalSpentAmount += cd.sellingPrice * quantity;// ���»�Ա�����ѽ��
        
        this.storeFunds += cd.sellingPrice * quantity;// �����̵��ʽ�
        this.storeProfit = this.storeFunds - 100000;// �����̵�����
        
        Bill bill = new Bill(TransactionType.SALE, saleDate, memberName, discName, quantity, 0);
        bills.add(bill);// �����˵�
    }

    // �������
    public void rentCDs() throws IOException {
    	System.out.print("\n�������������: ");
    	String rentalDate = stdin.readLine();
    	
        String discName;
        while(true){
        	System.out.print("�������������: ");
            discName = stdin.readLine();
            if (!cds.containsKey(discName)){
                System.out.println("�Ҳ����ô��̣�����������");
            }
            else
            	break;
        }
        
    	System.out.print("�������������: ");
    	int quantity = Integer.parseInt(stdin.readLine());
    	
        String memberName;
        while(true){
        	System.out.print("�������Ա����: ");
        	memberName = stdin.readLine();
            if (!members.containsKey(memberName)){
                System.out.println("�Ҳ����û�Ա������������");
            }
            else
            	break;
        }
    	
    	System.out.print("�������������: ");
    	int rentalDays = Integer.parseInt(stdin.readLine());
    	
        CD cd = cds.get(discName);
        cd.remainingQuantity -= quantity;// ���´��̿��
        cd.rentedQuantity += quantity;// ���´������������
        
        Member member =members.get(memberName);
        member.rentedQuantity += quantity;// ���»�Ա�����������
        member.totalSpentAmount += cd.rentalPrice * quantity * rentalDays;// ���»�Ա�����ѽ��
        
        this.storeFunds += cd.rentalPrice * quantity * rentalDays;// �����̵��ʽ�
        this.storeProfit = this.storeFunds - 100000;// �����̵�����
        
        Bill bill = new Bill(TransactionType.RENTAL, rentalDate, memberName, discName, quantity, rentalDays);
        bills.add(bill);// �����˵�
    }

    // �黹����
    public void receiveReturnedCDs() throws IOException {
    	
        String memberName;
        while(true){
        	System.out.print("�������Ա����: ");
        	memberName = stdin.readLine();
            if (!members.containsKey(memberName)){
                System.out.println("�Ҳ����û�Ա������������");
            }
            else
            	break;
        }
    	
        String discName;
        while(true){
        	System.out.print("�������������: ");
            discName = stdin.readLine();
            if (!cds.containsKey(discName)){
                System.out.println("�Ҳ����ô��̣�����������");
            }
            else
            	break;
        }
        
    	System.out.print("�������������: ");
    	int quantity = Integer.parseInt(stdin.readLine());
    	
        CD cd = cds.get(discName);
        cd.remainingQuantity += quantity;// ���´��̿��
        cd.rentedQuantity -= quantity;// ���´������������
        
        Member member =members.get(memberName);
        member.rentedQuantity -= quantity;// ���»�Ա�����������
    }
    
    public static void main(String[] args) {
        StoreAdministrator storeAdmin = new StoreAdministrator();

        while (true) {
            System.out.println("\n1. չʾ����ͳ�� 2. ��ӻ�Ա 3. ɾ����Ա 4. ���Ӵ������� "
            		+ "5. �������� 6. ���۴��� 7. ������� 8. �黹���� 0. �˳�����");
            System.out.print("��ѡ��һ������: ");
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
                        System.out.println("�˳�����");
                        System.exit(0);
                    default:
                        System.out.println("��⵽��Ч���룬�������Ч���룡");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
        }
    }
}