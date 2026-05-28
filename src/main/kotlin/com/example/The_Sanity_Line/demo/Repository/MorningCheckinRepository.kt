package com.example.The_Sanity_Line.demo.Repository

import com.example.The_Sanity_Line.demo.Entities.MorningCheckin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface MorningCheckinRepository : JpaRepository<MorningCheckin, String> {
    fun findByUserUserIdAndDate(userId: String, date: LocalDate): MorningCheckin?
    fun findAllByUserUserId(userId: String): List<MorningCheckin>
}
