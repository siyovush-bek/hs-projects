package engine.repository;

import engine.model.QuizUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizUserRepository extends CrudRepository<QuizUser, Long> {
    Optional<QuizUser> findByEmail(String email);
}
