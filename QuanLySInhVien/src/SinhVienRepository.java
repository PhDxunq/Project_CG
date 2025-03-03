import model.DBConnection;
import model.HocPhan;
import model.SinhVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SinhVienRepository {

    public ArrayList<SinhVien> layDanhSachSinhVien() {
        ArrayList<SinhVien> listSinhVien = new ArrayList<>();
        String sqlSelect = "SELECT * FROM SinhVien";
        try {
            DBConnection db = DBConnection.getInstance();
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sqlSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String msv = rs.getString("msv");
                String fullname = rs.getString("fullname");
                int soluonghocphan = rs.getInt("soluonghocphan");
                double diemtrungbinh = rs.getDouble("diemtrungbinh");
                SinhVien sv = new SinhVien(msv, fullname, soluonghocphan, diemtrungbinh);
                listSinhVien.add(sv);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listSinhVien;
    }

    public ArrayList<HocPhan> layDanhSachHocPhan(String msv) {
        ArrayList<HocPhan> listHocPhan = new ArrayList<>();
        String sqlSelect = "SELECT DISTINCT mamonhoc, tenmonhoc, sotinchi, msv, songaynghi, diem FROM hocphan WHERE msv = ?"; // chatgpt
        try {
            DBConnection db = DBConnection.getInstance();
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sqlSelect);
            ps.setString(1, msv);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String mamonhoc = rs.getString("mamonhoc");
                String tenmonhoc = rs.getString("tenmonhoc");
                int sotinchi = rs.getInt("sotinchi");
                int songaynghi = rs.getInt("songaynghi");
                double diem = rs.getDouble("diem");
                HocPhan hp = new HocPhan(mamonhoc, tenmonhoc, sotinchi, msv, songaynghi, diem);
                listHocPhan.add(hp);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listHocPhan;
    }


    public void themSinhVien(SinhVien sv) {
        String sqlInsert = "INSERT INTO sinhvien(msv, fullname, soluonghocphan, diemtrungbinh) VALUES (?,?,?,?)";
        try {
            DBConnection db = DBConnection.getInstance();
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sqlInsert);
            ps.setString(1, sv.getMsv());
            ps.setString(2, sv.getFullname());
            ps.setInt(3, sv.getSoluonghocphan());
            ps.setDouble(4, sv.getDiemtrungbinh());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Thêm sinh viên thành công");
            } else {
                System.out.println("Thêm sinh viên thất bại");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void themHocPhan(HocPhan hp){
        String sqlInsert = "INSERT INTO hocphan(mamonhoc, tenmonhoc, sotinchi, msv, songaynghi, diem) VALUES (?,?,?,?,?,?)";
        try {
            DBConnection db = DBConnection.getInstance();
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sqlInsert);
            ps.setString(1,hp.getMamonhoc());
            ps.setString(2,hp.getTenmonhoc());
            ps.setInt(3,hp.getSotinchi());
            ps.setString(4,hp.getMsv());
            ps.setInt(5,hp.getSongaynghi());
            ps.setDouble(6,hp.getDiem());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("Them hoc phan thanh cong");
            } else {
                System.out.println("Them hoc phan that bai");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void xoaSinhVien(String msv) {
        String sqlDeleteHocPhan = "DELETE FROM hocphan WHERE msv = ?";
        try {
            DBConnection db = DBConnection.getInstance();
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sqlDeleteHocPhan);
            ps.setString(1, msv);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                String sqlDeleteSinhVien = "DELETE FROM sinhvien WHERE msv = ?";
                ps = cn.prepareStatement(sqlDeleteSinhVien);
                ps.setString(1, msv);
                rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Xoá sinh viên thành công");
                }
            } else {
                System.out.println("Xoá sinh viên thất bại");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void capNhatSinhVien(String msv) {
        String sqlUpdate = "UPDATE sinhvien SET  soluonghocphan = ?, diemtrungbinh = ? WHERE msv = ?";
        try {
            DBConnection db = DBConnection.getInstance();
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sqlUpdate);
            ArrayList<HocPhan> listHocPhan = layDanhSachHocPhan(msv);
            int soluonghocphan = listHocPhan.size();
            double diemtrungbinh = 0;
            for (HocPhan hp : listHocPhan) {
                diemtrungbinh += hp.getDiem();
            }
            diemtrungbinh /= soluonghocphan;
            ps.setInt(1, soluonghocphan);
            ps.setDouble(2, diemtrungbinh);
            ps.setString(3, msv);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
