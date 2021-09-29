package com.groundhog.security.utils;

import com.alibaba.fastjson.JSON;
import com.groundhog.security.model.dto.SecurityUserDetails;
import com.groundhog.security.model.dto.SecurityUserRole;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * @author guotianyu
 */
@Component
public class SecurityContextHolderUtil {

    public static SecurityUserDetails getPrincipal() {
        Map userDetails = (Map) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userDetailsJson = JSON.toJSONString(userDetails);
        SecurityUserDetails securityUserDetails = JSON.parseObject(userDetailsJson, SecurityUserDetails.class);

        return securityUserDetails;
    }


    public static Long getUserId() {
        return getPrincipal().getSecurityUser().getUserId();
    }


    public static Set<SecurityUserRole> getAuthorities() {
        return (Set<SecurityUserRole>) getPrincipal().getAuthorities();
    }
}