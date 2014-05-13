package br.com.ufu.lsi.apriori.simple;

import java.util.List;

public class Rule {
    
    private Itemset antecedent;
    
    private Itemset consequent;
    
    
    public Rule( Itemset total, Itemset consequent ){
	
	List<Item> items = consequent.getItems();
	if( items.size() == 1 ){
	    Item item = items.get(0);
	    if( item.getName().equals("democrat") || item.getName().equals("republican") ) {
		this.consequent = consequent;
		
		this.antecedent = Utils.extractComplementarSubset(total, consequent);
	    }
	}
	
	/*this.consequent = consequent;
	
	this.antecedent = Utils.extractComplementarSubset(total, consequent);
	*/
	
    }
    
    @Override
    public String toString(){
	if( antecedent == null || consequent == null )
	    return "";
	return antecedent.toString() + " --> " + consequent.toString() + "\n";
    }

}
