package br.com.ufu.lsi.apriori.simple;

public class Item {

    String name;

    Boolean value;

    
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Boolean isValue() {
	return value;
    }

    public void setValue(Boolean value) {
	this.value = value;
    }
    
    public String toString(){
	if( value == null )
	    return name;
	
	return name + "=" + value;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((value == null) ? 0 : value.hashCode());
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
	Item other = (Item) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (value == null) {
	    if (other.value != null)
		return false;
	} else if (!value.equals(other.value))
	    return false;
	return true;
    }

}
