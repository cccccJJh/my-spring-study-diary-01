package stream;

import java.util.*;

public class Student {
    private String name;
    private int age;
    private double gpa;

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    // Getter
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getGpa() { return gpa; }

    @Override
    public String toString() {
        return name + "(" + age + "세, " + gpa + ")";
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("김철수", 22, 3.5),
                new Student("이영희", 20, 4.0),
                new Student("박민수", 21, 3.8),
                new Student("최지은", 20, 3.5)
        );

        // 이름순 (가나다순)
        List<Student> byName = students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .toList();
        System.out.println("이름순: " + byName);

        // 나이순 (어린 순)
        List<Student> byAge = students.stream()
                .sorted(Comparator.comparingInt(Student::getAge))
                .toList();
        System.out.println("나이순: " + byAge);

        // 학점순 (낮은 순)
        List<Student> byGpa = students.stream()
                .sorted(Comparator.comparingDouble(Student::getGpa))
                .toList();
        System.out.println("학점순: " + byGpa);
    }
}
