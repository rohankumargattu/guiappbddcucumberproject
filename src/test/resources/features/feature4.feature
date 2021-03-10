Feature: Arithmetic Operations

Scenario: Validate Arithmetic operations
Given launch calculator app
When perform operation as maps and validate result
|operation			|input1	|input2	|
|addition				|10			|15			|
|subtraction		|20			|12			|
|multiplication	|8			|5			|
|division				|20			|4			|
When close app