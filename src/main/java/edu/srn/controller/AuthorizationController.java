package edu.srn.controller;


import edu.srn.db.entity.Tenant;
import edu.srn.db.entity.User;
import edu.srn.db.repo.TenantRepository;
import edu.srn.db.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/authorize")
public class AuthorizationController {

    @Value("${authorization.token}")
    public String AUTHORIZATION_TOKEN;

    @Autowired private TenantRepository tenantRepository;
    @Autowired private UserRepository userRepository;

    @PostMapping
    public ResponseEntity authorize(HttpServletRequest httpServletRequest,
                                             @RequestHeader("Authorization") String authorization,
                                             @RequestHeader("MSTENANT_ID") String MSTENANT_ID,
                                             @RequestHeader("MSUSER_ID") String MSUSER_ID,
                                             @RequestHeader("MSUSER_MAIL") String MSUSER_MAIL){

        if(!authorization.equals(AUTHORIZATION_TOKEN))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Tenant tenant = tenantRepository.findByTenantId(MSTENANT_ID);
        if(tenant != null){
            User user = userRepository.findByUserID(MSUSER_ID);
            if(user != null) {

                httpServletRequest.getSession().setAttribute("userMappingId", user.getUserMappingId());
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
