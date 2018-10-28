# Bike Rental

## Design

The solution was thought as an invoicing application which manages a product catalog, different unit of measures, a price list and promotion rules. <br />
This way I could add a product called “Bike”, three unit of measures like ‘DAY’, ‘WEEK’ and ‘HOUR’ and a price list to get prices by product and unit of measure. <br /> 
If I would like, by example, I could add a new promotion called “Family Rental” which consider a discount of 30% with a range of 3 to 5 bikes by rental.<br />


## Tests
The tests are made using Mockito Framework. To execute all tests, please run: <br /> <br />
&nbsp;&nbsp;&nbsp;**mvn test**

## Tests coverage
To ensure a 85% of test coverage I used OpenClover. To verify it, you could run: <br /> <br />
&nbsp;&nbsp;&nbsp;**mvn install**
&nbsp;&nbsp;&nbsp;**mvn clover:instrument clover:check  -Dmaven.clover.failOnViolation=false**
<br /> <br />
This solution doesn´t include any report of test coverage

## Author
Jorge Luis Almiron
