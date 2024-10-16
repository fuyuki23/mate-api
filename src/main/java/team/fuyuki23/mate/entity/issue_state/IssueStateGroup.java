package team.fuyuki23.mate.entity.issue_state;

import lombok.Getter;

@Getter
public enum IssueStateGroup {
  BACKLOG("backlog", "#F5F5F5"),
  TODO("todo", "#FFFFFF"),
  IN_PROGRESS("in_progress", "#638DFF"),
  DONE("done", "#4CC764"),
  CANCELED("canceled", "#FF334B");

  private final String group;
  private final String color;
  IssueStateGroup(String group, String color) {
    this.group = group;
    this.color = color;
  }
}
