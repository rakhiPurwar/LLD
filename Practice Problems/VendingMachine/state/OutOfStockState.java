package VendingMachine.state;

import VendingMachine.VendingMachine;
import VendingMachine.model.Coin;
import VendingMachine.model.Item;

public class OutOfStockState implements VendingMachineState{

    private static final OutOfStockState INSTANCE = new OutOfStockState();

    public static OutOfStockState getInstance() {
        return INSTANCE;
    }

    private OutOfStockState(){}
    @Override
    public void insertCoin(VendingMachine vendingMachine, Coin coin) {
        System.out.println("Machine out of stock");
    }

    @Override
    public void cancel(VendingMachine vendingMachine) {
        System.out.println("Machine out of stock");
    }

    @Override
    public void dispense(VendingMachine vendingMachine) {
        System.out.println("Machine out of stock");
    }

    @Override
    public void selectItem(VendingMachine vendingMachine, Item item) {
        System.out.println("Machine out of stock");
    }
}
