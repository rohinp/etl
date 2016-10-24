# Simple ETL process

## Walk through of the code

Oprerations are the basic unit for abstracting the different tranformations on data
To keet it simple there are three types of Operation

1. (Records) => Records
2. (InputFilePath) => Records
3. (Records,OutputFilePath) => Unit

Records are data structures which carry data in (ColumnName,Value) tuple (check package object and Record trait)

Using these Operation abstractions, Component objects are made.
More component can be added based on the current operation abstractions.
More Operations can also be added if required where current operations don't suffice the Components requirement.

ETL integration tests contains the Part1 and Part2 implementation of the assignment.



