package com.example.The_Sanity_Line.demo.Service

import com.example.The_Sanity_Line.demo.Entities.DailyNutrientTotal
import com.example.The_Sanity_Line.demo.Repository.DailyNutrientTotalRepository
import com.example.The_Sanity_Line.demo.Repository.UserRepository
import com.example.The_Sanity_Line.demo.dtos.DailyNutrientTotalRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class DailyNutrientTotalService(
    private val repository: DailyNutrientTotalRepository,
    private val userRepository: UserRepository,
) {

    fun getByUserAndDate(userId: String, date: LocalDate): DailyNutrientTotal? =
        repository.findByUserUserIdAndDate(userId, date)

    fun getAllByUser(userId: String): List<DailyNutrientTotal> =
        repository.findAllByUserUserId(userId)

    @Transactional
    fun saveOrUpdate(request: DailyNutrientTotalRequest): DailyNutrientTotal {

        val user = userRepository.findById(request.userId)
            .orElseThrow { NoSuchElementException("User not found: ${request.userId}") }

        val existing = repository.findByUserUserIdAndDate(request.userId, request.date)

        val entity = if (existing != null) {

            // 🔄 UPDATE (copy existing)
            existing.copy(
                plantSpeciesCount = request.plantSpeciesCount,
                fermentedServings = request.fermentedServings,
                prebioticFiberG = request.prebioticFiberG,
                omega3G = request.omega3G,
                magnesiumMg = request.magnesiumMg,
                tryptophanMg = request.tryptophanMg,
                overallScorePct = request.overallScorePct,

                targetPlantSpecies = request.targetPlantSpecies,
                targetFermented = request.targetFermented,
                targetFiberG = request.targetFiberG,
                targetOmega3G = request.targetOmega3G,
                targetMagnesiumMg = request.targetMagnesiumMg,
                targetTryptophanMg = request.targetTryptophanMg
            )

        } else {

            // ➕ CREATE
            DailyNutrientTotal(
                user = user,
                date = request.date,

                plantSpeciesCount = request.plantSpeciesCount,
                fermentedServings = request.fermentedServings,
                prebioticFiberG = request.prebioticFiberG,
                omega3G = request.omega3G,
                magnesiumMg = request.magnesiumMg,
                tryptophanMg = request.tryptophanMg,
                overallScorePct = request.overallScorePct,

                targetPlantSpecies = request.targetPlantSpecies,
                targetFermented = request.targetFermented,
                targetFiberG = request.targetFiberG,
                targetOmega3G = request.targetOmega3G,
                targetMagnesiumMg = request.targetMagnesiumMg,
                targetTryptophanMg = request.targetTryptophanMg
            )
        }

        return repository.save(entity)
    }

    @Transactional
    fun upsert(userId: String, date: LocalDate, request: DailyNutrientTotalRequest): DailyNutrientTotal {
        val user = userRepository.findById(userId)
            .orElseThrow { NoSuchElementException("User not found: $userId") }

        // Update existing record for this day, or create a fresh one
        val existing = repository.findByUserUserIdAndDate(userId, date)

        val entity = (existing ?: DailyNutrientTotal(user = user, date = date)).copy(
            plantSpeciesCount  = request.plantSpeciesCount,
            fermentedServings  = request.fermentedServings,
            prebioticFiberG    = request.prebioticFiberG,
            omega3G            = request.omega3G,
            magnesiumMg        = request.magnesiumMg,
            tryptophanMg       = request.tryptophanMg,
            overallScorePct    = request.overallScorePct,
            targetPlantSpecies = request.targetPlantSpecies,
            targetFermented    = request.targetFermented,
            targetFiberG       = request.targetFiberG,
            targetOmega3G      = request.targetOmega3G,
            targetMagnesiumMg  = request.targetMagnesiumMg,
            targetTryptophanMg = request.targetTryptophanMg,
        )

        return repository.save(entity)
    }
}