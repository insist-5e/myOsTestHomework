package os.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import os.core.Memory;
import os.util.ResultPage;

@RestController
public class Refresh {

    @Autowired
    public Memory memory;

    @PostMapping("/refresh")
    public ResultPage refresh(){
        memory.refresh();
        return memory.getResultPage();
    }
}
