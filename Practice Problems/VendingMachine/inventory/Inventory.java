package VendingMachine.inventory;

import VendingMachine.model.Item;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    private final Map<Item,Integer> stock = new ConcurrentHashMap<>();

    public void addItem(Item item, int quantity){
        // merge — atomically adds quantity to existing value, or sets quantity if absent
        stock.merge(item, quantity, Integer::sum);
    }

    public boolean isItemAvailable(Item item){
        return stock.getOrDefault(item,0)>0;
    }

    public void deductItem(Item item) {
        // compute — atomically reads currentQty, runs lambda, writes result back
        // no other thread can interleave between the read and write
        stock.compute(item, (k, currentQty) -> {
            if (currentQty == null || currentQty <= 0) {
                throw new IllegalArgumentException("Item out of stock: " + item.getName());
            }
            return currentQty - 1;
        });
    }

    public void display(){
        System.out.println("\n----- Available Items -----");
        stock.forEach((item, qty) ->
                System.out.println(" "+ item + " qty" + qty));

        System.out.println("---------");

    }

}
