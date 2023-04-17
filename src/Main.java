import io.ReadAndWrite;
import model.Student;
import service.ManagerStudent;
import validation.StudentValidate;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ManagerStudent managerStudent = new ManagerStudent();
        StudentValidate studentValidate = new StudentValidate();
        File fileStudent = new File("D:\\BT L0-1 ArrayList\\src\\file_txt\\fileStudent.txt");
        ReadAndWrite<Student> studentReadAndWrite = new ReadAndWrite<>();
        ArrayList<Student> students = studentReadAndWrite.read(fileStudent);
        Menu menu = new Menu();

        while (true){
            menu.menu();
            int choice = studentValidate.inputChoice(1,9);
            switch (choice){
                case 1:
                    managerStudent.show();
                    break;
                case 2:
                    managerStudent.addStudent();
                    break;
                case 3:
                    managerStudent.searchStudentById();
                    break;
                case 4:
                    managerStudent.updateStudentById();
                    break;
                case 5:
                    managerStudent.deleteStudentById();
                    break;
                case 6:
                    managerStudent.displayLevelSort(students);
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    return;
            }
        }
    }
}
