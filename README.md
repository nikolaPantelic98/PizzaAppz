# PizzaAppz

* PizzaAppz is a full-stack web application that allows the customers to pick their favorite flavors of pizza from their favorite restaurant without paying a physical visit. Backend using Spring Boot and frontend using Thymeleaf. 
 
## Table of Contents

* [Info](https://github.com/nikolaPantelic98/PizzaAppz#info)
* [Some Images](

## Info

* There are two roles in the application: Customer and Admin. Anyone can register and then log in to the application. Role "Customer" will be automatically assigned to each new customer. Each customer can view the list of available pizzas, select the desired pizza, as well as the desired size of the particular pizza, and then order by entering their delivery information. 
* Admin manages all data within the application, such as:
     * All Pizzas - Admin can add a new pizza for sale (picture, description, multiple sizes and price for each size), as well as modify and delete an existing one;
     * All Registered Users - Admin can register a new user and modify or delete an existing user. Admin can also assign the "Admin" role to the desired user;
     * All Orders - Admin can review orders made by customers and further process customer requests.
* Security in the application is done in Spring Security and all passwords are encrypted.
* The final view to the user is provided by Thymeleaf, the JAVA library.

## Some Images

![01  registracija](https://user-images.githubusercontent.com/109813536/201185743-2938e600-1d9b-4dab-a96e-c517beec6f6e.png)
![02  login](https://user-images.githubusercontent.com/109813536/201185768-99b54578-7082-4e7a-971f-7fa3a43aeca4.png)
![05  pizzas customer](https://user-images.githubusercontent.com/109813536/201185807-b208e31a-6483-4dce-9681-b2218ed7e6cc.png)
![06  pizza details customer](https://user-images.githubusercontent.com/109813536/201185851-9c512291-7ef8-426c-a60d-7e4e851252b7.png)
![07  make new order customer](https://user-images.githubusercontent.com/109813536/201185876-e7fe9a6e-515d-49b8-9dfa-a294ea632b9f.png)
![08  pizzas admin](https://user-images.githubusercontent.com/109813536/201185886-f4e2d89e-3aa1-4a07-9f78-c805754454a5.png)
![11  view pizza details](https://user-images.githubusercontent.com/109813536/201185920-46825516-2356-4ed7-a37f-6cf95807f365.png)
![12  pizza sizes by pizza](https://user-images.githubusercontent.com/109813536/201185933-4a38e10a-4fe3-4b02-a98e-995562919e15.png)
![13  add size and price to pizza](https://user-images.githubusercontent.com/109813536/201185946-40d93072-0ad3-41a8-bc66-9980176b756e.png)
![14  orders](https://user-images.githubusercontent.com/109813536/201185965-eda929a4-a5a5-4c6a-91d5-d84e25c2f98a.png)
![16  registered users](https://user-images.githubusercontent.com/109813536/201185985-ded179df-d2af-4c56-995c-ac0b2d30b923.png)
![17  update user](https://user-images.githubusercontent.com/109813536/201185999-38dd4462-606b-4f71-8bff-d47678938dac.png)
