package com.edusys.dao;

import com.edusys.utils.XJdbc;
import com.edusys.entity.KhoaHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer>{

    // Thêm một khóa học vào cơ sở dữ liệu
    public void insert(KhoaHoc model){
        String sql="INSERT INTO KhoaHoc (MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV) VALUES (?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql, 
                model.getMaCD(), 
                model.getHocPhi(), 
                model.getThoiLuong(), 
                model.getNgayKG(),
                model.getGhiChu(),
                model.getMaNV());
    }
    
    // Cập nhật thông tin của một khóa học trong cơ sở dữ liệu
    public void update(KhoaHoc model){
        String sql="UPDATE KhoaHoc SET MaCD=?, HocPhi=?, ThoiLuong=?, NgayKG=?, GhiChu=?, MaNV=? WHERE MaKH=?";
        XJdbc.update(sql, 
                model.getMaCD(), 
                model.getHocPhi(), 
                model.getThoiLuong(), 
                model.getNgayKG(), 
                model.getGhiChu(),
                model.getMaNV(),
                model.getMaKH());
    }
    
    // Xóa một khóa học khỏi cơ sở dữ liệu dựa trên mã khóa học
    public void delete(Integer MaKH){
        String sql="DELETE FROM KhoaHoc WHERE MaKH=?";
        XJdbc.update(sql, MaKH);
    }
    
    // Lấy danh sách tất cả các khóa học từ cơ sở dữ liệu
    public List<KhoaHoc> selectAll(){
        String sql="SELECT * FROM KhoaHoc";
        return selectBySql(sql);
    }
    
    // Lấy thông tin một khóa học dựa trên mã khóa học
    public KhoaHoc selectById(Integer makh){
        String sql="SELECT * FROM KhoaHoc WHERE MaKH=?";
        List<KhoaHoc> list = selectBySql(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    // Phương thức bảo vệ để tránh code trùng lặp khi truy vấn dữ liệu từ cơ sở dữ liệu
    protected List<KhoaHoc> selectBySql(String sql, Object...args){
        List<KhoaHoc> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    KhoaHoc entity=new KhoaHoc();
                    entity.setMaKH(rs.getInt("MaKH"));
                    entity.setHocPhi(rs.getDouble("HocPhi"));
                    entity.setThoiLuong(rs.getInt("ThoiLuong"));
                    entity.setNgayKG(rs.getDate("NgayKG"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setNgayTao(rs.getDate("NgayTao"));
                    entity.setMaCD(rs.getString("MaCD"));
                    list.add(entity);
                }
            } 
            finally{
                if (rs != null) {
                    rs.getStatement().getConnection().close();
                }
            }
        } 
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    // Lấy danh sách khóa học theo mã chuyên đề
    public List<KhoaHoc> selectByChuyenDe(String macd){
        String sql="SELECT * FROM KhoaHoc WHERE MaCD=?";
        return this.selectBySql(sql, macd);
    }

    // Lấy danh sách các năm mà có khóa học được tổ chức
    public List<Integer> selectYears() {
        String sql="SELECT DISTINCT year(NgayKG) Year FROM KhoaHoc ORDER BY Year DESC";
        List<Integer> list=new ArrayList<>();
        try {
           ResultSet rs = XJdbc.query(sql);
           while(rs.next()){
                 list.add(rs.getInt(1));
            }
            if (rs != null) {
                rs.getStatement().getConnection().close();
            }
            return list;
        } 
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
