package my.pro.acommunity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("my.pro.acommunity.mapper")
public class AcommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcommunityApplication.class, args);
    }

}
