package team.fuyuki23.mate.application.common.usecase;

import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.vo.WPU;

public interface ValidateWPUUseCase {

  Result validateWPU(Command command);

  record Command(String slug, String identifier, User user) {

  }

  record Result(WPU wpu) {

  }

}
