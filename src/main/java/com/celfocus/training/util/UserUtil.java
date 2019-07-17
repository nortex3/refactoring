package com.celfocus.training.util;

import com.celfocus.training.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserUtil {
    private UserUtil() {
    }

    public static Optional<User> findUser(String name, List<User> users) {
        return Optional.ofNullable(users)
                       .orElseGet(Collections::emptyList).stream()
                       .filter(user -> name != null && Optional.ofNullable(user.getName())
                                                               .filter(name::equals)
                                                               .isPresent())
                       .findFirst();
    }

    public boolean containsUser(String name, List<User> users) {
        return findUser(name, users).isPresent();
    }
}
