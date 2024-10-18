package team.fuyuki23.mate.adapter.in.rest.issue;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fuyuki23.mate.common.annotation.FromPath;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.vo.SI;

@Slf4j
@RestController
@RequestMapping("/workspaces/{slug}/projects/{identifier}/issues")
@SecurityRequirement(name = "jwt")
@RequiredArgsConstructor
public class PrivateIssueRestAdapter {

  @PostMapping
  public ResponseEntity<?> createIssue(@FromPath SI si, @AuthenticationPrincipal User user) {
    log.info("slug: {}, identifier: {}", si.slug(), si.identifier());
    return null;
  }

}
