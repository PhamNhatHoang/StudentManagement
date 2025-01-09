package com.edusys.dao;

import com.edusys.utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    // Lấy bảng điểm của khóa học được chỉ định
    public List<Object[]> getBangDiem(Integer makh){
        String sql = "{CALL sp_BangDiem (?)}";
        String[] cols = {"MaNH", "HoTen", "Diem"};
        return this.getListOfArray(sql, cols, makh);
    }
    
    // Lấy thông tin lượng người học theo năm
    public List<Object[]> getLuongNguoiHoc(){
        String sql = "{CALL sp_LuongNguoiHoc}";
        String[] cols = {"Nam", "SoLuong", "DauTien", "CuoiCung"};
        return this.getListOfArray(sql, cols);
    }
    
    // Lấy thông tin điểm của từng chuyên đề
    public List<Object[]> getDiemChuyenDe(){
        String sql = "{CALL sp_DiemChuyenDe}";
        String[] cols = {"ChuyenDe", "SoHV", "ThapNhat", "CaoNhat", "TrungBinh"};
        return this.getListOfArray(sql, cols);
    }
    
    // Lấy thông tin doanh thu theo năm
    public List<Object[]> getDoanhThu(int nam){
        String sql = "{CALL sp_DoanhThu (?)}";
        String[] cols = {"ChuyenDe", "SoKH", "SoHV",  "DoanhThu", "ThapNhat", "CaoNhat", "TrungBinh"};
        return this.getListOfArray(sql, cols, nam);
    }
    
    // Tránh code trùng lặp khi thực hiện truy vấn dữ liệu từ cơ sở dữ liệu
    private List<Object[]> getListOfArray(String sql, String[] cols, Object...args){
        try {
            List<Object[]> list=new ArrayList<>();
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for(int i=0; i<cols.length; i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            if (rs != null) {
                rs.getStatement().getConnection().close();
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
