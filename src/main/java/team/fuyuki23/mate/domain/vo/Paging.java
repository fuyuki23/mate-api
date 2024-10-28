package team.fuyuki23.mate.domain.vo;

public record Paging<V>(
    V items,
    Integer limit,
    Integer offset
) {

}
