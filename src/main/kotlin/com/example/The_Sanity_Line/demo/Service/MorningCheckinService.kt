package com.example.The_Sanity_Line.demo.Service

import com.example.The_Sanity_Line.demo.Entities.MorningCheckin
import com.example.The_Sanity_Line.demo.Repository.MorningCheckinRepository
import com.example.The_Sanity_Line.demo.Repository.UserRepository
import com.example.The_Sanity_Line.demo.dtos.MorningCheckinRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class MorningCheckinService(private val repository: MorningCheckinRepository,val UserRepository: UserRepository) {

    fun getByUserAndDate(userId: String, date: LocalDate): MorningCheckin? =
        repository.findByUserUserIdAndDate(userId, date)

    fun getAllByUser(userId: String): List<MorningCheckin> =
        repository.findAllByUserUserId(userId)

    @Transactional
    fun saveFromRequest(req: MorningCheckinRequest): MorningCheckin {

        val user = UserRepository.findById(req.userId)
            .orElseThrow { RuntimeException("User not found") }

        val checkin = MorningCheckin(
            user = user,
            date = req.date,
            bloating = req.bloating,
            digestionQuality = req.digestionQuality,
            energy = req.energy,
            mood = req.mood,
            stoolFormBss = req.stoolFormBss,
            notes = req.notes
        )

        return repository.save(checkin)
    }

    @Transactional
    fun delete(id: String) =
        repository.deleteById(id)
}
