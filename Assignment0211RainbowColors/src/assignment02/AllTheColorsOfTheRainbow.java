package assignment02;

public class AllTheColorsOfTheRainbow {
    int anIntegerRepresentingColors;

    public void changeTheHueOfTheColor(int newHue) {
        anIntegerRepresentingColors = newHue;
    }

    public static void main(String[] args) {
        AllTheColorsOfTheRainbow rainbow = new AllTheColorsOfTheRainbow();

        // Set the initial color
        rainbow.changeTheHueOfTheColor(42);
        System.out.println("The hue of the color is: " + rainbow.anIntegerRepresentingColors);

        // Change the color
        rainbow.changeTheHueOfTheColor(100);
        System.out.println("The new hue of the color is: " + rainbow.anIntegerRepresentingColors);
    }
}

