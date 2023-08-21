import java.util.Scanner;
public abstract class  Beverage { //PARENT CLASS
        private String name;
        private float price;
        private float calories;
        private int qty;
        private float sugar_lvl;

        //CONSTRUCTOR
        Beverage (String name, float price, float calories){
                this.name = name;
                this.price = price;
                this.calories = calories;
                this.qty = 0;
                this.sugar_lvl = 0;
        }

        //GETTERS
        public String getName(){
                return name;
        }
        public Float getPrice(){
                return price;
        }
        public Float getCalories(){
                return calories;
        }
        public void setQty(int qty){
                this.qty = qty;
        }
        public void setSugar_lvl(float setSugar_lvl){
                this.sugar_lvl = sugar_lvl;
        }
        public int getQty(){
                return qty;
        }
        public float getSugar_lvl(){
                return sugar_lvl;
        }
        private float getSubtotal(){
                return qty * price;
        }
}

