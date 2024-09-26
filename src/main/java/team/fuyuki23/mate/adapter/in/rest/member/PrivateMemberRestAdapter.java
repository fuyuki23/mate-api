package team.fuyuki23.mate.adapter.in.rest.member;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fuyuki23.mate.application.member.usecase.FindMembersByWorkspaceUseCase;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.domain.User;

@RestController
@RequestMapping("/workspaces/{slug}/members")
@SecurityRequirement(name = "jwt")
@RequiredArgsConstructor
public class PrivateMemberRestAdapter {

  private final FindMembersByWorkspaceUseCase findMembersByWorkspaceUseCase;

  @GetMapping
  public ResponseEntity<?> findMembersByWorkspace(
      @PathVariable String slug,
      @AuthenticationPrincipal User user
  ) throws ApiException {
    // TODO: return usecase result
    findMembersByWorkspaceUseCase.findMembersByWorkspace(
        new FindMembersByWorkspaceUseCase.Command(
            slug,
            user
        )
    );
    return ResponseEntity.ok().build();
  }

}
