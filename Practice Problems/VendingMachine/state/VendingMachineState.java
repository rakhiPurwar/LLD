package VendingMachine.state;
import VendingMachine.VendingMachine;
import VendingMachine.model.Coin;
import VendingMachine.model.Item;

public interface VendingMachineState {

    void insertCoin(VendingMachine vendingMachine, Coin coin);
    void selectItem(VendingMachine vendingMachine, Item item);
    void dispense( VendingMachine vendingMachine);
    void cancel(VendingMachine vendingMachine);

}

//vending machine needed for state transition
