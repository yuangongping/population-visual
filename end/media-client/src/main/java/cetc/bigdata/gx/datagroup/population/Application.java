package cetc.bigdata.gx.datagroup.population;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * description:
 * Created by YGP on 2021-1-4
 */
@SpringBootApplication
@MapperScan("cetc.bigdata.gx.datagroup.population.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
