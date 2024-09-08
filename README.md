Introduction
 - Welcome to my order system application! This is a REST-API based solution for managing both orders, customers, addresses 
 - and more for your factory. This spring Boot application offers a list of functionalities to efficiently
 - handle CRUD operations for various entities like; customers, addresses, parts, machines and orders. 
 - The solution provides an array of RESTful endpoints, that facilitates CRUD operations, and was made with 
 - the intention of making it a scalable and flexible solution for order management.

Instructions
   -The order system is a RESTful service that can be interacted with through tools like Postman
1. start the application by running OrderSystemApplication.java. This starts the Spring Boot server
2. (Switch out "Customers" with any other entity like "Addresses","Machines", "Orders", "Parts" or "Subassemblies")
   (Switch out {id}, with actual ID)
 - Get All Customers: GET /customers - Retrieves a list of all customers.
 - Add New Customer: POST /customers - Adds a new customer. Requires a JSON body with customer details.
 - Get Specific Customer: GET /customers/{id} - Retrieves details of a customer by ID.
 - Update A Customer: PUT /customers/{id} - Update an existing customer by ID
 - Delete A Customer: DELETE /customers/{id} - Delete a customer by ID
 - Add AN Address To a customer(Specific for customer): POST /customers/{customerId}/addAddress - Add an address to a customer by ID
3. Open postman and create a new request.
- Choose what HTTP method you desire (GET, POST etc.)
- Set the request URL to http://localhost:8080/{endpoint}, replacing {endpoint} with the specific API endpoint.
- For POST requests, add the necessary JSON payload in the request body.
