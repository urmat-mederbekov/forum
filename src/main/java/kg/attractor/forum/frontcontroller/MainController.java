package kg.attractor.forum.frontcontroller;

import kg.attractor.forum.dto.ResponseDTO;
import kg.attractor.forum.dto.SubjectDTO;
import kg.attractor.forum.model.CustomerRegisterForm;
import kg.attractor.forum.service.ResponseService;
import kg.attractor.forum.service.SubjectService;
import kg.attractor.forum.service.UserService;
import kg.attractor.forum.util.PropertiesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {

    private final UserService userService;
    private final SubjectService subjectService;
    private final PropertiesService propertiesService;
    private final ResponseService responseService;

    @GetMapping
    public String index(Model model, Pageable pageable, HttpServletRequest uriBuilder) {

        var subjects = subjectService.getAll(pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(subjects, propertiesService.getDefaultPageSize(), model, uri);

        return "index";
    }

    @GetMapping("/subjects/{id}/responses")
    public String responsesById(@PathVariable String id, Model model, Pageable pageable,
                                HttpServletRequest uriBuilder){

        SubjectDTO subjectDTO = subjectService.getSubjectById(Integer.parseInt(id));
        model.addAttribute("dto", subjectDTO);

        var responses = responseService.getAllBySubjectId(Integer.parseInt(id), pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(responses, propertiesService.getDefaultPageSize(), model, uri);

        return "response";
    }

    @GetMapping("/profile")
    public String pageCustomerProfile(Model model, Principal principal)
    {
        var user = userService.getByEmail(principal.getName());
        model.addAttribute("dto", user);
        return "profile";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {

        if (!model.containsAttribute("dto")) {
            model.addAttribute("dto", new CustomerRegisterForm());
        }

        return "register";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }
}
