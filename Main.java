package final_task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Toys toy1 = new Toys("Мишка",1,13,7);
        Toys toy2 = new Toys("Кошка",2,9,32);
        Toys toy3 = new Toys("Пони",3,7,17);

        ArrayList<Toys> assort = new ArrayList<>(Arrays.asList(new Toys[] { toy1, toy2, toy3 }));
        ToysMachine toysMashin = new ToysMachine();
        toysMashin.initProducts(assort);




        System.out.println("Вы хотите добавить игрушку[y/n]  ?");
        Scanner scanner = new Scanner(System.in);
        String ans;
        ans = scanner.nextLine();

        if (ans.equals("y")) {
            System.out.println("Введите имя");
            String name = scanner.nextLine();
            System.out.println("Введите id");
            int id = scanner.nextInt();
            System.out.println("Введите колличество");
            int quantity = scanner.nextInt();
            System.out.println("Введите вероятность");
            int chanse = scanner.nextInt();
            Toys toyN = new Toys(name,id,quantity,chanse);
            toysMashin.addToys(toyN);
        }
        try(FileWriter writer = new FileWriter("notes2.txt", false))
        {
            Object text = toysMashin.getToys();
            writer.write(String.valueOf(text));
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }



        System.out.println(toysMashin.getToys());



    }
    public static class Toys {
        String name;
        int id;
        int quantity;
        int chance;

        @Override
        public String toString() {
            return "Toys{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", quantity=" + quantity +
                    ", chance=" + chance +
                    '}';
        }
        public void cangeChance(int newChance){
            this.chance = newChance;
        }

        public void cangeQuantity(){
            this.quantity = quantity-1;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getChance() {
            return chance;
        }

        public Toys(String name, int id, int quantity, int chance) {
            this.name = name;
            this.id = id;
            this.quantity = quantity;
            this.chance = chance;


        }
    }
    public static class ToysMachine{
        private ArrayList<Toys> assortmentToys = new ArrayList<>();

        protected void addToys(Toys item) {
            this.assortmentToys.add(item);
        }

        protected void initProducts(ArrayList<Toys> items) {
            for (Toys item : items) {
                addToys(item);
            }

        }

            public Object getToys() {
            int sumChance =0;
            for (int i = 0; i < this.assortmentToys.size(); i++) {
                sumChance += this.assortmentToys.get(i).getChance();
            }
            Random random = new Random();
            for (int i = 0; i < this.assortmentToys.size(); i++) {
                int rnd = random.nextInt(sumChance);
                if (rnd<=this.assortmentToys.get(i).getChance()) {
                    Toys boughProduct = this.assortmentToys.get(i);
                    this.assortmentToys.get(i).cangeQuantity();
                    if (assortmentToys.get(i).getQuantity()==0) {
                        this.assortmentToys.remove(i);
                    }
                    return boughProduct;
                }
                sumChance-=this.assortmentToys.get(i).getChance();
            }
            return new Toys ("Empty",0,0,0);
        }

        }
    }