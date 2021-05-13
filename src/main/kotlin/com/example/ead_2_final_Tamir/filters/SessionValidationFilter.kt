package com.example.Assignment_rk1_ead_2.filter

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter(filterName = "SessionValidationFilter")
class SessionValidationFilter : Filter {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val path = (request as HttpServletRequest).requestURI.replace(".jsp", "")
        if (path == "/login" || path == "/registration") {
            chain.doFilter(request, response)
            return
        }

        var userName: String?
        val session = request.getSession(false)
        session.let {
            userName = session.getAttribute("userName") as String
        }

        if (userName == null)
            (response as HttpServletResponse).sendRedirect("login.jsp")
        else
            chain.doFilter(request, response)
    }
}
