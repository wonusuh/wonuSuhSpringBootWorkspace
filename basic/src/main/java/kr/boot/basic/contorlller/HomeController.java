package kr.boot.basic.contorlller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/servlet")
    public String basic(){
        return "basic";
    }
    @GetMapping("/")
    public String home() {
        return "hello";
    }
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", " 스프링 부트 시작~ ");
        return "hello";
    }
}
