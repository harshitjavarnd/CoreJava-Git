package com.exam.ServiceImpl;

import com.exam.DAO.AddressDAO;
import com.exam.DAO.EmployeeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddressServicesImpl {
    public static void displayAddress() {
        //JDBC Code for Displaying Employee from the Database
        try {
            Statement stmt = EmployeeServicesImpl.con.createStatement();
            System.out.println("Address Table");
            System.out.println("ID | EID | City | Country | Pin");
            System.out.println("--------------------------------------------------");
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM address");
            while (rs2.next()) {
                int id = rs2.getInt("id");
                int emp_id = rs2.getInt("emp_id");
                String city = rs2.getString("city");
                String country = rs2.getString("country");
                String pin = rs2.getString("pin");
                System.out.println(id + " | " + emp_id + " | " + city + " | " + country + " | " + pin);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void addAddress(EmployeeDAO employee, AddressDAO localAddress, AddressDAO perAddress) {
        //JDBC Code for Adding Employee into the Database
        try {

            //Add Local Address
            String localAddressInsertSQL = "INSERT INTO address (id, emp_id, city, country, pin) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement addressInsertStmt = EmployeeServicesImpl.con.prepareStatement(localAddressInsertSQL);
            int localAddressId = employee.getId();
            int empIdForAddress = employee.getId();
            String city = localAddress.getCity();
            String country = localAddress.getCountry();
            String pin = localAddress.getPin();

            addressInsertStmt.setInt(1, localAddressId);
            addressInsertStmt.setInt(2, empIdForAddress);
            addressInsertStmt.setString(3, city);
            addressInsertStmt.setString(4, country);
            addressInsertStmt.setString(5, pin);
            addressInsertStmt.executeUpdate();

            //Permanent Address
            String perAddressInsertSQL = "INSERT INTO address (id, emp_id, city, country, pin) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement perAddressInsertStmt = EmployeeServicesImpl.con.prepareStatement(perAddressInsertSQL);
            int perAddressId = employee.getId()+1;
            int perEmpIdForAddress = employee.getId();
            String perCity = perAddress.getCity();
            String perCountry = perAddress.getCountry();
            String perPin = perAddress.getPin();

            perAddressInsertStmt.setInt(1, perAddressId);
            perAddressInsertStmt.setInt(2, perEmpIdForAddress);
            perAddressInsertStmt.setString(3, perCity);
            perAddressInsertStmt.setString(4, perCountry);
            perAddressInsertStmt.setString(5, perPin);
            perAddressInsertStmt.executeUpdate();


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateAddress(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Address ID:");
        int empId = sc.nextInt();
        try{
            String query = "SELECT * FROM address WHERE id = ?";
            PreparedStatement statement = EmployeeServicesImpl.con.prepareStatement(query);
            statement.setInt(1, empId);

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Address ID Does Not Exists!");
                System.exit(0);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Select \n 1 City \n 2 Country \n 3 Pin \n 4 Exit");
        int result = sc.nextInt();
        switch(result){
            case 1:
                try {
                    String updateAddressSQL = "UPDATE address SET city = ? WHERE id = ?";
                    PreparedStatement updateCityStmt = EmployeeServicesImpl.con.prepareStatement(updateAddressSQL);

                    System.out.println("Enter New City");
                    String localAddressCity = sc.next();
                    int employeeIdToUpdate = empId;

                    updateCityStmt.setString(1, localAddressCity);
                    updateCityStmt.setInt(2, employeeIdToUpdate);
                    updateCityStmt.executeUpdate();
                }
                catch(SQLException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                try {
                    String updateCountrySQL = "UPDATE address SET country = ? WHERE id = ?";
                    PreparedStatement updateCountryStmt = EmployeeServicesImpl.con.prepareStatement(updateCountrySQL);

                    System.out.println("Enter New Country");
                    String newEmployeeCountry = sc.next();
                    int updateCountryWithID = empId;

                    updateCountryStmt.setString(1, newEmployeeCountry);
                    updateCountryStmt.setInt(2, updateCountryWithID);
                    updateCountryStmt.executeUpdate();
                }
                catch(SQLException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                try {
                    String updatePinSQL = "UPDATE address SET pin = ? WHERE id = ?";
                    PreparedStatement updatePinStmt = EmployeeServicesImpl.con.prepareStatement(updatePinSQL);


                    System.out.println("Enter New Pin");
                    String newEmployeePin = sc.next();
                    int updatePinWithID = empId;

                    // Set values for the prepared statement
                    updatePinStmt.setString(1, newEmployeePin);
                    updatePinStmt.setInt(2, updatePinWithID);
                    updatePinStmt.executeUpdate();
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

}
