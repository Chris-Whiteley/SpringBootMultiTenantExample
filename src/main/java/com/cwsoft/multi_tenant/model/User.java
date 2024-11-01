package com.cwsoft.multi_tenant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password; // Make sure to store a hashed password
    private String companyId; // Reference to the company/tenant
    private String email;

    // Getters and Setters
}
