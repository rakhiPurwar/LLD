package VendingMachine.state;

import VendingMachine.VendingMachine;
import VendingMachine.model.Coin;
import VendingMachine.model.Item;

public class SelectItem implements VendingMachineState{
    private static final SelectItem INSTANCE = new SelectItem();
    public static SelectItem getInstance() {
        return INSTANCE;
    }

    private SelectItem() {
    }

    @Override
    public void insertCoin(VendingMachine vendingMachine, Coin coin) {
        System.out.println("Item already selected. Dispensing shortly.");
    }

    @Override
    public void selectItem(VendingMachine vendingMachine, Item item) {
        System.out.println("Item already selected. Please wait.");
    }

    @Override
    public void cancel(VendingMachine vendingMachine) {
        int refund = vendingMachine.getBalance();
        vendingMachine.resetBalance();
        vendingMachine.setSelectedItem(null);
        vendingMachine.setState(IdleState.getInstance());
        System.out.println("Transaction cancelled. Returning to idle state. Please collect refund: " + refund + " cents.");
    }

    @Override
    public void dispense(VendingMachine vendingMachine) {
        Item item = vendingMachine.getSelectedItem();
        vendingMachine.getInventory().deductItem(item);

        int change = vendingMachine.getBalance() - item.getPrice();
        vendingMachine.resetBalance();
        vendingMachine.setSelectedItem(null);
        System.out.println("Dispensing item..." +item.getName());
        if (change > 0) {
            System.out.println("Please collect your change: " + change + " cents.");
        }
        vendingMachine.setState(IdleState.getInstance());
    }



}
