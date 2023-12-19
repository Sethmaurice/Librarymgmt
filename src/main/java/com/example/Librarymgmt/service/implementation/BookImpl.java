package com.example.Librarymgmt.service.implementation;


import com.example.Librarymgmt.model.BorrowBook;
import com.example.Librarymgmt.repo.BookRepo;
import com.example.Librarymgmt.service.BookInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookImpl implements BookInterface {

    private BookRepo citizenRepo;
    public BookImpl(BookRepo citizenRepo){
        this.citizenRepo = citizenRepo;
    }
    @Override
    public BorrowBook registerCitizen(BorrowBook citizen) {
        return citizenRepo.save(citizen);
    }

    @Override
    public BorrowBook updateCitizen(BorrowBook citizen) {
        return citizenRepo.save(citizen);
    }

    @Override
    public void deleteCitizen(Long citizen) {
        citizenRepo.deleteById(citizen);
    }

    @Override
    public List<BorrowBook> citizenList() {
        return citizenRepo.findAll();
    }

    @Override
    public BorrowBook findCitizenByCitizenId(Long citizen) {
        return citizenRepo.findById(citizen).get();
    }

    @Override
    public Page<BorrowBook> pagenateCitizen(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo -1,pageSize);
        return this.citizenRepo.findAll(pageable);
    }
}
