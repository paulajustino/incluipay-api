package com.incluipay.api.model;

import lombok.Getter;

@Getter
public enum DonationStatus {
    PENDING("Aguardando confirmação do pagamento."),
    COMPLETED("Pagamento processado com sucesso."),
    FAILED("Pagamento recusado pela instituição financeira ou por falha técnica.");

    private final String description;

    DonationStatus(String description) {
        this.description = description;
    }
}
