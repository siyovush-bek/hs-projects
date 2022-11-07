package engine.service;

import engine.exception.EmailAlreadyTakenException;
import engine.model.QuizUser;
import engine.repository.QuizUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class QuizUserService implements UserDetailsService {

    private QuizUserRepository repository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public QuizUserService(QuizUserRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
            .orElseThrow();
    }

    public void addUser(QuizUser user) {
        if(repository.findByEmail(user.getUsername()).isPresent()) {
            throw new EmailAlreadyTakenException(
                user.getUsername() + " already taken");
        }
        user.setPassword(
            encoder.encode(user.getPassword())
        );
        repository.save(user);
    }
}
