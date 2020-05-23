package kg.attractor.forum.service;

import kg.attractor.forum.dto.ResponseDTO;
import kg.attractor.forum.model.Response;
import kg.attractor.forum.model.Subject;
import kg.attractor.forum.model.User;
import kg.attractor.forum.repository.ResponseRepo;
import kg.attractor.forum.repository.SubjectRepo;
import kg.attractor.forum.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ResponseService {

    private final ResponseRepo responseRepo;
    private final UserRepo userRepo;
    private final SubjectRepo subjectRepo;

    public Page<ResponseDTO> getAll(Pageable pageable){
        Page<Response> responses = responseRepo.findAll(pageable);
        return responses.map(ResponseDTO::from);
    }

    public Page<ResponseDTO> getAllBySubjectId(Integer id, Pageable pageable){
        return responseRepo.findAllBySubjectId(id, pageable).map(ResponseDTO::from);
    }

    public void respond(ResponseDTO messageDTO, String email) {

        User user = userRepo.findByEmail(email).get();
//                .orElseThrow(() -> new ResourceNotFoundException("Can't find user with the name: " + commenterUsername));

        Subject subject = subjectRepo.findById(messageDTO.getId()).get();

        System.out.println(subject);

        Response response = Response.builder()
                .text(messageDTO.getText())
                .dateTime(LocalDateTime.now())
                .subject(subject)
                .author(user)
                .build();

        responseRepo.save(response);
    }
}
