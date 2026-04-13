package VendingMachine.state;

import VendingMachine.VendingMachine;
import VendingMachine.model.Coin;
import VendingMachine.model.Item;

public class HasCoinState implements VendingMachineState {
    private static final HasCoinState INSTANCE = new HasCoinState();
    public static HasCoinState getInstance(){
        return INSTANCE;
    }

    public HasCoinState() {
    }

    // has money state is manitained so that if user entered less amt, add more. so u stay in has money state until user has enough money to select item. if user cancels, then we can return to idle state
    @Override
    public void insertCoin(VendingMachine vendingMachine, Coin coin) {
        vendingMachine.addBalance(coin.getValue());
        System.out.println("Inserted coin " + coin.getValue() +
                " cents. Current balance: " + vendingMachine.getBalance() + " cents.");
    }

    @Override
    public void cancel(VendingMachine vendingMachine) {
        System.out.println("Cancelling transaction. Returning to idle state.Please collect refund "+ vendingMachine.getBalance());
        vendingMachine.setState(IdleState.getInstance());
        vendingMachine.resetBalance();
    }

    @Override
    public void dispense(VendingMachine vendingMachine) {
        System.out.println("Please select an item first");
    }

    @Override
    public void selectItem(VendingMachine vendingMachine, Item item) {
        if(!vendingMachine.getInventory().isItemAvailable(item)){
            System.out.println("Sorrryyy "+ item.getName() + "is out of stock");
            return;
        }

        if(vendingMachine.getBalance()<item.getPrice()){
            System.out.println("Insufficient balance. Please insert more coins."+ (item.getPrice() - vendingMachine.getBalance() )+ " cents more needed.");
            return;
        }

        vendingMachine.setSelectedItem(item);
        System.out.println("Selected item "+ item);
        vendingMachine.setState(SelectItem.getInstance());
    }
}

//we are in hasmoney we can select dispense and cancel. cancel in money is less or user doesnt want he can still choose cancel and avail refund