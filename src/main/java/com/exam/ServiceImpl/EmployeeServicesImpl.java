package com.exam.ServiceImpl;

import com.exam.DAO.AddressDAO;
import com.exam.DAO.EmployeeDAO;
import com.exam.Exception.DuplicatePhoneNumberException;

import java.sql.*;
import java.util.*;

public class EmployeeServicesImpl {

    //Global Variable
    public static Connection con;
    public static void databaseConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/assignment", "root", "sample1");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Employee Service Class

    public static void addEmployee(EmployeeDAO employee, AddressDAO localAddress, AddressDAO perAddress) {
        //JDBC Code for Adding Employee into the Database
        try {

            //Add Employee
            String employeeQuery = " insert into employee (id, name, phone)"
                    + " values (?, ?, ?)";
            PreparedStatement preparedStmt1 = con.prepareStatement(employeeQuery);
            preparedStmt1.setInt(1, employee.getId());
            preparedStmt1.setString(2, employee.getName());
            preparedStmt1.setString(3, employee.getPhone());
            preparedStmt1.execute();

            AddressServicesImpl.addAddress(employee, localAddress, perAddress);

            System.out.println("Employee and Address Records Added Successfully");

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public static void displayEmployee() {
        //JDBC Code for Displaying Employee from the Database
        try {
            Statement stmt = con.createStatement();
            System.out.println("Employee Table");
            System.out.println("ID | Name | Phone");
            System.out.println("--------------------------------------------------");
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM employee");
            while (rs1.next()) {
                int id = rs1.getInt("id");
                String name = rs1.getString("name");
                String phone = rs1.getString("phone");
                System.out.println(id + " | " +name + " | " + phone);
            }
            System.out.println();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteEmployee(int empId){
        //JDBC Code for Displaying Employee from the Database
        try{
            //Check If Employee is Present or Not
            String query = "SELECT * FROM employee WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, empId);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Employee Does Not Exists!");
                System.exit(0);
            }

            String sql = "DELETE FROM employee " +
                    "WHERE id =" + empId;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("Data Deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateEmployee(int empId){
        Scanner sc = new Scanner(System.in);
        try{
            String query = "SELECT * FROM employee WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, empId);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Employee Does Not Exists!");
                System.exit(0);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Select \n 1 Name \n 2 Phone \n 3 Exit");
        int result = sc.nextInt();
        switch(result){
            case 1:
                try {
                    String updateNameSQL = "UPDATE employee SET name = ? WHERE id = ?";
                    PreparedStatement updateNameStmt = con.prepareStatement(updateNameSQL);

                    System.out.println("Enter New Name");
                    String newEmployeeName = sc.next();

                    int employeeIdToUpdate = empId;

                    // Set values for the prepared statement
                    updateNameStmt.setString(1, newEmployeeName);
                    updateNameStmt.setInt(2, employeeIdToUpdate);
                    updateNameStmt.executeUpdate();
                }
                catch(SQLException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 2:

                try {
                    String updatePhoneSQL = "UPDATE employee SET phone = ? WHERE id = ?";
                    PreparedStatement updateNameStmt = con.prepareStatement(updatePhoneSQL);

                    // New name and employee ID for the update
                    System.out.println("Enter New Phone");
                    String newEmployeePhone = sc.next();
                    int updatePhoneWithID = empId;


                    // Set values for the prepared statement
                    updateNameStmt.setString(1, newEmployeePhone);
                    updateNameStmt.setInt(2, updatePhoneWithID);
                    updateNameStmt.executeUpdate();

                }
                catch(SQLException e){
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.exit(0);
        }
        System.out.println("Record Updated Successfully!");
    }

    public static void duplicatePhoneNo(String phone){
        try{
            String sql = "SELECT * FROM employee WHERE phone = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, phone);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                throw new DuplicatePhoneNumberException("Duplicate Phone No");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

}
