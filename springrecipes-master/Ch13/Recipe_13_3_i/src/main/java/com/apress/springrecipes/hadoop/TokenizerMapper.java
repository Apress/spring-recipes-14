package com.apress.springrecipes.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by marten on 15-10-14.
 */
public class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

    private static final Pattern WORD_BOUNDARY = Pattern.compile("\\s*\\b\\s*");
    private final static IntWritable ONE = new IntWritable(1);

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        for (String word : WORD_BOUNDARY.split(line)) {
            if (word == null || word.trim().length() == 0 ) {
                continue;
            }
            context.write(new Text(word.toLowerCase()), ONE);
        }
    }
}
