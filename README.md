# Simple ETL process

## Walk through of the code

Operations are the basic unit for abstracting the different transformations on data
To keep it simple there are three types of Operation

1. (Records) => Records
2. (InputFilePath) => Records
3. (Records,OutputFilePath) => Unit

Note: #1 and #2 fall under (I) => O Operation0 trait implementation

Records are data structures which carry data in Field, (ColumnName,Value) tuple (check package object and Record trait)

Using these Operation abstractions, Component objects are made.
More component can be added based on the current operation abstractions.
More Operations can also be added if required where current operations don't suffice the Components requirement.

ETL integration tests contains the Part1 and Part2 implementation of the assignment (check the tests).



