package kg.attractor.forum.dto;

import kg.attractor.forum.model.Response;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ResponseDTO {

    public static ResponseDTO from(Response response){
        return ResponseDTO.builder()
                .id(response.getId())
                .text(response.getText())
                .dateTime(response.getDateTime())
                .author(UserDTO.from(response.getAuthor()))
                .build();
    }
    private int id;
    private String text;
    private LocalDateTime dateTime;
    private UserDTO author;
}
