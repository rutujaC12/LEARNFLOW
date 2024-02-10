import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String studentId;
    private String name;
    private Map<String, Integer> courses;
    private Map<String, String> grades;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.courses = new HashMap<>();
        this.grades = new HashMap<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getCourses() {
        return courses;
    }

    public Map<String, String> getGrades() {
        return grades;
    }

    public void registerCourse(String courseId) {
        courses.put(courseId, 0); // Initial grade is set to 0
    }

    public void updateGrade(String courseId, String grade) {
        if (courses.containsKey(courseId)) {
            grades.put(courseId, grade);
        } else {
            System.out.println("Course not registered.");
        }
    }
}

class StudentInformationSystem {
    private Map<String, Student> students;

    public StudentInformationSystem() {
        students = new HashMap<>();
    }

    public void addStudent(String studentId, String name) {
        students.put(studentId, new Student(studentId, name));
    }

    public Student getStudent(String studentId) {
        return students.get(studentId);
    }
}

public class StudentSystem {
    public static void main(String[] args) {
        StudentInformationSystem sis = new StudentInformationSystem();
        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            System.out.println("1. Add Student");
            System.out.println("2. Register Course");
            System.out.println("3. Update Grade");
            System.out.println("4. Generate Transcript");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.next();
                    System.out.print("Enter student name: ");
                    String studentName = scanner.next();
                    sis.addStudent(studentId, studentName);
                    System.out.println("Student added successfully.");
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    String regStudentId = scanner.next();
                    Student regStudent = sis.getStudent(regStudentId);
                    if (regStudent != null) {
                        System.out.print("Enter course ID to register: ");
                        String courseId = scanner.next();
                        regStudent.registerCourse(courseId);
                        System.out.println("Course registered successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    String updateStudentId = scanner.next();
                    Student updateStudent = sis.getStudent(updateStudentId);
                    if (updateStudent != null) {
                        System.out.print("Enter course ID: ");
                        String updateCourseId = scanner.next();
                        System.out.print("Enter grade: ");
                        String grade = scanner.next();
                        updateStudent.updateGrade(updateCourseId, grade);
                        System.out.println("Grade updated successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    String transcriptStudentId = scanner.next();
                    Student transcriptStudent = sis.getStudent(transcriptStudentId);
                    if (transcriptStudent != null) {
                        generateTranscript(transcriptStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void generateTranscript(Student student) {
        System.out.println("Transcript for Student: " + student.getName());
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Courses:");

        for (Map.Entry<String, Integer> entry : student.getCourses().entrySet()) {
            String courseId = entry.getKey();
            int grade = entry.getValue();
            String letterGrade = student.getGrades().getOrDefault(courseId, "Not Graded");
            System.out.println(courseId + ": " + letterGrade);
        }
    }
}