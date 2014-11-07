package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SqlTest {
    public static void main(String[] args) {
        // 驱动程序名
        String driver = "com.mysql.jdbc.Driver";

        // URL指向要访问的数据库名scutcs
        String url = "jdbc:mysql://127.0.0.1:3306/lesson";

        // MySQL配置时的用户名
        String user = "root";

        // MySQL配置时的密码
        String password = "root";

        try {
            // 加载驱动程序
            Class.forName(driver);

            // 连续数据库
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = conn.prepareStatement("select * from test");
            ResultSet rst = pstmt.executeQuery();
            System.out.println(rst.next());
            System.out.println(rst.getInt(3));
            System.out.println(conn);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
