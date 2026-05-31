package com.example.The_Sanity_Line.demo.Service

import com.example.The_Sanity_Line.demo.Entities.DailyState
import com.example.The_Sanity_Line.demo.Repository.DailyStateRepository
import com.example.The_Sanity_Line.demo.Repository.UserRepository
import com.example.The_Sanity_Line.demo.dtos.DailyStateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class DailyStateService(
    private val repository: DailyStateRepository,
    private val userRepository: UserRepository,
) {

    fun getByUserAndDate(userId: String, date: LocalDate): DailyState? =
        repository.findByUserUserIdAndDate(userId, date)

    fun getAllByUser(userId: String): List<DailyState> =
        repository.findAllByUserUserId(userId)

    @Transactional
    fun saveOrUpdate(request: DailyStateRequest): DailyState {

        val user = userRepository.findById(request.userId)
            .orElseThrow { NoSuchElementException("User not found: ${request.userId}") }

        val existing = repository.findByUserUserIdAndDate(request.userId, request.date)

        val entity = if (existing != null) {

            existing.copy(
                sleepHoursPrevNight = request.sleepHoursPrevNight,
                sleepQuality        = request.sleepQuality,
                wokeUpFeeling       = request.wokeUpFeeling,
                physicalTraining    = request.physicalTraining,
                trainingType        = request.trainingType,
                trainingIntensity   = request.trainingIntensity,
                trainingDurationMin = request.trainingDurationMin,
                stressLevel         = request.stressLevel,
                currentMood         = request.currentMood,
                hungerLevel         = request.hungerLevel,
                energyLevel         = request.energyLevel,
            )

        } else {

            DailyState(
                user                = user,
                date                = request.date,
                sleepHoursPrevNight = request.sleepHoursPrevNight,
                sleepQuality        = request.sleepQuality,
                wokeUpFeeling       = request.wokeUpFeeling,
                physicalTraining    = request.physicalTraining,
                trainingType        = request.trainingType,
                trainingIntensity   = request.trainingIntensity,
                trainingDurationMin = request.trainingDurationMin,
                stressLevel         = request.stressLevel,
                currentMood         = request.currentMood,
                hungerLevel         = request.hungerLevel,
                energyLevel         = request.energyLevel,
            )
        }

        return repository.save(entity)
    }

    @Transactional
    fun deleteDailyState(id: String) =
        repository.deleteById(id)
}