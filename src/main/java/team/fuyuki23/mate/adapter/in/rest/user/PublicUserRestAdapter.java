package team.fuyuki23.mate.adapter.in.rest.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fuyuki23.mate.adapter.in.rest.user.dto.PublicUserRequest;

@RestController
@RequestMapping("/users")
public class PublicUserRestAdapter {

  @PostMapping("/login")
  public ResponseEntity<?> Login(@Valid @RequestBody PublicUserRequest.LoginDto payload) {
    return ResponseEntity.ok().build();
  }

}
