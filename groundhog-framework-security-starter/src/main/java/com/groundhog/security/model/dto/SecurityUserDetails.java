package com.groundhog.security.model.dto;

import com.groundhog.security.model.entity.OauthUserAccount;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @author: guotianyu
 * @description:
 * @create: 2021/09/16 21:26
 */
@Data
@Builder
public class SecurityUserDetails implements UserDetails {

    private OauthUserAccount securityUser;
    private Set<SecurityUserRole> roleList;


    public SecurityUserDetails() {
    }

    public SecurityUserDetails(OauthUserAccount securityUser, Set<SecurityUserRole> roleList) {
        this.securityUser = securityUser;
        this.roleList = roleList;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return securityUser.getPassword();
    }

    @Override
    public String getUsername() {
        return securityUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return securityUser.getStatus();
    }
}
