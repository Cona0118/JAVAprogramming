package TESTDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class ConnectionExample {
    public static void main(String[] args) {
        Connection conn = null;
        try{
            // JDBC Driver 등록
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 연결하기
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb",
                "root",
                "univ301"
            );

            System.out.println("드라이버 로드 성공");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(conn != null){
                try{
                    // 연결 끊기
                    conn.close();
                    System.out.println("연결 끊기");
                } catch (SQLException e) {}
            }
        }
    }
}

class UserInsertExample {
    public static void main(String[] args) {
        Connection conn = null;
        try{
            // JDBC Driver 등록
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 연결하기
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb",
                "root",
                "univ301"
            );

            // 매개변수화된 SQL문 작성
            String sql = "" +
              "INSERT INTO USERS (USERID, USERNAME, USERPASSWORD, USERAGE, USERMAIL)" + "VALUES (?, ?, ?, ?, ?)";

            // PreparedStatement 얻기 및 값 지정
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "winter");
            pstmt.setString(2, "한겨울");
            pstmt.setString(3, "12345");
            pstmt.setInt(4, 25);
            pstmt.setString(5, "winter@mycompany.com");

            // SQL문 실행
            int rows = pstmt.executeUpdate();
            System.out.println("저장된 행 수:" + rows);

            // PreparedStatement 닫기
            pstmt.close();

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(conn != null){
                try{
                    // 연결 끊기
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }
}

/* Oracle
class BoradWithFileInsertExample{
    public static void main(String[] args) {
        Connection conn = null;
        try{
            // JDBC Driver 등록
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 연결하기
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb",
                "root",
                "univ301"
            );

            String sql = "" +
              "INSERT INTO BOARDS (BNO, BTITLE, BCONTENT, BWRITER, BDATE, BFILENAME, BFILEDATA)" + 
              "VALUES (SEQ_BNO.NEXTVAL, ?, ?, ?, curdate(), ?, ?)";

            // PreparedStatement 얻기 및 값 지정
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "눈 오는 날");
            pstmt.setString(2, "함박눈이 내려요");
            pstmt.setString(3, "WINTER");
            pstmt.setString(4, "snow.jpg");
            pstmt.setBlob(5, BoardInsertExample.class.getResourceAsStream("snow.jpg"));

            // SQL문 실행
            int rows = pstmt.executeUpdate();
            System.out.println("저장된 행 수: "+rows);

            // BNO 값 얻기
            if(rows == 1){
                ResultSet rs = pstmt.getGeneratedKeys();
                if(rs.next()){
                    int bno = rs.getInt(1);
                    System.out.println("저장된 bno: "+bno);
                }
                rs.close();
            }

            pstmt.close();

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(conn != null){
                try{
                    // 연결 끊기
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }
}
 */

class BoardInsertExample {
    public static void main(String[] args) {
        Connection conn = null;
        try{
            // JDBC Driver 등록
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 연결하기
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb",
                "root",
                "univ301"
            );
            /* 
            // 매개변수화된 SQL문 작성
            String sql = "" +
              "INSERT INTO BOARDS (BNO, BTITLE, BCONTENT, BWRITER, BDATE, BFILENAME, BFILEDATA)" + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            // PreparedStatement 얻기 및 값 지정
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 1);
            pstmt.setString(2, "눈 오는 날");
            pstmt.setString(3, "함박눈이 내려요");
            pstmt.setString(4, "WINTER");
            pstmt.setString(5, "2025-08-05");
            pstmt.setString(6, "snow.jpg");
            pstmt.setBlob(7, BoardInsertExample.class.getResourceAsStream("snow.jpg"));
            */

            String sql = "" +
              "INSERT INTO BOARDS (BTITLE, BCONTENT, BWRITER, BFILENAME, BFILEDATA)" + "VALUES (?, ?, ?, ?, ?)";

            // PreparedStatement 얻기 및 값 지정
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "눈 오는 날");
            pstmt.setString(2, "함박눈이 내려요");
            pstmt.setString(3, "WINTER");
            pstmt.setString(4, "snow.jpg");
            pstmt.setBlob(5, BoardInsertExample.class.getResourceAsStream("snow.jpg"));
            
            // SQL문 실행
            int rows = pstmt.executeUpdate();
            System.out.println("저장된 행 수:" + rows);

            // PreparedStatement 닫기
            pstmt.close();

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(conn != null){
                try{
                    // 연결 끊기
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }
}

class BoardUpdateExample {
    public static void main(String[] args) {
        Connection conn = null;
        try{
            // JDBC Driver 등록
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 연결하기
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb",
                "root",
                "univ301"
            );

            // String sql = "" +
            //   "UPDATE BOARDS SET BTITLE = ?, BCONTENT = ?, BWRITER = ? WHERE BNO = 2";

            String sql = new StringBuilder()
                        .append("UPDATE BOARDS SET ")
                        .append("BTITLE = ?, ")
                        .append("BCONTENT = ?, ")
                        .append("BWRITER = ? ")
                        .append("WHERE BNO = ?")
                        .toString();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "눈 사람");
            pstmt.setString(2, "눈으로 만든 사람");
            pstmt.setString(3, "Winter0");
            pstmt.setInt(4, 1);

            // SQL문 실행
            int rows = pstmt.executeUpdate();
            System.out.println("저장된 행 수:" + rows);

            // PreparedStatement 닫기
            pstmt.close();

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(conn != null){
                try{
                    // 연결 끊기
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }
}

class BoardDeleteExample {
    public static void main(String[] args) {
        Connection conn = null;
        try{
            // JDBC Driver 등록
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 연결하기
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb",
                "root",
                "univ301"
            );

            String sql = "" +
              "DELETE FROM BOARDS WHERE BNO = 2";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // SQL문 실행
            int rows = pstmt.executeUpdate();
            System.out.println("저장된 행 수:" + rows);

            // PreparedStatement 닫기
            pstmt.close();

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(conn != null){
                try{
                    // 연결 끊기
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }
}


class UserSelectExample{
    public static void main(String[] args) {
        Connection conn = null;
        try{
            //JDBC Driver 등록
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 연결하기
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb",
                "root",
                "univ301"
            );

            // 매개변수화된 SQL문 작성
            String sql = "" +
                "SELECT USERID, USERNAME, USERPASSWORD, USERAGE, USERMAIL " +
                "FROM USERS " +
                "WHERE USERID = ?"; 

            // PreparedStatement 얻기 및 값 지정
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "winter");

            // SQL문 실행 후, ResultSet을 통해 데이터 읽기
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) { // 1개의 데이터 행을 가져왔을 경우
                User user = new User();
                user.setUserId(rs.getString("USERID"));
                user.setUserName(rs.getString("USERNAME"));
                user.setUserPassword(rs.getString("USERPASSWORD"));
                user.setUserAge(rs.getInt(4));    // 컬럼 순번을 이용
                user.setUserMail(rs.getString(5));
                System.out.println(user);
            } else System.out.println("사용자 아이디가 존재하지 않습니다.");

            rs.close();

            // PreparedStatement 닫기
            pstmt.close();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(conn != null){
                try{
                    // 연결 끊기
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }
}