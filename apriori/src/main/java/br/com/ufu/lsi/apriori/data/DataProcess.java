package br.com.ufu.lsi.apriori.data;

import java.util.Scanner;

public class DataProcess {

    public static final String SEPARATOR = "*";

    public static void main(String... args) {

	Scanner sc = new Scanner(System.in);

	while (sc.hasNextLine()) {
	    String itemset = sc.nextLine();
	    String[] items = itemset.split(",");
	    StringBuilder newItems = new StringBuilder();
	    for (int i = 0; i < items.length; i++) {

		if (!items[i].equals("?")) {
		    String newItem = items[i];

		    if (i == 1) {
			newItem = "handicappedInfants" + SEPARATOR + items[i];
		    } else if (i == 2) {
			newItem = "waterProjectCostSharing" + SEPARATOR
				+ items[i];
		    } else if (i == 3) {
			newItem = "adoptionOfTheBudgetResolution" + SEPARATOR
				+ items[i];
		    } else if (i == 4) {
			newItem = "physicianFeeFreeze" + SEPARATOR + items[i];
		    } else if (i == 5) {
			newItem = "elSalvadorAid" + SEPARATOR + items[i];
		    } else if (i == 6) {
			newItem = "religiousGroupsInSchools" + SEPARATOR
				+ items[i];
		    } else if (i == 7) {
			newItem = "antiSatelliteTestBan" + SEPARATOR + items[i];
		    } else if (i == 8) {
			newItem = "aidToNicaraguanContras" + SEPARATOR
				+ items[i];
		    } else if (i == 9) {
			newItem = "mxMissile" + SEPARATOR + items[i];
		    } else if (i == 10) {
			newItem = "immigration" + SEPARATOR + items[i];
		    } else if (i == 11) {
			newItem = "synfuelsCorporationCutback" + SEPARATOR
				+ items[i];
		    } else if (i == 12) {
			newItem = "educationSpending" + SEPARATOR + items[i];
		    } else if (i == 13) {
			newItem = "superfundRightToSue" + SEPARATOR + items[i];
		    } else if (i == 14) {
			newItem = "crime" + SEPARATOR + items[i];
		    } else if (i == 15) {
			newItem = "dutyFreeExports" + SEPARATOR + items[i];
		    } else if (i == 16) {
			newItem = "exportAdministrationActSouthAfrica"
				+ SEPARATOR + items[i];
		    }

		    newItems.append(newItem + ",");
		}

	    }

	    
	    int end = newItems.length()-1;
	    if( newItems.charAt(end) == ',' )
		newItems.deleteCharAt(end);
	    System.out.println(newItems.toString());

	}
	sc.close();
    }

}
