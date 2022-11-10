package com.app.pizzaorderingsystem;

import com.app.pizzaorderingsystem.entity.Role;
import com.app.pizzaorderingsystem.repository.RoleRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTests {

    @Autowired
    RoleRepository roleRepository;

    @Test
    @Disabled
    public void testCreateRoles() {
        Role customer = new Role("Customer");

        roleRepository.saveAll(List.of(customer));

        List<Role> listRoles = roleRepository.findAll();
        assertThat(listRoles.size()).isEqualTo(3);
    }

    @Test
    public void testDeleteRoleById() {
        Role role = roleRepository.findById(1L).get();
        roleRepository.delete(role);

        List<Role> listRoles = roleRepository.findAll();
        assertThat(listRoles.size()).isEqualTo(2);
    }
}
