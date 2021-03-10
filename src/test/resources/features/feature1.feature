Feature: Arithmetic Operations

@smoketest
Scenario: Validate Addition operation
Given launch calculator app
When perform "addition" with two numbers like "12" and "5"
Then validate "addition" operation for "12" and "5"
When close app

@regression
Scenario: Validate Subtraction operation
Given launch calculator app
When perform "subtraction" with two numbers like "13" and "20"
Then validate "subtraction" operation for "13" and "20"
When close app

@regression
Scenario: Validate Multiplication operation
Given launch calculator app
When perform "multiplication" with two numbers like "4" and "6"
Then validate "multiplication" operation for "4" and "6"
When close app

@regression
Scenario: Validate Division operation
Given launch calculator app
When perform "division" with two numbers like "10" and "2"
Then validate "division" operation for "10" and "2"
When close app