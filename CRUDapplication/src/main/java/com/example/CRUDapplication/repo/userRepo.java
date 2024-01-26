package com.example.CRUDapplication.repo;

import com.example.CRUDapplication.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends JpaRepository<Users, Long> // type of entity and primary key
{

}
