package kg.attractor.forum.restcontroller;

import kg.attractor.forum.dto.ResponseDTO;
import kg.attractor.forum.dto.SubjectDTO;
import kg.attractor.forum.service.ResponseService;
import kg.attractor.forum.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/api/subjects")
@AllArgsConstructor
public class SubjectRestController {

    private final SubjectService subjectService;
    private final ResponseService responseService;

    @PostMapping
    public String createSubject(SubjectDTO subjectDTO, Authentication authentication) {

        subjectService.createSubject(subjectDTO, authentication);

        return "redirect:/";
    }

    @GetMapping("/{id}/responses")
    @ResponseBody
    public List<ResponseDTO> getResponsesBySubjectId(@PathVariable String id, Pageable pageable){
        return responseService.getAllBySubjectId(Integer.parseInt(id), pageable).getContent();
    }
}
