package br.com.ufu.lsi.apriori.simple;

import java.util.ArrayList;
import java.util.List;

public class Itemset implements Comparable<Itemset> {
    
    List<Item> items;
    
    Double support;
    
    int size;
    
    public Itemset(){
	this.items = new ArrayList<Item>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Double getSupport() {
        return support;
    }

    public void setSupport(Double support) {
        this.support = support;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public String toString(){
	return items.toString() + " " + support;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((items == null) ? 0 : items.hashCode());
	result = prime * result + size;
	result = prime * result + ((support == null) ? 0 : support.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Itemset other = (Itemset) obj;
	if (items == null) {
	    if (other.items != null)
		return false;
	} else if (!items.equals(other.items)){
	    return false;
	/*
	    for( Item i : items ){
		if( !other.items.contains(i) )
		    return false;
	    }*/
	    
	}
	if (size != other.size)
	    return false;
	/*if (support == null) {
	    if (other.support != null)
		return false;
	} else if (!support.equals(other.support))
	    return false;*/
	return true;
    }

    
    @Override
    public int compareTo(Itemset other){
	
	if( this.getSize() < other.getSize() )
	    return -1;
	if( this.getSize() > other.getSize() )
	    return 1;
	return 0;
    }

}
