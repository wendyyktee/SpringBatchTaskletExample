package com.example.springbatchtaskletexample.Job;

import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import com.example.springbatchtaskletexample.Model.FutureTransaction;

public class TransactionMapper {

    public static FutureTransaction mapTransaction(String record) {
        FutureTransaction transaction = new FutureTransaction();
        TransactionTokenizer tokenizer = new TransactionTokenizer();
        FixedLengthTokenizer tokenize = tokenizer.createTokenizer();
        FieldSet fieldSet = tokenize.tokenize(record);

        transaction.setRecordCode(fieldSet.readInt("recordCode"));
        transaction.setClientType(fieldSet.readRawString("clientType"));
        transaction.setClientNumber(fieldSet.readInt("clientNumber"));
        transaction.setAccountNumber(fieldSet.readInt("accountNumber"));
        transaction.setSubAccountNumber(fieldSet.readInt("subAccountNumber"));
        transaction.setOppositePartyCode(fieldSet.readRawString("oppositePartyCode"));
        transaction.setProductGroupCode(fieldSet.readRawString("productGroupCode"));
        transaction.setExchangeCode(fieldSet.readRawString("exchangeCode"));
        transaction.setSymbol(fieldSet.readRawString("symbol"));
        transaction.setExpirationDate(fieldSet.readRawString("expirationDate"));
        transaction.setCurrencyCode(fieldSet.readRawString("currencyCode"));
        transaction.setMovementCode(fieldSet.readRawString("movementCode"));
        transaction.setBuySellCode(fieldSet.readRawString("buySellCode"));
        transaction.setQuantityLongSign(fieldSet.readInt("quantityLongSign"));
        transaction.setQuantityLong(fieldSet.readLong("quantityLong"));
        transaction.setQuantityShortSign(fieldSet.readInt("quantityShortSign"));
        transaction.setQuantityShort(fieldSet.readLong("quantityShort"));
        transaction.setExchBrokerFeeDec(fieldSet.readDouble("exchBrokerFeeDec"));
        transaction.setExchBrokerFeeDC(fieldSet.readInt("exchBrokerFeeDC"));
        transaction.setExchBrokerFeeCurCode(fieldSet.readRawString("exchBrokerFeeCurCode"));
        transaction.setClearingFeeDec(fieldSet.readDouble("clearingFeeDec"));
        transaction.setClearingFeeDC(fieldSet.readInt("clearingFeeDC"));
        transaction.setClearingFeeCurCode(fieldSet.readRawString("clearingFeeCurCode"));
        transaction.setCommission(fieldSet.readDouble("commission"));
        transaction.setCommissionDC(fieldSet.readInt("commissionDC"));
        transaction.setCommissionCurCode(fieldSet.readRawString("commissionCurCode"));
        transaction.setTransactionDate(fieldSet.readRawString("transactionDate"));
        transaction.setFutureReference(fieldSet.readInt("futureReference"));
        transaction.setTicketNumber(fieldSet.readRawString("ticketNumber"));
        transaction.setExternalNumber(fieldSet.readInt("externalNumber"));
        transaction.setTransactionPrice(fieldSet.readDouble("transactionPrice"));
        transaction.setTraderInitials(fieldSet.readRawString("traderInitials"));
        transaction.setOppositeTraderId(fieldSet.readRawString("oppositeTraderId"));
        transaction.setOpenCloseCode(fieldSet.readRawString("openCloseCode"));
        transaction.setFiller(fieldSet.readRawString("filler"));
        return transaction;
    }
}
