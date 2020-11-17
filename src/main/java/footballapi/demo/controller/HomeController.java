package footballapi.demo.controller;

import footballapi.demo.service.SecurityContextHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final SecurityContextHolderService contextHolderService;

    @Autowired
    public HomeController(SecurityContextHolderService contextHolderService) {
        this.contextHolderService = contextHolderService;
    }

//    @GetMapping
//    public String username() {
//        return contextHolderService.getUserUsername();
//    }
}
