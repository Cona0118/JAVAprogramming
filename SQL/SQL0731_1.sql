USE SCOTT;
-- 1. 급여가 3000이상이고, 직속상관이 있는 사원들의 사원정보와 부서정보를 출력하시오
SELECT *
FROM   EMP E, DEPT D
WHERE  E.DEPTNO = D.DEPTNO
AND    E.SAL >= 3000
AND    E.MGR IS NOT NULL;

SELECT E.*, D.*
FROM   EMP E
	   NATURAL JOIN DEPT D
WHERE  E.SAL >= 3000 
AND    MGR IS NOT NULL;

-- 2. 모든 부서정보와 사원 정보를 부서 번호, 사원 이름 순으로 정렬하여 출력하시오
-- (단, 부서 테이블에 있는 모든 부서코드 정보가 출력되어야 함)
SELECT *
FROM   DEPT D LEFT OUTER JOIN EMP E ON (D.DEPTNO=E.DEPTNO)
ORDER BY D.DEPTNO, E.ENAME;

SELECT D.*, E.*
FROM DEPT D LEFT JOIN EMP E ON D.DEPTNO = E.DEPTNO
ORDER BY D.DEPTNO , E.ENAME;


-- 3. 화면과 같이 결과 화면으로 출력 될수 있도록 sql문을 작성하시오
SELECT   D.DEPTNO, D.DNAME,
	     E.EMPNO, E.ENAME, E.MGR, E.SAL, E.DEPTNO AS DEPTNO_1,
         S.LOSAL, S.HISAL, S.GRADE,
         E.MGR AS MGR_EMPNO, E2.ENAME AS MGR_ENAME
FROM     DEPT D 
	     LEFT OUTER JOIN EMP E      ON (D.DEPTNO = E.DEPTNO)
         LEFT OUTER JOIN SALGRADE S ON (E.SAL BETWEEN S.LOSAL AND S.HISAL )
	     LEFT OUTER JOIN EMP E2     ON (E.MGR = E2.EMPNO)
ORDER BY DEPTNO;

SELECT   D.DEPTNO, D.DNAME,
	     E1.EMPNO, E1.ENAME, E1.MGR, E1.SAL, E1.DEPTNO AS DEPTNO_1,
		 S.LOSAL, S.HISAL, S.GRADE,
		 E2.MGR AS MGR_EMPNO, E2.ENAME AS MGR_ENAME
FROM	 DEPT D
         LEFT JOIN EMP     E1 ON (D.DEPTNO = E1.DEPTNO)
         LEFT JOIN SALGRADE S ON (E1.SAL BETWEEN S.LOSAL AND S.HISAL)
		 LEFT JOIN EMP     E2 ON (E1.MGR = E2.EMPNO)
ORDER BY D.DEPTNO , E1.ENAME;

-- 4. 사원 'WARD'보다 많은 추가 수당을 받는 사원의 사원정보를 출력하시오
SELECT *
FROM   EMP
WHERE  COMM > (SELECT COMM FROM EMP WHERE ENAME = 'WARD');

-- 5. 30번 부서의 사원 중에서, 전체 사원의 평균 급여 보다 높은 급여를 받는 사원의 사원정보와 부서정보를 출력하시오
SELECT *
FROM   EMP E, DEPT D
WHERE  E.DEPTNO = D.DEPTNO
AND    E.SAL > (SELECT AVG(SAL) FROM EMP)
AND    E.DEPTNO = 30;

SELECT E.*, D.*
FROM   EMP E
	   JOIN DEPT D USING (DEPTNO)
WHERE  SAL > (SELECT AVG(SAL) FROM EMP)
AND    DEPTNO = 30;

-- 6. 사원 테이블에 있는 전체 사원정보를 출력하되, 부서이름도 포함시켜서 출력하시오(Result Grid에 DEPTNM 이라는 이름의 컬럼으로 출력)
SELECT E.*, 
	   (SELECT DNAME FROM DEPT D WHERE E.DEPTNO = D.DEPTNO) AS DEPTNM 
FROM   EMP E;

-- -----------------------------------------------------------------------------------------------------------
-- 다중행 서브쿼리 249P
-- IN 연산자
SELECT *
FROM   EMP
WHERE  SAL IN (SELECT MAX(SAL) FROM EMP GROUP BY DEPTNO);
		-- IN (2850,3000,5000);
        
-- ANY, SOME 
SELECT *
FROM   EMP 
WHERE  SAL = ANY (SELECT MAX(SAL) FROM EMP GROUP BY DEPTNO); 
	        -- 2850, 3000, 5000 중 하나라도 충족할 경우 출력
            
SELECT *
FROM   EMP 
WHERE  SAL = SOME (SELECT MAX(SAL) FROM EMP GROUP BY DEPTNO); 
			-- 2850, 3000, 5000 중 하나라도 충족할 경우 출력

SELECT 	 *
FROM     EMP
WHERE 	 SAL < ANY (SELECT MAX(SAL) FROM EMP WHERE DEPTNO = 30)
ORDER BY SAL, EMPNO;

-- ALL : 모두 TRUE 면 출력
SELECT 	 *
FROM     EMP
WHERE 	 SAL < ALL (SELECT MAX(SAL) FROM EMP WHERE DEPTNO = 30)
ORDER BY SAL, EMPNO;

-- EXISTS : 결과값이 있으면 T / 없으면 F
SELECT 	*
FROM    EMP
WHERE 	EXISTS (SELECT DNAME FROM DEPT WHERE DEPTNO = 10);

SELECT 	*
FROM    EMP
WHERE 	EXISTS (SELECT DNAME FROM DEPT WHERE DEPTNO = 50);

-- 다중열 서브쿼리
SELECT *
FROM   EMP
WHERE  (DEPTNO, SAL)
   IN  (SELECT DEPTNO, MAX(SAL) FROM EMP GROUP BY DEPTNO);
   
-- WITH 절
SELECT E10.EMPNO, E10.ENAME, E10.DEPTNO, D.DNAME, D.LOC
FROM   (SELECT * FROM EMP WHERE DEPTNO = 10) E10,
	   (SELECT * FROM DEPT) D
WHERE  E10.DEPTNO = D.DEPTNO; 

WITH
E10 AS (SELECT * FROM EMP WHERE DEPTNO = 10),
D   AS (SELECT * FROM DEPT)
SELECT E10.EMPNO, E10.ENAME, E10.DEPTNO, D.DNAME, D.LOC
FROM   E10, D
WHERE  E10.DEPTNO = D.DEPTNO;

-- SELECT절에 사용하는 서브쿼리
SELECT EMPNO, ENAME, JOB, SAL,
	   (SELECT GRADE FROM SALGRADE WHERE E.SAL BETWEEN LOSAL AND HISAL) AS SALGRADE,
       DEPTNO,
       (SELECT DNAME FROM DEPT WHERE E.DEPTNO = DEPT.DEPTNO) AS DNAME
FROM   EMP E;

-- 262P Q1 ALLEN과 같은 직책인 사원들의 사원정보, 부서정보 출력
SELECT E.JOB, E.EMPNO, E.ENAME, E.SAL,
	   D.DEPTNO, D.DNAME
FROM   EMP E, DEPT D
WHERE  E.DEPTNO = D.DEPTNO
AND    E.JOB = (SELECT JOB FROM EMP WHERE ENAME = 'ALLEN');

-- Q2 전체사원의 평균 급여보다 높은 급여를 받는 사원들의 사원정보, 부서정보, 급여등급 정보를 출력
SELECT   E.EMPNO, E.ENAME, D.DNAME, E.HIREDATE, D.LOC, E.SAL,
	     (SELECT GRADE FROM SALGRADE WHERE E.SAL BETWEEN LOSAL AND HISAL) AS GRADE
FROM     EMP E NATURAL JOIN DEPT D
WHERE    E.SAL > (SELECT AVG(SAL) FROM EMP)
ORDER BY E.SAL DESC, E.EMPNO;

SELECT   E.EMPNO, E.ENAME, D.DNAME, E.HIREDATE, D.LOC, E.SAL, S.GRADE
FROM     EMP E, DEPT D, SALGRADE S
WHERE    E.DEPTNO = D.DEPTNO
AND      E.SAL BETWEEN S.LOSAL AND S.HISAL
AND      E.SAL > (SELECT AVG(SAL) FROM EMP)
ORDER BY E.SAL DESC, E.EMPNO;

-- Q3 10번 부서에 근무하는 사원 중 30번 부서에는 존재하지 않는 직책을 가진 사원들의 사원정보, 부서정보 출력
SELECT E.EMPNO, E.ENAME, E.JOB,
	   D.DEPTNO, D.DNAME, D.LOC
FROM   EMP E NATURAL JOIN DEPT D
WHERE  E.JOB NOT IN (SELECT JOB FROM EMP WHERE DEPTNO = 30)
AND    DEPTNO = 10;

-- Q4 SALESMAN의 최고급여보다 더 높은 급여를 받는 사원들의 사원정보, 급여등급 출력
SELECT   E.EMPNO, E.ENAME, E.SAL, 
	     (SELECT GRADE FROM SALGRADE WHERE E.SAL BETWEEN LOSAL AND HISAL) AS GRADE
FROM     EMP E
WHERE    E.SAL > ALL (SELECT DISTINCT SAL FROM EMP WHERE JOB = 'SALESMAN')  
ORDER BY EMPNO;

SELECT   E.EMPNO, E.ENAME, E.SAL, 
	     (SELECT GRADE FROM SALGRADE WHERE E.SAL BETWEEN LOSAL AND HISAL) AS GRADE
FROM     EMP E
WHERE    E.SAL > (SELECT MAX(SAL) FROM EMP WHERE JOB = 'SALESMAN') 
ORDER BY EMPNO;

-- ---------------------------------------------------------------------------------------------
USE SHOPPING;

-- 1.각 상품에 대한 평균 평점(rating)을 구하되, 평균 평점이 4 이상인 상품만 출력하시오.
SELECT   P.NAME        AS 상품_이름, 
	     AVG(R.RATING) AS 평균_평점
FROM     REVIEWS R NATURAL JOIN PRODUCTS P 
GROUP BY PRODUCT_ID
HAVING   AVG(RATING) >= 4;

-- 2.모든 카테고리와 상품을 출력하되, 카테고리가 지정되지 않은 상품이나 상품이 없는 카테고리도 포함되도록 하시오. (카테고리 이름, 상품 이름)
SELECT P.NAME, C.CATEGORY_NAME
FROM   PRODUCTS P RIGHT JOIN CATEGORIES C ON (P.CATEGORY_ID = C.CATEGORY_ID)
UNION 
SELECT P.NAME, C.CATEGORY_NAME
FROM   PRODUCTS P LEFT JOIN CATEGORIES C ON (P.CATEGORY_ID = C.CATEGORY_ID);

-- 3.각 고객이 주문한 상품 이름과 수량을 출력하시오. 고객 이름, 상품 이름, 수량을 출력하세요.
SELECT C.NAME AS 고객이름, P.NAME AS 상품이름, OI.QUANTITY AS 수량
FROM   ORDER_ITEMS OI, PRODUCTS P, CUSTOMERS C, ORDERS O
WHERE  OI.ORDER_ID = O.ORDER_ID
AND    OI.PRODUCT_ID = P.PRODUCT_ID
AND    O.CUSTOMER_ID = C.CUSTOMER_ID;

-- 4.모든 상품에 대해 작성된 리뷰를 출력하시오. 상품에 리뷰가 없는 경우에도 상품 정보가 출력되도록 하시오. (상품 이름, 평점, 리뷰 내용)
SELECT  P.NAME AS '상품이름',
		R.RATING AS '평점',
        R.COMMENT AS '리뷰내용'
FROM 	PRODUCTS P LEFT JOIN REVIEWS R USING (PRODUCT_ID);

-- 5.각 상품 카테고리별로 판매된 상품의 수량을 구하시오. 카테고리 이름과 판매된 상품 수량을 출력하시오.
SELECT 	 C.CATEGORY_NAME, SUM(OI.QUANTITY) AS '판매수량'
FROM 	 CATEGORIES C, PRODUCTS P, ORDER_ITEMS OI
WHERE    C.CATEGORY_ID = P.CATEGORY_ID
AND      OI.PRODUCT_ID = P.PRODUCT_ID
GROUP BY C.CATEGORY_ID;

-- 6.각 카테고리별로 가격이 가장 높은 상품을 출력하시오. 카테고리 이름, 상품 이름, 가격을 출력하세요.
SELECT   C.CATEGORY_NAME AS '카테고리 이름',
		 (SELECT DISTINCT NAME FROM PRODUCTS WHERE PRICE = MAX(P.PRICE)) AS '상품 이름', 
		 MAX(P.PRICE) 	 AS '가격'
FROM     CATEGORIES C LEFT JOIN PRODUCTS P USING (CATEGORY_ID)
GROUP BY C.CATEGORY_ID;

SELECT
    C.CATEGORY_NAME,
    P.NAME AS PRODUCT_NAME,
    P.PRICE
    # AVG_TBL.AVG_PRICE AS CATEGORY_AVG_PRICE
FROM
    PRODUCTS P
JOIN
    CATEGORIES C ON P.CATEGORY_ID = C.CATEGORY_ID
/* JOIN (
    SELECT CATEGORY_ID, AVG(PRICE) AS AVG_PRICE
    FROM PRODUCTS
    GROUP BY CATEGORY_ID
) AS AVG_TBL ON P.CATEGORY_ID = AVG_TBL.CATEGORY_ID */
WHERE
    (P.CATEGORY_ID, P.PRICE) IN (
        SELECT CATEGORY_ID, MAX(PRICE)
        FROM PRODUCTS
        GROUP BY CATEGORY_ID
    );

-- 7.모든 고객에 대해 주문한 내역을 출력하시오. 고객이 주문을 하지 않은 경우에도 고객 정보가 출력되도록 하시오. (고객 이름, 이메일, 주문 날짜)
SELECT C.NAME, C.EMAIL, O.ORDER_DATE
FROM   CUSTOMERS C LEFT JOIN ORDERS O USING (CUSTOMER_ID);

-- 8.주문 총액이 가장 큰 고객을 출력하시오. 고객 이름과 이메일, 총 주문 금액을 출력하세요.
SELECT   C.NAME, C.EMAIL, SUM(O.TOTAL_AMOUNT) AS '총 주문 금액'
FROM     CUSTOMERS C NATURAL JOIN ORDERS O
GROUP BY CUSTOMER_ID
ORDER BY '총 주문 금액' DESC
LIMIT    1;

SELECT
    c.name AS 고객_이름,
    c.email AS 이메일,
    SUM(o.total_amount) AS 총_주문_금액
FROM
    customers c, orders o
WHERE
    c.customer_id = o.customer_id
GROUP BY
    c.customer_id
HAVING
    SUM(o.total_amount) = (
        SELECT MAX(total_sum) FROM (
            SELECT SUM(total_amount) AS total_sum
            FROM orders
            GROUP BY customer_id
        ) AS sub
    );         

-- 9.고객이 주문한 각 상품의 총 수량을 출력하되, 주문을 하지 않은 고객도 포함되도록 하시오. (고객 이름, 상품 이름, 총 수량)
SELECT C.NAME, P.NAME, OI.QUANTITY
FROM   CUSTOMERS C 
	   LEFT JOIN ORDERS O 		ON (C.CUSTOMER_ID = O.CUSTOMER_ID)
	   LEFT JOIN ORDER_ITEMS OI ON (O.ORDER_ID = OI.ORDER_ID)
	   LEFT JOIN PRODUCTS P		ON (OI.PRODUCT_ID = P.PRODUCT_ID);
            
SELECT
    c.name AS 고객_이름,
    p.name AS 상품_이름,
    SUM(oi.quantity) AS 총_수량
FROM
    customers c
left outer JOIN orders o ON c.customer_id = o.customer_id
left outer JOIN order_items oi ON o.order_id = oi.order_id
left outer JOIN products p ON oi.product_id = p.product_id
GROUP BY
    c.customer_id, p.product_id, c.name, p.name
ORDER BY
    c.name, p.name;

-- 10.각 고객별로 총 주문 금액(total_amount)을 구하시오. 고객 정보와 함께 출력하되, 총 주문 금액이 1000 이상인 고객만 출력하세요.
SELECT   C.*, SUM(O.TOTAL_AMOUNT) AS '총 주문 금액'
FROM     CUSTOMERS C NATURAL JOIN ORDERS O
GROUP BY C.CUSTOMER_ID
HAVING   SUM(O.TOTAL_AMOUNT) >= 1000;

-- 11.모든 상품에 대해 주문된 수량을 출력하되, 주문이 없는 상품도 포함되도록 하시오. (상품 이름, 주문된 수량)
SELECT   P.NAME 	      AS '상품 이름', 
	     SUM(OI.QUANTITY) AS '주문된 수량'   -- IFNULL(SUM(oi.quantity), 0) AS 주문_수량
FROM     PRODUCTS P LEFT JOIN ORDER_ITEMS OI ON (P.PRODUCT_ID = OI.PRODUCT_ID)
GROUP BY P.PRODUCT_ID;

-- 12.리뷰가 작성되지 않은 상품의 상품 코드와 상품 이름을 출력하시오.
SELECT P.PRODUCT_ID, P.NAME
FROM   PRODUCTS P LEFT OUTER JOIN REVIEWS R ON (P.PRODUCT_ID = R.PRODUCT_ID)
WHERE  R.REVIEW_ID IS NULL;

