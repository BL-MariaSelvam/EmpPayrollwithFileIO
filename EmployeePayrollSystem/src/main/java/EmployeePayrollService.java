package main.java;

import java.util.Scanner;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeePayrollService {

    private static final String FILE_NAME = "C:\\Users\\sathi\\OneDrive\\Desktop\\selva\\bridgelabz\\FileIo\\employee_payroll.txt";

    public static void main(String[] args) {

        List<String> payrollLines = readPayrollFile();

        System.out.println("--- Employee Payroll File Data ---");
        payrollLines.forEach(System.out::println);

        // Sample Analysis
        System.out.println("\n--- Payroll Analysis ---");
        System.out.println("Total Entries       : " + payrollLines.size());
        System.out.println("Total Salary Amount : " + calculateTotalSalary(payrollLines));
        System.out.println("Average Salary      : " + calculateAverageSalary(payrollLines));
    }

    // Read employee payroll file using File IO
    private static List<String> readPayrollFile() {
        try (Stream<String> lines = Files.lines(Paths.get(FILE_NAME))) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error reading payroll file");
            return List.of();
        }
    }

    // Calculate total salary
    private static double calculateTotalSalary(List<String> lines) {
        return lines.stream()
                .mapToDouble(EmployeePayrollService::extractSalary)
                .sum();
    }

    // Calculate average salary
    private static double calculateAverageSalary(List<String> lines) {
        if (lines.isEmpty()) return 0;
        return calculateTotalSalary(lines) / lines.size();
    }

    // Extract salary value from file line
    private static double extractSalary(String line) {
        // Example line:
        // Employee [empId=1, empName=Rahul, salary=50000.0]
        String salaryPart = line.substring(line.indexOf("salary=") + 7, line.indexOf("]"));
        return Double.parseDouble(salaryPart);
    }
}
