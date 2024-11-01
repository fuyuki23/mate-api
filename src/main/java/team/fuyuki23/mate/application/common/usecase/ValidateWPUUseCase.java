package team.fuyuki23.mate.application.common.usecase;

import java.util.UUID;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.vo.WPU;

public interface ValidateWPUUseCase {

  Result validateWPU(Command command);

  record Command(UUID workspaceId, User user) {

  }

  record Result(WPU wpu) {

  }

}
