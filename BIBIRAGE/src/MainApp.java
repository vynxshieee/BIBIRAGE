import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class MainApp{
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            UIManager.put("Button.select", new Color(0,0,0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Frame();
    }
}