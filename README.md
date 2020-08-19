The client would like to develop an independent API based backend system. Main purpose of this system is expose below operations as set of  APIs so that corresponding front end application can consume them
a.	Create/Update new customer
b.	Insert/Update Card Details detail associated with customer
c.	Allow user to initiate/close payment transaction only if customer has already one card and no existing open payment transaction 

How to run the application:

> Download all the projects as a different jars or clone the GITHUB repo
> Import as existing Maven application to the IDE
> First run the ServiceRegistration project
> run the file ServiceRegistrationApplication as a java application
> in a browser navigate to "http://localhost:8080/eureka"

>Second, Run the customerAPI project
>run the file CustomerServiceApplication as a java application
>in a browser navigate to "http://localhost:8081/customers"
>in a browser navigate to "http://localhost:8081/swagger-ui.html" to check all seervices available

>Third, Run the cardserviceAPI project
>run the file CardServiceApplication as a java application
>in a browser navigate to "http://localhost:8082/cards"
>in a browser navigate to "http://localhost:8082/swagger-ui.html" to check all seervices available


>Fourth, Run the payment-api-service project
>run the file PaymentApiServiceApplication as a java application
>in a browser navigate to "http://localhost:8083/payments"
>in a browser navigate to "http://localhost:8083/swagger-ui.html" to check all seervices available



Outputs Expected:
> The validated OK results will end up as JSON in the browser
> If records are not available,  would end up response code 500




