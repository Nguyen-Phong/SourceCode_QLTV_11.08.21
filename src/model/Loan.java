/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NCpc
 */
public class Loan {

    private String maThanhVien;
    private String maSach;
    private String tenThanhVien;
    private String chucVu;
    private String tenSach;
    private Date ngayMuon;
    private Date ngayTra;
    private int period;

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public Loan(String maThanhVien, String maSach, Date ngayMuon, Date ngayTra) {
        this.maThanhVien = maThanhVien;
        this.maSach = maSach;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
    }

    public Loan() {
    }

    public Loan(String maThanhVien, String tenThanhVien, String maSach, String tenSach, Date ngayMuon, Date ngayTra, int period) {
        this.maThanhVien = maThanhVien;
        this.tenThanhVien = tenThanhVien;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.period = period;
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

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public static List<Loan> getAllLoan() {
        List<Loan> loanList = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");
            statement = conn.createStatement();
            String sql = "select \n"
                    + "lm.thanhvien.maThanhVien, lm.thanhvien.tenThanhVien,"
                    + "lm.book.MaSach, lm.book.TenSach, lm.muontra.ngayMuon,"
                    + "lm.muontra.ngayTra, timestampdiff(day, lm.muontra.ngayMuon, "
                    + "lm.muontra.ngayTra) as 'period'"
                    + "from lm.book, lm.muontra, lm.thanhvien\n"
                    + "where lm.book.MaSach = lm.muontra.MaSach "
                    + "and lm.muontra.maThanhVien = lm.thanhvien.maThanhVien";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Loan ln = new Loan(resultSet.getString("maThanhVien"),
                        resultSet.getString("tenThanhVien"),
                        resultSet.getString("MaSach"),
                        resultSet.getString("TenSach"),
                        resultSet.getDate("ngayMuon"),
                        resultSet.getDate("ngayTra"),
                        resultSet.getInt("period"));
                loanList.add(ln);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return loanList;
    }

    public static void create(Loan loan) {
        Connection conn = null;
        PreparedStatement statement = null;
        DateFormat dateFormatYMD = new SimpleDateFormat("yyyy/MM/dd");

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");
            String sql = "insert into lm.muontra (maThanhVien , MaSach , ngayMuon, ngayTra) values (?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, loan.getMaThanhVien());
            statement.setString(2, loan.getMaSach());
            statement.setString(3, dateFormatYMD.format(loan.getNgayMuon()));
            statement.setString(4, dateFormatYMD.format(loan.ngayTra));
            statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void update() {

    }

    public static List<Loan> searchByBorrowerID(String brID) {
        List<Loan> loanList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");
            String sql = "select \n"
                    + "lm.thanhvien.maThanhVien, lm.thanhvien.tenThanhVien,\n"
                    + "lm.book.MaSach, lm.book.TenSach, lm.muontra.ngayMuon,\n"
                    + "lm.muontra.ngayTra, timestampdiff(day, lm.muontra.ngayMuon, lm.muontra.ngayTra) as 'period'\n"
                    + "from lm.book, lm.muontra, lm.thanhvien\n"
                    + "where lm.book.MaSach = lm.muontra.MaSach and lm.muontra.maThanhVien = lm.thanhvien.maThanhVien\n"
                    + "and lm.thanhvien.maThanhVien like ?;";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + brID + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Loan ln = new Loan(resultSet.getString("maThanhVien"),
                        resultSet.getString("tenThanhVien"),
                        resultSet.getString("MaSach"),
                        resultSet.getString("TenSach"),
                        resultSet.getDate("ngayMuon"),
                        resultSet.getDate("ngayTra"),
                        resultSet.getInt("period"));
                loanList.add(ln);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return loanList;
    }

    public static List<Loan> searchByBookID(String bkID) {
        List<Loan> loanList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");
            String sql = "select \n"
                    + "lm.thanhvien.maThanhVien, lm.thanhvien.tenThanhVien,\n"
                    + "lm.book.MaSach, lm.book.TenSach, lm.muontra.ngayMuon,\n"
                    + "lm.muontra.ngayTra, timestampdiff(day, lm.muontra.ngayMuon, lm.muontra.ngayTra) as 'period'\n"
                    + "from lm.book, lm.muontra, lm.thanhvien\n"
                    + "where lm.book.MaSach = lm.muontra.MaSach and lm.muontra.maThanhVien = lm.thanhvien.maThanhVien\n"
                    + "and lm.book.MaSach like ?;";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + bkID + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Loan ln = new Loan(resultSet.getString("maThanhVien"),
                        resultSet.getString("tenThanhVien"),
                        resultSet.getString("MaSach"),
                        resultSet.getString("TenSach"),
                        resultSet.getDate("ngayMuon"),
                        resultSet.getDate("ngayTra"),
                        resultSet.getInt("period"));
                loanList.add(ln);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return loanList;
    }
    
    public static void traSach(String maSach, String maTV) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");

            String query = "delete from lm.muontra where MaSach = ? and maThanhVien = ?";
            statement = conn.prepareStatement(query);
            statement.setString(1,maSach );
            statement.setString(2, maTV);
            statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
