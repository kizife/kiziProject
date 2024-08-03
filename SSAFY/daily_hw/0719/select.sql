use world;

select * from country
where name like '%Korea%';

select * from city
where countrycode = 'KOR';

select * from countrylanguage
where countryCode = 'KOR';

-- country table에서 code가 KOR인 자료 조회
select code, name, continent, region
from country
where code = 'KOR';


-- 이하 데일리 실습 풀이


-- country에서 gnp변동량이 양수인 국가에 대해 변동량 오름차순 정렬
select code, name, gnp, gnpold, (gnp-gnpold) "gnp변동량"
from country
where ((gnp-gnpold)/100) > 0
order by ((gnp-gnpold)/100) asc
;

-- country table에서 continent 중복 없이 조회 및 continent 길이로 정렬
select distinct continent
from country
order by length(continent) asc;

-- country에서 asia에 속하는 국가들 정보 및 name으로 정렬
select concat(name, '은', region, '에 속하며 인구는', population, '명이다.')
from country
where Continent = 'Asia';


-- 독립년도 기록 없고 인구 10000 이상인 국가를 인구 오름차순 출력
select name, continent, gnp, population
from country
where IndepYear is null
and population >= 10000
order by population asc;

-- country에서 인구 1억<=x<=2억인 국가를 인구 내림차순 3개만 출력
select code, name, population
from country
where 100000000 <= population and population <= 200000000
order by population desc
limit 3;

-- country에서 특정 년도에 독립한 나라를 독립년 오름차순 정렬. 독립년 같으면 코드 내림차순
select code, name, indepyear
from country
where indepyear = 800
or indepyear = 1810
or indepyear = 1811
or indepyear = 1901
order by indepyear asc, code desc;

-- region에 asia 들어가고, name 두번째 글자 o인 정보 
select code, name, region
from country
where region like '%Asia%'
and name like '_o%';

-- 홍길동, hong 글자 길이 각각 출력
select char_length('홍길동') 한글, char_length('hong') 영어
from dual;

-- governmentform에 republic 들어있고, name 길이 10 이상 자료를 name길이 내림차순 상위10개
select code, name, governmentform
from country
where GovernmentForm like '%republic%'
and char_length(name) >= 10
order by char_length(name) desc
limit 10;

-- code가 모음으로 시작하는 나라. name 오름차순, 3번부터 3개만
select code, name
from country
where code like 'a%' 
or 'e%'
or 'i%'
or 'o%'
or 'u%'
order by name asc
limit 2, 3;

-- name 맨앞, 맨위 2글자 제외하고 나머지 *로 처리
select name, concat(left(name, 2), repeat('*', length(name) - 4), right(name, 2)) "guess"
from country
;

-- region 중복 없이 공백을 _로 처리
select distinct replace(region, ' ', '_') "지역들"
from country
order by char_length(지역들) desc;

-- 인구 1억 이상들인 국가들 1인당 점유면적 반올림하여 소숫점 3자리 표현. 오름차순 정렬
select name, surfacearea, population, round((surfacearea/population), 3) "1인당 점유면적"
from country 
where population >= 100000000
order by (SurfaceArea/population) asc;

-- 현재 날짜와 올해가 며칠 지났는지, 100일 후는 며칠인지
select curdate() "오늘", dayofyear(curdate()) "올해 지난 날", date_add(curdate(), interval 100 day) "100일 후"
from dual;

-- asia 나라 중 기대수명 80초과 장수국가, 60초과 일반국가, 아니면 단명국가. 기대수명 정렬
select name, continent, lifeexpectancy, 
case when LifeExpectancy > 80
then "장수국가"
when LifeExpectancy > 60
then "일반국가"
else "단명국가"
end "구분"
from country
where lifeexpectancy is not null
and continent = 'asia'
order by LifeExpectancy asc;

-- gnp향상 표현. gnpold 없으면 신규 출력
select name, gnp, gnpold,
case when gnpold is null
then "신규"
else (gnp-gnpold)
end "gnp 향상"
from country 
order by name;

-- 어린이날 평일이면 행복, 주말이면 불행
select weekday('2020-05-05'), 
case when weekday('2020-05-05') in (5, 6)
then "불행"
else "행복"
end "행복여부";