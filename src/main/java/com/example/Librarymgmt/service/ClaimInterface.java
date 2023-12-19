package com.example.Librarymgmt.service;


import com.example.Librarymgmt.model.Claim;

import java.util.List;

public interface ClaimInterface {
    public Claim saveClaim(Claim claim);
    public List<Claim> GetAllClaim();
    public void deleteClaim(String email);
}
