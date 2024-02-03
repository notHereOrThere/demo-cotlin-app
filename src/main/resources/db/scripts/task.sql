-- db.changelog-V1.sql

-- Создаем таблицу task
CREATE TABLE IF NOT EXISTS task (
                                    id BIGSERIAL PRIMARY KEY,
                                    description VARCHAR(255) NOT NULL UNIQUE,
    is_reminder_set BOOLEAN DEFAULT FALSE,
    is_task_open BOOLEAN DEFAULT TRUE,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    priority VARCHAR(50) NOT NULL
    );

-- Создаем уникальный индекс на поле description
CREATE UNIQUE INDEX IF NOT EXISTS uk_task_description ON task (description);
