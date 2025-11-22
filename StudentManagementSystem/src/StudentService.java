// StudentService.java
import java.util.List;

public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student addStudent(String name, int age, String grade, String email) {
        if (name == null || name.isBlank()) {
            System.out.println("Name cannot be empty.");
            return null;
        }
        if (age <= 0) {
            System.out.println("Age must be positive.");
            return null;
        }
        // Simple email check
        if (!email.contains("@")) {
            System.out.println("Invalid email format.");
            return null;
        }
        return repository.addStudent(name, age, grade, email);
    }

    public List<Student> getAllStudents() {
        return repository.getAllStudents();
    }

    public Student findStudent(int id) {
        return repository.findById(id);
    }

    public boolean deleteStudent(int id) {
        return repository.deleteById(id);
    }

    public boolean updateStudent(int id, String name, int age, String grade, String email) {
        Student s = repository.findById(id);
        if (s == null) {
            return false;
        }
        if (name != null && !name.isBlank()) s.setName(name);
        if (age > 0) s.setAge(age);
        if (grade != null && !grade.isBlank()) s.setGrade(grade);
        if (email != null && !email.isBlank()) s.setEmail(email);
        return true;
    }
}
