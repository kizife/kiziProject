// 일단 제출하고 수정하겠습니다......

package daily0723;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import daily0723.ArticleDto;
import daily0723.DBUtil3;


public class JDBCArticle {
    private final String url = "jdbc:mysql://127.0.0.1:3306/ws_board?useUnicode=yes&characterEncoding=UTF-8";
    static final String ID = "ssafy";
    static final String PASSWORD = "ssafy";

    public void insertArticle(String username, String subject, String content) {
        String sql = "insert into article (username, subject, content) values (?, ?, ?)";

        try (conection con = DriverManager.getconection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, subject);
            pstmt.setString(3, content);
            pstmt.executeUpdate();
            System.out.println("insert success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateArticle(int idx, String username, String subject, String content) {
        String sql = "UPDATE article SET username = ?, subject = ?, content = ? WHERE idx = ?";

        try (conection con = DriverManager.getconection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, subject);
            pstmt.setString(3, content);
            pstmt.setInt(4, idx);
            pstmt.executeUpdate();
            System.out.println("update success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteArticle(int idx) {
        String sql = "DELETE FROM article WHERE idx = ?";

        try (conection con = DriverManager.getconection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, idx);
            pstmt.executeUpdate();
            System.out.println("delete success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArticleDto selectArticle(int idx) {
        String sql = "SELECT * FROM article WHERE idx = ?";
        ArticleDto article = null;

        try (conection con = DriverManager.getconection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, idx);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    article = new ArticleDto();
                    article.setIdx(rs.getInt("idx"));
                    article.setUsername(rs.getString("username"));
                    article.setSubject(rs.getString("subject"));
                    article.setContent(rs.getString("content"));
                    article.setRegtime(rs.getTimestamp("regtime"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    public List<ArticleDto> selectAllArticle() {
        String sql = "SELECT * FROM article";
        List<ArticleDto> articles = new ArrayList<>();

        try (conection con = DriverManager.getconection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ArticleDto article = new ArticleDto();
                article.setIdx(rs.getInt("idx"));
                article.setUsername(rs.getString("username"));
                article.setSubject(rs.getString("subject"));
                article.setContent(rs.getString("content"));
                article.setRegtime(rs.getTimestamp("regtime"));
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public static void main(String[] args) {
        JDBCArticle test = new JDBCArticle();
        test.insertArticle("홍길동", "제목입력", "내용입력");
        System.out.println("========================");
        System.out.println(test.selectAllArticle());
        System.out.println("========================");

        ArticleDto article = test.selectArticle(1);
        System.out.println(article);
        test.updateArticle(1, "홍길동", "제목수정", "내용수정");
        article = test.selectArticle(1);
        System.out.println(article);
        System.out.println("========================");

        test.deleteArticle(1);
        System.out.println(test.selectAllArticle());
        System.out.println("========================");
    }
}
