package kg.attractor.forum.dto;

import kg.attractor.forum.model.Subject;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class SubjectDTO {

    public static SubjectDTO from(Subject subject){
        return SubjectDTO.builder()
                .id(subject.getId())
                .name(subject.getName())
                .content(subject.getContent())
                .dateTime(subject.getDateTime())
                .msgNumber(subject.getMsgNumber())
                .owner(UserDTO.from(subject.getOwner()))
                .build();
    }

    private int id;
    private String name;
    private String content;
    private LocalDateTime dateTime;
    private float msgNumber;
    private UserDTO owner;
}
