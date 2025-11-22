// ConsoleUI.java
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final StudentService service;
    private final Scanner scanner;

    public ConsoleUI(StudentService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addStudentFlow();
                case 2 -> viewAllStudentsFlow();
                case 3 -> searchStudentFlow();
                case 4 -> updateStudentFlow();
                case 5 -> deleteStudentFlow();
                case 6 -> {
                    System.out.println("Exiting... Bye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("==== Student Management System ====");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student by ID");
        System.out.println("5. Delete Student by ID");
        System.out.println("6. Exit");
    }

    private void addStudentFlow() {
        System.out.println("--- Add Student ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();

        int age = readInt("Age: ");

        System.out.print("Grade/Class: ");
        String grade = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        var s = service.addStudent(name, age, grade, email);
        if (s != null) {
            System.out.println("Student added successfully with ID " + s.getId());
        }
    }

    private void viewAllStudentsFlow() {
        System.out.println("--- All Students ---");
        List<Student> all = service.getAllStudents();
        if (all.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : all) {
                System.out.println(s);
            }
        }
    }

    private void searchStudentFlow() {
        System.out.println("--- Search Student ---");
        int id = readInt("Enter student ID: ");
        Student s = service.findStudent(id);
        if (s == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Student details:");
            System.out.println(s);
        }
    }

    private void updateStudentFlow() {
        System.out.println("--- Update Student ---");
        int id = readInt("Enter student ID: ");
        Student s = service.findStudent(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Leave field empty to keep old value.");
        System.out.print("New name (" + s.getName() + "): ");
        String name = scanner.nextLine();

        String ageInput;
        int age = -1;
        System.out.print("New age (" + s.getAge() + "): ");
        ageInput = scanner.nextLine();
        if (!ageInput.isBlank()) {
            try {
                age = Integer.parseInt(ageInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Keeping old value.");
                age = -1;
            }
        }

        System.out.print("New grade (" + s.getGrade() + "): ");
        String grade = scanner.nextLine();

        System.out.print("New email (" + s.getEmail() + "): ");
        String email = scanner.nextLine();

        boolean updated = service.updateStudent(id, 
                name.isBlank() ? null : name,
                age,
                grade.isBlank() ? null : grade,
                email.isBlank() ? null : email);

        if (updated) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Update failed.");
        }
    }

    private void deleteStudentFlow() {
        System.out.println("--- Delete Student ---");
        int id = readInt("Enter student ID: ");
        boolean deleted = service.deleteStudent(id);
        if (deleted) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
