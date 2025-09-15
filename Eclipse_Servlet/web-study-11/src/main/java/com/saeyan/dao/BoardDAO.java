package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;
import com.saeyan.dto.BoardVO;

// 게시판 테이블을 엑세스 하는 DAO 클래스
// MVC 패턴의 Model 역할
public class BoardDAO {
	
	// 싱글톤 패턴 적용
	private BoardDAO() {}
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	
	// 최신글 부터 전체 게시글 조회
	public List<BoardVO> selectAllBoards(){
		// 실행할 SQL문 (게시판 테이블의 모든 데이터를 글번호(num) 기준 내림차순 정렬)
		String sql = "SELECT * FROM board ORDER BY num DESC";
		
		// 조회된 게시글들을 담을 리스트 (BoardVO 객체들의 모음)
		List<BoardVO> list = new ArrayList<>();
		
		// DB 연결 및 SQL 실행에 필요한 객체들
		Connection conn = null; // DB 연결 객체
		Statement stmt = null;  // SQL 실행 객체
		ResultSet rs = null;    // SELECT 결과 집합
		
		try {
			// 1. DB 연결 가져오기 
			conn = DBManager.getConnection(); 
			
			// 2. SQL 실행준비 (Statement 객체 생성)
			stmt = conn.createStatement();
			
			// 3. SQL 실행하여 결과를 ResultSet으로
			rs = stmt.executeQuery(sql);
			
			// 4. 결과 집합을 한행씩 반복처리
			while(rs.next()) {
				// 4-1. 한행의 데이터를 담을 VO 객체
				BoardVO bVo = new BoardVO();
				
				// 4-2. ResultSet에서 컬럼값을 꺼내 BoardVO에 저장
				bVo.setNum(rs.getInt("num"));              // 글번호
	            bVo.setName(rs.getString("name"));         // 작성자 이름
	            bVo.setEmail(rs.getString("email"));       // 이메일
	            bVo.setPass(rs.getString("pass"));         // 비밀번호
	            bVo.setTitle(rs.getString("title"));       // 글 제목
	            bVo.setContent(rs.getString("content"));   // 글 내용
	            bVo.setReadcount(rs.getInt("readcount"));  // 조회수
	            bVo.setWritedate(rs.getTimestamp("writedate")); // 작성일자
	            
	            // 4-3. 완성된 BoardVO 객체를 리스트에 추가
	            list.add(bVo);
			}
		} catch (SQLException e) {
			// SQL 실행과정에서 발생한 예외
			e.printStackTrace();
		} finally {
			// 5. 사용한 리소스 해제
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}
	
	// 게시글 등록
	public void insertBoard(BoardVO bVo) {
		String sql = "INSERT INTO board(name, email, pass, title, content) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection(); // DB 연결 가져오기
			pstmt = conn.prepareStatement(sql); // SQL 준비
			pstmt.setString(1, bVo.getName()); // 이름
			pstmt.setString(2, bVo.getEmail()); // 이메일
			pstmt.setString(3, bVo.getPass()); // 비밀번호
			pstmt.setString(4, bVo.getTitle()); // 제목
			pstmt.setString(5, bVo.getContent()); // 내용
			pstmt.executeUpdate(); // INSERT 실행
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt); 
		}
	}
	
	// 게시글 조회수 증가
	public void updateReadCount(String num) {
		// num이 일치하는 행의 readcount를 1 증가
		String sql = "UPDATE board SET readcount = readcount + 1 WHERE num = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection(); 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num); // 조회수 증가할 게시글 번호
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt); 
		}
	}
	
	// 글번호로 게시글 조회 (상세보기)
    public BoardVO selectOneBoardByNum(String num) {
        String sql = "SELECT * FROM board WHERE num = ?"; // 글 번호로 게시글 검색
        BoardVO bVo = null; // 결과 저장용 객체 초기화

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; // 결과Set

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                bVo = new BoardVO();
                bVo.setNum(rs.getInt("num"));
                bVo.setName(rs.getString("name"));
                bVo.setPass(rs.getString("pass"));
                bVo.setEmail(rs.getString("email"));
                bVo.setTitle(rs.getString("title"));
                bVo.setContent(rs.getString("content"));
                bVo.setWritedate(rs.getTimestamp("writedate"));
                bVo.setReadcount(rs.getInt("readcount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return bVo; // num은 pk이기 때문에 중복될 일이 없어 리스트로 리턴할 필요 없음
    }

    // 게시글 수정 (UPDATE)
    public void updateBoard(BoardVO bVo) {
        String sql = "UPDATE board SET name=?, email=?, pass=?, title=?, content=? WHERE num=?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bVo.getName());
            pstmt.setString(2, bVo.getEmail());
            pstmt.setString(3, bVo.getPass());
            pstmt.setString(4, bVo.getTitle());
            pstmt.setString(5, bVo.getContent());
            pstmt.setInt(6, bVo.getNum());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
    }

    // 비밀번호 확인 (글 삭제/수정 전 확인)
    public BoardVO checkPassword(String pass, String num) {
        String sql = "SELECT * FROM board WHERE pass=? AND num=?"; // 조건 일치 확인
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BoardVO bVo = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pass);
            pstmt.setString(2, num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                bVo = new BoardVO();
                bVo.setNum(rs.getInt("num"));
                bVo.setName(rs.getString("name"));
                bVo.setEmail(rs.getString("email"));
                bVo.setPass(rs.getString("pass"));
                bVo.setTitle(rs.getString("title"));
                bVo.setContent(rs.getString("content"));
                bVo.setReadcount(rs.getInt("readcount"));
                bVo.setWritedate(rs.getTimestamp("writedate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return bVo;
    }

    // 게시글 삭제
    public void deleteBoard(String num) {
        String sql = "DELETE FROM board WHERE num = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, num);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
    }
}
