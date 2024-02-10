import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    private int id;
    private String name;
    private double hourlyRate;
    private double taxRate;

    public Employee(int id, String name, double hourlyRate, double taxRate) {
        this.id = id;
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.taxRate = taxRate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getTaxRate() {
        return taxRate;
    }
}

class Attendance {
    private int employeeId;
    private int hoursWorked;

    public Attendance(int employeeId, int hoursWorked) {
        this.employeeId = employeeId;
        this.hoursWorked = hoursWorked;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }
}

class Payroll {
    private ArrayList<Employee> employees;
    private ArrayList<Attendance> attendanceRecords;

    public Payroll() {
        employees = new ArrayList<>();
        attendanceRecords = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void recordAttendance(Attendance attendance) {
        attendanceRecords.add(attendance);
    }

    public void processPayroll() {
        for (Employee employee : employees) {
            double totalHoursWorked = 0;
            for (Attendance attendance : attendanceRecords) {
                if (attendance.getEmployeeId() == employee.getId()) {
                    totalHoursWorked += attendance.getHoursWorked();
                }
            }

            double grossSalary = totalHoursWorked * employee.getHourlyRate();
            double taxDeduction = grossSalary * (employee.getTaxRate() / 100);
            double netSalary = grossSalary - taxDeduction;

            // Generate Payslip
            System.out.println("Employee ID: " + employee.getId());
            System.out.println("Employee Name: " + employee.getName());
            System.out.println("Gross Salary: " + grossSalary);
            System.out.println("Tax Deduction: " + taxDeduction);
            System.out.println("Net Salary: " + netSalary);
            System.out.println("--------------------------------------");
        }
    }
}

public class EmployeePayrollSystem {
    public static void main(String[] args) {
        // Example Usage
        Employee employee1 = new Employee(1, "John Doe", 15.0, 10.0);
        Employee employee2 = new Employee(2, "Jane Smith", 20.0, 15.0);

        Payroll payroll = new Payroll();
        payroll.addEmployee(employee1);
        payroll.addEmployee(employee2);

        Attendance attendance1 = new Attendance(1, 40);
        Attendance attendance2 = new Attendance(2, 30);

        payroll.recordAttendance(attendance1);
        payroll.recordAttendance(attendance2);

        payroll.processPayroll();
    }
}
 
    