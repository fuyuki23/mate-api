package team.fuyuki23.mate.application.common.usecase;

import java.util.UUID;
import team.fuyuki23.mate.domain.ProjectUser;

public interface ValidateProjectUseCase {

  ProjectUser validateProject(String slug, String identifier, UUID userId);

}
