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

@RestController
public class Build{

    @Autowired
    public Memory memory;

    @PostMapping("/new")
    public ResultPage newFile(@RequestBody Fat fat) {
        memory.dir.create(1, fat.name, fat);
        return memory.getResultPage();
    }
}
