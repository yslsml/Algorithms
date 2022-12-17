package lab2.task3;

public class Main {

    public static void main(String[] args) {

        final HashTable table2 = new HashTable();
        System.out.println("\nDouble hashing method: ");
        table2.addDoubleHashMethod(10, 666);
        table2.addDoubleHashMethod(20, -12345);
        table2.addDoubleHashMethod(13, 245);
        table2.addDoubleHashMethod(40, -123);
        table2.addDoubleHashMethod(5, 888);
        table2.addDoubleHashMethod(6, 777);
        table2.addDoubleHashMethod(7, 333);
        table2.addDoubleHashMethod(19, 8);
        table2.addDoubleHashMethod(16, 77);
        table2.addDoubleHashMethod(71, 633);
        System.out.println(table2.print());
        System.out.println("Number of a collisions are " + table2.getNumberOfCollision());

        final HashTable table3 = new HashTable();
        System.out.println("\nLinear sensing method: ");
        table3.addLinearMethod(10, 666);
        table3.addLinearMethod(20, -12345);
        table3.addLinearMethod(13, 245);
        table3.addLinearMethod(40, -123);
        table3.addLinearMethod(5, 888);
        table3.addLinearMethod(6, 777);
        table3.addLinearMethod(7, 333);
        table3.addLinearMethod(19, 8);
        table3.addLinearMethod(16, 77);
        table3.addLinearMethod(71, 633);
        System.out.println(table3.print());
        System.out.println("Number of a collisions are " + table3.getNumberOfCollision());

        final HashTable table = new HashTable();
        System.out.println("\nOverflow chains method: ");
        table.addChainMethod(10, 666);
        table.addChainMethod(63, -12345);
        table.addChainMethod(13, 245);
        table.addChainMethod(40, -123);
        table.addChainMethod(5, 888);
        table.addChainMethod(6, 777);
        table.addChainMethod(7, 333);
        table.addChainMethod(19, 8);
        table.addChainMethod(16, 77);
        table.addChainMethod(71, 633);
        System.out.println(table.print());
        System.out.println("Number of a collisions are " + table.getNumberOfCollision());
        System.out.println("Max length of a collision is " + table.getLengthOfCollision());


    }
}
