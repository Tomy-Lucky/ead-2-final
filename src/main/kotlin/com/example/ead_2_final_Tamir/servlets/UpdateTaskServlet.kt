package com.example.ead_2_final_Tamir.servlets

import com.example.ead_2_final_Tamir.beans.Task
import com.example.ead_2_final_Tamir.db.Database
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(value = ["/editTask"])
class UpdateTaskServlet : HttpServlet() {

    private val db = Database()

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val session = request.getSession(false)
        val userId = if (session != null)
            (request.getSession(false).getAttribute("userId"))?.toString()?.toLong()
        else null
        val taskId = request.getParameter("taskId").toLong()

        val task = db.getTask(taskId = taskId)
        request.setAttribute("userTask", false)
        if (userId != null && task != null && userId == task.userId)
            request.setAttribute("userTask", true)

        request.setAttribute("task", task)
        request.getRequestDispatcher("/editTask.jsp").forward(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        val task = Task(
            id = request.getParameter("taskId").toLong(),
            title = request.getParameter("title"),
            content = request.getParameter("content"),
            userId = request.getSession(false).getAttribute("userId").toString().toLong()
        )

        db.updateTask(task = task)

        request.setAttribute("message", "Your task ${task.title} updated!")
        request.getRequestDispatcher("index.jsp").forward(request, response)
    }
}
