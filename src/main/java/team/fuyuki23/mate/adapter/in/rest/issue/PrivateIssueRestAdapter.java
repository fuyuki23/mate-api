package team.fuyuki23.mate.adapter.in.rest.issue;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fuyuki23.mate.adapter.in.rest.issue.dto.PrivateCreateIssueRequest;
import team.fuyuki23.mate.application.issue.usecase.CreateIssueUseCase;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.vo.SI;

@Slf4j
@RestController
@RequestMapping("/workspaces/{slug}/projects/{identifier}/issues")
@SecurityRequirement(name = "jwt")
@RequiredArgsConstructor
public class PrivateIssueRestAdapter {

  private final CreateIssueUseCase createIssueUseCase;

  @PostMapping
  public ResponseEntity<?> createIssue(
      @PathVariable String slug,
      @PathVariable String identifier,
      @Valid @RequestBody PrivateCreateIssueRequest payload,
      @AuthenticationPrincipal User user) {
    SI si = new SI(slug, identifier);

    createIssueUseCase.createIssue(
        payload.toCommand(si, user)
    );
    return null;
  }

}
