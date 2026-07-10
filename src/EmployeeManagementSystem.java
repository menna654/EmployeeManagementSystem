import java.util.ArrayList;
import java.util.Scanner;

class Employee {


    private int id;
    private String name;
    private double salary;
    private String department;


    public Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    @Override
    public String toString() {
        return "ID: " + id
                + " | Name: " + name
                + " | Salary: " + salary
                + " | Department: " + department;
    }
}


class EmployeeManager {


    private ArrayList<Employee> employees;


    public EmployeeManager() {
        employees = new ArrayList<>();
    }


    public boolean addEmployee(Employee employee) {


        if (findEmployeeById(employee.getId()) != null) {
            return false;
        }

        employees.add(employee);
        return true;
    }


    public void viewEmployees() {

        if (employees.isEmpty()) {
            System.out.println("\nNo employees found.");
            return;
        }

        System.out.println("\n========== Employees ==========");

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        System.out.println("================================");
    }


    public Employee findEmployeeById(int id) {

        for (Employee employee : employees) {

            if (employee.getId() == id) {
                return employee;
            }
        }

        return null;
    }

    public boolean updateEmployee(
            int id,
            String newName,
            double newSalary,
            String newDepartment
    ) {

        Employee employee = findEmployeeById(id);

        if (employee == null) {
            return false;
        }

        employee.setName(newName);
        employee.setSalary(newSalary);
        employee.setDepartment(newDepartment);

        return true;
    }

    public boolean deleteEmployee(int id) {

        Employee employee = findEmployeeById(id);

        if (employee == null) {
            return false;
        }

        employees.remove(employee);
        return true;
    }
}


public class EmployeeManagementSystem {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EmployeeManager manager = new EmployeeManager();

        int choice;

        do {

            System.out.println("\n================================");
            System.out.println("   Employee Management System");
            System.out.println("================================");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.println("================================");

            choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {

                case 1:
                    addEmployee(scanner, manager);
                    break;

                case 2:
                    manager.viewEmployees();
                    break;

                case 3:
                    updateEmployee(scanner, manager);
                    break;

                case 4:
                    deleteEmployee(scanner, manager);
                    break;

                case 5:
                    System.out.println("\nProgram closed.");
                    break;

                default:
                    System.out.println(
                            "\nInvalid choice. Enter a number from 1 to 5."
                    );
            }

        } while (choice != 5);

        scanner.close();
    }


    public static void addEmployee(
            Scanner scanner,
            EmployeeManager manager
    ) {

        System.out.println("\n========== Add Employee ==========");

        int id = readInt(scanner, "Enter employee ID: ");

        if (manager.findEmployeeById(id) != null) {
            System.out.println("Employee ID already exists.");
            return;
        }

        String name = readString(
                scanner,
                "Enter employee name: "
        );

        double salary = readDouble(
                scanner,
                "Enter employee salary: "
        );

        String department = readString(
                scanner,
                "Enter employee department: "
        );

        Employee employee = new Employee(
                id,
                name,
                salary,
                department
        );

        boolean added = manager.addEmployee(employee);

        if (added) {
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Employee could not be added.");
        }
    }


    public static void updateEmployee(
            Scanner scanner,
            EmployeeManager manager
    ) {

        System.out.println("\n========== Update Employee ==========");

        int id = readInt(
                scanner,
                "Enter employee ID to update: "
        );

        Employee employee = manager.findEmployeeById(id);

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.println("\nCurrent employee information:");
        System.out.println(employee);

        String newName = readString(
                scanner,
                "Enter new employee name: "
        );

        double newSalary = readDouble(
                scanner,
                "Enter new employee salary: "
        );

        String newDepartment = readString(
                scanner,
                "Enter new employee department: "
        );

        boolean updated = manager.updateEmployee(
                id,
                newName,
                newSalary,
                newDepartment
        );

        if (updated) {
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Employee could not be updated.");
        }
    }


    public static void deleteEmployee(
            Scanner scanner,
            EmployeeManager manager
    ) {

        System.out.println("\n========== Delete Employee ==========");

        int id = readInt(
                scanner,
                "Enter employee ID to delete: "
        );

        boolean deleted = manager.deleteEmployee(id);

        if (deleted) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }


    public static int readInt(
            Scanner scanner,
            String message
    ) {

        while (true) {

            System.out.print(message);

            if (scanner.hasNextInt()) {

                int number = scanner.nextInt();
                scanner.nextLine();

                return number;

            } else {

                System.out.println(
                        "Invalid input. Please enter a whole number."
                );

                scanner.nextLine();
            }
        }
    }


    public static double readDouble(
            Scanner scanner,
            String message
    ) {

        while (true) {

            System.out.print(message);

            if (scanner.hasNextDouble()) {

                double number = scanner.nextDouble();
                scanner.nextLine();

                if (number >= 0) {
                    return number;
                }

                System.out.println("Salary cannot be negative.");

            } else {

                System.out.println(
                        "Invalid input. Please enter a number."
                );

                scanner.nextLine();
            }
        }
    }


    public static String readString(
            Scanner scanner,
            String message
    ) {

        while (true) {

            System.out.print(message);

            String text = scanner.nextLine().trim();

            if (!text.isEmpty()) {
                return text;
            }

            System.out.println("This field cannot be empty.");
        }
    }
}