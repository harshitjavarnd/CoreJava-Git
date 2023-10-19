package com.exam.JavaExam;
import com.exam.DAO.AddressDAO;
import com.exam.DAO.EmployeeDAO;
import com.exam.ServiceImpl.AddressServicesImpl;
import com.exam.ServiceImpl.EmployeeServicesImpl;

import java.util.Scanner;

public class MainDriver {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //Established Database Connection

        //Menu Driven Code
        EmployeeServicesImpl.databaseConnection();
        while (true) {
            System.out.println();
            System.out.println("Enter Your Choice \n 1 Insert Employee and Address \n 2 Display Employee \n 3 Display Address \n 4 Delete Employee and Address " +
                    "\n 5 Update Employee \n 6 Update Address \n 7 Exit");
            int choice = sc.nextInt();
            switch (choice) {

                //Inserting Data
                case 1:
                    System.out.println("Enter Employee ID");
                    int id = sc.nextInt();
                    System.out.println("Enter Name of Employee");
                    String name = sc.next();
                    System.out.println("Enter Phone of Employee");
                    String phone = sc.next();

                    //Duplicate Phone Number Check
                    EmployeeServicesImpl.duplicatePhoneNo(phone);
                    EmployeeDAO employee = new EmployeeDAO(id, name, phone);
                    System.out.println("Enter City of Local Address");
                    String localCity = sc.next();
                    System.out.println("Enter Country of Local Address");
                    String localCountry = sc.next();
                    System.out.println("Enter Pin of Local Address");
                    String localPin = sc.next();
                    AddressDAO localAddress = new AddressDAO(localCity, localCountry, localPin);
                    System.out.println("Enter City of Permanent Address");
                    String perCity = sc.next();
                    System.out.println("Enter Country of Permanent Address");
                    String perCountry = sc.next();
                    System.out.println("Enter Pin of Permanent Address");
                    String perPin = sc.next();
                    AddressDAO perAddress = new AddressDAO(perCity, perCountry, perPin);
                    EmployeeServicesImpl.addEmployee(employee, localAddress, perAddress);
                    break;

                case 2:
                    EmployeeServicesImpl.displayEmployee();
                    break;

                case 4:
                    System.out.println("Enter the Employee ID");
                    int empId = sc.nextInt();
                    EmployeeServicesImpl.deleteEmployee(empId);
                    break;

                case 5:
                    System.out.println("Enter the Employee ID:");
                    int updateEmpId = sc.nextInt();
                    EmployeeServicesImpl.updateEmployee(updateEmpId);
                    break;

                case 3:
                    AddressServicesImpl.displayAddress();
                    break;

                case 6:
                    AddressServicesImpl.updateAddress();
                    break;
                default:
                    System.exit(0);
            }
        }

    }
}
