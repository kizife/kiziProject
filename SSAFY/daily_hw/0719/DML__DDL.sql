use empdept;

-- 0. 참고용
select * from emp;

select * from dept;

-- 1. empno, ename, hiredate를 hiredate의 오름차순으로 5개만 정렬하라.
select empno, ename, hiredate
from emp
order by hiredate asc
limit 5;

-- 2. empno, ename, sal을 각각 "번호", "이름", "급여"로 표현하고, 급여의 내림차순으로 정렬하라.
select empno "번호", ename "이름", sal "급여"
from emp
where sal >= 2000.00
order by sal desc;

-- 3. 이름에 e가 들어가는 직원 중 job이 MANAGER, SALESMAN인 직원만 추출하여 job의 오름차순으로 정렬하여라.
select ename, job, hiredate
from emp
where ename like '%e%'
and job in ('SALESMAN', 'MANAGER')
order by job asc;

-- 4. empno, ename, dname, job, loc(근무지역)을 추출하라. 
-- 단, 뉴욕과 시카고에서 근무하는 직원만 추출하되, dname 오름차순으로 정렬한 후 동일 dname 안에서는 job의 내림차순으로 정렬한다.
select emp.empno, emp.ename, dept.dname, emp.job, dept.loc
from emp
join dept on emp.deptno = dept.deptno
where dept.loc = "NEW YORK"
or dept.loc =  "CHICAGO"
order by dept.dname asc, job desc;

-- 5. "ename은(는) ****년 **월 **일에 입사했습니다." 표현을 출력하되, 입사일 기준 오름차순으로 정렬하라.
select concat(ename, "은(는) ", left(hiredate, 4), "년 ", substr(hiredate, 6, 2), "월 ", right(hiredate, 2), "일에 입사했습니다.") "입사 순서"
from emp
order by hiredate asc;