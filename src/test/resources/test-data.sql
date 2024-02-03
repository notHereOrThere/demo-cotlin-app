INSERT INTO task (description, is_reminder_set, is_task_open, created_on, priority)
VALUES
    ('first test todo', false, false, CURRENT_TIMESTAMP, 'LOW'),
    ('second test todo', true, false, CURRENT_TIMESTAMP, 'MEDIUM'),
    ('third test todo', true, true, CURRENT_TIMESTAMP, 'HIGH');
