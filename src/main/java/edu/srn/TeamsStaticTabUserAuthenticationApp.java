package edu.srn;

import edu.srn.db.entity.Tenant;
import edu.srn.db.entity.User;
import edu.srn.db.repo.TenantRepository;
import edu.srn.db.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TeamsStaticTabUserAuthenticationApp {

    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(TeamsStaticTabUserAuthenticationApp.class);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadEntities() {

        String MSTENANT_ID = "XXXXXXXX";
        String MSUSER_ID = "YYYYYYYYY";
        String MSUSER_MAIL = "ZZZZZZ@mail.com";

        Tenant tenant = tenantRepository.findByTenantId(MSTENANT_ID);
        if (tenant == null) {
            tenant = Tenant.builder()
                    .tenantId(MSTENANT_ID)
                    .build();

            tenant = tenantRepository.save(tenant);
        }

        User user = userRepository.findByUserID(MSUSER_ID);
        if (user == null) {
            user = User.builder()
                    .userEmail(MSUSER_MAIL)
                    .userId(MSUSER_ID)
                    .build();

            user = userRepository.save(user);
        }
    }
}
