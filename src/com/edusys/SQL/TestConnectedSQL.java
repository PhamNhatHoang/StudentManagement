package com.edusys.SQL;

import com.edusys.utils.XJdbc;
import java.sql.ResultSet;

public class TestConnectedSQL {
    public static void main(String[] args) {
        try {
            ResultSet rs = XJdbc.query("SELECT 1");
            System.out.println("Connected to SQL Server successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to connect to SQL Server!");
        }
    }
}
