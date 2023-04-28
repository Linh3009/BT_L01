package model;

import java.io.*;
import java.time.LocalDate;

public class Student extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 123456789L;
    private String studentId;
    private String school;
    private Integer collegeStartYear;
    private Double gpa;
    private Level level;

    public Student() {
    }

    public Student(String name, LocalDate birthDate, String address, Double height, Double weight, String studentId, String school, Integer collegeStartYear, Double gpa) {
        super(name, birthDate, address, height, weight);
        this.studentId = studentId;
        this.school = school;
        this.collegeStartYear = collegeStartYear;
        this.gpa = gpa;
        levelAcademic();
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(super.getId());
        out.writeObject(super.getName());
        out.writeObject(super.getBirthDate());
        out.writeObject(super.getAddress());
        out.writeDouble(super.getHeight());
        out.writeDouble(super.getWeight());
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        super.setId(in.readInt());
        super.setName((String) in.readObject());
        this.setBirthDate((LocalDate)in.readObject());
        super.setAddress((String) in.readObject());
        super.setHeight(in.readDouble());
        super.setWeight(in.readDouble());
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getCollegeStartYear() {
        return collegeStartYear;
    }

    public void setCollegeStartYear(int collegeStartYear) {
        this.collegeStartYear = collegeStartYear;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
        levelAcademic();
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void levelAcademic(){
        if (gpa < 3){
            level = Level.POOR;
        } else if (gpa < 5) {
            level = Level.WEAK;
        } else if (gpa < 6.5) {
            level = Level.AVERAGE;
        } else if (gpa < 7.5) {
            level = Level.GOOD;
        } else if (gpa < 9) {
            level = Level.VERYGOOD;
        } else {
            level = Level.EXCELLENT;
        }
    }

    @Override
    public String toString() {
        return "Sinh viên {" +
                super.toString() +
                ", Mã sinh viên: '" + studentId + '\'' +
                ", Trường: '" + school + '\'' +
                ", Năm bắt đầu đại học: '" + collegeStartYear + '\'' +
                ", GPA: '" + gpa + '\'' +
                ", Học lực: '" + level.getLevelName() +
                "} " ;
    }
}
