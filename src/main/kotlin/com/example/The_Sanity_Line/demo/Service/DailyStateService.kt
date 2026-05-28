package com.example.The_Sanity_Line.demo.Service

import com.example.The_Sanity_Line.demo.Entities.DailyState
import com.example.The_Sanity_Line.demo.Repository.DailyStateRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class DailyStateService(private val repository: DailyStateRepository) {

    fun getByUserAndDate(userId: String, date: LocalDate): DailyState? =
        repository.findByUserUserIdAndDate(userId, date)

    fun getAllByUser(userId: String): List<DailyState> =
        repository.findAllByUserUserId(userId)

    @Transactional
    fun saveDailyState(dailyState: DailyState): DailyState =
        repository.save(dailyState)

    @Transactional
    fun deleteDailyState(id: String) =
        repository.deleteById(id)
}
