package ec.com.nwi.springaws.service;

import ec.com.nwi.springaws.domain.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    // JPA/Hibernate > Database
    // UserDaoService > Static List
    private static int usersCount = 0;

    private static List<User> users = new ArrayList<>();

    static {
        users.add(User.builder().id(1).name("Adam").birthDate(LocalDate.now().minusYears(30)).build());
        users.add(User.builder().id(2).name("Eve").birthDate(LocalDate.now().minusYears(25)).build());
        users.add(User.builder().id(3).name("Jim").birthDate(LocalDate.now().minusYears(20)).build());
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
