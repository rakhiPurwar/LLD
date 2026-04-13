package VendingMachine;

import VendingMachine.inventory.Inventory;
import VendingMachine.model.Coin;
import VendingMachine.model.Item;
import VendingMachine.state.IdleState;
import VendingMachine.state.VendingMachineState;

public class VendingMachine {
    private VendingMachineState currentState;
    private  final Inventory inventory;
    private int balance;
    private Item selectedItem;

    public VendingMachine() {
        this.currentState = IdleState.getInstance();
        this.inventory = new Inventory();
        this.balance = 0;
    }

    //-- public API for user interaction (delegated to current state)
    //delegate to states
    public void insertCoin(Coin coin){
        currentState.insertCoin(this,coin);
    }

    public void selectItem( Item item){
        currentState.selectItem(this,item);
    }

    public void dispense(){
        currentState.dispense(this);
    }

    public void cancel(){
        currentState.cancel(this);
    }

    //state management
    public void setState(VendingMachineState currentState) {
        this.currentState = currentState;
    }

    //balance management


    public void addBalance(int amount){
        this.balance += amount;
    }
    public int getBalance() {
        return balance;
    }

    public void resetBalance(){
        this.balance = 0;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    // item helpers

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }


    //-- Inventory
    public Inventory getInventory() {
        return inventory;
    }

}
