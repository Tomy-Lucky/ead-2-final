package com.example.ead_2_final_Tamir.servlets

import com.example.ead_2_final_Tamir.beans.Task
import com.example.ead_2_final_Tamir.db.Database
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(value = ["/writeTask"])
class CreateTaskServlet : HttpServlet() {

    private val db = Database()

    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        val task = Task(
            id = 0,
            title = request.getParameter("title"),
            content = request.getParameter("content"),
            userId = request.getSession(false).getAttribute("userId").toString().toLong()
        )

        db.createTask(task = task)

        request.setAttribute("message", "Your task ${task.title} added!")
        request.getRequestDispatcher("index.jsp").forward(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
    }
}
