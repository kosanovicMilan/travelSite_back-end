package travelHelper.repos.user;

import travelHelper.entities.User;

import java.util.List;

public interface UserRepository {
    public User getOneUser(int user_id);
    public List<User> getAllUsers();
    public void deleteUser(int user_id);
    public User addUser(User user);
    public User findByEmail(String email);
}
