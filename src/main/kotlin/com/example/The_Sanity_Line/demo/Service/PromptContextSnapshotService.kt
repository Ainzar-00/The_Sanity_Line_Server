package com.example.The_Sanity_Line.demo.Service

import com.example.The_Sanity_Line.demo.Entities.PromptContextSnapshot
import com.example.The_Sanity_Line.demo.Repository.PromptContextSnapshotRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PromptContextSnapshotService(private val repository: PromptContextSnapshotRepository) {

    fun getBySuggestion(suggestionId: String): PromptContextSnapshot? =
        repository.findBySuggestionSuggestionId(suggestionId)

    @Transactional
    fun save(snapshot: PromptContextSnapshot): PromptContextSnapshot =
        repository.save(snapshot)
}
