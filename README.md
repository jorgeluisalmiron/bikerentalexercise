# Bike Rental

## Design

The solution was thought as an invoicing application which manages a product catalog, different unit of measures, a price list and promotion rules. <br />
These are the modeling classes used: <br>
- Invoice
- InoviceItem
- PriceListItem
- Product
- Promotion

Also, the solution propose to use three repositories (which are not implemented, only interfaces was included) <br>
- PriceListRepository
- ProductRepository
- PromotionsRepository
<br>
Unit of measures and Promo Types was included as an enumerated, although it could have been included as a repositories.
<br><br>
The class InvoiceBuilder includes all the logic to create an invoice, add products and calculate promotions.
<br><br>
This way I could add a product called “Bike”, three unit of measures like ‘DAY’, ‘WEEK’ and ‘HOUR’ and a price list to get prices by product and unit of measure. <br /> <br>
If I would like, by example, I could add a new promotion called “Family Rental” which consider a discount of 30% with a range of 3 to 5 bikes by rental.<br />





## Tests
The tests are made using Mockito Framework. To execute all tests, please run: <br /> <br />
&nbsp;&nbsp;&nbsp;**mvn test** <br /> <br />

The class InvoiceBuilderTest include tests for different scenarios.

## Tests coverage
To ensure a 85% of test coverage I used OpenClover. To verify it, you could run: <br /> <br />
&nbsp;&nbsp;&nbsp;**mvn clover:instrument clover:check  -Dmaven.clover.failOnViolation=false**
<br /> <br />
This solution doesn´t include any report of test coverage

## Author
Jorge Luis Almiron
