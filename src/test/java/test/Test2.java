package test;

import org.springframework.jdbc.core.JdbcTemplate;
import util.JdbcUtil;

import java.util.Random;

public class Test2 {
    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate(JdbcUtil.getDataSource());
        String str = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        String sql = "insert into t_user (username,password) values (?,123456)";

        for(int i=0;i<100;i++){
            for(int j=0;j<4;j++){
                int index = random.nextInt(str.length());
                sb.append(str.charAt(index));
            }
            template.update(sql,sb.toString());
            sb.setLength(0);
        }

    }
}
