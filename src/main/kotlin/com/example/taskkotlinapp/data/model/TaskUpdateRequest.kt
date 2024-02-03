package com.example.taskkotlinapp.data.model

import com.example.taskkotlinapp.data.Priority

data class TaskUpdateRequest(
    val description: String?,
    val isReminderSet: Boolean?,
    val isTaskOpen: Boolean?,
    val priority: Priority?
)