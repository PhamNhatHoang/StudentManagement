package com.edusys.dao;

import java.util.List;

/**
 * Lớp định nghĩa các phương thức cơ bản của một DAO (Data Access Object).
 * @param <EntityType> Kiểu dữ liệu của đối tượng thực thể (Entity).
 * @param <KeyType> Kiểu dữ liệu của khóa chính (Primary Key).
 */
abstract public class EduSysDAO<EntityType, KeyType> {
    /**
     * Thêm một đối tượng vào cơ sở dữ liệu.
     * @param entity Đối tượng cần thêm vào cơ sở dữ liệu.
     */
    abstract public void insert(EntityType entity);
    
    /**
     * Cập nhật thông tin của một đối tượng trong cơ sở dữ liệu.
     * @param entity Đối tượng cần cập nhật thông tin.
     */
    abstract public void update(EntityType entity);
    
    /**
     * Xóa một đối tượng khỏi cơ sở dữ liệu dựa trên khóa chính.
     * @param id Khóa chính của đối tượng cần xóa.
     */
    abstract public void delete(KeyType id);
    
    /**
     * Lấy thông tin của một đối tượng dựa trên khóa chính.
     * @param id Khóa chính của đối tượng cần lấy thông tin.
     * @return Đối tượng được lấy từ cơ sở dữ liệu.
     */
    abstract public EntityType selectById(KeyType id);
    
    /**
     * Lấy danh sách tất cả các đối tượng từ cơ sở dữ liệu.
     * @return Danh sách tất cả các đối tượng.
     */
    abstract public List<EntityType> selectAll();
    
    /**
     * Phương thức bảo vệ để tránh code trùng lặp khi truy vấn dữ liệu từ cơ sở dữ liệu.
     * @param sql Câu truy vấn SQL.
     * @param args Danh sách các đối số truyền vào câu truy vấn SQL.
     * @return Danh sách các đối tượng được trả về từ câu truy vấn.
     */
    abstract protected List<EntityType> selectBySql(String sql, Object...args);
}
