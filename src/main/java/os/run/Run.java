package os.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import os.util.Fat;
import os.core.Memory;
import os.util.Pair;
import os.util.ResultPage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class Run {

    @Autowired
    public Memory memory;

    @PostMapping("/run")
    public ResultPage putIn(@RequestBody Fat fat){
        memory.fileC(fat.name);
        return memory.getResultPage();
    }
    public void free(){
        memory.free();
        System.out.println("线程已结束，内存释放成功！");
    }
}
