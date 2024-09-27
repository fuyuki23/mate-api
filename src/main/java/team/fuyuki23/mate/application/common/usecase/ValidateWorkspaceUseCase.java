package team.fuyuki23.mate.application.common.usecase;

import team.fuyuki23.mate.domain.Member;
import team.fuyuki23.mate.domain.vo.SlugAndUser;

public interface ValidateWorkspaceUseCase {

  Member validateWorkspace(SlugAndUser su);


}
