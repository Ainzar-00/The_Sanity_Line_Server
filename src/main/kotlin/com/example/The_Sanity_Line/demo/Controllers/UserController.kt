package com.example.The_Sanity_Line.demo.Controllers

import com.example.The_Sanity_Line.demo.Entities.User
import com.example.The_Sanity_Line.demo.Service.UserService
import com.example.The_Sanity_Line.demo.Entities.Provider
import com.example.The_Sanity_Line.demo.dtos.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun createUser(@RequestBody request: UserRequest): ResponseEntity<User> {
        val user = User(
            userId = request.userId,
            email = request.email,
            displayName = request.displayName,
            photoUrl = request.photoUrl,
            provider = Provider.valueOf(request.provider),  // "GOOGLE" → Provider.GOOGLE
            timezone = request.timezone,
            isActive = request.isActive,
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user))
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: String): ResponseEntity<Any> =
        userService.getById(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @PutMapping("/{id}/last-seen")
    fun updateLastSeen(@PathVariable id: String): ResponseEntity<Unit> {
        userService.updateLastSeen(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}/deactivate")
    fun deactivate(@PathVariable id: String): ResponseEntity<Unit> {
        userService.deactivate(id)
        return ResponseEntity.noContent().build()
    }

//    @DeleteMapping("/{id}")
//    fun delete(@PathVariable id: String): ResponseEntity<Unit> {
//        userService.delete(id)
//        return ResponseEntity.noContent().build()
//    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(ex: NoSuchElementException): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(mapOf("error" to (ex.message ?: "Not found")))

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(ex: IllegalArgumentException): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(mapOf("error" to (ex.message ?: "Bad request")))
}