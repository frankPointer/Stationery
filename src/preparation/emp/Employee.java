package preparation.emp;

import lombok.ToString;

@ToString
public class Employee {
//    private int id;
    private int age;
    private String name;
    private double salary;

    public Employee() {

    }

    public Employee(String name, int age, double salary) {
        this.age = age;
        this.name = name;
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

