package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;

public class EmployeePayrollService {

		 private static final String FILE_NAME = "C:\\Users\\sathi\\OneDrive\\Desktop\\selva\\bridgelabz\\FileIo\\employee_payroll.txt";

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

		        // Create Employee object
		        Employee employee = new Employee(id, name, salary);

		        // Write to file
		        writeEmployeeToFile(employee);

		        // Count entries in file
		        long count = countEntriesInFile();

		        // Output
		        System.out.println("\n--- Employee Payroll Details ---");
		        System.out.println(employee);
		        

		        System.out.println("\n--- Employee Payrolls From File ---");
		        printEmployeePayrolls();
		        
		        System.out.println("Number of Employee Entries in File: " + count);

		        scanner.close();
		    }

		    // Write employee payroll to file
		    private static void writeEmployeeToFile(Employee employee) {
		        try {
		            Files.write(
		                    Paths.get(FILE_NAME),
		                    Collections.singleton(employee.toString()),
		                    StandardOpenOption.CREATE,
		                    StandardOpenOption.APPEND
		            );
		        } catch (IOException e) {
		            System.out.println("Error writing employee payroll to file");
		            e.printStackTrace();
		        }
		    }
		    
		    // Print employee payrolls from file
		    private static void printEmployeePayrolls() {
		        try (Stream<String> lines = Files.lines(Paths.get(FILE_NAME))) {
		            lines.forEach(System.out::println);
		        } catch (IOException e) {
		            System.out.println("Error reading payroll file");
		        }
		    }

		    // Count number of entries in file
		    private static long countEntriesInFile() {
		        try (Stream<String> lines = Files.lines(Paths.get(FILE_NAME))) {
		            return lines.count();
		        } catch (IOException e) {
		            System.out.println("Error reading employee payroll file");
		            return 0;
		        }
		    }
	}

