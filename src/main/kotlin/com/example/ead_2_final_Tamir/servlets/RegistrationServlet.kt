package com.example.ead_2_final_Tamir.servlets

import com.example.ead_2_final_Tamir.beans.User
import com.example.ead_2_final_Tamir.db.Database
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(value = ["/registration"])
class RegistrationServlet : HttpServlet() {

    private val db = Database()

    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        val user = User(
            id = 0,
            email = request.getParameter("email"),
            name = request.getParameter("userName"),
            password = request.getParameter("password")
        )
        val result = db.createUser(user = user)

        if (result) {
            request.setAttribute("message", "You were registered successfully! ${user.name}, please log in.")
            request.getRequestDispatcher("/login.jsp").forward(request, response)
        } else {
            request.setAttribute("errMessage", "already exists")
            request.getRequestDispatcher("/registration.jsp").forward(request, response)
        }
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
    }
}
