package com.apress.springrecipes.nosql;

import java.io.*;

/**
 * Created by marten on 29-09-14.
 */
public class SerializationUtils {

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(baos)) {
            out.writeObject(obj);
            return baos.toByteArray();
        } finally {
            baos.close();
        }
    }

    public static <T> T deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            return (T) in.readObject();
        }
    }

}
