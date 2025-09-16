package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import com.test.dto.emplVO;
import util.DBManager;

 // 16~29 라인 양식 책보고 시작했습니다.
public class emplDAO {
	private emplDAO() {}
	private static emplDAO instance = new emplDAO();
	public static emplDAO getInstance() {
		return instance;
	}
	
	public List<emplVO> selectAllEmpls(){
		String sql = "Select * from empl ORDER BY enter DESC";
		
		List<emplVO> emplList = new ArrayList<emplVO>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				emplVO eVo = new emplVO();
				eVo.setId(rs.getString("id"));
				eVo.setName(rs.getString("name"));
				emplList.add(eVo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return emplList;
	}
	
	public emplVO selectOneEmpl(String id) {
		String sql = "Select * from empl where id = ?";
		emplVO eVo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            	eVo = new emplVO();
				eVo.setId(rs.getString("id"));
				eVo.setName(rs.getString("name"));
				eVo.setPass(rs.getString("pass"));
				eVo.setLev(rs.getString("lev"));
				eVo.setBigo(rs.getString("bigo"));
				eVo.setPhone(rs.getString("phone"));
				eVo.setEnter(rs.getTimestamp("enter"));
				eVo.setGender(rs.getInt("gender"));
            }
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
            DBManager.close(rs, pstmt, conn);
        }
		
		return eVo;
	}
	
	public void updateEmpl(emplVO eVo) {
		String sql = "Update empl Set pass=?, name=?, lev=?, bigo=?, phone=?, gender=? Where id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, eVo.getPass());
			pstmt.setString(2, eVo.getName());
			pstmt.setString(3, eVo.getLev());
			pstmt.setString(4, eVo.getBigo());
			pstmt.setString(5, eVo.getPhone());
			pstmt.setInt(6, eVo.getGender());
			pstmt.setString(7, eVo.getId());
			
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
            DBManager.close(pstmt, conn);
        }
	}
	
	public void deleteEmpl(String id) {
		String sql = "Delete From empl Where id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
            DBManager.close(pstmt, conn);
        }
	}
	
	public void insertEmpl(emplVO eVo) {
		String sql = "Insert Into empl (id, pass, name, lev, bigo, phone, gender) Values (?,?,?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, eVo.getId());
			pstmt.setString(2, eVo.getPass());
			pstmt.setString(3, eVo.getName());
			pstmt.setString(4, eVo.getLev());
			pstmt.setString(5, eVo.getBigo());
			pstmt.setString(6, eVo.getPhone());
		    pstmt.setInt(7, eVo.getGender());
			
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
            DBManager.close(pstmt, conn);
        }
	}
	
	public String checkPass(String id) {
		String pass = null;
		String sql = "Select pass from empl where id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            	pass = rs.getString("pass");
            }
        } catch(SQLException e) {
			e.printStackTrace();
		} finally {
            DBManager.close(rs, pstmt, conn);
        }
		
		return pass;
	}
	
}
