package team.fuyuki23.mate.application.common.port.in;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.common.port.out.FindUserWorkspaceBySlugAndUserIdOutputPort;
import team.fuyuki23.mate.application.common.usecase.ValidateWorkspaceUseCase;
import team.fuyuki23.mate.application.member.exception.MemberError;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.domain.Member;
import team.fuyuki23.mate.domain.vo.SlugAndUser;

@Service
@RequiredArgsConstructor
public class ValidateWorkspaceInputPort implements ValidateWorkspaceUseCase {

  private final FindUserWorkspaceBySlugAndUserIdOutputPort findUserWorkspaceBySlugAndUserIdOutputPort;

  @Override
  public Member validateWorkspace(SlugAndUser su) {
    Optional<Member> maybeMember = findUserWorkspaceBySlugAndUserIdOutputPort.findUserWorkspaceBySlugAndUserId(
        su.slug(),
        su.userId());

    if (maybeMember.isEmpty()) {
      throw new ApiException(MemberError.NOT_FOUND);
    }

    return maybeMember.get();
  }
}
