package dao;

import config.JDBCUtil;
import model.Account;

import java.sql.*;
import java.util.ArrayList;

public class AccountDAO implements DAOInterface<Account> {

    public static AccountDAO getInstance() {
        return new AccountDAO();
    }

    @Override
    public int insert(Account t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "INSERT INTO account (confirm, username, password, gmail) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getConfirm());
            pst.setString(2, t.getUsername());
            pst.setString(3, t.getPassword());
            pst.setString(4, t.getGmail());
            result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Account t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "UPDATE account SET username = ?, password = ?, gmail = ? WHERE confirm = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getUsername());
            pst.setString(2, t.getPassword());
            pst.setString(3, t.getGmail());
            pst.setString(4, t.getConfirm());
            result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(Account t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "DELETE FROM account WHERE confirm = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getConfirm());
            result = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Account> selectALL() {
        ArrayList<Account> list = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM account";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String confirm = rs.getString("confirm");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String gmail = rs.getString("gmail");
                list.add(new Account(confirm, username, password, gmail));
            }
            rs.close();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Account selectByID(Account t) {
        Account acc = null;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM account WHERE confirm = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getConfirm());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String gmail = rs.getString("gmail");
                acc = new Account(t.getConfirm(), username, password, gmail);
            }
            rs.close();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }

    @Override
    public ArrayList<Account> selectByCondition(String condition) {
        ArrayList<Account> list = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM account WHERE " + condition;
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String confirm = rs.getString("confirm");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String gmail = rs.getString("gmail");
                list.add(new Account(confirm, username, password, gmail));
            }
            rs.close();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ArrayList<Account> search(String keyword) {
        ArrayList<Account> list = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM account WHERE confirm LIKE ? OR username LIKE ? OR password LIKE ? OR gmail LIKE ?";
            PreparedStatement pst = c.prepareStatement(sql);
            String like = "%" + keyword + "%";
            pst.setString(1, like);
            pst.setString(2, like);
            pst.setString(3, like);
            pst.setString(4, like);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String confirm = rs.getString("confirm");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String gmail = rs.getString("gmail");
                list.add(new Account(confirm, username, password, gmail));
            }
            rs.close();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean exists(Account t) {
        boolean result = false;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM account WHERE confirm = ? OR username = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getConfirm());
            pst.setString(2, t.getUsername());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int count() {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM account";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            rs.close();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Object[]> countGroupByTwoFields(String field1, String field2) {
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT " + field1 + ", " + field2 + ", COUNT(*) FROM account GROUP BY " + field1 + ", " + field2;
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String val1 = rs.getString(1);
                String val2 = rs.getString(2);
                int count = rs.getInt(3);
                list.add(new Object[]{val1, val2, count});
            }
            rs.close();
            pst.close();
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
