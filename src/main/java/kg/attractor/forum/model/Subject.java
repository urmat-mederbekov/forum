package kg.attractor.forum.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Table(name="subjects")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    private String name;

    @NotBlank
    @Size(min = 1, max = 500)
    @Column(length = 500)
    private String content;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    private LocalDateTime dateTime;

    @PositiveOrZero
    @Column(length = 128)
    private float msgNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
}
