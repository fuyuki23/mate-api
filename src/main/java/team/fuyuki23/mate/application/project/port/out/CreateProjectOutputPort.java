package team.fuyuki23.mate.application.project.port.out;

import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.Workspace;

public interface CreateProjectOutputPort {

  Project createProject(String name, String identifier, String description, Workspace workspace,
      User leader);

}
