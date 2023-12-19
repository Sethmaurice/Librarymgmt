package com.example.Librarymgmt.repo;


import com.example.Librarymgmt.model.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<BorrowBook, Long> {
}
