package com.example.The_Sanity_Line.demo.Repository

import com.example.The_Sanity_Line.demo.Entities.PromptContextSnapshot
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PromptContextSnapshotRepository : JpaRepository<PromptContextSnapshot, String> {
    fun findBySuggestionSuggestionId(suggestionId: String): PromptContextSnapshot?
}
