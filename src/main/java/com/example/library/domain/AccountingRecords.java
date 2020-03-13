package com.example.library.domain;

import lombok.Data;
import java.sql.Date;

@Data
public class AccountingRecords {
    private Long id;
    private Long accountId;
    private Long bookId;
    private Date receiptDate;
    private Date returnDate;
    private String statusId;
}
