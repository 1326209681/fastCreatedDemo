import com.demo.entity.Teachplan;
import com.demo.mapper.DemoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: ComTest
 * @author: 张磊
 * @create: 2019/4/15-19:49
 **/
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class ComTest {

    @Autowired
    private DemoMapper demoMapper;


    @Test
    public void test(){
        List<Teachplan> list=demoMapper.findById("张三");
        System.out.println(list.toString());
    }

    @Test
    public void test1(){
        int i=demoMapper.deleteById(3);
        System.out.println(i);
    }
}
