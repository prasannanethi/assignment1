package assignment1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class employee {

    public static void main(String[] args) throws IOException {
        // Create a FileInputStream object to read the Excel file
        FileInputStream fis = new FileInputStream(new File("C:/Users/prasanna/Downloads/Assignment_Timecard.xlsx%20-%20Sheet1.pdf"));

        // Create an HSSFWorkbook object to represent the Excel workbook
        HSSFWorkbook workbook = new HSSFWorkbook(fis);

        // Get the first sheet in the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);

        // Create a list to store the employees who have worked for 7 consecutive days
        List<Employee> employeesWithConsecutiveDays = new ArrayList<>();

        // Create a list to store the employees who have less than 10 hours of time between shifts but greater than 1 hour
        List<Employee> employeesWithShortTimeBetweenShifts = new ArrayList<>();

        // Create a list to store the employees who have worked for more than 14 hours in a single shift
        List<Employee> employeesWithLongShifts = new ArrayList<>();

        // Iterate over the rows in the sheet
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            // Get the current row
            HSSFSheet.Row row = sheet.getRow(i);

            // Get the employee's name and position
            String name = row.getCell(0).getStringCellValue();
            String position = row.getCell(1).getStringCellValue();

            // Create an Employee object
            Employee employee = new Employee(name, position);

            // Check if the employee has worked for 7 consecutive days
            if (employee.hasWorkedForConsecutiveDays(7)) {
                employeesWithConsecutiveDays.add(employee);
            }

            // Check if the employee has less than 10 hours of time between shifts but greater than 1 hour
            if (employee.hasShortTimeBetweenShifts()) {
                employeesWithShortTimeBetweenShifts.add(employee);
            }

            // Check if the employee has worked for more than 14 hours in a single shift
            if (employee.hasLongShifts()) {
                employeesWithLongShifts.add(employee);
            }
        }

        // Print the name and position of the employees who have worked for 7 consecutive days
        System.out.println("Employees who have worked for 7 consecutive days:");
        for (Employee employee : employeesWithConsecutiveDays) {
            System.out.println(employee.getName() + " - " + employee.getPosition());
        }

        // Print the name and position of the employees who have less than 10 hours of time between shifts but greater than 1 hour
        System.out.println("Employees who have less than 10 hours of time between shifts but greater than 1 hour:");
        for (Employee employee : employeesWithShortTimeBetweenShifts) {
            System.out.println(employee.getName() + " - " + employee.getPosition());
        }

        // Print the name and position of the employees who have worked for more than 14 hours in a single shift
        System.out.println("Employees who have worked for more than 14 hours in a single shift:");
        for (Employee employee : employeesWithLongShifts) {
            System.out.println(employee.getName() + " - " + employee.getPosition());
        }

        // Close the FileInputStream and HSSFWorkbook objects
        fis.close();
        workbook.close();
    }
}
