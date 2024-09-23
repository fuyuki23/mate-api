package team.fuyuki23.mate.adapter.in.rest.workspace;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fuyuki23.mate.application.workspace.usecase.FindWorkspacesByUserUseCase;
import team.fuyuki23.mate.domain.User;

@RestController
@RequestMapping("/workspaces")
@SecurityRequirement(name = "jwt")
@RequiredArgsConstructor
public class PrivateWorkspaceRestAdapter {

    private final FindWorkspacesByUserUseCase findWorkspacesByUserUseCase;

    @GetMapping
    public ResponseEntity<?> findWorkspacesByUser(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(findWorkspacesByUserUseCase.findWorkspacesByUser(
                new FindWorkspacesByUserUseCase.Command(user)
        ).workspaces());
    }

}
