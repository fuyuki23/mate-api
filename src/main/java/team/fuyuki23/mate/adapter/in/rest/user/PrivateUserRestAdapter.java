package team.fuyuki23.mate.adapter.in.rest.user;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fuyuki23.mate.domain.User;

@Slf4j
@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "jwt")
public class PrivateUserRestAdapter {

    @GetMapping("/me")
    public ResponseEntity<?> findMyInfo(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok().body(user);
    }

}
