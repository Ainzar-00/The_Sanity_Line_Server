package com.example.The_Sanity_Line.demo.Service

import com.example.The_Sanity_Line.demo.Entities.MealLog
import com.example.The_Sanity_Line.demo.Repository.MealLogRepository
import com.example.The_Sanity_Line.demo.Repository.MealSuggestionRepository
import com.example.The_Sanity_Line.demo.Repository.UserRepository
import com.example.The_Sanity_Line.demo.dtos.MealLogRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class MealLogService(
    private val repository: MealLogRepository,
    private val userRepository: UserRepository,
    private val mealSuggestionRepository: MealSuggestionRepository,
) {

    fun getAllByUser(userId: String): List<MealLog> =
        repository.findAllByUserUserId(userId)

    fun getByUserAndDate(userId: String, date: LocalDate): List<MealLog> =
        repository.findAllByUserUserIdAndDate(userId, date)

    @Transactional
    fun save(request: MealLogRequest): MealLog {
        val user = userRepository.findById(request.userId)
            .orElseThrow { NoSuchElementException("User not found: ${request.userId}") }

        val suggestion = request.suggestionId?.let {
            mealSuggestionRepository.findById(it)
                .orElseThrow { NoSuchElementException("MealSuggestion not found: $it") }
        }

        val entity = MealLog(
            user             = user,
            suggestion       = suggestion,
            date             = request.date,
            mealSlot         = request.mealSlot,
            plantSpeciesCount = request.plantSpeciesCount,
            fermentedServings = request.fermentedServings,
            prebioticFiberG  = request.prebioticFiberG,
            omega3G          = request.omega3G,
            magnesiumMg      = request.magnesiumMg,
            tryptophanMg     = request.tryptophanMg,
            plantSpeciesList = request.plantSpeciesList

        )

        return repository.save(entity)
    }

    @Transactional
    fun delete(id: String) =
        repository.deleteById(id)
}