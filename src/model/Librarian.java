/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NCpc
 */
public class Librarian {

    private long id_acc;
    private String name;
    private String username;
    private String password;

    public long getId_acc() {
        return id_acc;
    }

    public void setId_acc(long id_acc) {
        this.id_acc = id_acc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Librarian(long id_acc, String name, String username, String password) {
        this.id_acc = id_acc;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Librarian() {
    }

    public Librarian(long id_acc, String username, String password) {
        this.id_acc = id_acc;
        this.username = username;
        this.password = password;
    }

    public static List<Librarian> getAllAccounts() {
        List<Librarian> librarianList = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");
            statement = conn.createStatement();
            String sql = "select *from lm.thuthu";
            ResultSet resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()){
                Librarian lib = new Librarian(resultSet.getInt("id_acc"),
                        resultSet.getString("username"), 
                        resultSet.getString("password"));
                librarianList.add(lib);
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(Librarian.class.getName()).log(Level.SEVERE, null, ex);
        }
        return librarianList;
    }

}
