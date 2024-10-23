package team.fuyuki23.mate.application.common.usecase;

import java.util.UUID;
import team.fuyuki23.mate.domain.Member;

public interface ValidateWorkspaceUseCase {

  Member validateWorkspace(String slug, UUID userId);


}
