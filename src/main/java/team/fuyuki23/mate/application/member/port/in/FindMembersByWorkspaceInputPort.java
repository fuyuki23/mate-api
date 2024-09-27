package team.fuyuki23.mate.application.member.port.in;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.member.exception.MemberError;
import team.fuyuki23.mate.application.member.port.out.FindMembersByWorkspaceIdOutputPort;
import team.fuyuki23.mate.application.member.port.out.FindWorkspaceBySlugOutputPort;
import team.fuyuki23.mate.application.member.usecase.FindMembersByWorkspaceUseCase;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.domain.Member;
import team.fuyuki23.mate.domain.Workspace;

@Service
@RequiredArgsConstructor
public class FindMembersByWorkspaceInputPort implements FindMembersByWorkspaceUseCase {

  private final FindWorkspaceBySlugOutputPort findWorkspaceBySlugOutputPort;
  private final FindMembersByWorkspaceIdOutputPort findMembersByWorkspaceIdOutputPort;

  @Override
  public Result findMembersByWorkspace(Command command) {
    Workspace workspace = findWorkspaceBySlugOutputPort.findWorkspaceBySlug(command.slug())
        .orElseThrow(() -> new ApiException(MemberError.WORKSPACE_NOT_FOUND));

    // TODO: check user permission, only workspace members can view members
    List<Member> members = findMembersByWorkspaceIdOutputPort.findMembersByWorkspaceId(
        workspace.id());

    return new Result(
        members
    );
  }
}
