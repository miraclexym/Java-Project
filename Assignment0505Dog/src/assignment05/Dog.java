package assignment05;

public class Dog {
    public void bark(String sound) {
        System.out.println("���ͣ�" + sound);
    }

    public void bark(int soundLevel) {
        System.out.println("�������������� " + soundLevel + " �ֱ�");
    }

    public void bark(double soundFrequency) {
        System.out.println("���У�Ƶ�� " + soundFrequency + " Hz");
    }

    public static void main(String[] args) {
        Dog myDog = new Dog();

        myDog.bark("��������");
        myDog.bark(60);
        myDog.bark(122.5);
    }
}

