package service;

import io.ReadAndWrite;
import model.Level;
import model.Person;
import model.Student;
import validation.StudentValidate;

import javax.swing.event.ListSelectionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ManagerStudent {
    Scanner scanner = new Scanner(System.in);
    File fileStudent = new File("D:\\BT L0-1 ArrayList\\src\\file_txt\\fileStudent.txt");
    ReadAndWrite<Student>studentReadAndWrite = new ReadAndWrite<>();
    ArrayList<Student> students = studentReadAndWrite.read(fileStudent);

    public ManagerStudent() {
        if (!fileStudent.exists()){
            try {
                fileStudent.createNewFile();
            } catch (IOException e) {
                System.err.println("Không thể tạo tệp: " + e.getMessage());
            }
        }
        int idCount = Person.loadIdCount("D:/BT L0-1 ArrayList/src/file_txt/idCount.txt");
        Person.idCount = idCount;
        students = studentReadAndWrite.read(fileStudent);
    }

    public void show() {
        System.out.println();
        System.out.println("-------------- Danh sách sinh viên --------------");
        int counter = 0;
        if (students.isEmpty()) {
            System.out.println("Danh sách trống. Cần thêm mới!!!");
            System.out.println();
        } else {
//            for (int i = 0; i < students.size(); i++) {
//                System.out.println(students.get(i));
            for (Student student : students) {
                System.out.println(student);
                counter = students.size();
            }
            System.out.println();
            System.out.println("\tTổng số sinh viên: " + counter);
            System.out.println();
        }
    }

    public String inputName() {
        System.out.println("Nhập tên sinh viên: ");
        String name = scanner.nextLine();
        if (!StudentValidate.checkName(name)) {
            System.err.println("Tên không được trống và < " + StudentValidate.MAX_NAME_LENGTH + " ký tự");
            return inputName();
        }
        return name;
    }

    public LocalDate inputBirthDate() {
        System.out.println("Nhập ngày sinh (theo định dạng dd/mm/yyyy): ");
        LocalDate birthDate = null;
        boolean flag = true;
        while (flag) {
            try {
                birthDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (!StudentValidate.checkDate(birthDate)) {
                    System.err.println("Ngày sinh không hợp lệ! Hãy nhập lại.");
                } else {
                    flag = false;
                }
            } catch (DateTimeParseException e) {
                System.err.println("Định dạng ngày không đúng! Hãy nhập lại.");
            }
        }
        return birthDate;
    }


    public String inputAddress() {
        System.out.println("Nhập địa chỉ: ");
        boolean flag = true;
        String address = "";
        while (flag) {
            address = scanner.nextLine();
            if (!StudentValidate.checkAddress(address)) {
                System.err.println("Địa chỉ không được trống và < " + StudentValidate.MAX_ADDRESS_LENGTH + " ký tự");
            } else {
                flag = false;
            }
        }
        return address;
    }

    public double inputHeight() {
        System.out.println("Nhập chiều cao (cm): ");
        boolean flag = false;
        double height = 0;
        while (!flag) {
            try {
                height = Double.parseDouble(scanner.nextLine());
                if (StudentValidate.checkHeight(height)) {
                    flag = true;
                } else {
                    System.err.println("Chiều cao không hợp lệ! Phải trong khoảng từ " + StudentValidate.MIN_HEIGHT + " đến " + StudentValidate.MAX_HEIGHT + " cm.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Phải là số và không được rỗng! Mời nhập lại");
            }
        }
        return height;
    }

    public double inputWeight() {
        System.out.println("Nhập cân nặng (kg): ");
        boolean flag = false;
        double weight = 0;
        while (!flag) {
            try {
                weight = Double.parseDouble(scanner.nextLine());
                if (StudentValidate.checkWeight(weight)) {
                    flag = true;
                } else {
                    System.err.println("Cân nặng không hợp lệ! Phải trongg khoảng từ " + StudentValidate.MIN_WEIGHT + " đến " + StudentValidate.MAX_WEIGHT + " kg.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Phải là số và không được rỗng! Mời nhập lại");
            }
        }
        return weight;
    }

    public String randomStudentId() {
        String keyword = "OCT";
        int randomNumber = new Random().nextInt(1000000);
        String studentId = keyword + String.format("%07d", randomNumber);
        if (StudentValidate.checkStudentId(studentId)) {
            return studentId;
        } else return randomStudentId();
    }

    public String inputSchool() {
        System.out.println("Nhập tên trường: ");
        boolean flag = false;
        String school = "";
        while (!flag) {
            school = scanner.nextLine();
            if (StudentValidate.checkSchool(school)) {
                flag = true;
            } else {
                System.err.println("Trường học không được trống và < " + StudentValidate.MAX_SCHOOL_LENGTH + " ký tự");
            }
        }
        return school;
    }

    public int inputStartYear() {
        System.out.println("Nhập năm bắt đầu học đại học: ");
        boolean flag = false;
        int startYear = 0;
        while (!flag) {
            try {
                startYear = Integer.parseInt(scanner.nextLine());
                if (StudentValidate.checkStartYear(startYear)) {
                    flag = true;
                } else {
                    System.err.println("Năm nhập học phải có 4 số, >1900 và không được nhỏ hơn năm hiện tại");
                }
            } catch (NumberFormatException e) {
                System.err.println("Sai định dạng! Mời nhập lại");
            }
        }
        return startYear;
    }

    public double inputGpa() {
        System.out.println("Nhập điểm trung bình: ");
        boolean flag = false;
        double gpa = 0;
        while (!flag) {
            try {
                gpa = Double.parseDouble(scanner.nextLine());
                if (StudentValidate.checkGpa(gpa)) {
                    flag = true;
                } else {
                    System.err.println("Điểm không hợp lệ! Phải trong khoảng từ " + StudentValidate.MIN_GPA + " đến " + StudentValidate.MAX_GPA);
                }
            } catch (NumberFormatException e) {
                System.err.println("Sai định dạng! Mời nhập lại");
            }
        }
        return gpa;
    }

    public Student inputInfo() {
        String name = inputName();
        LocalDate birthDate = inputBirthDate();
        String address = inputAddress();
        double height = inputHeight();
        double weight = inputWeight();
        String studentId = randomStudentId();
        String school = inputSchool();
        int collegeStartYear = inputStartYear();
        double gpa = inputGpa();

        Student student = new Student(name, birthDate, address, height, weight, studentId, school, collegeStartYear, gpa);
        return student;
    }

    public void addStudent() {
        Student student = inputInfo();
        Person.idCount++;
        Person.saveIdCount(Person.idCount, "D:/BT L0-1 ArrayList/src/file_txt/idCount.txt");
        student.updateLevel();
        students.add(student);
        studentReadAndWrite.write(fileStudent,students);
        System.out.println("Thêm sinh viên thành công !");
        show();
    }

    public void searchStudentById() {
        if (students.isEmpty()) {
            System.err.println("Chưa có sinh viên trong danh sách!");
            System.out.println();
            return;
        }

        while (true) {
            System.out.println("Nhập id sinh viên bạn muốn tìm: ");
            try {
                int id = Integer.parseInt(scanner.nextLine());
                for (Student student : students) {
                    if (student.getId() == id) {
                        System.out.println(student);
                        return;
                    }
                }
                System.err.println("Không có sinh viên có id tương ứng");
            } catch (NumberFormatException e) {
                System.err.println("Id không hợp lệ!");
            }
        }
    }

    public void updateStudentById() {
        if (students.isEmpty()) {
            System.err.println("Chưa có sinh viên trong danh sách!");
            System.out.println();
            return;
        }
        System.out.println("Nhập id sinh viên bạn muốn cập nhật: ");
        int id = 0;
        boolean flag = false;
        while (!flag) {
            try {
                id = Integer.parseInt(scanner.nextLine());
                int index = -1;
                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getId() == id) {
                        System.out.println("Sinh viên cần cập nhật: " + students.get(i));
                        System.out.println("Nhập thông tin mới cho sinh viên:");
                        System.out.println("--------------");

                        String name = inputName();
                        LocalDate birthDate = inputBirthDate();
                        String address = inputAddress();
                        double height = inputHeight();
                        double weight = inputWeight();
                        String school = inputSchool();
                        int collegeStartYear = inputStartYear();
                        double gpa = inputGpa();

                        students.get(i).setName(name);
                        students.get(i).setBirthDate(birthDate);
                        students.get(i).setAddress(address);
                        students.get(i).setHeight(height);
                        students.get(i).setWeight(weight);
                        students.get(i).setSchool(school);
                        students.get(i).setCollegeStartYear(collegeStartYear);
                        students.get(i).setGpa(gpa);
                        students.get(i).updateLevel();
//                        System.out.println("Cập nhật thành công");
//                        System.out.println("Thông tin sinh viên sau khi cập nhật: " + students.get(i));
                        index = i;
                        break;
                    }
                }
                if (index == -1) {
                    System.err.println("Không tìm thấy sinh viên");

                } else {
                    studentReadAndWrite.write(fileStudent, students);
                    System.out.println("Cập nhật sinh viên thành công!");
                    System.out.println("Thông tin sinh viên sau khi cập nhật: ");
                    System.out.println("------------------------");
                    System.out.println(students.get(index));
                    System.out.println();
                }
                flag = true;
            } catch (NumberFormatException e) {
                System.err.println("Id sinh viên không hợp lệ!");
            }
        }
    }

    public void deleteStudentById() {
        if (students.isEmpty()) {
            System.err.println("Chưa có sinh viên trong danh sách!");
            System.out.println();
            return;
        }
        int idCount = Person.loadIdCount("D:/BT L0-1 ArrayList/src/file_txt/idCount.txt");
        int deletedCount = 0;
        while (deletedCount < students.size()) {
            System.out.println("Nhập mã sinh viên bạn muốn xóa (Nhập 0 để quay lại danh sách)");
            try {
                int id = Integer.parseInt(scanner.nextLine());
                if (id == 0) {
                    break;
                }
                boolean found = false;
                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getId() == id) {
                        found = true;
                        System.out.println("Bạn có chắc chắn muốn xóa sinh viên này? (y/n)");
                        while (true) {
                            String yn = scanner.nextLine();
                            if (yn.isEmpty()) {
                                System.err.println("Vui lòng nhập 'y' hoặc 'n'");
                            } else if (yn.equalsIgnoreCase("y")) {
                                students.remove(i);
                                for (int j = i; j < students.size(); j++) {
                                    students.get(j).setId(students.get(j).getId() - 1);
                                }
                                idCount--;
                                Person.saveIdCount(idCount, "D:/BT L0-1 ArrayList/src/file_txt/idCount.txt");
                                studentReadAndWrite.write(fileStudent, students);
                                System.out.println("Xóa sinh viên thành công");
                                deletedCount++;
                                break;
                            } else if (yn.equalsIgnoreCase("n")) {
                                System.err.println("Hủy xóa sinh viên");
                                break;
                            } else {
                                System.err.println("Vui lòng nhập 'y' hoặc 'n'");
                            }
                        }
                        break;
                    }
                }
                if (!found) {
                    System.err.println("Không tìm thấy sinh viên có mã số tương ứng");
                }
            } catch (NumberFormatException e) {
                System.err.println("Mã sinh viên không hợp lệ!");
            }
        }
    }

    public void displayLevelSort(List<Student> students){
        Map<Level, Integer> levelCounts = new HashMap<>();
        int allStudents = students.size();

        for (Student student : students) {
            Level level = student.getLevel();
            levelCounts.put(level, levelCounts.getOrDefault(level,0) +1);
        }
        System.out.println("Danh sách học lực của các sinh viên theo % từ cao xuống thấp: ");
        List<Map.Entry<Level,Integer>> sortedLevel = new ArrayList<>(levelCounts.entrySet());
        sortedLevel.sort(Collections.reverseOrder(Comparator.comparingDouble(entry -> (entry.getValue() * 100.0 / allStudents))));
        for (Map.Entry<Level , Integer> level : sortedLevel) {
            double percentage = level.getValue() * 100 / allStudents;
            System.out.println("- " + level.getKey().getLevelName() + ": " + String.format("%.2f%%", percentage));
        }
    }
}
