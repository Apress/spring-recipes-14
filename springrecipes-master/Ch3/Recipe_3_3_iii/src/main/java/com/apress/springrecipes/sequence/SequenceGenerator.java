package com.apress.springrecipes.sequence;
import org.springframework.beans.factory.annotation.Autowired;

public class SequenceGenerator {
    @Autowired
    private PrefixGenerator[] prefixGenerators;
    private String suffix;
    private int initial;
    private int counter;
    public SequenceGenerator() {}
    public SequenceGenerator(PrefixGenerator[] prefixGenerators, String suffix, int initial) {
        this.prefixGenerators = prefixGenerators;
        this.suffix = suffix;
        this.initial = initial;
    }
    public void setPrefixGenerator(PrefixGenerator[] prefixGenerators) {
        this.prefixGenerators = prefixGenerators;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }
    public synchronized String getSequence() {
        StringBuffer buffer = new StringBuffer();
        for (PrefixGenerator prefix : prefixGenerators) {
            buffer.append(prefix.getPrefix());
            buffer.append("-");
        }
        buffer.append(initial + counter++);
        buffer.append(suffix);
        return buffer.toString();
    }
}
