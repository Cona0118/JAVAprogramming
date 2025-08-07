/* 1. 다음은 은행 업무에서 사용되는 공통 코드를 저장하는 CommonCode 테이블의 구조이다. 이 테이블을 생성하는 SQL 문을 작성하시오.
code_group: 문자열(최대 20자), 코드 그룹명 (예: 'AccountType', 'UseYn') (코드그룹)
code: 정수형, 코드 번호 (예: 10, 15, 1, 0 등) (코드번호)
code_name: 문자열(최대 50자), 코드 설명 (코드명)
is_active: 정수형(TINYINT), 사용 여부 (1: 사용함, 0: 사용안함), 기본값 1 (사용여부)
기본키는 (code_group, code)의 복합키로 한다. */

CREATE TABLE COMMONCODE ( 
    CODE_GROUP VARCHAR(20),
    CODE INT,
    CODE_NAME VARCHAR(50),
    IS_ACTIVE TINYINT DEFAULT 1,
    PRIMARY KEY (CODE_GROUP, CODE)
);

/* -다음은 은행 고객 정보를 저장하는 Customer 테이블의 구조이다. 해당 테이블을 생성하는 SQL 문을 작성하시오.
customer_id: 정수형, 기본키 (고객번호)
name: 문자열(최대 100자) (이름)
phone: 문자열(최대 20자) (전화번호)
email: 문자열(최대 100자) (이메일)
address: 문자열(최대 255자) (주소) */

CREATE TABLE CUSTOMER (
    CUSTOMER_ID INT PRIMARY KEY,
    NAME VARCHAR(100),
    PHONE VARCHAR(20),
    EMAIL VARCHAR(100),
    ADDRESS VARCHAR(255)
);

/* -다음은 고객 계좌 정보를 저장하는 Account 테이블의 구조이다. 해당 테이블을 생성하는 SQL 문을 작성하시오.
account_id: 정수형, 기본키 (계좌번호)
customer_id: 정수형, Customer.customer_id를 참조하는 외래키 (고객번호)
account_type_code: 정수형, CommonCode 테이블의 AccountType 그룹에 해당하는 코드 (계좌유형코드)
balance: 소수점 둘째 자리까지의 숫자형(DECIMAL(15,2)) (잔액)
is_dormant: 정수형(TINYINT), 사용 여부 코드 (UseYn 그룹 참조), 기본값 0 (휴면계좌여부)
opened_date: 날짜형 (개설일) */

CREATE TABLE ACCOUNT (
    ACCOUNT_ID INT PRIMARY KEY,
    CUSTOMER_ID INT,
    ACCOUNT_TYPE_CODE INT,
    BALANCE DECIMAL(15,2),
    IS_DORMANT TINYINT DEFAULT 0,
    OPENED_DATE DATE,
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID)
);


/* -다음은 계좌의 거래 내역을 저장하는 AccountTransaction 테이블의 구조이다. 해당 테이블을 생성하는 SQL 문을 작성하시오.
transaction_id: 정수형, 기본키 (거래번호)
account_id: 정수형, Account.account_id를 참조하는 외래키 (계좌번호)
transaction_type: 문자열(최대 10자), 거래 유형 (입금, 출금 등) (거래유형)
amount: 소수점 둘째 자리까지의 숫자형(DECIMAL(15,2)) (금액)
transaction_date: 일시형(DATETIME) (거래일시) */

CREATE TABLE AccountTransaction (
    TRANSACTION_ID INT PRIMARY KEY,
    ACCOUNT_ID INT,
    TRANSACTION_TYPE VARCHAR(10),
    AMOUNT DECIMAL(15,2),
    TRANSACTION_DATE DATETIME,
    FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNT(ACCOUNT_ID)
);


/* -다음은 대출 정보를 저장하는 Loan 테이블의 구조이다. 해당 테이블을 생성하는 SQL 문을 작성하시오.
loan_id: 정수형, 기본키 (대출번호)
account_id: 정수형, Account.account_id를 참조하는 외래키 (대출 계좌만 해당) (계좌번호)
loan_amount: 소수점 둘째 자리까지의 숫자형(DECIMAL(15,2)) (대출금액)
interest_rate: 소수점 둘째 자리까지의 숫자형(DECIMAL(5,2)), 이자율(%) (이자율)
due_date: 날짜형 (상환예정일) */

CREATE TABLE LOAN (
    LOAN_ID INT PRIMARY KEY,
    ACCOUNT_ID INT,
    LOAN_AMOUNT DECIMAL(15,2),
    INTEREST_RATE DECIMAL(5,2),
    DUE_DATE DATE,
    FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNT(ACCOUNT_ID)
);


/* 2. 다음은 은행 고객 및 계좌 데이터를 삽입하기 위한 정보이다. 아래 내용을 참고하여 각 테이블에 데이터를 삽입하는 SQL 문을 작성하시오.
-고객 정보
고객 1: 홍길동, 전화번호 '010-1111-2222', 이메일 'hong@example.com', 주소 '서울특별시 강남구'
고객 2: 김영희, 전화번호 '010-2222-3333', 이메일 'kim@example.com', 주소 '부산광역시 해운대구'
고객 3: 이철수, 전화번호 '010-3333-4444', 이메일 'lee@example.com', 주소 '대전광역시 서구'
고객 4: 박지민, 전화번호 '010-4444-5555', 이메일 'park@example.com', 주소 '광주광역시 남구'
고객 5: 최수민, 전화번호 '010-5555-6666', 이메일 'choi@example.com', 주소 '인천광역시 남동구' */

INSERT INTO CUSTOMER 
VALUES  (1, '홍길동', '010-1111-2222', 'hong@example.com', '서울특별시 강남구'),
	(2, '김영희', '010-2222-3333',  'kim@example.com', '부산광역시 해운대구'),
	(3, '이철수', '010-3333-4444',  'lee@example.com', '대전광역시 서구'),
	(4, '박지민', '010-4444-5555', 'park@example.com', '광주광역시 남구'),
	(5, '최수민', '010-5555-6666', 'choi@example.com', '인천광역시 남동구');
        
        
/* -계좌 정보
계좌 101: 고객 1, 보통예금(10), 잔액 1,500,000원, 휴면 아님(0), 개설일 '2020-01-15'
계좌 102: 고객 1, 적금(15), 잔액 5,000,000원, 휴면 아님(0), 개설일 '2022-03-01'
계좌 103: 고객 2, 보통예금(10), 잔액 200,000원, 휴면 계좌(1), 개설일 '2019-11-20'
계좌 104: 고객 3, 대출(20), 잔액 -10,000,000원, 휴면 아님(0), 개설일 '2021-06-10'
계좌 106: 고객 2, 보통예금(10), 잔액 750,000원, 휴면 아님(0), 개설일 '2023-09-10'
계좌 107: 고객 4, 보통예금(10), 잔액 300,000원, 휴면 아님(0), 개설일 '2023-01-10'
계좌 108: 고객 4, 적금(15), 잔액 600,000원, 휴면 아님(0), 개설일 '2023-05-20'
계좌 109: 고객 2, 보통예금(10), 잔액 0원, 휴면 아님(0), 개설일 '2009-12-31' */
  
INSERT INTO ACCOUNT 
VALUES  (101, 1, 10,   1500000, 0, '2020-01-15'),
	(102, 1, 15,   5000000, 0, '2022-03-01'),
	(103, 2, 10, 	200000, 1, '2019-11-20'),
	(104, 3, 20, -10000000, 0, '2021-06-10'),
	(106, 2, 10, 	750000, 0, '2023-09-10'),
	(107, 4, 10, 	300000, 0, '2023-01-10'),
	(108, 4, 15, 	600000, 0, '2023-05-20'),
	(109, 2, 10,    	 0, 0, '2009-12-31' );


/* -거래 내역
거래 1: 계좌 101, 입금, 1,000,000원, '2025-08-01 10:00:00'
거래 2: 계좌 101, 출금, 200,000원, '2025-08-03 15:30:00'
거래 3: 계좌 102, 입금, 5,000,000원, '2025-07-01 09:00:00'
거래 4: 계좌 103, 출금, 50,000원, '2024-12-25 11:00:00'
거래 5: 계좌 104, 입금(대출 상환), 1,000,000원, '2025-08-01 13:00:00'
거래 6: 계좌 101, 입금, 300,000원, '2024-03-10 12:00:00'
거래 7: 계좌 102, 출금, 1,000,000원, '2024-11-05 14:00:00'
거래 8: 계좌 106, 입금, 100,000원, '2025-01-01 10:00:00'
거래 9: 계좌 106, 출금, 50,000원, '2025-02-01 10:00:00'
거래 10: 계좌 106, 입금, 200,000원, '2025-03-01 10:00:00' */

INSERT INTO ACCOUNTTRANSACTION 
VALUES  (1, 101, '입금', 1000000, '2025-08-01 10:00:00'),
	(2, 101, '출금',  200000, '2025-08-03 15:30:00'),
	(3, 102, '입금', 5000000, '2025-07-01 09:00:00'),
	(4, 103, '출금',   50000, '2024-12-25 11:00:00'),
	(5, 104, '입금', 1000000, '2025-08-01 13:00:00'),
	(6, 101, '입금',  300000, '2024-03-10 12:00:00'),
	(7, 102, '출금', 1000000, '2024-11-05 14:00:00'),
	(8, 106, '입금',  100000, '2025-01-01 10:00:00'),
	(9, 106, '출금',   50000, '2025-02-01 10:00:00'),
	(10, 106, '입금', 200000, '2025-03-01 10:00:00');


/* -대출 정보
대출 1: 계좌 104, 대출금 10,000,000원, 이자율 3.5%, 상환일 '2026-12-31' */

INSERT INTO LOAN
VALUES (1, 104, 10000000, 3.5, '2026-12-31');


/* -공통 코드 데이터
  -계좌 종류 (AccountType)
   code_group: 'AccountType', code: 10, code_name: '보통예금', is_active: 1
   code_group: 'AccountType', code: 15, code_name: '적금', is_active: 1
   code_group: 'AccountType', code: 18, code_name: '예금', is_active: 1
   code_group: 'AccountType', code: 20, code_name: '대출', is_active: 1
   code_group: 'AccountType', code: 30, code_name: '펀드', is_active: 0  
  -사용 여부 (UseYn)
   code_group: 'UseYn', code: 0, code_name: '사용안함', is_active: 1
   code_group: 'UseYn', code: 1, code_name: '사용함', is_active: 1 */

INSERT INTO COMMONCODE 
VALUES  ('AccountType', 10, '보통예금', 1),
	('AccountType', 15,    '적금', 1),
	('AccountType', 18,    '예금', 1),
	('AccountType', 20,    '대출', 1),
	('AccountType', 30,    '펀드', 0), 
	(      'UseYn',  0, '사용안함', 1),
	(      'UseYn',  1,  '사용함', 1);
        
	
/* 3. 모든 고객의 정보를 조회하되, 계좌가 있는 고객은 계좌 정보도 함께 보여주시오.
※ 계좌가 없는 고객도 결과에 포함되어야 하며, 각 고객의 이름, 계좌번호, 계좌종류코드, 잔액을 출력하시오.
단, 잔액이 많은 순으로 정렬하시오. */

SELECT C.NAME, A.ACCOUNT_ID, A.ACCOUNT_TYPE_CODE, A.BALANCE 
FROM CUSTOMER C 
LEFT JOIN ACCOUNT A USING (CUSTOMER_ID)
ORDER BY BALANCE DESC;


/* 4. 계좌 정보와 함께 각 계좌의 계좌 유형명을 함께 조회하시오.
CommonCode 테이블을 활용하여 계좌 유형명(code_name)을 출력하고, 잔액이 많은 순으로 정렬하시오.(서브쿼리로 작성) */

SELECT A.*, C.CODE_NAME
FROM ACCOUNT A 
JOIN (SELECT * FROM COMMONCODE WHERE CODE_GROUP = 'ACCOUNTTYPE') C
ON A.ACCOUNT_TYPE_CODE =  C.CODE 
ORDER BY BALANCE DESC;

# 답
SELECT 
    a.account_id,
    a.customer_id,
    a.account_type_code,
    (
        SELECT cc.code_name
        FROM CommonCode cc
        WHERE cc.code_group = 'AccountType'
          AND cc.code = a.account_type_code
    ) AS account_type_name,
    a.balance
FROM 
    Account a
ORDER BY 
    a.balance DESC;


/* 5. 모든 계좌에 대해 계좌번호, 거래 횟수, 거래 총액을 조회하시오.
거래 내역이 없는 계좌도 결과에 포함되어야 하며, 거래 횟수는 0으로 표시하시오.
거래 총액은 NULL 대신 0으로 나타내시오.
거래 횟수가 3회 이상인 계좌만 결과에 포함하시오.
결과는 계좌번호 오름차순으로 정렬하시오. */

SELECT ACCOUNT_ID AS '계좌 번호', 
       COUNT(TRANSACTION_ID) AS '거래 횟수', 
       SUM(IFNULL(AMOUNT,0)) AS '거래 총액'  # ==  IFNULL(SUM(AMOUNT,0))
FROM ACCOUNT LEFT JOIN ACCOUNTTRANSACTION USING(ACCOUNT_ID)
GROUP BY ACCOUNT_ID
HAVING COUNT(TRANSACTION_ID) >= 3 
ORDER BY ACCOUNT_ID;


/* 6. 고객과 계좌 테이블을 활용하여, 아래 조건을 만족하는 SQL 문을 작성하시오.
고객 이름, 이메일, 계좌 번호, 계좌 잔액을 모두 조회한다.
고객과 계좌 간에 고객별로 연결된 정보를 출력해야 한다.
계좌 잔액이 1,000,000원 이상인 계좌만 조회 대상에 포함한다.
결과는 계좌 잔액을 기준으로 내림차순 정렬하며, 잔액이 같으면 고객 이름을 기준으로 오름차순 정렬한다. */

SELECT   C.NAME, C.EMAIL, A.ACCOUNT_ID, A.BALANCE
FROM     CUSTOMER C NATURAL JOIN ACCOUNT A  # == FROM Customer c, Account a WHERE c.customer_id = a.customer_id
WHERE    BALANCE >= 1000000
ORDER BY BALANCE DESC, NAME;


/* 7. 계좌 잔액이 1,000,000 이상이면 '우량 고객', 500,000 이상이면 '보통 고객', 아니면 '관심 고객'으로 표시하는 SQL문을 작성하시오.
(계좌번호,잔액의 내용과 같이 출력) */

SELECT ACCOUNT_ID, BALANCE, 
       IF (BALANCE>=1000000, '우량 고객', IF(BALANCE>=500000, '보통 고객', '관심 고객')) AS 'CUSTOMER_GRADE'
FROM   ACCOUNT;
  # == 
SELECT 
    account_id,
    balance,
    CASE
        WHEN balance >= 1000000 THEN '우량 고객'
        WHEN balance >= 500000 THEN '보통 고객'
        ELSE '관심 고객'
    END AS customer_grade
FROM 
    Account;


/* 8. 특정 고객 ‘홍길동’이 2024년 1월 1일부터 2024년 12월 31일 사이에 거래한 계좌별로, 계좌 번호와 해당 기간 내 마지막 거래 일자를 조회하시오.
고객 정보와 계좌, 거래 내역 테이블을 이용하시오.
거래일자는 '2024-01-01'부터 '2024-12-31' 범위 내여야 한다.
결과는 마지막 거래 일자 내림차순으로 정렬하시오. */

SELECT 	 A.ACCOUNT_ID, MAX(AT.TRANSACTION_DATE)
FROM   	 ACCOUNTTRANSACTION AT 
JOIN     ACCOUNT A USING (ACCOUNT_ID)
JOIN 	 CUSTOMER C USING (CUSTOMER_ID)
WHERE    NAME = '홍길동'
GROUP BY A.ACCOUNT_ID
HAVING   MAX(AT.TRANSACTION_DATE) BETWEEN '2024-01-01' AND '2024-12-31'
ORDER BY MAX(AT.TRANSACTION_DATE) DESC;

# 답
SELECT 	 A.ACCOUNT_ID, MAX(AT.TRANSACTION_DATE)
FROM   	 ACCOUNTTRANSACTION AT 
JOIN     ACCOUNT A USING (ACCOUNT_ID)
JOIN 	 CUSTOMER C USING (CUSTOMER_ID)
WHERE    NAME = '홍길동'
AND      AT.TRANSACTION_DATE BETWEEN '2024-01-01' AND '2024-12-31'
GROUP BY A.ACCOUNT_ID
ORDER BY MAX(AT.TRANSACTION_DATE) DESC;


/*9. 계좌 개설일자가 2010년 1월 1일 이전이고 잔액이 0인 계좌를 휴면 계좌(is_dormant = 1)로 변경하시오.*/

UPDATE ACCOUNT
SET    IS_DORMANT = 1
WHERE  OPENED_DATE < '2010-01-01'
AND    BALANCE = 0;


/* 10. 2020년 1월 1일 이전에 개설된 계좌 중, 거래 내역이 한 건도 없는 계좌를 삭제하시오.*/ 

DELETE FROM ACCOUNT
WHERE ACCOUNT_ID IN (SELECT ACCOUNT_ID
		     FROM (SELECT    ACCOUNT_ID
			   FROM      ACCOUNT 
			   LEFT JOIN ACCOUNTTRANSACTION USING (ACCOUNT_ID) 
			   WHERE     OPENED_DATE < '2020-01-01'
			   GROUP BY  ACCOUNT_ID 
			   HAVING    COUNT(TRANSACTION_ID) = 0) A);
# 답
DELETE FROM Account
WHERE opened_date < '2020-01-01'
  AND account_id NOT IN (
      SELECT DISTINCT account_id FROM AccountTransaction
  );


/*
11.  데이터 사전(Data Dictionary)에 대한 설명으로 옳은 것은? B
A.데이터 사전은 일반 사용자도 자유롭게 수정할 수 있다.
B.데이터 사전은 시스템의 메타데이터를 저장하는 테이블이다.
C.데이터 사전은 사용자가 직접 생성하는 사용자 정의 테이블이다.
D.데이터 사전에는 오직 테이블 구조 정보만 저장된다.


12. 트랜잭션(Transaction)에 대한 설명으로 옳은 것을 고르시오. D
A.트랜잭션은 하나의 SQL 문장을 의미하며 자동으로 커밋된다. 
B.트랜잭션은 데이터베이스의 정합성을 보장하지 않는다. 
C.트랜잭션은 중간에 실패해도 이전 작업 결과는 모두 유지된다. 
D.트랜잭션은 모두 성공하거나 모두 실패해야 하는 단위이다. 


13. 다음 SQL 명령어들이 각각 어느 범주(DDL, DML, DCL, TCL)에 속하는지 알맞은 범주명을 적으시오.

13-1)DDL (Data Definition Language) :  B. drop,  D. create,  F. truncate,  I. rename,   L. alter,       
13-2)DML (Data Manipulation Language) : C. update, G. insert,  J. select,  M. delete
13-3)DCL (Data Control Language) : H. grant,  K. revoke 
13-4)TCL (Transaction Control Language) : A. savepoint, E. rollback, N. commit
*/


