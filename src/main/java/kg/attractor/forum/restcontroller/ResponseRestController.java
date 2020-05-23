package kg.attractor.forum.restcontroller;

import kg.attractor.forum.dto.ResponseDTO;
import kg.attractor.forum.service.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/responses")
@AllArgsConstructor
public class ResponseRestController {

    private final ResponseService responseService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void comment(@RequestBody ResponseDTO messageDTO,
                              Authentication authentication) {
        String authenticationName = authentication.getName();
        responseService.respond(messageDTO, authenticationName);
    }
}
