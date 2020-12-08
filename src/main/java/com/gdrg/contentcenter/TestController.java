package com.gdrg.contentcenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    /*@GetMapping("get1")
    public String gets(){
        return "hello1";
    }
*/



    @GetMapping("/get1")
    public String get1(){
        return "hello1";
    }

}
