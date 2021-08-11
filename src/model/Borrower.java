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
public class Borrower {

    private String maThanhVien;
    private String tenThanhVien;
    private int tuoi;
    private String gioiTinh;
    private String chucVu;
    private String phone;
    private String diaChi;

    public Borrower(String maThanhVien, String tenThanhVien, int tuoi, String gioiTinh, String chucVu, String phone, String diaChi) {
        this.maThanhVien = maThanhVien;
        this.tenThanhVien = tenThanhVien;
        this.tuoi = tuoi;
        this.gioiTinh = gioiTinh;
        this.chucVu = chucVu;
        this.phone = phone;
        this.diaChi = diaChi;
    }

    public String getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(String maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public String getTenThanhVien() {
        return tenThanhVien;
    }

    public void setTenThanhVien(String tenThanhVien) {
        this.tenThanhVien = tenThanhVien;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Borrower() {
    }

    public static List<Borrower> findAllBorrower() {
        List<Borrower> borrowerList = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");
            statement = conn.createStatement();
            String sql = "select *from lm.thanhvien";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Borrower br = new Borrower(resultSet.getString("maThanhVien"),
                        resultSet.getString("tenThanhVien"),
                        resultSet.getInt("tuoi"),
                        resultSet.getString("gioiTinh"),
                        resultSet.getString("chucVu"),
                        resultSet.getString("phone"),
                        resultSet.getString("diaChi"));
                borrowerList.add(br);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Borrower.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Borrower.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Borrower.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return borrowerList;
    }

    public static void create(Borrower borrower) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");
            String query = "insert into lm.thanhvien values(?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(query);

            statement.setString(1, borrower.getMaThanhVien());
            statement.setString(2, borrower.getTenThanhVien());
            statement.setInt(3, borrower.getTuoi());
            statement.setString(4, borrower.getGioiTinh());
            statement.setString(5, borrower.getChucVu());
            statement.setString(6, borrower.getPhone());
            statement.setString(7, borrower.getDiaChi());

            statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Borrower.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Borrower.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Borrower.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public static void update(Borrower br, String id) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");
            String query = "update lm.thanhvien set tenThanhVien = ?,"
                    + "tuoi=?, gioiTinh=?,"
                    + "chucVu=?, phone=?, diaChi=? where maThanhVien=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, br.getTenThanhVien());
            statement.setInt(2, br.getTuoi());
            statement.setString(3, br.getGioiTinh());
            statement.setString(4, br.getChucVu());
            statement.setString(5, br.getPhone());
            statement.setString(6, br.getDiaChi());
            statement.setString(7, id);
            statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Borrower.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Borrower.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Borrower.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public static void delete(String id) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");
            String query = "delete from lm.thanhvien where maThanhVien = ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, id);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Borrower.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Borrower.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Borrower.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static List<Borrower> searchByID(String id) {
        List<Borrower> brList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");
            String query = "select *from lm.thanhvien where maThanhVien like ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, "%"+id+"%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Borrower br = new Borrower(resultSet.getString("maThanhVien"),
                        resultSet.getString("tenThanhVien"),
                        resultSet.getInt("tuoi"),
                        resultSet.getString("gioiTinh"),
                        resultSet.getString("chucVu"),
                        resultSet.getString("phone"),
                        resultSet.getString("diaChi"));
                brList.add(br);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Borrower.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Borrower.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Borrower.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return brList;
    }
}
