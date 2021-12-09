package os.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import os.core.Memory;
import os.util.ResultPage;

@RestController
public class Ans {

    @Autowired
    public Memory memory;

    @PostMapping("/get")
    public ResultPage getAns(){
        return memory.getResultPage();
    }
}
