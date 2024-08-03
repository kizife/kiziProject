use world;

select * from country;
select * from city;
select * from countrylanguage;

-- #1
select c.code, c.name
from country as c, city as t
where t.CountryCode = c.Code
and t.Name = 'Kabul';

-- #2
select c.name
	, l.language
	, l.percentage
from country as c, countrylanguage as l
where l.CountryCode = c.Code
and l.IsOfficial = 'T'
and l.Percentage = 100.0
order by c.name asc;

-- #3
select t.name
	, l.language
	, c.name
from city as t
join country as c on t.CountryCode = c.Code
join countrylanguage as l on c.code = l.CountryCode
where l.IsOfficial = 'T'
and t.name = 'Amsterdam';

-- #4
select c.name
	, c.capital
	, t.name as '수도'
	, t.population as '수도인구'
from city as t
join country as c on c.code = t.countrycode
and c.Capital = t.id
where c.capital is not null
and c.name like 'united%';

-- #5
select c.name, c.capital
	, case when t.name is null then '수도없음'
    else t.name
    end as '수도'
	, case when t.population is null then '수도없음'
    else t.population
    end as '수도인구'
from city as t
right outer join country as c on c.code = t.countrycode
and c.capital = t.id 
where c.name like 'united%';

-- #6
select count(l.CountryCode) as "국가수"
from countrylanguage l
join (
	select percentage
    from countrylanguage
    where countrycode = 'CHE'
    order by percentage desc
    limit 1) chep 
    on l.percentage > chep.percentage
where l.isofficial = 'T'
;

-- #7
select l.language
from countrylanguage as l
join country as c on c.code = l.CountryCode
where c.Name = 'South Korea'
and l.isofficial = 'T';

-- #8
select c.Name
	, t.CountryCode
	, count(t.CountryCode) as '도시개수'
from city as t
join country as c on c.Code = t.CountryCode
where c.name like "Bo%"
group by c.name, t.countrycode;

-- #9
select c.name
	, c.code
	, case when count(t.countrycode) = 0 then '단독'
		else count(t.countrycode)
		end as '도시개수'
from country as c
left join city as t on c.code = t.CountryCode
where c.name like "Bo%"
group by c.name, c.code;

-- #10
select countrycode, name, population
from city
order by population desc
limit 1;

-- #11
select c.name, t.CountryCode, t.name, t.population
from city as t,
	(select min(t.population) pop
    from city as t) p
join country as c
where c.code = t.countrycode
and t.population = p.pop;

-- #12
select t.countrycode, t.name, t.population
from city as t
where t.population > (
	select population from city where name = 'Seoul')
;
    
-- #13
select t.CountryCode, l.language
from city as t
join countrylanguage as l 
on t.CountryCode = l.CountryCode
and t.Name = "San Miguel"
and l.IsOfficial = 'T'
order by t.CountryCode
;
    
-- #14
select t.countrycode, cc.pop as "max_pop"
from (
    select countrycode, max(population) pop
    from city
    group by countrycode) cc
join city t
on t.countrycode = cc.countrycode
and t.Population = cc.pop
order by t.countrycode asc;

-- #15
select t.countrycode, t.name, cc.pop as 'population'
from (
	select countrycode, max(population) pop
	from city
    group by countrycode) cc
join city as t
on t.countrycode = cc.countrycode
and t.population = cc.pop
join country as c
on t.countrycode = c.code
order by t.countrycode asc;

-- #16
select t.countrycode, c.name, t.name, cc.pop as 'population'
from country c
left join (
	select countrycode, max(population) pop
	from city
    group by countrycode) cc on c.code = cc.countrycode
left join city as t on t.countrycode = cc.countrycode and t.population = cc.pop
;

-- #17
create or replace view summary as
select t.countrycode 
	, c.name as co_name
	, t.name as ci_name
	, cc.pop as 'population'
from country c
left join (
	select countrycode, max(population) pop
	from city
    group by countrycode) cc on c.code = cc.countrycode
left join city as t on t.countrycode = cc.countrycode and t.population = cc.pop
;

-- #18
select *
from summary
where countrycode = 'KOR';