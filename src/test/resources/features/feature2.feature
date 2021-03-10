Feature: Arithmetic Operations

Background:
Given launch calculator app

@smoketest
Scenario: Validate Addition operation
When perform "addition" with two numbers like "12" and "5"
Then validate "addition" operation for "12" and "5"
When close app

@regression
Scenario: Validate Subtraction operation
When perform "subtraction" with two numbers like "21" and "13"
Then validate "subtraction" operation for "21" and "13"
When close app

@regression
Scenario: Validate Multiplication operation
When perform "multiplication" with two numbers like "4" and "6"
Then validate "multiplication" operation for "4" and "6"
When close app

@regression
Scenario: Validate Division operation
When perform "division" with two numbers like "10" and "2"
Then validate "division" operation for "10" and "2"
When close app