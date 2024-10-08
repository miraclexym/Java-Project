package assignment05;

public class Dog {
    public void bark(String sound) {
        System.out.println("¹··Í£º" + sound);
    }

    public void bark(int soundLevel) {
        System.out.println("ÅØÏø£ºÒôÁ¿¼¶±ğ " + soundLevel + " ·Ö±´");
    }

    public void bark(double soundFrequency) {
        System.out.println("º¿½Ğ£ºÆµÂÊ " + soundFrequency + " Hz");
    }

    public static void main(String[] args) {
        Dog myDog = new Dog();

        myDog.bark("ÍôÍôÍô£¡");
        myDog.bark(60);
        myDog.bark(122.5);
    }
}

