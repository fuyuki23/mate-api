package team.fuyuki23.mate.adapter.in.rest.issue;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.fuyuki23.mate.adapter.in.rest.issue.dto.PrivateCreateIssueRequest;
import team.fuyuki23.mate.adapter.in.rest.issue.dto.PrivateFindIssuesRequest;
import team.fuyuki23.mate.application.issue.usecase.CreateIssueUseCase;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.vo.SI;
import team.fuyuki23.mate.domain.vo.WPU;

@Slf4j
@RestController
@RequestMapping("/workspaces/{slug}/projects/{identifier}/issues")
@SecurityRequirement(name = "jwt")
@RequiredArgsConstructor
public class PrivateIssueRestAdapter {

  private final CreateIssueUseCase createIssueUseCase;

  @PostMapping
  @ApiResponse(responseCode = "201", description = "Issue created")
  public ResponseEntity<UUID> createIssue(@PathVariable String slug,
      @PathVariable String identifier, @Valid @RequestBody PrivateCreateIssueRequest payload,
      @AuthenticationPrincipal User user) {
    SI si = new SI(slug, identifier);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(createIssueUseCase.createIssue(payload.toCommand(si, user)).id());
  }

  @GetMapping
  public ResponseEntity<?> findIssuesWithPage(
      @PathVariable String slug,
      @PathVariable String identifier,
      @RequestParam PrivateFindIssuesRequest payload,
      @AuthenticationPrincipal WPU wpu) {
    return ResponseEntity.ok().build();
  }

}
