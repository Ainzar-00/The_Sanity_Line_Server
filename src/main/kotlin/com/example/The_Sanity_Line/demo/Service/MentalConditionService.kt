package com.example.The_Sanity_Line.demo.Service

import com.example.The_Sanity_Line.demo.Entities.MentalCondition
import com.example.The_Sanity_Line.demo.Repository.MentalConditionRepository
import com.example.The_Sanity_Line.demo.Repository.UserRepository
import com.example.The_Sanity_Line.demo.dtos.MentalConditionRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MentalConditionService(
    private val repository: MentalConditionRepository,
    private val userRepository: UserRepository,
) {
    fun getByUserId(userId: String): List<MentalCondition> =
        repository.findByUser_UserId(userId)

    @Transactional
    fun create(request: MentalConditionRequest): MentalCondition {
        val user = userRepository.findById(request.userId)
            .orElseThrow { NoSuchElementException("User not found: ${request.userId}") }

        // Enforce the unique constraint gracefully before hitting the DB
        if (repository.existsByUser_UserIdAndConditionName(request.userId, request.conditionName)) {
            throw IllegalStateException("Condition '${request.conditionName}' already exists for user: ${request.userId}")
        }

        return repository.save(
            MentalCondition(
                user = user,
                conditionName = request.conditionName,
                severity = request.severity,
                durationMonths = request.durationMonths,
                priorityNutrients = request.priorityNutrients,
                restrictCaffeine = request.restrictCaffeine,
                restrictSugar = request.restrictSugar,
                addVitaminD = request.addVitaminD,
                addAdaptogens = request.addAdaptogens,
                gradualFiber = request.gradualFiber,
                active = request.active,
            )
        )
    }

    @Transactional
    fun delete(conditionId: String) {
        if (!repository.existsById(conditionId))
            throw NoSuchElementException("Condition not found: $conditionId")
        repository.deleteById(conditionId)
    }
}