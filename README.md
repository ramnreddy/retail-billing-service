retail-billing-service
------------------------------------
### Problem Statement
On a retail website, the following discounts apply:
1.	If the user is an employee of the store, he gets a 30% discount
2.	If the user is an affiliate of the store, he gets a 10% discount
3.	If the user has been a customer for over 2 years, he gets a 5% discount.
4.	For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
5.	The percentage based discounts do not apply on groceries.
6.	A user can get only one of the percentage based discounts on a bill.
Write a program in a programming language of your choice with test cases such that given a bill, it finds the net payable amount

------------------------------------
### UML 
- Use case diagram & UML class diagram are located at project root folder
### Required Tech Stack to run the application
- Java 1.8
- Gradle 4.8
- Junit 4.1.2
- Jacoco

### Setup & Test
- $ git clone https://github.com/ramnreddy/retail-billing-service.git
OR
- $ git clone git@github.com:ramnreddy/retail-billing-service.git
- $ gradlew build
- $ gradlew test
- $ gradlew test jacocoTestReport
- $ gradlew test jacocoTestCoverageVerification
- Look for 
    * **test summary**  at /retail-billing-service/build/reports/tests/test/index.html
    * **test coverage** at /retail-billing-service/build/reports/jacoco/test/html/index.html

