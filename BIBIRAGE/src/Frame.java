import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.border.Border;

public class Frame extends JFrame implements ActionListener {
    // menu
    public ArrayList<Beverage> orderList = new ArrayList<>();
    public ArrayList<Coffee> coffeeMenu = new ArrayList<>();
    public ArrayList<Frappe> frappeMenu = new ArrayList<>();
    public ArrayList<FruitShake> fruitShakeMenu = new ArrayList<>();
    public ArrayList<Snacks> snacksMenu = new ArrayList<>();
    public HashMap<String, Beverage> bevDetails = new HashMap<>();

    public void initializeMenus(){
        // coffee menu
        coffeeMenu.add(new Coffee("Black", 3.00f, 5.00f));
        coffeeMenu.add(new Coffee("Latte", 4.50f, 120.00f));
        coffeeMenu.add(new Coffee("Cappuccino", 4.00f, 110.25f));
        coffeeMenu.add(new Coffee("Espresso", 2.00f, 5.00f));

        // frappe menu
        frappeMenu.add(new Frappe("Macchiato", 5.00f, 450.50f));
        frappeMenu.add(new Frappe("Chocolate", 5.75f, 500.65f));
        frappeMenu.add(new Frappe("Mocha", 5.00f, 433.68f));
        frappeMenu.add(new Frappe("Java chip", 5.95f, 560.90f));
        frappeMenu.add(new Frappe("Matcha", 5.25f, 330.75f));

        // fruit shake menu
        fruitShakeMenu.add(new FruitShake("Mango", 4.00f, 150.50f));
        fruitShakeMenu.add(new FruitShake("Ube", 4.50f, 250.00f));
        fruitShakeMenu.add(new FruitShake("Melon", 5.00f, 100.40f));
        fruitShakeMenu.add(new FruitShake("Lemon", 5.50f, 120.60f));
        fruitShakeMenu.add(new FruitShake("Orange", 4.75f, 110.60f));
        fruitShakeMenu.add(new FruitShake("Apple", 4.95f, 100.60f));

        // Snacks menu
        snacksMenu.add(new Snacks("Burger", 6.00f, 150.50f));
        snacksMenu.add(new Snacks("Fries", 3.50f, 250.00f));
        snacksMenu.add(new Snacks("Meatballs", 5.00f, 100.40f));
        snacksMenu.add(new Snacks("Chicken Chops", 5.25f, 120.60f));
        snacksMenu.add(new Snacks("Sandwich", 4.50f, 110.60f));
        snacksMenu.add(new Snacks("Burrito", 8.00f, 100.60f));

    }
    // default colors
    Color blackColor = new Color(28, 28, 28);
    Color whiteColor = new Color(234, 234, 234);

    // UI components
    private Container c;

    private JPanel logoPanel = new JPanel();
    private JLabel title;
    private JPanel moneyPanel = new JPanel();
    private JLabel money;
    private JLabel money_value_text;

    private JPanel menuPanel = new JPanel();
    private JButton menuCoffeeBtn;
    private JButton menuFrappeBtn;
    private JButton menuFruitShakeBtn;
    private JButton menuSnacksBtn;

    private JPanel optionsPanel = new JPanel();
    private JButton exitBtn;
    private JButton payBtn;
    private JButton myOrdersbtn;

    private static JPanel storePanel = new JPanel();
    private JButton bevBackBtn;
    private JLabel qtyLabel;
    private JButton increaseButton;
    private JButton decreaseButton;
    private JLabel bevOrderName;
    private JLabel bevOrderPrice;
    private JLabel bevOrderCalories;
    private int qtyCount = 0;
    private JComboBox<String> sugarlvlComboBox;
    private String[] sugarLevels = {"0", "25", "50", "75", "100"};
    private float tempPrice;
    private float tempCalories;
    private float tempPriceTotal;
    float sugar_lvl;
    private float tempCalTotal;
    private String tempName;
    private JButton addOrderBtn;

    // constructor
    public Frame() {
        initializeMenus();
        setTitle("Bibirage");
        setSize(1100, 650);
        setIconImage(new ImageIcon("H:\\Michelle\\JAVA PRACTICE\\BIBIRAGE\\Images\\Logo.png")
                .getImage());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        getContentPane().setBackground(whiteColor);

        c = getContentPane();
        c.setLayout(null);

        logoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 25));
        logoPanel.setSize(300, 60);
        logoPanel.setBackground(whiteColor);
        logoPanel.setLocation(10, 0);
        //headerPanel.setBorder(BorderFactory.createLineBorder(new Color(45,45,45)));

        // logo icon + name
        ImageIcon logoIcon = new ImageIcon("H:\\Michelle\\JAVA PRACTICE\\BIBIRAGE\\Images\\LogoSmall.png");
        JLabel logoLabel = new JLabel(logoIcon);
        title = new JLabel("BIBIRAGE STORE");
        title.setFont(new Font("MV boli", Font.BOLD, 15));
        title.setSize(150, 20);

        logoPanel.add(logoLabel);
        logoPanel.add(title);

        // money panel
        moneyPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 25));
        moneyPanel.setSize(300, 60);
        moneyPanel.setBackground(whiteColor);
        moneyPanel.setLocation(740, 0);

        int money_value = 100;
        money = new JLabel("Your Money: ");
        money.setFont(new Font("MV boli", Font.BOLD, 15));
        money.setForeground(blackColor);
        money.setSize(150, 20);

        money_value_text = new JLabel("$" + money_value);
        money_value_text.setFont(new Font("MV boli", Font.BOLD, 15));
        money_value_text.setForeground(new Color(25, 101, 18));
        money_value_text.setSize(150, 20);

        moneyPanel.add(money);
        moneyPanel.add(money_value_text);

        c.add(logoPanel);
        c.add(moneyPanel);

        // menu buttons + panel
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 16));
        menuPanel.setSize(315, 200);
        menuPanel.setLocation(100, 130);
        menuPanel.setBackground(whiteColor);
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel subtitle = new JLabel("CHOOSE YOUR ORDER");
        subtitle.setFont(new Font("MV boli", Font.BOLD, 23));
        menuPanel.add(subtitle);

        menuCoffeeBtn = menuButton("COFFEE");
        menuPanel.add(menuCoffeeBtn);

        menuFrappeBtn = menuButton("FRAPPE");
        menuPanel.add(menuFrappeBtn);

        menuFruitShakeBtn = menuButton("FRUIT SHAKE");
        menuPanel.add(menuFruitShakeBtn);

        menuSnacksBtn = menuButton("SNACKS");
        menuPanel.add(menuSnacksBtn);


        c.add(menuPanel);

        // options panel
        Border doubleBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(blackColor, 1),
                BorderFactory.createLineBorder(whiteColor, 2)
        );

        optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 15));
        optionsPanel.setSize(315, 120);
        optionsPanel.setLocation(100, 370);
        optionsPanel.setBackground(blackColor);
        optionsPanel.setBorder(doubleBorder);

        exitBtn = optionsButton("EXIT");
        optionsPanel.add(exitBtn);

        payBtn = optionsButton("PAY NOW");
        optionsPanel.add(payBtn);

        myOrdersbtn = optionsButton("MY ORDERS");
        optionsPanel.add(myOrdersbtn);

        c.add(optionsPanel);

        //store Panel (for individual beverages)
        storePanel.setLayout(new GridLayout(3, 2, 50, 20));
        storePanel.setSize(500, 450);
        storePanel.setLocation(470, 90);
        storePanel.setBackground(new Color(222, 222, 222));
        Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(157, 157, 157), 1),
                BorderFactory.createEmptyBorder(30,50,80,50));
        storePanel.setBorder(border);

        bevBackBtn = optionsButton("BACK");
        bevBackBtn.setLocation(690, 490);
        bevBackBtn.setSize(60, 30);
        bevBackBtn.setVisible(false);

        addOrderBtn = optionsButton("ADD ORDER");
        addOrderBtn.setLocation(800, 480);
        addOrderBtn.setSize(120, 30);
        addOrderBtn.setVisible(false);

        c.add(bevBackBtn);
        c.add(addOrderBtn);
        c.add(storePanel);
    }

    // default button layout
    private JButton defaultButton(String text, Color bgColor, Color fgColor, Border border, int fontSize) {
        JButton button = new JButton(text);
        button.setFont(new Font("Comic Sans MS", Font.PLAIN, fontSize));
        button.setFocusable(false);
        button.setForeground(fgColor);
        button.setBackground(bgColor);
        button.addActionListener(this);
        button.setBorder(border);
        hoverEffect(button);
        return button;
    }

    // To create menu buttons
    private JButton menuButton(String text) {
        Border border1 = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(blackColor, 1),
                BorderFactory.createLineBorder(new Color(242, 242, 242), 3)
        );
        Border border2 = BorderFactory.createLineBorder(blackColor, 4);

        Border border3 = BorderFactory.createCompoundBorder(border1, border2);
        return defaultButton(text, blackColor, new Color(242, 242, 242), border3, 18);
    }

    // To create options buttons
    private JButton optionsButton(String text) {
        Border border1 = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(blackColor, 1),
                BorderFactory.createLineBorder(new Color(242, 242, 242), 1)
        );
        Border border2 = BorderFactory.createLineBorder(blackColor, 4);

        Border border3 = BorderFactory.createCompoundBorder(border1, border2);
        return defaultButton(text, blackColor, new Color(242, 242, 242), border3, 17);
    }

    private JButton bevButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        button.setFocusable(false);
        button.setForeground(blackColor);
        button.setBackground(Color.white);
        button.addActionListener(this);
        button.setBorder(BorderFactory.createLineBorder(blackColor));
        hoverEffect(button);
        return button;
    }

    private JButton qtyButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setFocusable(false);
        button.setForeground(blackColor);
        button.setBackground(Color.white);
        button.addActionListener(this);
        button.setBorder(BorderFactory.createLineBorder(blackColor));
        button.setSize(50,50);
        hoverEffect(button);
        clickChangeColor(button);
        return button;
    }

    //mouse cursor hover method
    private void hoverEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                button.setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    private void clickChangeColor(JButton button){
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.setForeground(Color.white);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(Color.white);
                button.setForeground(Color.black);
            }
        });
    }


    // actions mouse clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuCoffeeBtn) {
            showMenu(coffeeMenu);
        } else if (e.getSource() == menuFrappeBtn){
            showMenu(frappeMenu);
        } else if (e.getSource() == menuFruitShakeBtn){
            showMenu(fruitShakeMenu);
        } else if (e.getSource() == menuSnacksBtn){
            showMenu(snacksMenu);
        } else if (e.getSource() == bevBackBtn){
            storePanel.removeAll();
            storePanel.revalidate();
            storePanel.repaint();
            bevBackBtn.setVisible(false);
            c.repaint();
        } else if (e.getActionCommand().equals("+")){
            qtyCount++;
            tempPriceTotal = qtyCount * tempPrice;
            qtyLabel.setText("Quantity: " + qtyCount);
            bevOrderPrice.setText("Price: $" + tempPriceTotal);
        } else if (e.getActionCommand().equals("-")){
            if(qtyCount > 1){
                qtyCount--;
                tempPriceTotal = qtyCount * tempPrice;
                qtyLabel.setText("Quantity: " + qtyCount);
                bevOrderPrice.setText("Price: $" + tempPriceTotal);
            }
        } else if(e.getSource() == sugarlvlComboBox){
            String selectedSugarLevel = (String) sugarlvlComboBox.getSelectedItem();
            sugar_lvl = Integer.parseInt(selectedSugarLevel)/100f;
            tempCalTotal = tempCalories + (tempCalories * sugar_lvl);
            bevOrderCalories.setText("Energy: " + tempCalTotal + "kcal each");
            System.out.println(sugar_lvl);

        } else if (e.getSource() == addOrderBtn) {
            orderList.add(new Beverage(tempName, tempPriceTotal, tempCalTotal));
        } else if (e.getSource() == exitBtn){
            c.removeAll();
            c.revalidate();
            c.repaint();
            JLabel exitText = new JLabel("=^._.^= imma hacc your pc");
            exitText.setFont(new Font("MV boli", Font.BOLD, 30));
            exitText.setSize(500, 200);
            exitText.setLocation(400,150);
            c.add(exitText);

            Timer timer = new Timer(1000, new ActionListener() {
                private int countdown = 3;
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (countdown >= 1) {
                        exitText.setText(Integer.toString(countdown--) + " hackin...");
                    } else if(countdown == 0){
                        exitText.setText("hackin complete B) bye");
                        countdown--;
                    } else {
                        ((Timer)e.getSource()).stop();
                        System.exit(0);
                    }
                }
            });
            timer.start();
        }

        // when bevbutton is clicked
        else if(e.getSource() instanceof JButton){
            qtyCount = 1;
            JButton bevButton = (JButton) e.getSource();
            String bevButtonID = bevButton.getActionCommand();

            // change store panel's grid layout
            storePanel.removeAll();
            storePanel.revalidate();
            storePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 25));

            // get id from hashmap
            Beverage bev = bevDetails.get(bevButtonID);
            String text = "<html><font size='9' face='Comic Sans MS'>" + bev.getName() + "</font></html>";
            bevOrderName = new JLabel(text);
            bevOrderPrice = new JLabel("Price: $" + bev.getPrice());
            bevOrderCalories = new JLabel("Energy: " + bev.getCalories() + "kcal each");
            bevOrderPrice.setFont(new Font("Arial", Font.BOLD, 12));
            bevOrderCalories.setFont(new Font("Arial", Font.BOLD, 12));

            tempPrice = bev.getPrice();
            tempCalories = bev.getCalories();
            tempName = bev.getName();

            //create panel containing qty and sugar lvl input options
            JPanel orderOptions = new JPanel();

            orderOptions.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 35));
            orderOptions.setBackground(Color.white);
            Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 1),
                    BorderFactory.createEmptyBorder(5,5,5,5));
            orderOptions.setBorder(border);

            // select quantity
            qtyLabel = new JLabel("Quantity: " + qtyCount);
            qtyLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

            increaseButton = bevButton("+");
            decreaseButton = bevButton("-");

            // select sugar lvl
            JLabel sugarlvlLabel = new JLabel("Sugar Level: ");
            sugarlvlLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
            sugarlvlComboBox = new JComboBox<>(sugarLevels);
            sugarlvlComboBox.addActionListener(this);

            orderOptions.add(qtyLabel);
            orderOptions.add(increaseButton);
            orderOptions.add(decreaseButton);
            orderOptions.add(sugarlvlLabel);
            orderOptions.add(sugarlvlComboBox);

            addOrderBtn.setVisible(true);
            bevBackBtn.setVisible(true);
            bevBackBtn.setLocation(522, 480);

            storePanel.add(bevOrderName);
            storePanel.add(bevOrderPrice);
            storePanel.add(bevOrderCalories);
            storePanel.add(orderOptions);
            orderOptions.repaint();
            storePanel.repaint();
            c.repaint();
        }
    }

    // method to show individual menu beverages
    public void showMenu(ArrayList<? extends Beverage> bevList) {
        storePanel.setLayout(new GridLayout(3, 2, 50, 20));
        storePanel.removeAll();
        storePanel.revalidate();

        for (Beverage bev : bevList) {
            String text = "<html><center><font size='5.5' face='Comic Sans MS'>" + bev.getName()
                    + "</font><br><font size='3' face='Arial'>$" + bev.getPrice() + "<br>" + bev.getCalories() + "kcal</center></font></html>";
            JButton bevButton = bevButton(text);
            // set unique id + store button bev details in Hashmap
            bevButton.setActionCommand(bev.getName());
            bevDetails.put(bev.getName(), bev);

            bevButton.addActionListener(this);

            clickChangeColor(bevButton);
            storePanel.add(bevButton);
        }
        storePanel.repaint();
        bevBackBtn.setLocation(690, 490);
        bevBackBtn.setVisible(true);
        c.repaint();
    }
}
