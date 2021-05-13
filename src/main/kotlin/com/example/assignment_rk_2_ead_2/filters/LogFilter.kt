package com.example.assignment_rk_2_ead_2.filters

import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Enumeration
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest

@WebFilter(filterName = "LogFilter")
class LogFilter : Filter {

    @Throws(ServletException::class, IOException::class)
    override fun doFilter(req: ServletRequest, resp: ServletResponse, chain: FilterChain) {
        val httpServletRequest = req as HttpServletRequest
        val params: Enumeration<String> = req.getParameterNames()

        println(
            "Logging time: ${LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)}\n" +
                    "servlet path: + ${httpServletRequest.servletPath}\n" +
                    "remote address:  + ${httpServletRequest.remoteAddr}\n"
        )

        while (params.hasMoreElements()) {
            val name = params.nextElement()
            val value = req.getParameter(name)
            println("request parameter: $name = $value\n")
        }

        httpServletRequest.cookies.let {
            it.forEach { cookie ->
                println("${cookie.name}: ${cookie.value}")
            }
        }

        chain.doFilter(req, resp)
    }
}
