package com.el.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author:superJar
 * @date:2021/1/1
 * @time:15:28
 * @details:
 */
@Component("rbacService")
public class RbacService {

    //private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 鉴权
     * @param request
     * @param authentication
     * @return
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication){

        Object principal = authentication.getPrincipal();

        if(principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;

            String url = request.getRequestURL().toString();


            List<GrantedAuthority> authorities =
                    AuthorityUtils.commaSeparatedStringToAuthorityList(url.substring(url.lastIndexOf("/")));

            return userDetails.getAuthorities().contains(authorities.get(0));
        }

        return false;
    }
}
