package StoreAdministrator;

public class Member {
	// ��Ա����
    String memberName;// ��Ա����
    String phoneNumber;// �绰����
    int purchasedQuantity;// �����������
    int rentedQuantity;// ���ô�������
    int totalSpentAmount;// �����ѽ��

    // ���캯��
    public Member(String memberName, String phoneNumber) {
        this.memberName = memberName;
        this.phoneNumber = phoneNumber;
        this.purchasedQuantity = 0;
        this.rentedQuantity = 0;
        this.totalSpentAmount = 0;
    }
    
    // չʾ��Ա
    public void displayMember() {
        System.out.println(" ��Ա����: " + this.memberName 
        		+ " �绰����: " + this.phoneNumber + " �����������: " + this.purchasedQuantity 
        		+ " ���ô�������: " + this.rentedQuantity + " �����ѽ��: " + this.totalSpentAmount);
    }
}
