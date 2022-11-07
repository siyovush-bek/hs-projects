package recipes.recipeuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import recipes.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RecipeUserService implements UserDetailsService {

    private final BCryptPasswordEncoder encoder;

    private final RecipeUserRepository repository;

    public void addNewUser(RecipeUserRequest userRequest) {
        RecipeUser user = new RecipeUser();
        user.setEmail(userRequest.getEmail());
        String encodedPassword = encoder.encode(userRequest.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);
    }

    public RecipeUser findByEmail(String email) {
        return repository.findByEmail(email)
            .orElseThrow(UserNotFoundException::new);
    }

    public List<RecipeUser> getAllUsers() {
        List<RecipeUser> userList = new ArrayList<>();
        for(RecipeUser user : repository.findAll()) {
            userList.add(user);
        }
        return userList;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
            .orElseThrow(UserNotFoundException::new);
    }
}
