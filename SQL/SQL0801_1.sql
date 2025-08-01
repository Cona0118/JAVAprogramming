USE SCOTT;

SELECT * FROM EMP;
-- 1.emp 테이블의 사원 정보 중에서 사원 이름이 ALLEN인 사원의 추가수당보다 많은 추가 수당을 받는 사원 정보를 조회해줘(서브쿼리 사용)
SELECT *
FROM   EMP
WHERE  COMM > (SELECT COMM
			     FROM EMP
				WHERE ENAME = 'ALLEN');

-- 2.사원 이름이 SCOTT인 사원보다 빨리 입사한 사원들의 목록을 조회해줘(서브쿼리 사용)
SELECT ENAME
FROM   EMP
WHERE  HIREDATE < (SELECT HIREDATE
                     FROM EMP
                    WHERE ENAME = 'SCOTT');

-- 3.전체 사원의 평균 급여보다 작거나 같은 급여를 받고 있는 20번 부서의 사원 및 부서정보를 조회해줘(서브쿼리 사용)
SELECT *
FROM   EMP E
       NATURAL JOIN DEPT D
WHERE  SAL <= (SELECT AVG(SAL)
                 FROM EMP)
AND    DEPTNO = 20;

-- 4. 30번 부서 사원들의 최대 급여보다 적은 급여를 받는 사원 정보 출력하기(다중행 함수 사용하지 않음)
SELECT *
FROM   EMP
WHERE  SAL < ANY (SELECT SAL
				    FROM EMP 
			       WHERE DEPTNO = 30);

-- 5. 30번 부서 사원들의 최소 급여보다 많은 급여를 받는 사원 정보 출력하기(다중행 함수 사용하지 않음)
SELECT *
FROM   EMP
WHERE  SAL > ANY (SELECT SAL
				    FROM EMP 
			       WHERE DEPTNO = 30);

-- 6. 부서 번호가 30번인 사원들의 최소 급여보다 더 적은 급여를 받는 사원 정보 출력하기(다중행 함수 사용하지 않음)
SELECT *
FROM   EMP
WHERE  SAL < ALL (SELECT SAL
				    FROM EMP 
			       WHERE DEPTNO = 30);

-- 7. 부서번호가 10번인 사원들의 사원정보와 부서정보를 출력하려고 하는데,
-- 아래의 쿼리문에 작성된 SELECT와 WHERE절을 변경하지 않고 해당 요건이 반영된 출력이 나올수 있도록 FROM 부분을 작성해줘
SELECT E.EMPNO, E.ENAME, E.DEPTNO, D.DNAME, D.LOC
  FROM EMP E, (SELECT * FROM DEPT WHERE DEPTNO = 10) D
 WHERE E.DEPTNO = D.DEPTNO;
 
-- ----------------------------------CHAPTER 10-------------------------------------
-- 테이블 생성
CREATE TABLE DEPT_TMP
AS     SELECT * FROM DEPT;

-- 테이블 삭제
DROP TABLE DEPT_TMP;

-- 테이블에 데이터 추가
INSERT INTO DEPT_TMP (DEPTNO,DNAME,     LOC)
            VALUES   (50,   'DATABASE','SEOUL');

INSERT INTO DEPT_TMP  # 모든 열에 데이터 추가할 경우 열 지정 생략 가능
            VALUES   (60,'NETWORK','BUSAN'); 
            
INSERT INTO DEPT_TMP (DEPTNO,DNAME,LOC)
            VALUES   (70,   'WEB',NULL); # NULL 추가 방법 1
INSERT INTO DEPT_TMP (DEPTNO,DNAME,  LOC)
            VALUES   (80,   'MOBILE',''); # NULL 추가 방법 2 (ORACLE)
INSERT INTO DEPT_TMP (DEPTNO,LOC)
            VALUES   (80,    'INCHEON'); # NULL 추가 방법 3         
            
-- 테이블 열 구조만 가져오기
CREATE TABLE EMP_TEMP
AS SELECT * FROM EMP WHERE FALSE; # 1!=1 등과 같이 WHERE 절에 FALSE 를 입력하여 데이터를 입력x

-- 날짜 데이터 입력 # ORACLE 의 경우 '2001/01/01'
INSERT INTO EMP_TEMP (EMPNO, ENAME, JOB,        MGR,   HIREDATE,     SAL, COMM, DEPTNO)
			VALUES   (9999, '홍길동', 'PRESIDENT', NULL, '2001-01-01', 5000, 1000, 10);
INSERT INTO EMP_TEMP (EMPNO, ENAME, JOB,       MGR,   HIREDATE,    SAL, COMM, DEPTNO)
			VALUES   (1111, '성춘향', 'MANAGER', 9999, '2001-01-05', 4000, NULL, 20);
INSERT INTO EMP_TEMP (EMPNO, ENAME, JOB,       MGR,   HIREDATE,                         SAL, COMM, DEPTNO)
			VALUES   (2111, '이순신', 'MANAGER', 9999,STR_TO_DATE('07-01-2001', '%d-%m-%Y'), 4000, NULL, 20); # ORACLE : TO_DATE('07-01-2001', 'DD-MM-2001')
INSERT INTO EMP_TEMP (EMPNO, ENAME, JOB,       MGR,   HIREDATE, SAL, COMM, DEPTNO)
			VALUES   (3111, '심청이', 'MANAGER', 9999, SYSDATE(), 4000, NULL, 20); # ORACLE : SYSDATE

-- 서브쿼리 사용하여 한 번에 데이터 추가
INSERT INTO   EMP_TEMP
	   SELECT E.*    -- VALUES절 사용 x
	   FROM   EMP E, SALGRADE S
	   WHERE  E.SAL BETWEEN S.LOSAL AND S.HISAL
       AND    S.GRADE = 1;


CREATE TABLE DEPT_TMP2 AS SELECT * FROM DEPT;
-- 데이터 전체 수정
UPDATE DEPT_TMP2
SET    LOC = 'SEOUL';

-- DML 되돌리기 
ROLLBACK; 

-- 데이터 중 일부분 수정
UPDATE DEPT_TMP2
SET    DNAME = 'DATABASE',
	   LOC = 'SEOUL'
WHERE  DEPTNO = 40;

-- 서브쿼리로 데이터 일부 수정
/* # ORACLE 
UPDATE DEPT_TMP2
SET    (DNAME,LOC) = (SELECT DNAME,LOC
					    FROM DEPT
					   WHERE DEPTNO = 40)
WHERE  DEPTNO = 40;
*/
UPDATE DEPT_TMP2
SET    DNAME = (SELECT DNAME FROM DEPT WHERE DEPTNO = 40),
	   LOC = (SELECT LOC FROM DEPT WHERE DEPTNO = 40)
WHERE  DEPTNO = 40;
/* ORACLE
UPDATE DEPT_TMP2
SET    LOC = 'SEOUL'
WHERE  DEPTNO = (SELECT DEPTNO
				   FROM DEPT_TMP2
				  WHERE DNAME = 'OPERATIONS'
                  LIMIT 1);
*/
UPDATE DEPT_TMP2 d1
JOIN DEPT_TMP2 d2 ON d1.DEPTNO = d2.DEPTNO
SET d1.LOC = 'SEOUL'
WHERE d2.DNAME = 'OPERATIONS';


CREATE TABLE EMP_TEMP2 AS SELECT * FROM EMP;
-- 데이터 일부 삭제
DELETE FROM EMP_TEMP2
WHERE  JOB = 'MANAGER';

-- 서브쿼리를 사용하여 데이터 삭제
/* ORACLE 
DELETE FROM EMP_TEMP2
WHERE  EMPNO IN (SELECT E.EMPNO
				   FROM EMP_TEMP2 E, SALGRADE S
				  WHERE E.SAL BETWEEN S.LOSAL AND S.HISAL
                    AND S.GRADE = 3
                    AND DEPTNO = 30);
*/
DELETE E
FROM EMP_TEMP2 E
JOIN SALGRADE S ON E.SAL BETWEEN S.LOSAL AND S.HISAL
WHERE S.GRADE = 3
  AND E.DEPTNO = 30;

-- 데이터 전체 삭제
DELETE FROM EMP_TEMP2;

DROP TABLE DEPT_TMP, DEPT_TMP2, EMP_TEMP, EMP_TEMP2;
-- 287P 확인 문제
CREATE TABLE CHAP10HW_EMP AS SELECT * FROM EMP;
CREATE TABLE CHAP10HW_DEPT AS SELECT * FROM DEPT;
CREATE TABLE CHAP10HW_SALGRADE AS SELECT * FROM SALGRADE;
-- Q1 CHAP10HW_DEPT 테이블에 50,60,70,80번 부서를 등록하는 SQL문 
INSERT INTO   CHAP10HW_DEPT
       VALUES (50, 'ORACLE', 'BUSAN'),
			  (60, 'SQL', 'ILSAN'),
              (70, 'SELECT', 'INCHEON'),
              (80, 'DML', 'BUNDANG');
        
-- Q2 CHAP10HW_EMP 테이블에 8명의 사원 정보를 등록하는 SQL문
INSERT INTO   CHAP10HW_EMP
       VALUES (7201, 'TEST_USER1',  'MANAGER', 7788, '2016-01-02', 4500, NULL, 50),
			  (7202, 'TEST_USER2',    'CLERK', 7201, '2016-02-21', 1800, NULL, 50),
              (7203, 'TEST_USER3',  'ANALYST', 7201, '2016-04-11', 3400, NULL, 60),
              (7204, 'TEST_USER4', 'SALESMAN', 7201, '2016-05-31', 2700,  300, 60),
              (7205, 'TEST_USER5',    'CLERK', 7201, '2016-07-20', 2600, NULL, 70),
              (7206, 'TEST_USER6',    'CLERK', 7201, '2016-09-08', 2600, NULL, 70),
              (7207, 'TEST_USER7', 'LECTURER', 7201, '2016-10-28', 2300, NULL, 80),
              (7208, 'TEST_USER8',  'STUDENT', 7201, '2018-03-09', 1200, NULL, 80);

-- Q3 CHAP10HW_EMP 에 속한 사원중 50번 부서에서 근무하는 사원들의 평균 급여보다 많은 급여를 받고 있는 사원들을 70번 부서로 이동
UPDATE CHAP10HW_EMP
SET    DEPTNO = 70
WHERE  SAL > (SELECT TEMP.SAL
			  FROM   (SELECT AVG(SAL) AS SAL 
                      FROM CHAP10HW_EMP WHERE DEPTNO = 50) TEMP);

SELECT * FROM CHAP10HW_EMP;

-- Q4 CHAP10HW_EMP에 속한 사원 중, 60번 부서의 사원 중에 입사일이 가장 빠른 사원보다 늦게 입사한 사원의 급여를 10% 인상하고 80번 부서로 이동
UPDATE CHAP10HW_EMP
SET    DEPTNO = 80, SAL = SAL * 1.1
WHERE  HIREDATE > ( SELECT TEMP.HIREDATE
					FROM   (SELECT MIN(HIREDATE) AS HIREDATE 
							FROM   CHAP10HW_EMP WHERE DEPTNO = 60) TEMP);

SELECT * FROM CHAP10HW_EMP;

-- Q5 CHAP10HW_EMP에 속한 사원 중, 급여 등급이 5인 사원을 삭제하는 SQL문
DELETE FROM CHAP10HW_EMP
 WHERE EMPNO IN ( SELECT TEMP.EMPNO
				  FROM   (SELECT E.EMPNO AS EMPNO
						  FROM   CHAP10HW_EMP E, CHAP10HW_SALGRADE S
						  WHERE  E.SAL BETWEEN S.LOSAL AND S.HISAL
                          AND    S.GRADE = 5) TEMP);
                    
SELECT * FROM CHAP10HW_EMP;

DROP TABLE CHAP10HW_EMP, CHAP10HW_DEPT, CHAP10HW_SALGRADE;
-- ----------------------------------CHAPTER 11-------------------------------------
CREATE TABLE DEPT_TCL
AS     SELECT * FROM DEPT;

SET autocommit = 0; -- 자동 커밋모드 해제

-- 트랜젝션 : 더 이상 분할할 수 없는 최소 수행 단위
INSERT INTO DEPT_TCL VALUES (50, 'DATABASE', 'SEOUL');

-- 명령어 실행 취소
ROLLBACK;

-- 명령어 영구 반영 
INSERT INTO DEPT_TCL VALUES (50, 'NETWORK', 'SEOUL');
COMMIT;
ROLLBACK; -- ROLLBACK 실행해도 취소 불가

DROP TABLE DEPT_TCL;
SET autocommit = 1;

--  세션 : 어떤 활동을 위한 시간이나 기간 -> 하나의 세션에는 하나 이상의 트랜잭션이 존재
-- LOCK : 특정 세션에서 조작중인 데이터는 트랜잭션이 완료 되기 전까지 다른 세션에서 조작할 수 없는 상태가 됨
-- HANG : 특정 세션에서 데이터 조작이 완료될 때 까지 다른 세션에서 해당 데이터 조작을 기다리는 현상 

