package team.fuyuki23.mate.domain.vo;

import team.fuyuki23.mate.domain.Member;
import team.fuyuki23.mate.domain.Project;

public record ProjectMemberInfo(
    Member member,
    Project project
) {

}
