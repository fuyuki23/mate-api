package team.fuyuki23.mate.adapter.in.rest.workspace;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fuyuki23.mate.adapter.in.rest.workspace.dto.PrivateWorkspaceRequest;
import team.fuyuki23.mate.application.workspace.usecase.CreateWorkspaceUseCase;
import team.fuyuki23.mate.application.workspace.usecase.FindWorkspaceBySlugUseCase;
import team.fuyuki23.mate.application.workspace.usecase.FindWorkspacesByUserUseCase;
import team.fuyuki23.mate.domain.User;

@RestController
@RequestMapping("/workspaces")
@SecurityRequirement(name = "jwt")
@RequiredArgsConstructor
public class PrivateWorkspaceRestAdapter {

  private final FindWorkspacesByUserUseCase findWorkspacesByUserUseCase;
  private final FindWorkspaceBySlugUseCase findWorkspaceBySlugUseCase;
  private final CreateWorkspaceUseCase createWorkspaceUseCase;

  @GetMapping
  public ResponseEntity<?> findWorkspacesByUser(
      @AuthenticationPrincipal User user
  ) {
    return ResponseEntity.ok(findWorkspacesByUserUseCase.findWorkspacesByUser(
        new FindWorkspacesByUserUseCase.Command(user)
    ).workspaces());
  }

  @GetMapping("/{slug}")
  public ResponseEntity<?> FindWorkspaceBySlug(
      @PathVariable String slug,
      @AuthenticationPrincipal User user
  ) {
    return ResponseEntity.ok(findWorkspaceBySlugUseCase.findWorkspaceBySlug(
        new FindWorkspaceBySlugUseCase.Command(slug, user)
    ).workspace());
  }

  @PostMapping
  public ResponseEntity<?> createWorkspace(
      @RequestBody @Valid PrivateWorkspaceRequest.CreateDto payload,
      @AuthenticationPrincipal User user
  ) {
    return ResponseEntity.status(HttpStatus.CREATED.value()).body(createWorkspaceUseCase.create(
        payload.toCommand(user)
    ).workspace());
  }

}
