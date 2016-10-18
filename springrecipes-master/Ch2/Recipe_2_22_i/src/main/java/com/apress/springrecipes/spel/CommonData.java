package com.apress.springrecipes.spel;

import java.util.Properties;

public class CommonData { 

    private Properties commonProperties;
    private String userOS;
    private String userRegion;
    private double randomNumber; 
    private String email;

    public Properties getCommonProperties() { 
	return commonProperties;
    }

    public String getUserOS() { 
	return userOS;
    }

    public String getUserRegion() { 
	return userRegion;
    }
    
    public double getRandomNumber() { 
	return randomNumber;
    }

    public String getEmail() { 
	return email;
    }

    public void setCommonProperties(Properties commonProperties) { 
	this.commonProperties = commonProperties; 
    }

    public void setUserOS(String userOS) { 
	this.userOS = userOS;
    }

    public void setUserRegion(String userRegion) { 
	this.userRegion = userRegion;
    }

    public void setRandomNumber(double randomNumber) { 
	this.randomNumber = randomNumber;
    }

    public void setEmail(String email) { 
	this.email = email;
    }

}
