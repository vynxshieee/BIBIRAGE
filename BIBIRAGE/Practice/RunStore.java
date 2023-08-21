import java.util.ArrayList;
import java.util.Scanner;

public class RunStore {
    public static Scanner sc = new Scanner(System.in);
    static ArrayList<Beverage> orderList = new ArrayList<>();
    static ArrayList<Coffee> coffeeMenu = new ArrayList<>();
    static ArrayList<Frappe> frappeMenu = new ArrayList<>();
    static ArrayList<FruitShake> fruitShakeMenu = new ArrayList<>();
    static float subtotal;

    public static void main(String[] args) {
        char resp;

        // coffee menu
        coffeeMenu.add(new Coffee("Black     ", 55.00f, 40.50f));
        coffeeMenu.add(new Coffee("Latte     ", 85.50f, 120.00f));
        coffeeMenu.add(new Coffee("Cappuccino", 75.50f, 110.25f));
        coffeeMenu.add(new Coffee("Espresso  ", 30.00f, 5.00f));

        // frappe menu
        frappeMenu.add(new Frappe("Macchiato  ", 150.50f, 450.50f));
        frappeMenu.add(new Frappe("Chocolate  ", 175.40f, 500.65f));
        frappeMenu.add(new Frappe("Mocha      ", 160.00f, 433.68f));
        frappeMenu.add(new Frappe("Java chip  ", 190.25f, 560.90f));
        frappeMenu.add(new Frappe("Matcha     ", 170.50f, 330.75f));

        // fruit shake menu
        fruitShakeMenu.add(new FruitShake("Mango      ", 120.00f, 150.50f));
        fruitShakeMenu.add(new FruitShake("Ube        ", 130.50f, 250.00f));
        fruitShakeMenu.add(new FruitShake("Melon      ", 150.65f, 100.40f));
        fruitShakeMenu.add(new FruitShake("Strawberry ", 160.50f, 120.60f));

        do{
            System.out.println("!! Welcome to the BIBIRAGE STORE !!");
            System.out.println("A. Add an order.");
            System.out.println("B. Remove an order.");
            System.out.println("C. Show list of orders.");
            System.out.println("D. Pay.");
            System.out.println("E. Exit.");
            System.out.print("\nAnswer: ");
            resp = sc.next().toUpperCase().charAt(0);
            System.out.println();

            switch(resp){
                case 'A':
                    char ans;
                    do{
                        System.out.println("Beverage Types ");
                        System.out.println("A. Coffee");
                        System.out.println("B. Frappe");
                        System.out.println("C. Fruit Shake");
                        System.out.println("D. Done");
                        System.out.print("Answer: ");
                        ans = sc.next().toUpperCase().charAt(0);
                        System.out.println();
                        if(ans == 'D')
                            break;
                        DisplayMenu(ans);
                    }while(ans != 'D');
                    break;
                case 'B':
                    DisplayOrder();
                    System.out.print("Enter order's number to be deleted: ");
                    int del = sc.nextInt();
                    subtotal -= orderList.get(del - 1).getPrice() * orderList.get(del - 1).getQty();
                    orderList.remove(del - 1);
                    System.out.println();
                    break;
                case 'C':
                    DisplayOrder();
                    break;
                case 'D':
                    PayReceipt();
                    break;
                case 'E':
                    break;
                default:
                    System.out.println("\nEnter valid option!\n");
            }
        }while(resp != 'E');

        Outro();
    }

    public static void DisplayMenu(char ans){
        switch (ans) {
            case 'A' -> GetOrder(coffeeMenu);
            case 'B' -> GetOrder(frappeMenu);
            case 'C' -> GetOrder(fruitShakeMenu);
        }
    }

    public static void GetOrder(ArrayList<? extends Beverage> bevList){
        for(Beverage bev : bevList){
            System.out.print(bevList.indexOf(bev) + 1 + ". " + "\t\t");
            System.out.print(bev.getName() + "\t\t");
            System.out.printf("Php %.2f\t\t", bev.getPrice());
            System.out.printf("%.2f kcal\n", bev.getCalories());
        }
        System.out.println(bevList.size() + 1 + ". \t\tBack\n");
        System.out.print("Answer: ");
        int index = sc.nextInt();

        if(index == bevList.size() + 1)
            return;

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        System.out.print("Enter Sugar Level (0, 25, 50, 75, 100): ");
        float sugar_lvl = sc.nextFloat();

        Beverage addBev = bevList.get(index - 1);
        addBev.setQty(qty);
        addBev.setSugar_lvl(sugar_lvl);

        orderList.add(addBev);
        subtotal = subtotal + addBev.getPrice() * addBev.getQty();
        System.out.println();
    }
    public static void DisplayOrder(){
        if (orderList.isEmpty())
            System.out.println("You have not ordered yet!\n");
        else{
            for(Beverage bev : orderList){
                System.out.print(orderList.indexOf(bev) + 1 + ". " + "\t\t");
                System.out.print(bev.getQty() + "\t\t");
                System.out.print(bev.getName() + "\t\t");
                System.out.printf("%.2f kcal\t\t", bev.getCalories() + (bev.getCalories() * (bev.getSugar_lvl()/100)));
                System.out.printf("Php %.2f\t\t", bev.getPrice());
                System.out.printf("= Php %.2f\n", bev.getPrice() * bev.getQty());
            }
            System.out.printf("\nSubtotal (w/o VAT): %.2f\n\n", subtotal);
        }

    }
    public static void PayReceipt(){
        float VAT =  subtotal * 0.12f;
        System.out.printf("Subtotal:                 Php %.2f\n", subtotal);
        System.out.printf("VAT (12%%):                Php %.2f\n", VAT);
        System.out.print("Promo: (");
        float discount = CheckPromo();
        System.out.printf(")     -Php %.2f", discount);
        float total = subtotal + VAT - discount;
        System.out.printf("\nTOTAL:                    Php %.2f\n\n", total);
        System.out.print("Pay now? [Y/N]: ");
        char ans = sc.next().toUpperCase().charAt(0);
        if (ans == 'Y'){
            float payment;
            do{
                System.out.print("Enter Payment (Php): ");
                payment = sc.nextFloat();
                if(payment < total)
                    System.out.println("Insufficient funds!");
            }while(payment < total);
            System.out.printf("\nChange: Php %.2f", payment - total);
            System.out.println("\n\nTHANK YOU FOR YOU PURCHASE BIBI!\n\n");
            orderList.clear();
        }
    }

    public static float CheckPromo(){
        if (subtotal > 300 && subtotal < 500)
        {System.out.print("10%OFFPROMO"); return (subtotal * 0.1f);}
        else if(subtotal > 500 && subtotal < 1000)
        {System.out.print("15%OFFPROMO"); return (subtotal * 0.15f);}
        else if(subtotal > 1000)
        {System.out.print("25%OFFPROMO"); return (subtotal * 0.25f);}
        else
        {System.out.println("NONE"); return 0;}
    }

    public static void Outro(){
        System.out.println("  /\\_/\\");
        System.out.println(" ( o.o )");
        System.out.println("  > ^ <");
    }
}