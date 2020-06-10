package com.wedevol.springdatacouchbase.core.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.wedevol.springdatacouchbase.core.dao.UserRepository;
import com.wedevol.springdatacouchbase.core.dao.doc.UserBasicDoc;
import com.wedevol.springdatacouchbase.core.dao.doc.UserDoc;
import com.wedevol.springdatacouchbase.core.exception.ApiException;

/**
 * Test the User Service Implementation: test the service logic
 *
 * @author Charz++
 */

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private static final Long USER_ONE_ID = 1L;
    private static final String USER_ONE_KEY = UserDoc.getKeyFor(USER_ONE_ID);

    @Mock
    private UserRepository repoMock;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDoc userDoc;
    private UserBasicDoc userBasicDoc;

    @BeforeEach // For example, to reinitialize some class attributes used by the methods.
    public void init() {
        userDoc = new UserDoc();
        userDoc.setId(USER_ONE_ID);
        userDoc.setName("Carlos");
        userDoc.setNicknames(Arrays.asList("charz", "carlito"));
        userDoc.setAge(26);
        userDoc.setEmail("carlos@yopmail.com");

        userBasicDoc = new UserBasicDoc();
        userBasicDoc.setId(USER_ONE_ID);
        userBasicDoc.setName("Carlos");
    }

    @Test
    @DisplayName("Find one and user exists")
    public void findOneAndUserExists() {
        // Data preparation
        Mockito.when(repoMock.findById(USER_ONE_KEY)).thenReturn(Optional.of(userDoc));

        // Method call
        UserDoc user = userService.findByIdOrThrow(USER_ONE_ID);

        // Verification
        Assertions.assertNotNull(user);
        Mockito.verify(repoMock, Mockito.times(1)).findById(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void findOneAndUserIsNull() {
        // Method call
        Assertions.assertThrows(ApiException.class, () -> {
            UserDoc user = userService.findByIdOrThrow(USER_ONE_ID);
            Assertions.assertNull(user);
        });
        // Verification
        Mockito.verify(repoMock, Mockito.times(1)).findById(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void createUserAndUserAlreadyExists() {
        // Data preparation
        Mockito.when(repoMock.findByEmail("carlos@yopmail.com")).thenReturn(userDoc);

        // Method call
        Assertions.assertThrows(ApiException.class, () -> {
            UserDoc user = userService.create(userDoc);
            Assertions.assertNull(user);
        });

        // Verification
        Mockito.verify(repoMock, Mockito.times(1)).findByEmail(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void updateUserAndUserNotExists() {
        // Method call
        Assertions.assertThrows(ApiException.class, () -> {
            userService.update(USER_ONE_ID, userDoc);
        });

        // Verification
        Mockito.verify(repoMock, Mockito.times(1)).findById(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void findUsersByNicknameAndUsersExist() {
        // Data preparation
        List<UserDoc> users = Arrays.asList(userDoc, userDoc, userDoc);
        Mockito.when(repoMock.findUsersWithNickname(ArgumentMatchers.anyString())).thenReturn(users);

        // Method call
        List<UserDoc> userList = userService.findUsersByNickname("charz");

        // Verification
        MatcherAssert.assertThat(userList, Matchers.hasSize(3));
        Mockito.verify(repoMock, Mockito.times(1)).findUsersWithNickname(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void findUsersByNameAndUsersExist() {
        // Data preparation
        List<UserBasicDoc> users = Arrays.asList(userBasicDoc);
        Mockito.when(repoMock.findUsersWithName(ArgumentMatchers.anyString())).thenReturn(users);

        // Method call
        List<UserBasicDoc> userList = userService.findUsersByName("CARLOS");

        // Verification
        MatcherAssert.assertThat(userList, Matchers.hasSize(1));
        Mockito.verify(repoMock, Mockito.times(1)).findUsersWithName(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void findAllUsers() {
        // Data preparation
        List<UserDoc> users = Arrays.asList(userDoc, userDoc, userDoc);
        Mockito.when(repoMock.findAllUsers()).thenReturn(users);

        // Method call
        List<UserDoc> userList = userService.findAll();

        // Verification
        MatcherAssert.assertThat(userList, Matchers.hasSize(3));
        Mockito.verify(repoMock, Mockito.times(1)).findAllUsers();
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void countAllUsers() {
        // Data preparation
        List<UserDoc> users = Arrays.asList(userDoc, userDoc, userDoc);
        Mockito.when(repoMock.countAll()).thenReturn(users.size());

        // Method call
        Integer usersQty = userService.countAll();

        // Verification
        MatcherAssert.assertThat(usersQty, Matchers.is(3));
        Mockito.verify(repoMock, Mockito.times(1)).countAll();
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void deleteUsersByAge() {
        // Data preparation
        List<UserDoc> users = Arrays.asList(userDoc);
        Mockito.when(repoMock.deleteUsersWithAge(ArgumentMatchers.anyInt())).thenReturn(users);

        // Method call
        List<UserDoc> usersDeleted = userService.deleteUsersByAge(18);

        // Verification
        MatcherAssert.assertThat(usersDeleted.size(), Matchers.is(1));
        Mockito.verify(repoMock, Mockito.times(1)).deleteUsersWithAge(ArgumentMatchers.anyInt());
        Mockito.verifyNoMoreInteractions(repoMock);
    }
}
