package com.example.springbatchtaskletexample.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class FutureTransaction implements Serializable {

    private Integer recordCode;
    private String clientType;
    private Integer clientNumber;
    private Integer accountNumber;
    private Integer subAccountNumber;
    private String oppositePartyCode;
    private String productGroupCode;
    private String exchangeCode;
    private String symbol;
    private String expirationDate;
    private String currencyCode;
    private String movementCode;
    private String buySellCode;
    private Integer quantityLongSign;
    private Long quantityLong;
    private Integer quantityShortSign;
    private Long quantityShort;
    private Double exchBrokerFeeDec;
    private Integer exchBrokerFeeDC;
    private String exchBrokerFeeCurCode;
    private Double clearingFeeDec;
    private Integer clearingFeeDC;
    private String clearingFeeCurCode;
    private Double commission;
    private Integer commissionDC;
    private String commissionCurCode;
    private String transactionDate;
    private Integer futureReference;
    private String ticketNumber;
    private Integer externalNumber;
    private Double transactionPrice;
    private String traderInitials;
    private String oppositeTraderId;
    private String openCloseCode;
    private String filler;

    public String transformToClientInformation(){
        return clientType + clientNumber + accountNumber + subAccountNumber;
    }

    public String transformToProductInformation(){
        return exchangeCode + productGroupCode + symbol + expirationDate;
    }

    public Long transformToTransactionAmount(){
        return Math.max(0, quantityLong - quantityShort);
    }
}
