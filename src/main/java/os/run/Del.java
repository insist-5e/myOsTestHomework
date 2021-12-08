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
public class Del{

    @Autowired
    public Memory memory;

    @PostMapping("/del")
    public ResultPage delFile(@RequestBody Fat fat){
        memory.dir.rm(1, fat.name, fat);
        return memory.getResultPage();
    }
}
