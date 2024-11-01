package team.fuyuki23.mate.adapter.in.rest.project;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fuyuki23.mate.adapter.in.rest.project.dto.PrivateCreateProjectRequest;
import team.fuyuki23.mate.application.project.usecase.CreateProjectUseCase;
import team.fuyuki23.mate.application.project.usecase.FindProjectByIdentifierUseCase;
import team.fuyuki23.mate.application.project.usecase.FindProjectsByWorkspaceUseCase;
import team.fuyuki23.mate.domain.vo.WPU;

@Slf4j
@RestController
@RequestMapping("/workspaces/{slug}/projects")
@SecurityRequirement(name = "jwt")
@RequiredArgsConstructor
public class PrivateProjectRestAdapter {

  private final FindProjectsByWorkspaceUseCase findProjectsByWorkspaceUseCase;
  private final CreateProjectUseCase createProjectUseCase;
  private final FindProjectByIdentifierUseCase findProjectByIdentifierUseCase;

  @GetMapping
  public ResponseEntity<?> findProjectsByWorkspace(@PathVariable String slug,
      @AuthenticationPrincipal WPU wpu) {
    log.debug("{}", wpu);
    return ResponseEntity.ok().body(findProjectsByWorkspaceUseCase.findProjectsByWorkspace(
        new FindProjectsByWorkspaceUseCase.Command(slug, wpu.user())).projects());
  }

  @PostMapping
  public ResponseEntity<?> createProject(
      @PathVariable String slug,
      @Valid @RequestBody PrivateCreateProjectRequest payload,
      @AuthenticationPrincipal WPU wpu
  ) {
    return ResponseEntity.status(201).body(
        createProjectUseCase.createProject(
            payload.toCommand(slug, wpu.user())
        )
    );
  }

  @GetMapping("/{identifier}")
  @PreAuthorize("principal.hasProject(#identifier)") // NOTE: 효자
  public ResponseEntity<?> findProjectByIdentifier(
      @PathVariable String slug,
      @PathVariable String identifier,
      @AuthenticationPrincipal WPU wpu
  ) {
    log.debug("{}", wpu);
    return ResponseEntity.ok().body(
        findProjectByIdentifierUseCase.findProjectByIdentifier(
            new FindProjectByIdentifierUseCase.Command(slug, identifier, wpu.user())
        ).project()
    );
  }

}
