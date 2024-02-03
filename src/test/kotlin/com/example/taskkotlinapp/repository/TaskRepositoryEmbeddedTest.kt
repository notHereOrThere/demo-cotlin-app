package com.example.taskkotlinapp.repository

import com.example.taskkotlinapp.data.Priority
import com.example.taskkotlinapp.data.Task
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql

@DataJpaTest(properties = ["spring.jpa.properties.javax.persistence.validation.mode=none"])
class TaskRepositoryEmbeddedTest {

    @Autowired
    private lateinit var objectUnderTest: TaskRepository

    private val numberOfRecordsInTestDataSql = 3
    private val numberOfOpenRecordsInTestDataSql = 1
    private val numberOfClosedRecordsInTestDataSql = 2

    @Test
    @Sql("classpath:test-data.sql")
    fun `when task is saved then check for not null`() {

        assertThat(objectUnderTest.doesDescriptionExist("first test todo")).isEqualTo(true)
    }

    @Test
    @Sql("classpath:test-data.sql")
    fun `when all tasks are fetched then check for the number of records`() {
        // Given
        // When
        val tasks: List<Task> = objectUnderTest.findAll()

        // Then
        assertThat(tasks.size).isEqualTo(numberOfRecordsInTestDataSql)
    }

    @Test
    @Sql("classpath:test-data.sql")
    fun `when task is deleted then check for the size of list`() {
        // Given
        // When
        val tasksBefore: List<Task> = objectUnderTest.findAll()

        objectUnderTest.delete(tasksBefore[0])
        val tasks: List<Task> = objectUnderTest.findAll()

        // Then
        assertThat(tasks.size).isEqualTo(2)
    }

    @Test
    @Sql("classpath:test-data.sql")
    fun `when all open tasks are queried then check for the correct number of open tasks`() {
        val tasks: List<Task> = objectUnderTest.queryAllOpenTasks()

        assertThat(tasks.size).isEqualTo(numberOfOpenRecordsInTestDataSql)
    }

    @Test
    @Sql("classpath:test-data.sql")
    fun `when all closed tasks are queried then check for the correct number of closed tasks`() {
        val tasks: List<Task> = objectUnderTest.queryAllClosedTasks()

        assertThat(tasks.size).isEqualTo(numberOfClosedRecordsInTestDataSql)
    }

    @Test
    @Sql("classpath:test-data.sql")
    fun `when description is queried then check if description already exists`() {
        val doesDescriptionExist1 = objectUnderTest.doesDescriptionExist("second test todo")
        val doesDescriptionExist2 = objectUnderTest.doesDescriptionExist("feed the cat")

        assertThat(doesDescriptionExist1).isTrue
        assertThat(doesDescriptionExist2).isFalse
    }

    @Test
    fun `creating test`() {
        val taskTemp = Task()
        with(taskTemp) {
            description = "test"
            isTaskOpen = true
            isReminderSet = true
            priority = Priority.LOW
        }
        assertThat(objectUnderTest.save(taskTemp)).isNotNull
    }

}