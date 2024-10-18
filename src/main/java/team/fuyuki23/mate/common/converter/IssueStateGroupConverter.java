package team.fuyuki23.mate.common.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.EnumSet;
import java.util.NoSuchElementException;
import team.fuyuki23.mate.entity.issue_state.IssueStateGroup;

@Converter
public class IssueStateGroupConverter implements AttributeConverter<IssueStateGroup, String> {

  @Override
  public String convertToDatabaseColumn(IssueStateGroup issueStateGroup) {
    return issueStateGroup.getGroup();
  }

  @Override
  public IssueStateGroup convertToEntityAttribute(String s) {
    return EnumSet.allOf(IssueStateGroup.class).stream()
        .filter(g -> g.getGroup().equals(s))
        .findAny()
        .orElseThrow(() -> new NoSuchElementException("IssueStatusGroup not found: " + s));
  }
}
