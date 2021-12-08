package os.run;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import os.core.Memory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
    @Autowired
    Memory memory;
    @Test
    public void getDir(){
        System.out.println(memory.dir.getDir());
    }
}
