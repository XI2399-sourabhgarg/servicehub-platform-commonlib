package com.unobank.servicehub.platform.commonlib.dto.search;

import com.unobank.servicehub.platform.commonlib.dto.enums.Order;
import lombok.Data;

/**
 * @author tanay sen
 */

@Data
public class SortingCriteria {
    private String field;
    private Order order;

    public static class Builder {
        private String field;
        private Order order;

        public Builder withField(String field) {
            this.field = field;
            return this;
        }

        public Builder withOrder(Order order) {
            this.order = order;
            return this;
        }

        public SortingCriteria build() {
            SortingCriteria sortingCriteria = new SortingCriteria();
            sortingCriteria.field = field;
            sortingCriteria.order = order;
            return sortingCriteria;
        }
    }
}
