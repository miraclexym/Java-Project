package StoreAdministrator;

public class Bill {
    // ��Ա����
    TransactionType transactionType;// ��������
    String transactionDate;// ����ʱ��
    String transactionParty;// ���׶���
    String discName;// ��������
    int quantity;// ��������
    int rentalDays;// ��������

    // ���캯��
    public Bill(TransactionType transactionType, String transactionDate, String transactionParty
    		, String discName, int quantity, int rentalDays) {
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.transactionParty = transactionParty;
        this.discName = discName;
        this.quantity = quantity;
        this.rentalDays = rentalDays;
    }
    
    // չʾ�˵�
    public void displayBill() {
        if (this.transactionType != TransactionType.RENTAL){
            System.out.println(" ��������: " + this.transactionType + " ����ʱ��: " + this.transactionDate 
            		+ " ���׶���: " + this.transactionParty + " ��������: " + this.discName + " ��������: " + this.quantity);
            return;
        }
        
        System.out.println(" ��������: " + this.transactionType + " ����ʱ��: " + this.transactionDate 
        		+ " ���׶���: " + this.transactionParty + " ��������: " + this.discName 
        		+ " ��������: " + this.quantity + " ��������: " + this.rentalDays);
    }
}
