package com.edusys.dao;

import com.edusys.utils.XJdbc;
import com.edusys.entity.ChuyenDe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String>{

    // Thêm một chuyên đề vào cơ sở dữ liệu
    public void insert(ChuyenDe model){
        String sql="INSERT INTO ChuyenDe (MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa) VALUES (?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql, 
                model.getMaCD(), 
                model.getTenCD(), 
                model.getHocPhi(), 
                model.getThoiLuong(), 
                model.getHinh(),
                model.getMoTa());
    }
    
    // Cập nhật thông tin một chuyên đề trong cơ sở dữ liệu
    public void update(ChuyenDe model){
        String sql="UPDATE ChuyenDe SET TenCD=?, HocPhi=?, ThoiLuong=?, Hinh=?, MoTa=? WHERE MaCD=?";
        XJdbc.update(sql, 
                model.getTenCD(), 
                model.getHocPhi(), 
                model.getThoiLuong(), 
                model.getHinh(), 
                model.getMoTa(),
                model.getMaCD());
    }
    
    // Xóa một chuyên đề khỏi cơ sở dữ liệu
    public void delete(String MaCD){
        String sql="DELETE FROM ChuyenDe WHERE MaCD=?";
        XJdbc.update(sql, MaCD);
    }
    
    // Lấy danh sách tất cả các chuyên đề từ cơ sở dữ liệu
    public List<ChuyenDe> selectAll(){
        String sql="SELECT * FROM ChuyenDe";
        return selectBySql(sql);
    }
    
    // Lấy thông tin một chuyên đề dựa trên mã chuyên đề
    public ChuyenDe selectById(String macd){
        String sql="SELECT * FROM ChuyenDe WHERE MaCD=?";
        List<ChuyenDe> list = selectBySql(sql, macd);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    // Tránh code trùng lặp khi truy vấn dữ liệu từ cơ sở dữ liệu
    protected List<ChuyenDe> selectBySql(String sql, Object...args){
        List<ChuyenDe> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    ChuyenDe entity=new ChuyenDe();
                    entity.setMaCD(rs.getString("MaCD"));
                    entity.setHinh(rs.getString("Hinh"));
                    entity.setHocPhi(rs.getDouble("HocPhi"));
                    entity.setMoTa(rs.getString("MoTa"));
                    entity.setTenCD(rs.getString("TenCD"));
                    entity.setThoiLuong(rs.getInt("ThoiLuong"));
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
}
