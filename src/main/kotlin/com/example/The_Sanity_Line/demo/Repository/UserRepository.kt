package com.example.The_Sanity_Line.demo.Repository


import com.example.The_Sanity_Line.demo.Entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
    fun findByEmail(email: String): User?
}