package cn.more;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AApplication {

    public static void main(String[] args) {
        SpringApplication.run(AApplication.class);
    }

    @GetMapping
    public Integer a() {
        int plus = BUtils.plus(1, AUtils.plus(2, 3));
        return plus;
    }
}
