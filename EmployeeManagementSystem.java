import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementSystem {
    // Employee class defined inside the main class
    static class Employee {
        private int employeeID;
        private String name;
        private double salary;
        private String department;

        public Employee(int employeeID, String name, double salary, String department) {
            this.employeeID = employeeID;
            this.name = name;
            this.salary = salary;
            this.department = department;
        }

        public int getEmployeeID() {
            return employeeID;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        public String getDepartment() {
            return department;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public double getNetSalary() {
            return salary - (salary * 0.10); // 10% tax deduction
        }

        @Override
        public String toString() {
            return "EmployeeID: " + employeeID +
                   ", Name: " + name +
                   ", Gross Salary: " + salary +
                   ", Net Salary: " + getNetSalary() +
                   ", Department: " + department;
        }
    }

    // Employee list and scanner
    private static final List<Employee> employeeList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("\n--- Employee Management System ---");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> addEmployee();
                    case 2 -> viewEmployees();
                    case 3 -> updateEmployee();
                    case 4 -> deleteEmployee();
                    case 5 -> {
                        System.out.println("Exiting system. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numeric values only.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }

    private static void addEmployee() {
        try {
            System.out.print("Enter Employee ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter Department: ");
            String department = scanner.nextLine();

            employeeList.add(new Employee(id, name, salary, department));
            System.out.println("Employee added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Try again.");
            scanner.nextLine();
        }
    }

    private static void viewEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("\n--- Employee List ---");
            for (Employee emp : employeeList) {
                System.out.println(emp);
            }
        }
    }

    private static void updateEmployee() {
        try {
            System.out.print("Enter Employee ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Employee emp = findEmployeeById(id);
            if (emp == null) {
                System.out.println("Employee with ID " + id + " not found.");
                return;
            }

            System.out.print("Enter new Salary: ");
            double newSalary = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter new Department: ");
            String newDept = scanner.nextLine();

            emp.setSalary(newSalary);
            emp.setDepartment(newDept);
            System.out.println("Employee updated successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine();
        }
    }

    private static void deleteEmployee() {
        try {
            System.out.print("Enter Employee ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Employee emp = findEmployeeById(id);
            if (emp == null) {
                System.out.println("Employee with ID " + id + " not found.");
                return;
            }

            employeeList.remove(emp);
            System.out.println("Employee deleted successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine();
        }
    }

    private static Employee findEmployeeById(int id) {
        for (Employee emp : employeeList) {
            if (emp.getEmployeeID() == id) {
                return emp;
            }
        }
        return null;
    }
}
