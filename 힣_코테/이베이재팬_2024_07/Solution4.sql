-- 코드를 입력하세요
with ym_amount as (
    SELECT
        year(created_at) as year,
        month(created_at) as month,
        amount
    from card_usages
)

select
    year as YEAR,
    month as MONTH,
    count(case when amount < 0 then 1 end) as CANCEL_COUNT,
    sum(if(amount < 0,
           amount * -1,
           0
        )) as AMOUNT
from ym_amount
group by ym_amount.year, ym_amount.month
order by YEAR desc, MONTH desc
;