package com.example.springbatchtaskletexample.Job;

import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionTokenizer {

    @Bean
    public FixedLengthTokenizer createTokenizer() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setNames("recordCode", "clientType", "clientNumber", "accountNumber", "subAccountNumber",
                           "oppositePartyCode", "productGroupCode", "exchangeCode", "symbol", "expirationDate",
                           "currencyCode", "movementCode", "buySellCode", "quantityLongSign", "quantityLong",
                           "quantityShortSign", "quantityShort", "exchBrokerFeeDec", "exchBrokerFeeDC", "exchBrokerFeeCurCode",
                           "clearingFeeDec", "clearingFeeDC", "clearingFeeCurCode",
                           "commission", "commissionDC", "commissionCurCode",
                           "transactionDate", "futureReference", "ticketNumber", "externalNumber", "transactionPrice",
                           "traderInitials", "oppositeTraderId", "openCloseCode" , "filler");
        tokenizer.setColumns(new Range(1, 3), new Range(4, 7), new Range(8, 11), new Range(12, 15), new Range(16, 19),
                             new Range(20, 25), new Range(26, 27), new Range(28, 31), new Range(32, 37), new Range(38, 45),
                             new Range(46, 48), new Range(49, 50), new Range(51, 51), new Range(52, 52), new Range(53, 62),
                             new Range(63, 63), new Range(64, 73), new Range(74, 85), new Range(86, 86), new Range(87, 89),
                             new Range(90, 101), new Range(102, 102), new Range(103, 105), new Range(106, 117), new Range(118, 118),
                             new Range(119, 121), new Range(122, 129), new Range(130, 135), new Range(136, 141), new Range(142, 147),
                             new Range(148, 162), new Range(163, 168), new Range(169, 175), new Range(176, 176), new Range(177, 303));

        return tokenizer;
    }
}
