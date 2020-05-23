package kg.attractor.forum.service;

import kg.attractor.forum.dto.SubjectDTO;
import kg.attractor.forum.model.Subject;
import kg.attractor.forum.model.User;
import kg.attractor.forum.repository.SubjectRepo;
import kg.attractor.forum.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SubjectService {

    private final SubjectRepo subjectRepo;
    private final UserRepo userRepo;

    public Page<SubjectDTO> getAll(Pageable pageable){
        return subjectRepo.findAll(pageable).map(SubjectDTO::from);
    }

    public SubjectDTO getSubjectById(Integer id){
        return SubjectDTO.from(subjectRepo.findById(id).get());
    }

    public void createSubject(SubjectDTO subjectDTO, Authentication authentication){

        User user = userRepo.findByEmail(authentication.getName()).get();

        Subject subject = Subject.builder()
                .name(subjectDTO.getName())
                .content(subjectDTO.getContent())
                .dateTime(LocalDateTime.now())
                .msgNumber(0)
                .owner(user)
                .build();

        subjectRepo.save(subject);
    }
}
