package com.example.second;

import com.example.second.domain.Logo;
import com.example.second.domain.Users;
import com.example.second.repos.LogoRepo;
import com.example.second.repos.UsersRepo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
class GreetingController {
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private ServletContext context;

    @Autowired
    private LogoRepo logoRepo;


    public class WebMvcConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry
                    .addResourceHandler("/images/**")
                    .addResourceLocations("file:resources/")
                    .setCachePeriod(0);
        }
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Users> users = usersRepo.findAll();

        model.put("users", users);

        return "main";
    }

    @PostMapping("add")
    public String add(@RequestParam String name, @RequestParam String surname, @RequestParam String login,
                      @RequestParam String age, @RequestParam String address, @RequestParam String add_info,
                      @RequestParam String role, @RequestParam MultipartFile logo, Map<String, Object> model) {
        Logo savedLogo = null;
        try {
            String filePath = context.getRealPath("/uploads/");
            logo.transferTo(new File(filePath));
            System.out.println(filePath);
            System.out.println(logo.getOriginalFilename());
            savedLogo = new Logo(logo.getOriginalFilename(),filePath+logo.getOriginalFilename());
            logoRepo.save(savedLogo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Users user = new Users(name, surname, login, age, address, add_info, role, savedLogo);

        usersRepo.save(user);

        Iterable<Users> users = usersRepo.findAll();

        model.put("users", users);

        return "main";
    }

    @PostMapping("filter")
    public String info(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Users> users;

        if (filter != null && !filter.isEmpty()) {
            users = usersRepo.findByLogin(filter);
        } else {
            users = usersRepo.findAll();
        }

        model.put("user1", users);

        return "main";
    }

    @GetMapping("info")
    public String getInfo(@RequestParam("login") String login, Map<String, Object> model) {
        List<Users> users = new ArrayList<>();
        if (Strings.isNotBlank(login)) {
            users = usersRepo.findByLogin(login);
        }
        model.put("user1", users);
        return "main";
    }
}
