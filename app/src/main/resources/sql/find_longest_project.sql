SELECT CONCAT('Project ', p.id) AS name,
       DATEDIFF('MONTH', p.start_date, p.finish_date) AS month_count
FROM project p
WHERE DATEDIFF('MONTH', p.start_date, p.finish_date) = (
    SELECT MAX(DATEDIFF('MONTH', start_date, finish_date)) FROM project
);
