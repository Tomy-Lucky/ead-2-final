package com.example.ead_2_final_Tamir.servlets

import com.example.ead_2_final_Tamir.db.Database
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(value = ["/login"])
class LoginServlet : HttpServlet() {

    private val db = Database()

    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        val user = db.getUser(request.getParameter("userName"), request.getParameter("password"))
        if (user != null) {
            val oldSession = request.getSession(false)
            oldSession.invalidate()

            val newSession = request.getSession(true)
            newSession.maxInactiveInterval = 30 * 60

            newSession.setAttribute("userId", user.id)
            newSession.setAttribute("userName", user.name)

            request.getRequestDispatcher("/index.jsp").forward(request, response)
        }
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
    }
}
