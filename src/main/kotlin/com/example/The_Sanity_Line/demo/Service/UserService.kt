package com.example.The_Sanity_Line.demo.Service


import com.example.The_Sanity_Line.demo.Entities.User
import com.example.The_Sanity_Line.demo.Repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(private val userRepository: UserRepository) {

    fun save(user: User): User {
        return userRepository.save(user)
    }

    fun getById(id: String): User? {
        return userRepository.findById(id).orElse(null)
    }

    fun updateLastSeen(userId: String) {
        val user = userRepository.findById(userId).orElse(null)
        user?.let {
            it.lastSeenAt = LocalDateTime.now()
            userRepository.save(it)
        }
    }

    fun deactivate(userId: String) {
        val user = userRepository.findById(userId).orElse(null)
        user?.let {
            it.isActive = false
            userRepository.save(it)
        }
    }
}