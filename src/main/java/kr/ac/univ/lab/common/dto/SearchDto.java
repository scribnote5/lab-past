package kr.ac.univ.lab.common.dto;

import kr.ac.univ.lab.common.domain.enums.SearchType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SearchDto {
	private SearchType searchType = SearchType.NONE;
	private String keyword;
}