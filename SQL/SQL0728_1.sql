use scott;

select empno,deptno from emp; 

select distinct deptno from emp; -- 중복 제거

select all job,deptno from emp;

select ename, sal, sal*12+comm, comm from emp;
select ename, sal, sal*12+comm as annsal, comm from emp; -- 별칭 지정
select ename, sal, sal*12+comm annsal, comm from emp;
select ename, sal, sal*12+comm "annsal", comm from emp;
select ename, sal, sal*12+comm as "annsal", comm from emp;

select * from emp order by sal; -- 정렬 (default 오름차순)
select * from emp order by sal desc; -- 내림차순 정렬

-- 부서번호(오름차순)와 급여(내림차순) 로 정렬
select * from emp order by deptno, sal desc;

-- 중복X, job 열 데이터 출력
select distinct JOB from emp;

-- 별칭 지정, 부서번호 기준 내림차순 같다면 사원이름 기준 오름차순
 select	 EMPNO 	AS EMPLOYEE_NO,
		 ENAME 	AS EMPLOYEE_NAME,
		 JOB,
		 MGR 	AS MANAGER,
         HIREDATE,
		 SAL 	AS SALARY,
		 COMM 	AS COMMISSION,
		 DEPTNO AS DEPARTMENT_NO
  from   emp
order by deptno desc, ename;

-- where 조건절
SELECT *
FROM EMP
WHERE DEPTNO = 30;
   
SELECT *
FROM EMP
WHERE EMPNO = 7782;

SELECT *
FROM EMP
WHERE DEPTNO = 30 AND JOB = "SALESMAN";
   
SELECT *
FROM EMP
WHERE DEPTNO = 20 OR JOB = "SALESMAN";

SELECT *
FROM EMP
WHERE SAL >= 3000;

SELECT *
FROM EMP
WHERE JOB IN ('MANAGER', 'SALESMAN', 'CLERK');

SELECT * 
FROM EMP
WHERE SAL BETWEEN 2000 AND 3000;

SELECT * 
FROM EMP
WHERE SAL NOT BETWEEN 2000 AND 3000;

-- S로 시작하는 이름
SELECT *
FROM EMP
WHERE ENAME LIKE 'S%';

-- 두번째 문자가 L인 이름
SELECT *
FROM EMP
WHERE ENAME LIKE '_L%';

-- AM이 포함되어있는 이름
SELECT *
FROM EMP
WHERE ENAME LIKE '%AM%';

-- AM이 포함되어 있지 않은 이름
SELECT *
FROM EMP
WHERE ENAME NOT LIKE '%AM%';

-- IS NULL 연산자
SELECT *
FROM EMP
WHERE COMM IS NULL;

SELECT *
FROM EMP
WHERE COMM IS NOT NULL;

-- 유니온 (집합 연산자)
SELECT EMPNO, ENAME, SAL, DEPTNO
FROM EMP
WHERE DEPTNO = 10
UNION -- 합집합
SELECT EMPNO, ENAME, SAL, DEPTNO
FROM EMP
WHERE DEPTNO = 20;

SELECT EMPNO, ENAME, SAL, DEPTNO
FROM EMP
WHERE DEPTNO = 10
UNION ALL -- 중복 데이터도 모두 출력
SELECT EMPNO, ENAME, SAL, DEPTNO
FROM EMP
WHERE DEPTNO = 10;











