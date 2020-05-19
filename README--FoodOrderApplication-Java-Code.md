# FoodOrderApplication-Java-Code

This Quick Service Restaurant Application was built to get a deep insight about object-oriented design, architectural and enterprise patterns proposed by Martin Fowler. It's a monolithic application but a bit of microservice architecture is also exhibited in here. The application focuses mainly on four actors and they are Customer, Admin, Restaurant user and Delivery user. But the services for Customer and Admin is more evident. This application has got seeveral use-case scenarios:

    From the Customer point of view:
        1. Login and SignUp functionality
        2. Search for food Item and listing restaurants which fall in a particular radius of 10km.
        3. Users have the provision for subscribing for a membership(Gold or Silver).
        4. Provision to place an order.
        5. Requesting a menu of items based on a cuisine.
        6. Notification to the customer.
        
    From the Admin point of view (admin can give seaonal Offer(%)):
        1. Add or Delete Restaurants
        2. Can offer seasonal offers for the customers.
        
    From the Restaurant Point of view (restaurants can give Discount(%) price for their food items for different sets of customer):
        1. To display the set of current orders.
        2. To accept an order
       
Different Object-Oriented Design patterns are used to develop the use cases above.
      
      1. Facade Pattern - It's a creational pattern - This pattern is used to hide the implementation details from the client side. In                           this application, Different set of operations are possible for the different sets of customer.
                        - Customers include Student and General.
                        - Operations include :
                            ## Calculating the discount price for the products 
                               ** Discounted price varies for Student and General - as this is a sum of Discount(%) by Restaurant and 
                                  Offer(%) by Admin.
                            ## Calculating the reward points for each user.
                               ** Calculating reward points for each user based on the number of orders placed.
                               
      2. Builder Pattern - It's a creational pattern -  Let's use to construct complex objects. This pattern allows you to produce
                           different types and representations of an object using the same construction code.
                        - In this application, menu is constructed based on the cusine type like Italian, American, Indian based on the 
                          customer request.
                        - So for each cusine, there are Vegetarian and Non-Vegeterian items.
                        
                        
      3. Command Pattern
      4. Observer Design Pattern
      5. Iterator pattern
      6. Mediator Pattern
      7. Memento Pattern
      8. State Pattern
      9. Visitor Pattern
      
               
               
 Architectural Pattern used are :
     1. Front Controller Pattern
     2. Microservices
     
 Enterprise Pattern:
     1. DTO -Data Transfer Objects
