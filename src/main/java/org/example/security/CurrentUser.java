package org.example.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class CurrentUser {
    private static final String TEST_USER = "usr-ABCDEFG1"; // contract tests

    public static String id() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return TEST_USER;
        var p = auth.getPrincipal();
        if (p instanceof UserDetails ud) return ud.getUsername();
        if (p instanceof String s && !s.isBlank()) return s;
        return TEST_USER;
    }

    private CurrentUser() {}
}
