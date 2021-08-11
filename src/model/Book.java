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
import javax.swing.JOptionPane;

/**
 *
 * @author NCpc
 */
public class Book {

    private String MaSach;
    private String TenSach;
    private int soLuong;
    private int daMuon;
    private int conLai;

    public int getDaMuon() {
        return daMuon;
    }

    public void setDaMuon(int daMuon) {
        this.daMuon = daMuon;
    }

    public int getConLai() {
        return conLai;
    }

    public void setConLai(int conLai) {
        this.conLai = conLai;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String MaSach) {
        this.MaSach = MaSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String TenSach) {
        this.TenSach = TenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Book() {
    }

    public Book(String MaSach, String TenSach, int soLuong, int daMuon, int conLai) {
        this.MaSach = MaSach;
        this.TenSach = TenSach;
        this.soLuong = soLuong;
        this.daMuon = daMuon;
        this.conLai = conLai;
    }

    public Book(String MaSach, String TenSach, int soLuong) {
        this.MaSach = MaSach;
        this.TenSach = TenSach;
        this.soLuong = soLuong;
    }

    public static List<Book> findAll() {
        List<Book> bookList = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");
            statement = conn.createStatement();

            String sql = "select lm.book.MaSach, lm.book.TenSach, lm.book.soLuong, \n"
                    + "count(lm.muontra.MaSach) as 'damuon',\n"
                    + "(lm.book.soLuong-count(lm.muontra.MaSach)) as 'conlai'\n"
                    + "from lm.muontra\n"
                    + "right join lm.book on lm.book.MaSach = lm.muontra.MaSach\n"
                    + "group by lm.book.TenSach";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Book book = new Book(resultSet.getString("MaSach"),
                        resultSet.getString("TenSach"),
                        resultSet.getInt("soLuong"),
                        resultSet.getInt("damuon"),
                        resultSet.getInt("conlai"));
                bookList.add(book);
            }

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

        return bookList;
    }

    public static List<Book> findAllDaMuon() {
        List<Book> bookList = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");
            statement = conn.createStatement();

            String sql = "select lm.book.MaSach, lm.book.TenSach, lm.book.soLuong, \n"
                    + "count(lm.muontra.MaSach) as damuon,\n"
                    + "(lm.book.soLuong-count(lm.muontra.MaSach)) as conlai\n"
                    + "from lm.muontra\n"
                    + "right join lm.book on lm.book.MaSach = lm.muontra.MaSach\n"
                    + "group by lm.book.TenSach\n"
                    + "having damuon != 0";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Book book = new Book(resultSet.getString("MaSach"),
                        resultSet.getString("TenSach"),
                        resultSet.getInt("soLuong"),
                        resultSet.getInt("damuon"),
                        resultSet.getInt("conlai"));
                bookList.add(book);
            }

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

        return bookList;
    }

    public static List<Book> findAllChuaMuon() {
        List<Book> bookList = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");
            statement = conn.createStatement();

            String sql = "select lm.book.MaSach, lm.book.TenSach, lm.book.soLuong, \n"
                    + "count(lm.muontra.MaSach) as damuon,\n"
                    + "(lm.book.soLuong-count(lm.muontra.MaSach)) as conlai\n"
                    + "from lm.muontra\n"
                    + "right join lm.book on lm.book.MaSach = lm.muontra.MaSach\n"
                    + "group by lm.book.TenSach\n"
                    + "having damuon = 0";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Book book = new Book(resultSet.getString("MaSach"),
                        resultSet.getString("TenSach"),
                        resultSet.getInt("soLuong"),
                        resultSet.getInt("damuon"),
                        resultSet.getInt("conlai"));
                bookList.add(book);
            }

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

        return bookList;
    }

    public static void create(Book book) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");

            String query = "insert into lm.book(MaSach, TenSach, soLuong) "
                    + "values(?, ?, ?)";
            statement = conn.prepareStatement(query);

            statement.setString(1, book.getMaSach());
            statement.setString(2, book.getTenSach());

            statement.setInt(3, book.getSoLuong());

            statement.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ID hoặc tên Sách bị trùng");
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

    public static List<Book> findBook(String bookName) {
        List<Book> bookList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");
            String query = "select lm.book.MaSach, lm.book.TenSach, lm.book.soLuong, \n"
                    + "count(lm.muontra.MaSach) as damuon,\n"
                    + "(lm.book.soLuong-count(lm.muontra.MaSach)) as conlai\n"
                    + "from lm.muontra\n"
                    + "right join lm.book on lm.book.MaSach = lm.muontra.MaSach\n"
                    + "group by lm.book.TenSach\n"
                    + "having TenSach like ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, "%" + bookName + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(resultSet.getString("MaSach"),
                        resultSet.getString("TenSach"),
                        resultSet.getInt("soLuong"),
                        resultSet.getInt("damuon"),
                        resultSet.getInt("conlai"));
                bookList.add(book);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookList;
    }

    public static List<Book> findBookByID(String ID) {
        List<Book> bookList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");
            String query = "select lm.book.MaSach, lm.book.TenSach, lm.book.soLuong, \n"
                    + "count(lm.muontra.MaSach) as damuon,\n"
                    + "(lm.book.soLuong-count(lm.muontra.MaSach)) as conlai\n"
                    + "from lm.muontra\n"
                    + "right join lm.book on lm.book.MaSach = lm.muontra.MaSach\n"
                    + "group by lm.book.TenSach\n"
                    + "having MaSach like ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, "%" + ID + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(resultSet.getString("MaSach"),
                        resultSet.getString("TenSach"),
                        resultSet.getInt("soLuong"),
                        resultSet.getInt("damuon"),
                        resultSet.getInt("conlai"));
                bookList.add(book);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookList;
    }

    public static void deleteBook(String id) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");

            String query = "delete from lm.book where MaSach = ?";
            statement = conn.prepareStatement(query);
            statement.setString(1,id);
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

    public static void updateBook(String idSach, Book book) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm",
                    "root", "NhuPhong1522");

            String query = "update lm.book set TenSach = ?,"
                    + " soLuong = ? where MaSach = ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, book.getTenSach());
            statement.setInt(2, book.getSoLuong());
            statement.setString(3, idSach);

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
