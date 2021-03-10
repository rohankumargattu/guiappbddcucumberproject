Feature: Arithmetic Operations

Scenario Outline: Validate Arithmetic Operations
Given launch calculator app
When perform "<operation>" with two numbers like "<input1>" and "<input2>"
Then validate "<operation>" operation for "<input1>" and "<input2>"
When close app

Examples:
|operation			|input1	|input2	|
|addition				|14			|5			|
|subtraction		|125		|55			|
|multiplication	|100		|2			|
|division				|300		|100		|