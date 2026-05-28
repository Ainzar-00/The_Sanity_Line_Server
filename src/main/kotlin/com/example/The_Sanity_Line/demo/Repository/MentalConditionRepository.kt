package com.example.The_Sanity_Line.demo.Repository

import com.example.The_Sanity_Line.demo.Entities.MentalCondition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MentalConditionRepository : JpaRepository<MentalCondition, String> {
    fun findByUser_UserId(userId: String): List<MentalCondition>
    fun existsByUser_UserIdAndConditionName(userId: String, conditionName: String): Boolean
}
