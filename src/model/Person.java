package model;

import java.io.*;
import java.time.LocalDate;

public class Person {
    public static int idCount = 1;
    private Integer id;
    private String name;
    private LocalDate birthDate;
    private String address;
    private Double height;
    private Double weight;
    public Person() {
    }
    public Person(String name, LocalDate birthDate, String address, Double height, Double weight) {
        this.id = idCount;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.height = height;
        this.weight = weight;
    }

    public static void saveIdCount(Integer idCount, String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(idCount);
            objectOut.close();
        } catch (IOException e) {
            System.err.println("Không thể ghi tệp: " + e.getMessage());
        }
    }

    public static int loadIdCount(String filePath) {
        int idCount = 1;
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            idCount = (int) objectIn.readObject();
            objectIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Không thể đọc tệp: " + e.getMessage());
        }
        return idCount;
    }


    public Integer getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Id=" + id +
                ", Tên: '" + name + '\'' +
                ", Ngày sinh: '" + birthDate + '\'' +
                ", Địa chỉ: '" + address + '\'' +
                ", Chiều cao: '" + height + '\'' +
                ", Cân nặng: '" + weight + '\'' ;
    }
}
