package com.edusys.utils;

import com.edusys.entity.NhanVien;

public class Auth {
    // Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
    public static NhanVien user = null;
    
    // Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
    public static void clear() {
        Auth.user = null;       
    }
    
    // Kiểm tra xem người dùng đã đăng nhập hay chưa
    public static boolean isLogin() {
        return Auth.user != null;
    }
     
    // Kiểm tra xem người dùng có phải là trưởng phòng hay không
    public static boolean isManager() {
        return Auth.isLogin() && user.getVaiTro();
    }

    public static Object user() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}