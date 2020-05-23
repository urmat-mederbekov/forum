package kg.attractor.forum.repository;

import kg.attractor.forum.model.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResponseRepo extends JpaRepository<Response, Integer> {

//    @Query(value = "SELECT * FROM forum.responses ORDER BY date_time ASC", nativeQuery = true)
    Page<Response> findAllBySubjectId(Integer id, Pageable pageable);
}
