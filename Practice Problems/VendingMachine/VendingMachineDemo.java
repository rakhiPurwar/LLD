package VendingMachine;

import VendingMachine.model.Coin;
import VendingMachine.model.Item;

public class VendingMachineDemo {
    public static void main(String[] args)

    {
        VendingMachine machine = new VendingMachine();

        Item coke = new Item("Coke", 50);
        Item chips = new Item("Chips", 25);
        Item water = new Item("Water", 40);

        machine.getInventory().addItem(coke,5);
        machine.getInventory().addItem(chips,10);
        machine.getInventory().addItem(water,20);

        machine.getInventory().display();

        System.out.println("\n--- User Interaction --- simple scenerio buy chips 25 rs, pay 30---");
        machine.insertCoin(Coin.TEN);
        machine.insertCoin(Coin.TEN);
        machine.insertCoin(Coin.TEN);

        machine.selectItem(chips);
        machine.dispense();

        System.out.println("\n--- User Interaction --- simple scenerio buy water 40 rs, pay 30--");
        machine.insertCoin(Coin.TEN);
        machine.insertCoin(Coin.TEN);
        machine.insertCoin(Coin.TEN);

        machine.selectItem(water);
        machine.cancel();

        System.out.println("\n--- User Interaction --- cancel mid txn--");
        machine.insertCoin(Coin.FIFTY);
        machine.cancel();

        System.out.println("\n--- User Interaction --- select item w/o inserting coin-");
        machine.selectItem(chips);







    }


}
