package io;

import java.io.*;
import java.util.ArrayList;

public class ReadAndWrite<E> {
    public ArrayList<E> read(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (ArrayList<E>) objectInputStream.readObject();
        } catch (EOFException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void write(File file, ArrayList<E> list) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
