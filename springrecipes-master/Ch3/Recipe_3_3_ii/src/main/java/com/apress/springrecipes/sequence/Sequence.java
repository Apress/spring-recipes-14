package com.apress.springrecipes.sequence;

public class Sequence {

    private String id;
    private String prefix;
    private String suffix;

    public Sequence() {}
    public Sequence(String id, String prefix, String suffix) { 
	this.id = id;
	this.prefix = prefix;
	this.suffix = suffix;
    }

    public void setId(String id) { 
	this.id = id;
    }
    
    public String getId() { 
	return id;
    }    

    public void setPrefix(String prefix) { 
	this.prefix = prefix;
    }
    
    public String getPrefix() { 
	return prefix;
    }    

    public void setSuffix(String suffix) { 
	this.suffix = suffix;
    }
    
    public String getSuffix() { 
	return suffix;
    }    
    
}
