package hello.aop.exam;

import hello.aop.RetryAspect;
import hello.aop.TraceAspect;
import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Slf4j
//@Import(TraceAspect.class)
@Import({TraceAspect.class, RetryAspect.class})
public class ExamTest {

    @Autowired
    ExamService examService;

    @Test
    void test() {
        for( int i =0; i<4; i++ ){
            log.info("client request {}", i);
            examService.request("Data"+i);
        }
    }
}
