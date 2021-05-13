package com.example.Assignment_rk1_ead_2.filter

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest

@WebFilter(filterName = "ProlongingFilter")
class ProlongingFilter : Filter {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        chain.doFilter(request, response)

        val session = (request as HttpServletRequest).getSession(false)
        if (session != null) session.maxInactiveInterval = 30 * 60
    }
}
