package com.groundhog.security.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * @author guotianyu
 */
@Data
@Builder
public class SecurityUserRole implements GrantedAuthority {
    private final String role;

    public SecurityUserRole(String role) {
        Assert.hasText(role, "A granted authority textual representation is required");
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
