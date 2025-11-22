// Main.java
public class Main {
    public static void main(String[] args) {
        StudentRepository repository = new StudentRepository();
        StudentService service = new StudentService(repository);
        ConsoleUI ui = new ConsoleUI(service);
        ui.start();
    }
}

