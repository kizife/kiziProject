use empdept;

select * from emp;
select * from dept;

-- #1
select e.ename '이름'
	, e.job '업무'
	, e.sal '급여'
from emp e 
join dept d
on e.deptno = d.deptno
where d.loc = 'CHICAGO';

-- #2
select e.empno '사원번호'
	, e.ename '이름'
    , e.job '업무'
    , e.deptno '부서번호'
from emp e,
	(select max(count(mgr)) mgr 
    from emp) m
where e.mgr = m.mgr;
    
-- #3
select e.ename '이름', e.job '업무', e.mgr '상사번호'
from emp e,
(select mgr from emp where ename = 'BLAKE') blakeMgr
where e.mgr = blakeMgr.mgr;

-- #4-1 당장 떠오른 것...
select *
from emp
order by hiredate
limit 5;

-- #4-2 SubQuery 활용
select *
from emp e1
where 5 > 
	(select count(*)
    from emp e2
    where e2.hiredate < e1.hiredate)
order by e1.hiredate;

-- #5
select e.ename '이름', e.job '업무', d.dname '부서명'
from emp e,
(select empno from emp where ename = 'JONES') jonesNo
join dept d
where e.deptno = d.deptno
and e.mgr = jonesNo.empno
;
