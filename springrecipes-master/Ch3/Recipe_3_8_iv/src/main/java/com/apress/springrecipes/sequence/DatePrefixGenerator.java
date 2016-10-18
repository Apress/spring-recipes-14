package com.apress.springrecipes.sequence;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date; 

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary;

@Component
@Primary
public class DatePrefixGenerator implements PrefixGenerator {

    public String getPrefix() {
	DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(new Date());
    }
}
