package com.apress.springrecipes.spel;

import java.util.Properties;

import org.springframework.stereotype.Component; 
import org.springframework.beans.factory.annotation.Value;

@Component
public class CommonData { 

    @Value("#{systemProperties}")
    private Properties commonProperties;
    @Value("#{systemProperties['os.name']?:'unknown OS'}")
    private String userOS;
    @Value("#{systemProperties['user.region']?:'unknown region'}")
    private String userRegion;
    @Value("#{  T(java.lang.Math).random() * 100.0 }")
    private double randomNumber;
    @Value("#{emailUtilities.email}")
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
