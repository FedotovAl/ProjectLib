package com.example.library.controller;

import com.example.library.entity.AccountingRecords;
import com.example.library.service.AccountingRecordsService;

public class AccountingRecordsController {
    AccountingRecordsService accountingRecordsService = new AccountingRecordsService();

    public void addNewAccountingRecord(AccountingRecords accountingRecords, String bookName){
        accountingRecordsService.addNewAccountingRecord(accountingRecords, bookName);
    }

    public void removeAccountingRecord(Long id){
        accountingRecordsService.removeAccountingRecord(accountingRecordsService.getAccountingRecordByID(id));
    }

    public void updateAccountingRecord(AccountingRecords accountingRecords){
        accountingRecordsService.updateAccountingRecord(accountingRecords);
    }

    public AccountingRecords getAccountingRecordByID(Long id){
        return accountingRecordsService.getAccountingRecordByID(id);
    }

    public void showAllAROpen(){
        accountingRecordsService.showAllAccountingRecordsWhichOpen();
    }

    public void showAllAccountingRecords(){
        accountingRecordsService.showAllAccountingRecords();
    }

    public void checkExpireStatus(){
        accountingRecordsService.expireStatus();
    }

    public void closeStatus(Long id){
        accountingRecordsService.closeStatus(accountingRecordsService.getAccountingRecordByID(id));
    }
}
