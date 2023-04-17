package validation;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Scanner;

public class StudentValidate {
    Scanner scanner = new Scanner(System.in);
    public static final int MAX_NAME_LENGTH = 100;
    public static final int MAX_ADDRESS_LENGTH = 300;
    public static final LocalDate Min_YEAR = LocalDate.of(1900, 1, 1);
    public static final LocalDate MAX_YEAR = LocalDate.now().withDayOfYear(1).plusYears(1).minusDays(1);
    public static final double MIN_HEIGHT = 50;
    public static final double MAX_HEIGHT = 300;
    public static final double MIN_WEIGHT = 5;
    public static final double MAX_WEIGHT = 1000;
    public static final int STUDENT_ID_LENGTH = 10;
    private static final String STUDENT_ID_REGEX = "^OCT[0-9]{7}$";

    public static final int MAX_SCHOOL_LENGTH = 200;
    public static final int MAX_COLLEGE_START_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    public static final double MIN_GPA = 0.0;
    public static final double MAX_GPA = 10.0;

    public static boolean checkName(String name){
        return name != null && !name.isEmpty() && name.length() <= MAX_NAME_LENGTH;
    }

    public static boolean checkAddress(String address){
        return address != null && !address.isEmpty() && address.length() <= MAX_ADDRESS_LENGTH;
    }

    public static boolean checkDate(LocalDate birthDate){
        return birthDate.isAfter(Min_YEAR) && birthDate.isBefore(MAX_YEAR);
    }

    public static boolean checkHeight(double height){
        return height >= MIN_HEIGHT && height <= MAX_HEIGHT;
    }

    public static boolean checkWeight(double weight){
        return weight >= MIN_WEIGHT && weight <= MAX_WEIGHT;
    }

    public static boolean checkStudentId(String studentId){
        return studentId.matches(STUDENT_ID_REGEX) && studentId.length() == STUDENT_ID_LENGTH;
    }

    public static boolean checkSchool(String school){
        return school != null && !school.isEmpty() && school.length() <= MAX_SCHOOL_LENGTH;
    }

    public static boolean checkStartYear(int year){
        String yearString = String.valueOf(year);
       return yearString.length() == 4 && year >= 1900 && year <= MAX_COLLEGE_START_YEAR;
    }

    public static boolean checkGpa(double gpa){
        return gpa >= MIN_GPA && gpa <= MAX_GPA;
    }

    public int inputChoice(int min, int max){
        while (true){
            try {
                int inputNumber = Integer.parseInt(scanner.nextLine());
                if (inputNumber >= min && inputNumber <= max){
                    return inputNumber;
                } else {
                    System.err.println("Nhập số từ " + min + " đến " + max + "! Xin nhập lại:");
                }
            } catch (NumberFormatException e){
                System.err.println("Sai định dạng! Xin nhập lại:");
            }
        }
    }
}
