package com.utfpr.todo.users;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserModel, UUID> {
    
    UserModel findByUsername(String username);
    List<UserModel> findAll();

}
