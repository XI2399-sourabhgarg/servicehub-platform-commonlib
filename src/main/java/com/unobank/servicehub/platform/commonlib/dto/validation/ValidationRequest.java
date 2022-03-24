package com.unobank.servicehub.system.mambu.dto.fundTransfer.channelRequest.request;

import com.unobank.servicehub.platform.commonlib.dto.enums.FieldEnum;
import lombok.Data;

/**
 * @author shreyas kekre
 */
@Data
public class ValidationRequest {

    boolean isPayee;
    private String amount;
    private String feeAmount;
    private String clientSearchValue;
    private String acctSearchValue;
    private FieldEnum clientSearchField;
    private FieldEnum acctSearchField;

    public static class Builder {

        boolean isPayee;
        private String amount;
        private String feeAmount;
        private FieldEnum clientSearchField;
        private FieldEnum acctSearchField;
        private String clientSearchValue;
        private String acctSearchValue;

        public Builder withisPayee(boolean isPayee) {
            this.isPayee = isPayee;
            return this;
        }

        public Builder withAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public Builder withAcctSearchValue(String acctSearchValue) {
            this.acctSearchValue = acctSearchValue;
            return this;
        }

        public Builder withFeeAmount(String feeAmount) {
            this.feeAmount = feeAmount;
            return this;
        }

        public Builder withClientField(FieldEnum clientSearchField) {
            this.clientSearchField = clientSearchField;
            return this;
        }

        public Builder withAccountField(FieldEnum acctSearchField) {
            this.acctSearchField = acctSearchField;
            return this;
        }

        public Builder withclientSearchValue(String clientSearchValue) {
            this.clientSearchValue = clientSearchValue;
            return this;
        }

        public ValidationRequest build() {
            ValidationRequest validationRequest = new ValidationRequest();
            validationRequest.isPayee = isPayee;
            validationRequest.clientSearchField = clientSearchField;
            validationRequest.acctSearchField = acctSearchField;
            validationRequest.feeAmount = feeAmount;
            validationRequest.amount = amount;
            validationRequest.clientSearchValue = clientSearchValue;
            validationRequest.acctSearchValue = acctSearchValue;
            return validationRequest;
        }
    }
}
