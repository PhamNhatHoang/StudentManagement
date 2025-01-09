package com.edusys.dao;

import com.edusys.utils.XJdbc;
import com.edusys.entity.HocVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HocVienDAO extends EduSysDAO<HocVien, Integer>{

    // Thêm một học viên vào cơ sở dữ liệu
    public void insert(HocVien model){
        String sql="INSERT INTO HocVien(MaKH, MaNH, Diem) VALUES(?, ?, ?)";
        XJdbc.update(sql, 
                model.getMaKH(), 
                model.getMaNH(), 
                model.getDiem());
    }
    
    // Cập nhật thông tin của một học viên trong cơ sở dữ liệu
    public void update(HocVien model){
        String sql="UPDATE HocVien SET MaKH=?, MaNH=?, Diem=? WHERE MaHV=?";
        XJdbc.update(sql, 
                model.getMaKH(), 
                model.getMaNH(), 
                model.getDiem(), 
                model.getMaHV());
    }
    
    // Xóa một học viên khỏi cơ sở dữ liệu dựa trên mã học viên
    public void delete(Integer MaHV){
        String sql="DELETE FROM HocVien WHERE MaHV=?";
        XJdbc.update(sql, MaHV);
    }
    
    // Lấy danh sách tất cả các học viên từ cơ sở dữ liệu
    public List<HocVien> selectAll(){
        String sql="SELECT * FROM HocVien";
        return selectBySql(sql);
    }
    
    // Lấy thông tin một học viên dựa trên mã học viên
    public HocVien selectById(Integer mahv){
        String sql="SELECT * FROM HocVien WHERE MaHV=?";
        List<HocVien> list = selectBySql(sql, mahv);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    // Tránh code trùng lặp khi truy vấn dữ liệu từ cơ sở dữ liệu
    protected List<HocVien> selectBySql(String sql, Object...args){
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    HocVien entity=new HocVien();
                    entity.setMaHV(rs.getInt("MaHV"));
                    entity.setMaKH(rs.getInt("MaKH"));
                    entity.setMaNH(rs.getString("MaNH"));
                    entity.setDiem(rs.getDouble("Diem"));
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

    // Lấy danh sách học viên theo mã khóa học
    public List<HocVien> selectByKhoaHoc(int maKH) {
        String sql="SELECT * FROM HocVien WHERE MaKH=?";
        return this.selectBySql(sql, maKH);
    }
}
