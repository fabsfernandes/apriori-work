package br.com.ufu.lsi.apriori.simple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AprioriRunner {

    public static final String PATH = "/home/fabiola/Desktop/Doutorado/DataMining/Trabalho-Apriori/testes/input-without-blanks";

    public static final Double MINSUP = 0.01;
    
    public static final Double MINCONF = 1.0;

    public static List<Transaction> transactions;
    
    private static int counter = 0;

    public static void main(String... args) {

	transactions = Utils.readInput(PATH);

	AprioriRunner runner = new AprioriRunner();
	List<Itemset> finalFrequentItemsets = runner.frequentItemsetGeneration();
	
	Collections.sort( finalFrequentItemsets );
	
	System.out.println( "Generating rules..." );
	Long init = System.currentTimeMillis();
	runner.ruleGeneration( finalFrequentItemsets );
	Long end = System.currentTimeMillis() - init;
	System.out.println( "Time: " + end + "(ms)");
	System.out.println( "Total rules: " + counter );
    }
    
    public void ruleGeneration( List<Itemset> finalFrequentItemsets ){
	
	for( Itemset itemset : finalFrequentItemsets ){
	    if( itemset.getSize() > 1 ){
	    List<Itemset> h1Itemsets = generateH1Itemsets( itemset );
	    
	    List<Itemset> copyNextItemsets = new ArrayList<Itemset>( h1Itemsets );
	    for( Itemset nextItemset : h1Itemsets ){

		Double confidence = calcConfidence( itemset, nextItemset );
		if( confidence >= MINCONF ){
		    generateRule( itemset, nextItemset );
		} else {
		    copyNextItemsets.remove(nextItemset);
		}

	    }
	    
	    //apGenRules( itemset, h1Itemsets );
	    }
	}	
    }
    
    public void apGenRules( Itemset itemset, List<Itemset> hMXtemsets ){
	if( itemset.getSize() > hMXtemsets.get(0).getSize() + 1 ){
	    List<Itemset> nextItemsets = aprioriGen( hMXtemsets );
	    List<Itemset> copyNextItemsets = new ArrayList<Itemset>( nextItemsets );
	    for( Itemset nextItemset : nextItemsets ){

		Double confidence = calcConfidence( itemset, nextItemset );
		if( confidence >= MINCONF ){
		    generateRule( itemset, nextItemset );
		} else {
		    copyNextItemsets.remove(nextItemset);
		}

	    }
	    apGenRules( itemset, copyNextItemsets );
	}
    }
    
    public void generateRule( Itemset itemset, Itemset consequent ){
	Rule rule = new Rule( itemset, consequent );
	if( !rule.toString().isEmpty() ){
	counter++;
	
	    //System.out.print( counter + " " + rule );
	}
	
    }
    
    public Double calcConfidence( Itemset total, Itemset consequent ){
	calcSupport(total);
	calcSupport(consequent);
	Itemset antecedent = Utils.extractComplementarSubset( total, consequent );
	calcSupport( antecedent );
	
	Double confidence = total.getSupport() / antecedent.getSupport();
	
	return confidence;
    }
    
    public List<Itemset> generateH1Itemsets( Itemset itemset ){
	List<Itemset> h1Itemsets = new ArrayList<Itemset>();
	List<Item> items = itemset.getItems();
	for( Item item : items ){
	    
	    if( item.getName().equals("democrat") || item.getName().equals("republican") ){
	    
	    Itemset new1Itemset = new Itemset();
	    new1Itemset.setSize(1);
	    new1Itemset.getItems().add(item);
	    h1Itemsets.add(new1Itemset);
	    
	    }
	}
	return h1Itemsets;
    }
    

    public List<Itemset> findFrequent1Itemsets() {
	List<Itemset> itemsets = new ArrayList<Itemset>();

	for (Transaction transaction : transactions) {
	    List<Item> itemsTransaction = transaction.getItems();
	    for (Item item : itemsTransaction) {

		Itemset itemset = new Itemset();
		itemset.setSize(1);
		itemset.getItems().add(item);

		if (!itemsets.contains(itemset)) {
		    itemsets.add(itemset);
		    if (!calcSupport(itemset)) {
			itemsets.remove(itemset);
		    }
		}
	    }
	}
	return itemsets;
    }

    public List<Itemset> findFrequentKItemsets(List<Itemset> candidates) {
	List<Itemset> itemsets = new ArrayList<Itemset>();

	for (Itemset itemset : candidates) {

	    /*
	     * for( Transaction transaction : transactions ) { List<Item>
	     * itemsTransaction = transaction.getItems(); for( Item item :
	     * itemsTransaction ){
	     */
	    /*
	     * Itemset itemset = new Itemset(); itemset.setSize(1);
	     * itemset.getItems().add( item );
	     */

	    if (!itemsets.contains(itemset)) {
		itemsets.add(itemset);
		if (!calcSupport(itemset)) {
		    itemsets.remove(itemset);
		}
		// }
		// }
	    }
	}
	return itemsets;
    }

    public boolean calcSupport(Itemset itemset) {

	int count = 0;

	for (Transaction transaction : transactions) {

	    if (itemsetIsContainedInTransaction(transaction, itemset)) {
		count++;
	    }
	}

	Double support = (double) ((double) count / (double) transactions
		.size());
	itemset.setSupport(support);

	if (support >= MINSUP) {
	    return true;
	}
	return false;
    }

    public boolean itemsetIsContainedInTransaction(Transaction transaction,
	    Itemset itemset) {
	List<Item> itemsetItems = itemset.getItems();
	List<Item> transactionItems = transaction.getItems();

	for (Item item : itemsetItems) {
	    if (!transactionItems.contains(item)) {
		return false;
	    }
	}

	return true;
    }

    public List<Itemset> aprioriGen(List<Itemset> kFrequents) {

	List<Itemset> candidates = generateCandidates(kFrequents);

	/*
	 * This call does not help in performance because at this moment all
	 * data are already in memory and an extra phase (prune) just improve the
	 * total execution time. Remind: this Apriori implementation is so
	 * simple. The prune phase is good when data is not in memory.
	 * This is just a didactic implementation!
	 */
	// candidates = prunningCandidates( candidates, kFrequents );

	return candidates;
    }

    public List<Itemset> prunningCandidates(List<Itemset> candidates,
	    List<Itemset> kFrequents) {

	List<Itemset> temps = new ArrayList<Itemset>(candidates);

	for (Itemset temp : temps) {

	    for (int i = 0; i < temp.getSize(); i++) {

		Itemset temp2 = new Itemset();
		temp2.setSize(temp.getSize() - 1);
		temp2.setItems(new ArrayList<Item>(temp.getItems()));
		temp2.getItems().remove(i);

		if (!kFrequents.contains(temp2)) {
		    candidates.remove(temp);
		}
	    }
	}

	return candidates;
    }

    public List<Itemset> generateCandidates(List<Itemset> kFrequents) {

	List<Itemset> candidates = new ArrayList<Itemset>();

	for (int i = 0; i < kFrequents.size(); i++) {
	    for (int j = i + 1; j < kFrequents.size(); j++) {
		if (samePrefix(kFrequents.get(i), kFrequents.get(j))) {
		    Itemset itemset = merge(kFrequents.get(i),
			    kFrequents.get(j));
		    if (!candidates.contains(itemset)) {
			candidates.add(itemset);
		    }
		}
	    }
	}

	return candidates;
    }

    public boolean samePrefix(Itemset set1, Itemset set2) {

	List<Item> items1Original = set1.getItems();
	List<Item> items2Original = set2.getItems();

	List<Item> items1 = new ArrayList<Item>(items1Original);
	List<Item> items2 = new ArrayList<Item>(items2Original);

	int lastIndex1 = items1.size() - 1;
	int lastIndex2 = items2.size() - 1;

	// avoid same attribute with multiple values in the same itemset
	if (items1.get(lastIndex1).name.equals(items2.get(lastIndex2).name))
	    return false;

	items1.remove(lastIndex1);
	items2.remove(lastIndex2);

	if (items1.equals(items2)) {
	    return true;
	}
	return false;
    }

    public Itemset merge(Itemset set1, Itemset set2) {
	Itemset itemset = new Itemset();
	itemset.setSize(set1.getSize() + 1);
	itemset.setItems(new ArrayList<Item>(set1.getItems()));

	int lastIndex = set2.getItems().size() - 1;
	itemset.getItems().add(set2.getItems().get(lastIndex));

	return itemset;
    }

    public List<Itemset> frequentItemsetGeneration() {

	List<Itemset> finalFrequent = new ArrayList<Itemset>();

	// find all frequent 1-itemset	
	List<Itemset> kItemsetsFrequents = findFrequent1Itemsets();
	System.out.println("K = 1 Size = " + kItemsetsFrequents.size() );
	
	//print( kItemsetsFrequents );
	
	finalFrequent.addAll(kItemsetsFrequents);

	for (int k = 2;; k++) {
	
	    List<Itemset> candidates = aprioriGen(kItemsetsFrequents);

	    // find frequent itemset
	    List<Itemset> newFrequents = findFrequentKItemsets(candidates);

	    System.out.println("K = " + k + " Size = " + newFrequents.size());
	    
	    //print( newFrequents );

	    // add candidates into final set F
	    if (newFrequents.isEmpty())
		break;

	    finalFrequent.addAll(newFrequents);

	    kItemsetsFrequents.clear();
	    kItemsetsFrequents.addAll(newFrequents);
	}
	
	return finalFrequent;

    }

    public void print(List<Itemset> itemsets) {
	for (int i = 0; i < itemsets.size(); i++) {
	    System.out.println((i + 1) + ": " + itemsets.get(i));
	}
    }

}

