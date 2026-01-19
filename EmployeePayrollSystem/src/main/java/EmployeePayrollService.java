package main.java;

import java.util.Scanner;

public class EmployeePayrollService {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        // Read from Console
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();

        scanner.nextLine(); // consume newline

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();

        // Create Employee Payroll object
        Employee employee = new Employee(id, name, salary);

        // Write to Console
        System.out.println("\n--- Employee Payroll Details ---");
        System.out.println(employee);

        scanner.close();

	}

}
