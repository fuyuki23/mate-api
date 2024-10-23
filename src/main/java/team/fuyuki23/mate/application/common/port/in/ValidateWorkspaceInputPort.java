package team.fuyuki23.mate.application.common.port.in;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.common.port.out.FindUserWorkspaceBySlugAndUserIdOutputPort;
import team.fuyuki23.mate.application.common.usecase.ValidateWorkspaceUseCase;
import team.fuyuki23.mate.application.member.exception.MemberError;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.domain.Member;

@Service
@RequiredArgsConstructor
public class ValidateWorkspaceInputPort implements ValidateWorkspaceUseCase {

  private final FindUserWorkspaceBySlugAndUserIdOutputPort findUserWorkspaceBySlugAndUserIdOutputPort;

  @Override
  public Member validateWorkspace(String slug, UUID userId) {
    Optional<Member> maybeMember = findUserWorkspaceBySlugAndUserIdOutputPort.findUserWorkspaceBySlugAndUserId(
        slug,
        userId);

    if (maybeMember.isEmpty()) {
      throw new ApiException(MemberError.NOT_FOUND);
    }

    return maybeMember.get();
  }
}
