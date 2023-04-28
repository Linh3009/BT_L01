import io.ReadAndWrite;
import model.Student;
import service.ManagerStudent;
import validation.Validate;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        ManagerStudent managerStudent = new ManagerStudent();
        Validate validate = new Validate();
        File fileStudent = new File("src\\file_txt\\fileStudent.txt");
        ReadAndWrite<Student> studentReadAndWrite = new ReadAndWrite<>();
        studentReadAndWrite.read(fileStudent);
        Menu menu = new Menu();

        while (true){
            menu.menu();
            int choice = validate.inputChoice(1,9);
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
                    managerStudent.displayLevelSort();
                    break;
                case 7:
                    managerStudent.showGpa();
                    break;
                case 8:
                    managerStudent.showStudentByLevel();
                    break;
                case 9:
                    return;
            }
        }
    }
}
