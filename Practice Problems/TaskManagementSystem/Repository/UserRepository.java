package TaskManagementSystem.Repository;

import TaskManagementSystem.models.User;

import java.util.Optional;

public interface UserRepository {

    void save(User user);
    Optional<User> findById(String id);
}
