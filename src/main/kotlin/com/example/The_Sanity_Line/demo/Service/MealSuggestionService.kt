package com.example.The_Sanity_Line.demo.Service

import com.example.The_Sanity_Line.demo.Entities.MealSuggestion
import com.example.The_Sanity_Line.demo.Repository.DailyStateRepository
import com.example.The_Sanity_Line.demo.Repository.MealSuggestionRepository
import com.example.The_Sanity_Line.demo.Repository.UserRepository
import com.example.The_Sanity_Line.demo.dtos.MealSuggestionFeedbackRequest
import com.example.The_Sanity_Line.demo.dtos.MealSuggestionRequest
import tools.jackson.databind.JsonNode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tools.jackson.databind.ObjectMapper

@Service
@Transactional(readOnly = true)
class MealSuggestionService(
    private val repository: MealSuggestionRepository,
    private val userRepository: UserRepository,
    private val dailyStateRepository: DailyStateRepository,
    private val objectMapper: ObjectMapper,
) {

    fun getAllByUser(userId: String): List<MealSuggestion> =
        repository.findAllByUserUserId(userId)

    fun getById(id: String): MealSuggestion? =
        repository.findById(id).orElse(null)

    @Transactional
    fun saveOrUpdate(request: MealSuggestionRequest): MealSuggestion {
        val user = userRepository.findById(request.userId)
            .orElseThrow { NoSuchElementException("User not found: ${request.userId}") }

        val dailyState = request.stateId?.let {
            dailyStateRepository.findById(it)
                .orElseThrow { NoSuchElementException("DailyState not found: $it") }
        }

        val existing = repository.findById(request.suggestionId).orElse(null)

        val entity = if (existing != null) {
            existing.copy(
                user              = user,
                dailyState        = dailyState,
                mealSlot          = request.mealSlot,
                mealName          = request.mealName,
                ingredients       = request.ingredients?.toJsonNode(),
                instructions      = request.instructions,
                gutBrainRationale = request.gutBrainRationale,
                targetsCondition  = request.targetsCondition,
                plantSpeciesCount = request.plantSpeciesCount,
                fermentedServings = request.fermentedServings?.toBigDecimal(),
                prebioticFiberG   = request.prebioticFiberG?.toBigDecimal(),
                omega3G           = request.omega3G?.toBigDecimal(),
                magnesiumMg       = request.magnesiumMg?.toBigDecimal(),
                tryptophanMg      = request.tryptophanMg?.toBigDecimal(),
                promptSnapshot    = request.promptSnapshot?.toJsonNode(),
                rawResponseJson   = request.rawResponseJson?.toJsonNode(),
            )
        } else {
            MealSuggestion(
                suggestionId      = request.suggestionId,
                user              = user,
                dailyState        = dailyState,
                mealSlot          = request.mealSlot,
                mealName          = request.mealName,
                ingredients       = request.ingredients?.toJsonNode(),
                instructions      = request.instructions,
                gutBrainRationale = request.gutBrainRationale,
                targetsCondition  = request.targetsCondition,
                plantSpeciesCount = request.plantSpeciesCount,
                fermentedServings = request.fermentedServings?.toBigDecimal(),
                prebioticFiberG   = request.prebioticFiberG?.toBigDecimal(),
                omega3G           = request.omega3G?.toBigDecimal(),
                magnesiumMg       = request.magnesiumMg?.toBigDecimal(),
                tryptophanMg      = request.tryptophanMg?.toBigDecimal(),
                promptSnapshot    = request.promptSnapshot?.toJsonNode(),
                rawResponseJson   = request.rawResponseJson?.toJsonNode(),
                userAccepted      = false,
            )
        }

        return repository.save(entity)
    }

    @Transactional
    fun updateFeedback(id: String, request: MealSuggestionFeedbackRequest): MealSuggestion {
        val existing = repository.findById(id)
            .orElseThrow { NoSuchElementException("MealSuggestion not found: $id") }

        return repository.save(
            existing.copy(userAccepted = request.userAccepted)
        )
    }

    // Safely converts Any (ArrayList, LinkedHashMap, String…) to JsonNode
    private fun Any.toJsonNode(): JsonNode = objectMapper.valueToTree(this)
}