package com.apress.springrecipes.sequence;
import java.util.Map;
import java.util.Properties;

public class SequenceGenerator {
    private String prefix;
    private Properties suffixes;
    private int initial;
    private int counter;
    public SequenceGenerator() {}
    public SequenceGenerator(String prefix, Properties suffixes, int initial) {
        this.prefix = prefix;
        this.suffixes = suffixes;
        this.initial = initial;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffixes(Properties suffixes) {
        this.suffixes = suffixes;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }
    public synchronized String getSequence() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(prefix);
        buffer.append(initial + counter++);
        for (Map.Entry<Object, Object> entry : suffixes.entrySet()) {
            buffer.append("-");
            buffer.append(entry.getKey());
            buffer.append("@");
            buffer.append(entry.getValue());
        }
        return buffer.toString();
    }
}
