import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.border.Border;
import javax.swing.table.*;

public class Frame extends JFrame implements ActionListener {
    // menu
    public ArrayList<Beverage> orderList = new ArrayList<>();
    public ArrayList<Coffee> coffeeMenu = new ArrayList<>();
    public ArrayList<Frappe> frappeMenu = new ArrayList<>();
    public ArrayList<FruitShake> fruitShakeMenu = new ArrayList<>();
    public ArrayList<Soda> sodaMenus = new ArrayList<>();
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

        // Sodas menu
        sodaMenus.add(new Soda("Coca-Cola", 1.05f, 150.50f));
        sodaMenus.add(new Soda("Sprite", 1.00f, 140.00f));
        sodaMenus.add(new Soda("Mountain Dew", 0.95f, 130.40f));
        sodaMenus.add(new Soda("Fanta", 1.25f, 160.60f));
        sodaMenus.add(new Soda("Diet Coke", 1.00f, 00.00f));
        sodaMenus.add(new Soda("Pepsi", 1.00f, 150.60f));

    }
    // default colors
    Color blackColor = new Color(28, 28, 28);
    Color whiteColor = new Color(234, 234, 234);

    // UI components
    private Container c;

    private JPanel logoPanel = new JPanel();
    private JLabel title;
    private JPanel timePanel = new JPanel();
    private JLabel currentTime;
    private Timer timer;

    private JPanel menuPanel = new JPanel();
    private JButton menuCoffeeBtn;
    private JButton menuFrappeBtn;
    private JButton menuFruitShakeBtn;
    private JButton menuSodaBtn;

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
    private int tempQty = 0;
    private JComboBox<String> sugarlvlComboBox;
    private String[] sugarLevels = {"0", "25", "50", "75", "100"};
    private float tempPrice;
    private float tempCalories;
    private float tempPriceTotal;
    private float tempSugar_lvl;
    private float tempCalTotal;
    private String tempName;
    private JButton addOrderBtn;
    private JButton bevAddBackBtn;
    private JButton tempBtn;
    private int orderCount = 0;
    private JLabel orderStatus;

    private float totalNoVAT = 0;
    private JLabel totalNoVATLabel;
    private float totalVAT;
    private JLabel totalVATLabel = new JLabel(" ");
    private JLabel VATlabel = new JLabel(" ");
    private JLabel noOrderLabel = new JLabel(" ");

    // frame constructor
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

        // logo icon + name
        ImageIcon logoIcon = new ImageIcon("H:\\Michelle\\JAVA PRACTICE\\BIBIRAGE\\Images\\LogoSmall.png");
        JLabel logoLabel = new JLabel(logoIcon);
        title = new JLabel("BIBIRAGE STORE");
        title.setFont(new Font("MV boli", Font.BOLD, 15));
        title.setSize(150, 20);

        logoPanel.add(logoLabel);
        logoPanel.add(title);

        // time panel (top right)
        timePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 25));
        timePanel.setSize(300, 60);
        timePanel.setBackground(whiteColor);
        timePanel.setLocation(740, 0);

        JLabel clockLabel = new JLabel("CLOCK: ");
        clockLabel.setFont(new Font("MV Boli", Font.BOLD, 15));
        clockLabel.setForeground(Color.BLACK);

        currentTime = new JLabel("      ");
        currentTime.setFont(new Font("MV boli", Font.BOLD, 15));
        currentTime.setForeground(Color.BLACK);
        currentTime.setSize(150, 20);

        timer = new Timer(1000, e -> updateTimeLabel());

        timer.start();
        timePanel.add(clockLabel);
        timePanel.add(currentTime);
        c.add(logoPanel);
        c.add(timePanel);

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

        menuSodaBtn = menuButton("SODAS");
        menuPanel.add(menuSodaBtn);

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

        bevAddBackBtn = optionsButton("BACK");
        bevAddBackBtn.setLocation(522, 480);
        bevAddBackBtn.setSize(60, 30);

        addOrderBtn = optionsButton("ADD ORDER");
        addOrderBtn.setLocation(800, 480);
        addOrderBtn.setSize(120, 30);

        orderStatus = new JLabel();
        orderStatus.setFont(new Font("MV boli", Font.BOLD, 13));
        orderStatus.setForeground(Color.BLACK);
        orderStatus.setSize(400, 20);
        orderStatus.setLocation(100, 505);

        totalNoVATLabel = new JLabel(String.format("Subtotal: $%.2f", totalNoVAT));
        totalNoVATLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        totalNoVATLabel.setForeground(blackColor);
        totalNoVATLabel.setSize(200, 50);
        totalNoVATLabel.setLocation(790, 465);

        removeButtons();

        c.add(totalNoVATLabel);
        c.add(orderStatus);
        c.add(bevBackBtn);
        c.add(bevAddBackBtn);
        c.add(addOrderBtn);
        c.add(storePanel);
        c.repaint();
    }

    //remove some button visibilities
    private void removeButtons(){
        bevBackBtn.setVisible(false);
        bevAddBackBtn.setVisible(false);
        addOrderBtn.setVisible(false);
        orderStatus.setVisible(false);
        totalNoVATLabel.setVisible(false);
        totalVATLabel.setVisible(false);
        noOrderLabel.setVisible(false);
        VATlabel.setVisible(false);
    }

    //timer format
    private void updateTimeLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date now = new Date();
        String formattedTime = sdf.format(now);

        currentTime.setText(formattedTime);
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
    public static void hoverEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                button.setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    public static void clickChangeColor(JButton button){
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
        // menu buttons
        if (e.getSource() == menuCoffeeBtn) {
            showMenu(coffeeMenu);
            tempBtn = menuCoffeeBtn;
        } else if (e.getSource() == menuFrappeBtn){
            showMenu(frappeMenu);
            tempBtn = menuFrappeBtn;
        } else if (e.getSource() == menuFruitShakeBtn){
            showMenu(fruitShakeMenu);
            tempBtn = menuFruitShakeBtn;
        } else if (e.getSource() == menuSodaBtn){
            showMenu(sodaMenus);
            tempBtn = menuSodaBtn;

        // back button from menu indiv beverages
        } else if (e.getSource() == bevBackBtn){
            storePanel.removeAll();
            storePanel.revalidate();
            storePanel.repaint();
            removeButtons();
            c.repaint();

        // increase decrease buttons
        } else if (e.getActionCommand().equals("+")){
            tempQty++;
            tempPriceTotal = tempQty * tempPrice;
            qtyLabel.setText("Quantity: " + tempQty);
            bevOrderPrice.setText(String.format("Price: $%.2f", tempPriceTotal));
        } else if (e.getActionCommand().equals("-")){
            if(tempQty > 1){
                tempQty--;
                tempPriceTotal = tempQty * tempPrice;
                tempPriceTotal = Math.round(tempPriceTotal * 100.0f) / 100.0f;
                qtyLabel.setText("Quantity: " + tempQty);
                bevOrderPrice.setText(String.format("Price: $%.2f", tempPriceTotal));
            }

        // sugar lvl combobox
        } else if(e.getSource() == sugarlvlComboBox){
            String selectedSugarLevel = (String) sugarlvlComboBox.getSelectedItem();
            tempSugar_lvl = Integer.parseInt(selectedSugarLevel)/100f;
            tempCalTotal = tempCalories + (tempCalories * tempSugar_lvl);
            tempCalTotal =  Math.round(tempCalTotal * 100.0f) / 100.0f;
            bevOrderCalories.setText("Energy: " + tempCalTotal + "kcal each");

        // add order back button
        } else if(e.getSource() == bevAddBackBtn){
            removeButtons();
            if (tempBtn == menuCoffeeBtn) {
                menuCoffeeBtn.doClick();
            } else if (tempBtn == menuFrappeBtn) {
                menuFrappeBtn.doClick();
            } else if (tempBtn == menuFruitShakeBtn) {
                menuFruitShakeBtn.doClick();
            } else if (tempBtn == menuSodaBtn) {
                menuSodaBtn.doClick();}

        // add order button
        } else if (e.getSource() == addOrderBtn) {
            removeButtons();
            orderList.add(new Beverage(tempName, tempPriceTotal, tempCalTotal));
            orderList.get(orderCount).setQty(tempQty);
            orderList.get(orderCount).setSugar_lvl(tempSugar_lvl);
            totalNoVAT += tempPriceTotal;
            orderCount++;

            // !remove after done na icode
            System.out.println("\nORDERS: ");
            for (Beverage order : orderList){
                System.out.println(order.getName() + "    " + order.getQty() + "    " + order.getPrice() + "    " + order.getSugar_lvl());
            }
            System.out.printf("\nSUBTOTAL: %.2f", totalNoVAT);

            orderStatus.setText(tempName + " has been added to your orders!");
            orderStatus.setVisible(true);

            Timer timer = new Timer(3000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                        orderStatus.setVisible(false);
                        ((Timer)e.getSource()).stop();
                }
            });

            //update total
            totalNoVATLabel.setText(String.format("Subtotal: $%.2f", totalNoVAT));

            timer.start();

            storePanel.removeAll();
            storePanel.revalidate();
            storePanel.repaint();
            c.repaint();

        //my orders button
        } else if (e.getSource() == myOrdersbtn) {
            removeButtons();
            storePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            storePanel.removeAll();
            storePanel.revalidate();

            bevBackBtn.setLocation(530, 475);
            bevBackBtn.setVisible(true);

            //My orders table model
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("#");
            model.addColumn("Qty");
            model.addColumn("Name");
            model.addColumn("Subtotal");
            model.addColumn("Sugar Lvl");
            model.addColumn(" ");

            JTable table = new JTable(model);

            // column widths
            table.getColumnModel().getColumn(0).setPreferredWidth(30);
            table.getColumnModel().getColumn(1).setPreferredWidth(50);
            table.getColumnModel().getColumn(2).setPreferredWidth(150);
            table.getColumnModel().getColumn(3).setPreferredWidth(80);
            table.getColumnModel().getColumn(4).setPreferredWidth(80);
            table.getColumnModel().getColumn(5).setPreferredWidth(90);

            JScrollPane scrollPane = new JScrollPane(table);
            int num = 1;
            for (Beverage order : orderList) {
                String formattedPrice = String.format("%.2f", order.getPrice());
                Object[] row = {
                        num,
                        order.getQty(),
                        order.getName(),
                        "$" + formattedPrice,
                        order.getSugar_lvl(),
                        "Remove"
                };
                num++;
                model.addRow(row);
            }

            table.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
            table.setForeground(Color.BLACK);
            table.setBackground(whiteColor);

            table.getTableHeader().setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
            table.getTableHeader().setForeground(whiteColor);
            table.getTableHeader().setBackground(new Color(47, 47, 47));

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Object.class, centerRenderer);

            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setResizingAllowed(false);

            RemoveAction removeAction = new RemoveAction(model);
            ButtonColumn buttonColumn = new ButtonColumn(table, removeAction, 5);

            storePanel.add(scrollPane);
            storePanel.repaint();

            totalNoVATLabel.setLocation(790, 465);
            totalNoVATLabel.setVisible(true);
            c.repaint();
        }

        else if(e.getSource() == payBtn){
            removeButtons();
            storePanel.removeAll();
            storePanel.revalidate();
            storePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 500, 25));
            removeButtons();
            JLabel space = new JLabel("                                                       ");

            if (orderList.isEmpty()){
                noOrderLabel.setText("<html><br><br>You haven't ordered yet!</html>");
                noOrderLabel.setVisible(true);
                noOrderLabel.setForeground(blackColor);
                noOrderLabel.setFont(new Font("MV boli", Font.BOLD, 30));
                bevBackBtn.setVisible(true);
                storePanel.add(noOrderLabel);

            } else {

                VATlabel.setText(String.format("VAT: $%.2f", totalNoVAT * 0.12f));
                VATlabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

                totalVAT = totalNoVAT + (totalNoVAT * 0.12f);
                totalVATLabel.setText(String.format("Total: $%.2f", totalVAT));
                totalVATLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

                totalVATLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

                totalNoVATLabel.setLocation(662, 123);

                totalNoVATLabel.setVisible(true);
                totalVATLabel.setVisible(true);
                VATlabel.setVisible(true);

                storePanel.add(space);
                storePanel.add(VATlabel);
                storePanel.add(totalVATLabel);

                // textfield to get payment
                JLabel paymentFieldLabel = new JLabel("Enter Payment: $");
                paymentFieldLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
                JTextField paymentField = new JTextField(10);
                JButton payPaymentButton = new JButton("PAY NOW");
                payPaymentButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
                payPaymentButton.setFocusable(false);
                payPaymentButton.setForeground(whiteColor);
                payPaymentButton.setBackground(blackColor);

                Border border1 = BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(blackColor, 1),
                        BorderFactory.createLineBorder(new Color(242, 242, 242), 3)
                );
                Border border2 = BorderFactory.createLineBorder(blackColor, 4);

                Border border3 = BorderFactory.createCompoundBorder(border1, border2);

                payPaymentButton.setBorder(border3);
                hoverEffect(payPaymentButton);

                payPaymentButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String paymentText = paymentField.getText();
                        try {
                            float paymentAmount = Float.parseFloat(paymentText);
                            if (paymentAmount >= totalVAT) {
                                float change = paymentAmount - totalVAT;

                                // Clear the store panel
                                storePanel.removeAll();
                                storePanel.revalidate();
                                storePanel.repaint();

                                // Display "Payment Received!" and change amount
                                JLabel paymentReceivedLabel = new JLabel("<html><center>Payment Received!<br>Change: $" + change + "</center></html>");
                                paymentReceivedLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
                                paymentReceivedLabel.setVisible(true);
                                paymentReceivedLabel.setForeground(Color.BLACK);
                                paymentReceivedLabel.setSize(200, 50);
                                paymentReceivedLabel.setLocation(662, 123);

                                storePanel.add(paymentReceivedLabel);
                                storePanel.repaint();

                                totalNoVATLabel.setText("Subtotal: $0.00");
                                totalNoVATLabel.setVisible(false);
                                bevBackBtn.setVisible(true);

                                // Reset variables
                                totalVAT = 0.0f;
                                totalNoVAT = 0.0f;
                                orderCount = 0;
                                tempQty = 0;
                                orderList.clear();
                            } else {
                                JOptionPane.showMessageDialog(null, "Insufficient funds!", "Payment Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Payment Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                storePanel.add(paymentFieldLabel);
                storePanel.add(paymentField);
                storePanel.add(payPaymentButton);
                storePanel.repaint();
            }
        }
        // exit button
        else if (e.getSource() == exitBtn){
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
            tempQty = 1;
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
            bevOrderPrice = new JLabel(String.format("Price: $%.2f", bev.getPrice()));
            bevOrderCalories = new JLabel("Energy: " + bev.getCalories() + "kcal each");
            bevOrderPrice.setFont(new Font("Arial", Font.BOLD, 12));
            bevOrderCalories.setFont(new Font("Arial", Font.BOLD, 12));

            tempPrice = bev.getPrice();
            tempCalories = bev.getCalories();
            tempName = bev.getName();

            tempPriceTotal = tempQty * tempPrice;

            //create panel containing qty and sugar lvl input options
            JPanel orderOptions = new JPanel();

            orderOptions.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 35));
            orderOptions.setBackground(Color.white);
            Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 1),
                    BorderFactory.createEmptyBorder(5,5,5,5));
            orderOptions.setBorder(border);

            // select quantity
            qtyLabel = new JLabel("Quantity: " + tempQty);
            qtyLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

            increaseButton = bevButton("+");
            decreaseButton = bevButton("-");

            // select sugar lvl
            JLabel sugarlvlLabel = new JLabel("Sugar Level: ");
            sugarlvlLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
            sugarlvlComboBox = new JComboBox<>(sugarLevels);
            sugarlvlComboBox.setBackground(whiteColor);
            sugarlvlComboBox.addActionListener(this);

            orderOptions.add(qtyLabel);
            orderOptions.add(increaseButton);
            orderOptions.add(decreaseButton);
            orderOptions.add(sugarlvlLabel);
            orderOptions.add(sugarlvlComboBox);

            removeButtons();
            addOrderBtn.setVisible(true);
            bevAddBackBtn.setVisible(true);

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
        removeButtons();

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

    public class RemoveAction extends AbstractAction {
        private DefaultTableModel model;
        public RemoveAction(DefaultTableModel model) {
            this.model = model;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int row = Integer.parseInt(e.getActionCommand());
            model.removeRow(row);
            updateRowNumbers(model);

            if (row >= 0 && row < orderList.size()) {
                orderList.remove(row);
                updateTotalNoVAT();
            } else {
                System.out.println("OUT OF BOUNDS INDEX!");
            }
        }

        private void updateTotalNoVAT() {
            totalNoVAT = 0;

            for (Beverage order : orderList) {
                totalNoVAT += order.getPrice() * order.getQty();
            }

            totalNoVATLabel.setText(String.format("Subtotal: $%.2f", totalNoVAT));
        }
    }

    //update table order numbers
    private void updateRowNumbers(DefaultTableModel model) {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(i + 1, i, 0); // Update the row number in column 0
        }
        orderCount--;
    }
}
