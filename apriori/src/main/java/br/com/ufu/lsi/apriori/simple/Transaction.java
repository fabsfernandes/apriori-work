package br.com.ufu.lsi.apriori.simple;

import java.util.List;

public class Transaction {

    List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    
    public String toString(){	
	
	return items.toString();
    }

}
