package br.com.ufu.lsi.apriori.test;

import java.util.List;

import org.junit.Test;

import br.com.ufu.lsi.apriori.simple.AprioriRunner;
import br.com.ufu.lsi.apriori.simple.Transaction;
import br.com.ufu.lsi.apriori.simple.Utils;

public class UtilsTest {
    
    //@Test
    public void readInput(){
	
	List<Transaction> transactions = Utils.readInput( "/home/fabiola/Desktop/Doutorado/DataMining/Trabalho-Apriori/input-without-blanks" );
	
	for( int i = 0; i<transactions.size(); i++ ){
	    System.out.println( (i+1) + ": " + transactions.get(i).getItems() );
	}
	
    }
    
    @Test
    public void apriori(){
	
	AprioriRunner.main();
    }

}
