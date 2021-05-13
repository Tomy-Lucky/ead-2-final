package com.example.ead_2_final_Tamir.db

import com.example.ead_2_final_Tamir.beans.Task
import com.example.ead_2_final_Tamir.beans.User
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class Database {

    @Throws(ClassNotFoundException::class)
    fun createUser(user: User): Boolean {
        if (isExists(user))
            return false
        Class.forName("org.postgresql.Driver")
        try {
            DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "password")
                .use { connection ->
                    connection
                        .prepareStatement("insert into \"users\" values(?, ?, ?)")
                        .use { preparedStatement ->
                            preparedStatement.setString(1, user.email)
                            preparedStatement.setString(2, user.name)
                            preparedStatement.setString(3, user.password)
                            println(preparedStatement)
                            preparedStatement.executeQuery()
                        }
                }
        } catch (e: SQLException) {
            printSQLException(e)
        }

        return true
    }

    @Throws(ClassNotFoundException::class)
    fun isExists(user: User): Boolean {
        var status = false
        Class.forName("org.postgresql.Driver")
        try {
            DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "password")
                .use { connection ->
                    connection
                        .prepareStatement("select * from \"users\" where email = ?")
                        .use { preparedStatement ->
                            preparedStatement.setString(1, user.email)
                            println(preparedStatement)
                            val resultSet: ResultSet = preparedStatement.executeQuery()
                            status = resultSet.next()
                        }
                }
        } catch (e: SQLException) {
            printSQLException(e)
        }
        return status
    }

    @Throws(ClassNotFoundException::class)
    fun getUser(userName: String, password: String): User? {
        Class.forName("org.postgresql.Driver")
        return try {
            DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "password")
                .use { connection ->
                    connection
                        .prepareStatement("select * from \"users\" where name = ? and password = ?")
                        .use { preparedStatement ->
                            preparedStatement.setString(1, userName)
                            preparedStatement.setString(2, password)
                            println(preparedStatement)
                            val resultSet = preparedStatement.executeQuery()
                            User(
                                id = resultSet.getLong("id"),
                                name = resultSet.getString("name"),
                                email = resultSet.getString("email"),
                                password = resultSet.getString("password")
                            )
                        }
                }
        } catch (e: SQLException) {
            printSQLException(e)
            null
        }
    }

    @Throws(ClassNotFoundException::class)
    fun createTask(task: Task) {
        Class.forName("org.postgresql.Driver")
        try {
            DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "password")
                .use { connection ->
                    connection
                        .prepareStatement("insert into \"task\" values(?, ?, ?)")
                        .use { preparedStatement ->
                            preparedStatement.setString(1, task.title)
                            preparedStatement.setString(2, task.content)
                            preparedStatement.setLong(3, task.userId)
                            println(preparedStatement)
                            preparedStatement.executeQuery()
                        }
                }
        } catch (e: SQLException) {
            printSQLException(e)
        }
    }

    @Throws(ClassNotFoundException::class)
    fun getTasks(): ArrayList<Task> {
        Class.forName("org.postgresql.Driver")
        val tasks = arrayListOf<Task>()
        try {
            DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "password")
                .use { connection ->
                    connection
                        .prepareStatement("select * from \"task\"")
                        .use { preparedStatement ->
                            println(preparedStatement)
                            val resultSet = preparedStatement.executeQuery()
                            while (resultSet.next()) {
                                tasks.add(
                                    Task(
                                        id = resultSet.getLong("id"),
                                        title = resultSet.getString("title"),
                                        content = resultSet.getString("content"),
                                        userId = resultSet.getLong("user_id")
                                    )
                                )
                            }
                        }
                }
        } catch (e: SQLException) {
            printSQLException(e)
        }
        return tasks
    }

    @Throws(ClassNotFoundException::class)
    fun getTask(taskId: Long): Task? {
        Class.forName("org.postgresql.Driver")
        return try {
            DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "password")
                .use { connection ->
                    connection
                        .prepareStatement("select * from \"task\" where id = ?")
                        .use { preparedStatement ->
                            preparedStatement.setLong(1, taskId)
                            println(preparedStatement)
                            val resultSet = preparedStatement.executeQuery()
                            Task(
                                id = resultSet.getLong("id"),
                                title = resultSet.getString("title"),
                                content = resultSet.getString("content"),
                                userId = resultSet.getLong("user_id")
                            )
                        }
                }
        } catch (e: SQLException) {
            printSQLException(e)
            null
        }
    }

    @Throws(ClassNotFoundException::class)
    fun updateTask(task: Task) {
        Class.forName("org.postgresql.Driver")
        try {
            DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "password")
                .use { connection ->
                    connection
                        .prepareStatement("update \"task\" set title = ?, content = ?, user_id = ? where id = ?")
                        .use { preparedStatement ->
                            preparedStatement.setString(1, task.title)
                            preparedStatement.setString(2, task.content)
                            preparedStatement.setLong(3, task.userId)
                            preparedStatement.setLong(4, task.id)

                            println(preparedStatement)
                            preparedStatement.executeQuery()
                        }
                }
        } catch (e: SQLException) {
            printSQLException(e)
        }
    }

    private fun printSQLException(ex: SQLException) {
        for (e in ex) {
            if (e is SQLException) {
                e.printStackTrace(System.err)
                System.err.println("SQLState: " + e.sqlState)
                System.err.println("Error Code: " + e.errorCode)
                System.err.println("Message: " + e.message)
                var t = ex.cause
                while (t != null) {
                    println("Cause: $t")
                    t = t.cause
                }
            }
        }
    }
}
