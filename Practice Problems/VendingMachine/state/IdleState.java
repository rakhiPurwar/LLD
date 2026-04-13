package VendingMachine.state;

import VendingMachine.VendingMachine;
import VendingMachine.model.Coin;
import VendingMachine.model.Item;

public class IdleState implements VendingMachineState{
    private static final IdleState INSTANCE = new IdleState();
    public static IdleState getInstance() {
        return INSTANCE;
    }

    private IdleState(){}
    @Override
    public void insertCoin(VendingMachine vendingMachine, Coin coin) {
        vendingMachine.addBalance(coin.getValue());
        System.out.println("Inserted coin" + coin.getValue() +
                " cents. Current balance: " + vendingMachine.getBalance() + " cents.");
        vendingMachine.setState(HasCoinState.getInstance());


    }

    @Override
    public void cancel(VendingMachine vendingMachine) {
        System.out.println("Please insert coins first");
    }

    @Override
    public void dispense(VendingMachine vendingMachine) {
        System.out.println("Please insert coins first");
    }

    @Override
    public void selectItem(VendingMachine vendingMachine, Item item) {
        System.out.println("Please insert coins first");
    }
}
