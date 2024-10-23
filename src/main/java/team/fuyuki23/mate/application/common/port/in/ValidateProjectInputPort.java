package team.fuyuki23.mate.application.common.port.in;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.common.port.out.FindUserProjectOutputPort;
import team.fuyuki23.mate.application.common.usecase.ValidateProjectUseCase;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.common.exception.DefaultError;
import team.fuyuki23.mate.domain.ProjectUser;

@Service
@RequiredArgsConstructor
public class ValidateProjectInputPort implements ValidateProjectUseCase {

  private final FindUserProjectOutputPort findUserProjectOutputPort;

  @Override
  public ProjectUser validateProject(String slug, String identifier, UUID userId) {
    return findUserProjectOutputPort.findUserProjectByIds(slug, identifier, userId)
        .orElseThrow(() -> new ApiException(DefaultError.FORBIDDEN));
  }
}
