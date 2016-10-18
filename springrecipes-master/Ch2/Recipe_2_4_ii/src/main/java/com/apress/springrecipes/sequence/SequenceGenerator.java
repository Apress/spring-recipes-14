package com.apress.springrecipes.sequence;
import java.util.Set;

public class SequenceGenerator {
    private String prefix;
    private Set<Object> suffixes;
    private int initial;
    private int counter;
    public SequenceGenerator() {}
    public SequenceGenerator(String prefix, Set<Object> suffixes, int initial) {
        this.prefix = prefix;
        this.suffixes = suffixes;
        this.initial = initial;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffixes(Set<Object> suffixes) {
        this.suffixes = suffixes;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }
    public synchronized String getSequence() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(prefix);
        buffer.append(initial + counter++);
	for (Object suffix : suffixes) {
            buffer.append("-");
            buffer.append(suffix);
	}
        return buffer.toString();
    }
}
