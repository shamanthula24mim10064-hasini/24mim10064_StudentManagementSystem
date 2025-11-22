// StudentRepository.java
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final List<Student> students = new ArrayList<>();
    private int nextId = 1; // auto-increment ID

    public Student addStudent(String name, int age, String grade, String email) {
        Student s = new Student(nextId, name, age, grade, email);
        students.add(s);
        nextId++;
        return s;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public boolean deleteById(int id) {
        Student s = findById(id);
        if (s != null) {
            students.remove(s);
            return true;
        }
        return false;
    }
}
