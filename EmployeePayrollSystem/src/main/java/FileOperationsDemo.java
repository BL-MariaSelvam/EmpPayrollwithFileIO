package main.java;

import java.io.File;
import java.io.IOException;

public class FileOperationsDemo {

    public static void main(String[] args) {

        // File and Directory paths
        String directoryPath = "C:\\Users\\sathi\\OneDrive\\Desktop\\selva\\bridgelabz\\FileIo";
        String filePath = "C:\\Users\\sathi\\OneDrive\\Desktop\\selva\\bridgelabz\\FileIo\\sample.txt";

        // 1. Create Directory
        File directory = new File(directoryPath);
        if (directory.mkdir()) {
            System.out.println("Directory created successfully.");
        } else {
            System.out.println("Directory already exists.");
        }

        // 2. Create Empty File
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("Empty file created.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. Check File Exists
        if (file.exists()) {
            System.out.println("File exists.");
        } else {
            System.out.println("File does not exist.");
        }

        // 4. List Files and Directories
        System.out.println("\nListing all files and directories:");
        File[] filesList = directory.listFiles();
        if (filesList != null) {
            for (File f : filesList) {
                if (f.isFile()) {
                    System.out.println("File: " + f.getName());
                } else if (f.isDirectory()) {
                    System.out.println("Directory: " + f.getName());
                }
            }
        }

        // 5. List Files with Extension (.txt)
        System.out.println("\nListing files with .txt extension:");
        if (filesList != null) {
            for (File f : filesList) {
                if (f.isFile() && f.getName().endsWith(".txt")) {
                    System.out.println(f.getName());
                }
            }
        }

        // 6. Delete File and Check File Not Exist
        if (file.delete()) {
            System.out.println("\nFile deleted successfully.");
        } else {
            System.out.println("\nFailed to delete file.");
        }

        if (!file.exists()) {
            System.out.println("File does not exist after deletion.");
        }
    }
}
