package com.app.pizzaorderingsystem;

import com.app.pizzaorderingsystem.entity.Role;
import com.app.pizzaorderingsystem.entity.User;
import com.app.pizzaorderingsystem.repository.RoleRepository;
import com.app.pizzaorderingsystem.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    @Disabled
    public void testAddRolesToExistingUser() {
        User user = userRepository.findById(1L).get();

        Role roleAdmin = roleRepository.findByName("Admin");
        user.addRole(roleAdmin);

        User savedUser = userRepository.save(user);
        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }
}
