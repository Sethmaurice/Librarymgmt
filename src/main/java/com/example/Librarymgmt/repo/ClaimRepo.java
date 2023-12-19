package com.example.Librarymgmt.repo;


import com.example.Librarymgmt.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepo extends JpaRepository<Claim, String> {
}
