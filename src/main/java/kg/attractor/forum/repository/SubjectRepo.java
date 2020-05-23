package kg.attractor.forum.repository;

import kg.attractor.forum.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubjectRepo extends JpaRepository<Subject, Integer> {

    @Query(value = "SELECT * FROM subjects ORDER BY date_time DESC", nativeQuery = true)
    Page<Subject> findAll(Pageable pageable);
}
