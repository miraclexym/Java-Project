package StoreAdministrator;

public class CD {
	// ��Ա����
    String discName;// ��������
    int purchasePrice;// ���̽���
    int sellingPrice;// �����ۼ�
    int rentalPrice;// �������
    int soldQuantity;// ���۳�����
    int rentedQuantity;// ���������
    int remainingQuantity;// ʣ�������

    // ���캯��
    public CD(String discName, int purchasePrice, int sellingPrice, int rentalPrice) {
        this.discName = discName;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.rentalPrice = rentalPrice;
        this.soldQuantity = 0;
        this.rentedQuantity = 0;
        this.remainingQuantity = 0;
    }
    
    // չʾ����
    public void displayCD() {
	    System.out.println(" CD ����: " + this.discName 
	    		+ " CD ����: " + this.purchasePrice + " CD �ۼ�: " + this.sellingPrice + " CD ���: " + this.rentalPrice 
	    		+ " CD ���۳�: " + this.soldQuantity + " CD �����: " + this.rentedQuantity + " CD ʣ����: " + this.remainingQuantity);
    }
}
