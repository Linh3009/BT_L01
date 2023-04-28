package service;

import io.ReadAndWrite;
import model.Level;
import model.Person;
import model.Student;
import validation.Validate;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ManagerStudent {
    Scanner scanner = new Scanner(System.in);
    public File fileStudent = new File("src\\file_txt\\fileStudent.txt");
    public ReadAndWrite<Student> studentReadAndWrite = new ReadAndWrite<>();
    ArrayList<Student> students;
    Validate validate = new Validate();

    public ManagerStudent() {
        if (!fileStudent.exists()) {
            try {
                fileStudent.createNewFile();
            } catch (IOException e) {
                System.err.println("Không thể tạo tệp: " + e.getMessage());
            }
        }
        int idCount = Person.loadIdCount("src/file_txt/idCount.txt");
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
        if (!Validate.checkName(name)) {
            System.err.println("Tên không được trống và < " + Validate.MAX_NAME_LENGTH + " ký tự");
            return inputName();
        }
        return name;
    }

    public LocalDate inputBirthDate() {
        System.out.println("Nhập ngày sinh (theo định dạng dd/mm/yyyy): ");
        boolean flag = true;
        LocalDate birthDate = null;

        while (flag) {
            try {
                birthDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (!Validate.checkDate(birthDate)) {
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
            if (!Validate.checkAddress(address)) {
                System.err.println("Địa chỉ không được trống và < " + Validate.MAX_ADDRESS_LENGTH + " ký tự");
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
                if (Validate.checkHeight(height)) {
                    flag = true;
                } else {
                    System.err.println("Chiều cao không hợp lệ! Phải trong khoảng từ " + Validate.MIN_HEIGHT + " đến " + Validate.MAX_HEIGHT + " cm.");
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
                if (Validate.checkWeight(weight)) {
                    flag = true;
                } else {
                    System.err.println("Cân nặng không hợp lệ! Phải trongg khoảng từ " + Validate.MIN_WEIGHT + " đến " + Validate.MAX_WEIGHT + " kg.");
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
        for (Student student : students) {
            String currentStudentId = student.getStudentId();
            if (currentStudentId != null && currentStudentId.equals(studentId)) {
                return randomStudentId();
            }
        }
        if (Validate.checkStudentId(studentId)) {
            return studentId;
        } else {
            return randomStudentId();
        }
    }


    public String inputSchool() {
        System.out.println("Nhập tên trường: ");
        boolean flag = false;
        String school = "";
        while (!flag) {
            school = scanner.nextLine();
            if (Validate.checkSchool(school)) {
                flag = true;
            } else {
                System.err.println("Trường học không được trống và < " + Validate.MAX_SCHOOL_LENGTH + " ký tự");
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
                if (Validate.checkStartYear(startYear)) {
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
                if (Validate.checkGpa(gpa)) {
                    flag = true;
                } else {
                    System.err.println("Điểm không hợp lệ! Phải trong khoảng từ " + Validate.MIN_GPA + " đến " + Validate.MAX_GPA);
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
        Person.saveIdCount(Person.idCount, "src/file_txt/idCount.txt");
        students.add(student);
        studentReadAndWrite.write(fileStudent, students);
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
        int id;
        boolean flag = false;
        while (!flag) {
            try {
                id = Integer.parseInt(scanner.nextLine());
                int index = -1;
                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getId() == id) {
                        System.out.println("Sinh viên cần cập nhật: " + students.get(i));
                        System.out.println("Nhập tin mới cho sinh viên:");
                        System.out.println("1.Tên");
                        System.out.println("2.Ngày sinh");
                        System.out.println("3.Địa chỉ");
                        System.out.println("4.Chiều cao");
                        System.out.println("5.Cân nặng");
                        System.out.println("6.Trường");
                        System.out.println("7.Năm bắt đầu đại học");
                        System.out.println("8.Gpa");
                        System.out.println("9.Hủy cập nhật");
                        System.out.println("---------------------");
                        System.out.println("Chọn thông tin bạn muốn cập nhật:");

                        int choice = validate.inputChoice(1, 9);
                        switch (choice) {
                            case 1:
                                String name = inputName();
                                students.get(i).setName(name);
                                break;
                            case 2:
                                LocalDate birthDate = inputBirthDate();
                                students.get(i).setBirthDate(birthDate);
                                break;
                            case 3:
                                String address = inputAddress();
                                students.get(i).setAddress(address);
                                break;
                            case 4:
                                double height = inputHeight();
                                students.get(i).setHeight(height);
                                break;
                            case 5:
                                double weight = inputWeight();
                                students.get(i).setWeight(weight);
                                break;
                            case 6:
                                String school = inputSchool();
                                students.get(i).setSchool(school);
                                break;
                            case 7:
                                int collegeStartYear = inputStartYear();
                                students.get(i).setCollegeStartYear(collegeStartYear);
                                break;
                            case 8:
                                double gpa = inputGpa();
                                students.get(i).setGpa(gpa);
                                break;
                            case 9:
                                return;
                        }
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
        int idCount = Person.loadIdCount("src/file_txt/idCount.txt");
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
                                Person.saveIdCount(idCount, "src/file_txt/idCount.txt");
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

    public void displayLevelSort() {
        Map<Level, Integer> levelCounts = new HashMap<>();
        int allStudents = students.size();

        for (Student student : students) {
            Level level = student.getLevel();
            levelCounts.put(level, levelCounts.getOrDefault(level, 0) + 1);
        }
        System.out.println("Danh sách học lực của các sinh viên theo % từ cao xuống thấp: ");
        List<Map.Entry<Level, Integer>> sortedLevel = new ArrayList<>(levelCounts.entrySet());
        sortedLevel.sort(Collections.reverseOrder(Comparator.comparingDouble(entry -> (entry.getValue() * 100.0 / allStudents))));
        for (Map.Entry<Level, Integer> level : sortedLevel) {
            double percentage = level.getValue() * 100 / allStudents;
            System.out.println("- " + level.getKey().getLevelName() + ": " + String.format("%.2f%%", percentage));
        }
    }

    public void showGpa() {
        Map<Double, Integer> gpaCounts = new HashMap<>();
        int totalStudents = students.size();

        for (Student student : students) {
            double gpa = student.getGpa();
            gpaCounts.put(gpa, gpaCounts.getOrDefault(gpa, 0) + 1);
        }

        System.out.println("Thống kê GPA (% trên tổng số sinh viên):");
        List<Map.Entry<Double, Integer>> sortedEntries = new ArrayList<>(gpaCounts.entrySet());
        sortedEntries.sort(Comparator.comparingDouble(Map.Entry::getKey));
        for (Map.Entry<Double, Integer> entry : sortedEntries) {
            double gpa = entry.getKey();
            int count = entry.getValue();
            double percentage = count * 100.0 / totalStudents;
            System.out.println("- " + gpa + ": " + percentage + "%");
        }
    }

    public void showStudentByLevel() {
        while (true) {
            System.out.println("Nhập học lực (1 = Kém, 2 = Yếu, 3 = Trung bình, 4 = Khá, 5 = Giỏi, 6 = Xuất sắc, 7 = Thoát):");
            int inputLevel = validate.inputChoice(1, 7);
            Level selectedLevel = null;
            switch (inputLevel) {
                case 1:
                    selectedLevel = Level.POOR;
                    break;
                case 2:
                    selectedLevel = Level.WEAK;
                    break;
                case 3:
                    selectedLevel = Level.AVERAGE;
                    break;
                case 4:
                    selectedLevel = Level.GOOD;
                    break;
                case 5:
                    selectedLevel = Level.VERYGOOD;
                    break;
                case 6:
                    selectedLevel = Level.EXCELLENT;
                    break;
                case 7:
                    return;
            }

            List<Student> filteredStudents = new ArrayList<>();
            for (Student student : students) {
                if (student.getLevel() == selectedLevel) {
                    filteredStudents.add(student);
                }
            }

            if (filteredStudents.isEmpty()) {
                System.out.println("Không thấy sinh viên có học lực " + selectedLevel.getLevelName());
                System.out.println("--------------------------------");
            } else {
                System.out.println("Sinh viên có học lực " + selectedLevel.getLevelName() + ":");
                for (Student student : filteredStudents) {
                    System.out.println("- " + student.getName() + " (ID: " + student.getId() + ")");
                }
                System.out.println("--------------------------------");
            }
        }
    }
}
