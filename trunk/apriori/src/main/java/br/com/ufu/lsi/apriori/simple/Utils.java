package br.com.ufu.lsi.apriori.simple;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Utils {

    private static final Logger LOG = Logger.getLogger(Utils.class);

    public static List<Transaction> readInput(String path) {

	FileInputStream fis = null;

	try {
	    fis = new FileInputStream(path);
	} catch (FileNotFoundException e) {
	    LOG.error(e.getMessage());
	    throw new IllegalArgumentException(e);
	}

	List<Transaction> transactions = new ArrayList<Transaction>();

	Scanner sc = new Scanner(fis);

	while (sc.hasNextLine()) {
	    Transaction transaction = new Transaction();
	    List<Item> items = new ArrayList<Item>();
	    transaction.setItems(items);

	    String transactionInput = sc.nextLine();
	    String[] itemsInput = transactionInput.split(",");

	    for (int i = 0; i < itemsInput.length; i++) {
		Item item = new Item();

		String[] values = itemsInput[i].split("\\*");
		item.setName(values[0]);
		if (values.length > 1) {
		    if ("y".equals(values[1]))
			item.setValue(true);
		    else
			item.setValue(false);
		}

		items.add(item);
	    }

	    if (!transaction.getItems().isEmpty()) {
		transactions.add(transaction);
	    }
	}
	sc.close();

	return transactions;
    }

    public static Itemset extractComplementarSubset(Itemset total, Itemset part) {

	Itemset complementar = new Itemset();
	for (Item item : total.getItems()) {
	    if (!part.getItems().contains(item)) {
		complementar.getItems().add(item);
	    }
	}
	return complementar;
    }

}
