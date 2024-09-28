-- 코드를 입력하세요

with work_history as (
SELECT
    worker_id as worker_id,
    CEIL(TIMESTAMPDIFF(MINUTE, enter_at, leave_at) / 60) as work_hour
from working_log
where enter_at <= timestamp("2023-04-01")
),
work_fee as (
select
    worker.id,
    worker.name,
    IF(work_hour > 8,
        (worker.wage * 8) + (worker.wage * 1.5 * (work_hour - 8)),
        work_hour * worker.wage
    ) as fee
from worker
left join work_history on work_history.worker_id = worker.id
)

select
    id as ID,
    name as NAME,
    floor(sum(fee)) as SALARY
from work_fee
group by id
order by SALARY desc
;