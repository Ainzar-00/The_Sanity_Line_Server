package com.example.The_Sanity_Line.demo.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper

@Component
class RequestLoggingFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val wrappedRequest = ContentCachingRequestWrapper(request, 1024 * 1024)

        filterChain.doFilter(wrappedRequest, response)

        val body = wrappedRequest.contentAsByteArray
        if (body.isNotEmpty()) {
            println("🔥 RAW REQUEST BODY: ${String(body)}")
        }
    }
}