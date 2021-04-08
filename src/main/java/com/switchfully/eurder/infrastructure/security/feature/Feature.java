package com.switchfully.eurder.infrastructure.security.feature;

import com.switchfully.eurder.domain.users.UserRole;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

public enum Feature {
    CREATE_ITEM(UserRole.ADMIN),
    VIEW_ORDERS(UserRole.CUSTOMER);

    private final UserRole[] roles;

    Feature(UserRole... roles) {
        this.roles = roles;
    }

    public List<UserRole> getRoles() {
        return newArrayList(roles);
    }

    public static List<Feature> getFeaturesForRoles(List<String> rolesOfUserAsString) {
        List<UserRole> rolesOfUser = rolesOfUserAsString.stream()
                .map(UserRole::valueOf)
                .collect(Collectors.toList());
        return Arrays.stream(Feature.values())
                .filter(feature -> !Collections.disjoint(feature.getRoles(), rolesOfUser))
                .collect(Collectors.toList());
    }
}
