package team.fuyuki23.mate.application.workspace.port.in;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.workspace.port.out.FindWorkspacesByUserIdOutputPort;
import team.fuyuki23.mate.application.workspace.usecase.FindWorkspacesByUserUseCase;
import team.fuyuki23.mate.domain.Workspace;

@Service
@RequiredArgsConstructor
public class FindWorkspacesByUserInputPort implements FindWorkspacesByUserUseCase {

    private final FindWorkspacesByUserIdOutputPort findWorkspacesByUserIdOutputPort;

    @Override
    public Result findWorkspacesByUser(Command command) {
        List<Workspace> workspaces = findWorkspacesByUserIdOutputPort.findWorkspacesByUserId(
            command.user().id());

        return new Result(
            workspaces
        );
    }

}
