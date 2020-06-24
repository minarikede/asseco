package com.application.asseco.web.rest;

import com.application.asseco.AssecoApplication;
import com.application.asseco.dto.input.UserInputDTO;
import com.application.asseco.enums.Role;
import com.application.asseco.model.domain.AppUser;
import com.application.asseco.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssecoApplication.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserResourceTest {

    @Autowired
    protected MockMvc restMockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

    @Transactional
    @Before
    public void setup() {
        entityManager.persist(createDefaultUser());
        entityManager.flush();
        entityManager.close();
    }

    @Transactional
    @Test
    public void testlogin() throws Exception {
        restMockMvc.perform(
                post("/login")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(new Login("admin", "pwd"))))
                .andExpect(status().isOk());

    }

    @Transactional
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "PUBLIC"})
    public void testCreateEntity() throws Exception {

        long countBeforeUpdate = userRepository.count();

        final UserInputDTO iDTO = createTestCreateInput();

        restMockMvc.perform(
                post("/users")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(iDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Full Name"));

        long countAfterUpdate = userRepository.count();
        assertThat(countAfterUpdate).isEqualTo(countBeforeUpdate + 1);

    }

    UserInputDTO createTestCreateInput() {
        UserInputDTO dto = new UserInputDTO();
        dto.setAddress("Address");
        dto.setEmail("mail@mail.com");
        dto.setName("Full Name");
        dto.setPhone("11423452");
        dto.setUserName("username");
        dto.setPassword("password");
        return dto;
    }

    AppUser createDefaultUser() {
        AppUser appUser = new AppUser();
        appUser.setPassword(pwdEncoder.encode("pwd"));
        appUser.setEmail("email@email.com");
        appUser.setName("Default User");
        appUser.setUserName("admin");
        appUser.setRoles(Arrays.asList(Role.PUBLIC, Role.ADMIN));
        return appUser;
    }

    class Login implements Serializable {
        private String userName;
        private String password;

        public Login(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
