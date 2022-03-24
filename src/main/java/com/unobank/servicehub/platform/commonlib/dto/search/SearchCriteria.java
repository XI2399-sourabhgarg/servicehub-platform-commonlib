package com.unobank.servicehub.platform.commonlib.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchCriteria implements Serializable {

    private List<FilterCriteria> filterCriteria;
    private SortingCriteria sortingCriteria;

    public SearchCriteria(List<FilterCriteria> filterCriteria) {
        this.filterCriteria = filterCriteria;
    }

    public SearchCriteria(List<FilterCriteria> filterCriteria, SortingCriteria sortingCriteria) {
        this.filterCriteria = filterCriteria;
        this.sortingCriteria = sortingCriteria;
    }
}
