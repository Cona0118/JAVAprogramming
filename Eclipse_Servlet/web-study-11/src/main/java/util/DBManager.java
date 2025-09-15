package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * DBManager 클래스 (MySQL 버전)
 * - DB 연결(Connection) 객체를 가져오고
 * - 사용이 끝난 리소스(ResultSet, Statement, Connection)를 해제하는 역할
 * - JDBC + 커넥션 풀(DataSource, JNDI) 방식을 사용
 */
public class DBManager {

    /**
     * DB Connection 객체를 가져오는 메서드 (MySQL용)
     * - JNDI를 통해 context.xml에 등록된 DataSource를 찾아서 DB 연결을 획득한다.
     * @return Connection 객체 (DB 연결)
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 1. JNDI 초기화
            Context initContext = new InitialContext();

            // 2. context.xml의 <Resource>에 등록된 DB 정보 가져오기
            Context envContext = (Context) initContext.lookup("java:/comp/env");

            // 3. "jdbc/mysql" 이라는 이름으로 DataSource 객체 찾기
            DataSource ds = (DataSource) envContext.lookup("jdbc/mysql");

            // 4. DB 연결 객체 가져오기
            conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * SELECT 실행 후 사용한 리소스를 닫는 메서드
     * @param conn  DB 연결 객체
     * @param stmt  SQL 실행 객체
     * @param rs    SELECT 결과 집합(ResultSet)
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();   // ResultSet 해제
            if (stmt != null) stmt.close(); // Statement 해제
            if (conn != null) conn.close(); // Connection 반환(커넥션 풀로 반환됨)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * DML(INSERT, UPDATE, DELETE) 실행 후 사용한 리소스를 닫는 메서드
     * - SELECT는 ResultSet이 필요하지만, DML은 필요 없음
     * @param conn DB 연결 객체
     * @param stmt SQL 실행 객체
     */
    public static void close(Connection conn, Statement stmt) {
        try {
            if (stmt != null) stmt.close(); // Statement 해제
            if (conn != null) conn.close(); // Connection 반환(커넥션 풀로 반환됨)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
