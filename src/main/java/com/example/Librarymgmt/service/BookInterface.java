package com.example.Librarymgmt.service;


import com.example.Librarymgmt.model.BorrowBook;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookInterface {
    BorrowBook registerCitizen(BorrowBook borrowBook);
    BorrowBook updateCitizen(BorrowBook borrowBook);
    void deleteCitizen(Long citizen);
    List<BorrowBook> citizenList();
    BorrowBook findCitizenByCitizenId(Long citizen);
    Page<BorrowBook> pagenateCitizen(int pageNo, int pageSize);
}
