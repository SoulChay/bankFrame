package dao;

import entity.Atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("all")
public class AtmDao {

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/atm";
    private String user = "root";
    private String password = "root";

    //查询
    public Atm selectOne(String aname){
        Atm atm = null;
        String sql = "SELECT ANAME,APASSWORD,ABALANCE FROM ATM WHERE ANAME = ?";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1,aname);
            ResultSet rs = pstat.executeQuery();
            if(rs.next()){
                atm = new Atm();
                atm.setAname(rs.getString("aname"));
                atm.setApassword(rs.getString("apassword"));
                atm.setAbalance(rs.getFloat("abalance"));
            }
            rs.close();
            pstat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return atm;
    }

    //新增
    public int insert(Atm atm){
        int count = 0;//数据库更改的行数==1
        String sql = "INSERT INTO ATM VALUES(?,?,?)";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1,atm.getAname());
            pstat.setString(2,atm.getApassword());
            pstat.setFloat(3,atm.getAbalance());
            count = pstat.executeUpdate();
            pstat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //更新
    public int update(Atm atm){
        int count = 0;//记录更改的行数
        String sql = "UPDATE ATM SET APASSWORD = ? , ABALANCE = ? WHERE ANAME = ?";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1,atm.getApassword());
            pstat.setFloat(2,atm.getAbalance());
            pstat.setString(3,atm.getAname());
            count = pstat.executeUpdate();
            pstat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //删除
    public int delete(String aname){
        int count = 0;//数据库更改的行数==1
        String sql = "DELETE FROM ATM WHERE ANAME = ?";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1,aname);
            count = pstat.executeUpdate();
            pstat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}
