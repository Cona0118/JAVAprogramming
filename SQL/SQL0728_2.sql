USE SCOTT;
-- 사원 번호가 7499이고 부서 번호가 30번인 사원 전체 정보
SELECT *
FROM EMP
WHERE EMPNO = 7499
AND DEPTNO = 30;

-- 20번 부서 사원들 중에서 급여가 3000이상인 사원 전체 정보
SELECT *
FROM EMP
WHERE DEPTNO = 20
AND SAL >= 3000;

-- 부서번호가 10,20인 사원 전체 정보
SELECT *
FROM EMP
WHERE DEPTNO IN (10,20);

-- 급여가 2000 이상 2500 이하인 사원의 이름과 부서
SELECT ENAME, DEPTNO
FROM EMP
WHERE SAL BETWEEN 2000 AND 2500;

-- 사원 이름에 AM이 포함되어 있는 사원 전체 정보
SELECT *
FROM EMP
WHERE ENAME LIKE "%AM%";

-- 사원의 두 번째 글자가 I인 사원 전체 정보
SELECT *
FROM EMP
WHERE ENAME LIKE "_I%";

-- Q1 이름이 s로 끝나는 사원 데이터 모두 출력
SELECT *
FROM   EMP
WHERE  ENAME LIKE "%S";

-- Q2 30번 부서에서 근무하고 있는 사원 중 직책이 SALESMAN인 사원의
-- 사원번호 이름 직책 급여 부서번호 출력
SELECT EMPNO,
	   ENAME,
	   JOB,
       SAL,
       DEPTNO
FROM   EMP
WHERE  DEPTNO = 30  
AND    JOB = "SALESMAN";

-- Q3 20번, 30번 부서에서 근무하고 있는 사원 중 급여가 2000 초과인 사원을
-- 집합 연산자를 사용한 방식과 사용하지 않은 방식으로 사원번호 이름 급여 부서번호 출력
SELECT E1.EMPNO,
	   E1.ENAME,
       E1.SAL,
       E1.DEPTNO
FROM   EMP E1
INNER  JOIN EMP E2
ON 	   E1.EMPNO = E2.EMPNO
AND    E1.DEPTNO IN (20,30)
AND    E2.SAL > 2000;

SELECT EMPNO,
	   ENAME,
       SAL,
       DEPTNO
FROM   EMP
WHERE  SAL > 2000
AND    DEPTNO IN (20,30);

/* -- INTERSECT 사용 했을 경우
SELECT EMPNO, ENAME, JOB, SAL, DEPTNO
FROM EMP
WHERE DEPTNO IN (20,30)
  AND SAL > 2000;
SELECT EMPNO, ENAME, JOB, SAL, DEPTNO
FROM EMP
WHERE DEPTNO IN (20,30)
INTERSECT
SELECT EMPNO, ENAME, JOB, SAL, DEPTNO
FROM EMP
WHERE SAL > 2000;
*/

-- Q4 NOT BETWEEN 연산자를 쓰지 않고 급여 값이
-- 2000 이상 3000 이하인 범위 이외의 값을 가진 데이터만 출력
SELECT *
FROM   EMP
WHERE  SAL < 2000
OR     SAL > 3000;

-- Q5 사원 이름에 E가 포함되어 있는 30번 부서의 사원중
-- 급여가 1000~2000 사이가 아닌 사원의 이름 사원번호 급여 부서번호 출력
SELECT ENAME,
	   EMPNO,
       SAL,
       DEPTNO
FROM   EMP
WHERE  ENAME  LIKE "%E%"
AND	   DEPTNO = 30
AND    SAL    NOT BETWEEN 1000 AND 2000;

-- Q6 추가수당이 존재하지 않고 상급자가 있고 직책이 MANAGER CLERK인 
-- 사원중에서 사원의 이름 두번째 글자가 L이 아닌 사원의 정보를 출력
SELECT *
FROM   EMP
WHERE  COMM  IS NULL
AND    MGR   IS NOT NULL
AND    JOB   IN ("MANAGER","CLERK")
AND    ENAME NOT LIKE "_L%";






