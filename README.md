# Welcome to springboot_jwt_mysql
#How to run the application follow the below step :

Go to the src/main/java --> com.vikas.springboot_jwt_mysql -- > open SpringbootJwtMysqlApplication.java Right click on that and do Run as Java Application.

Once server is get start Pls. try to access this application with below address :

   http://localhost:8080/<api> 

1. if your want to change is server port then please add this below command in application.yaml available src/main/resources

     server.port=9090

2. Need to add mysql username and password in application.properties.
![Capture](https://github.com/vikuSingh/springboot_jwt_mysql/assets/20941580/7034e31a-606a-43c2-8f65-c4b00763893c)

3. After change start your server and try to test the application:
   a.) First do the registeration
   check the below postman call :-
   
      POST /register HTTP/1.1
      Host: localhost:8080
      Content-Type: application/json
      Content-Length: 63
      
      {
       "username" : "vikash@gmail.com",
       "password" : "123456"
      }

![Capture](https://github.com/vikuSingh/springboot_jwt_mysql/assets/20941580/8eb331ff-72f1-4006-b2a1-9a5d8b98d35f)

b.) Successfull registration call authencation url:

      POST /authenticate HTTP/1.1
      Host: localhost:8080
      Content-Type: application/json
      Content-Length: 62
      
      {
       "username" : "vikash@gmail.com",
       "password" : "12345"
      }
  ![Capture](https://github.com/vikuSingh/springboot_jwt_mysql/assets/20941580/9ae31d7b-0ce1-490b-a2b3-a10e02089c40)

c.) Take jwtToken passed into header and call test api.

      GET /test HTTP/1.1
      Host: localhost:8080
      Authorization: Bearer 
      eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aWthc2hAZ21haWwuY29tIiwiZXhwIjoxNjg5MDAzNTcyLCJpYXQiOjE2ODkwMDM1MTJ9.DcUiK8eIc_V_8- 
      OeIWgnVlMy_ofdMaTLJHxukbYgeChezhziX1lrb1jCQfj0QlvvfB128QMeNDfd0S5YAzki4Q
      Content-Type: application/json
      Content-Length: 62
      
      {
       "username" : "vikash@gmail.com",
       "password" : "12345"
      }
   
   ![Capture](https://github.com/vikuSingh/springboot_jwt_mysql/assets/20941580/1f67954a-a1bd-4f9d-9ff5-25511ba63d43)

  d.) Token get expired than call refreshToken api :

      GET /refreshToken HTTP/1.1
      Host: localhost:8080
      Authorization: Bearer 
 eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aWthc2hAZ21haWwuY29tIiwiZXhwIjoxNjg4OTY5Mzg5LCJpYXQiOjE2ODg5NjkzNTl9.BTrAGL5Hw23S1_sZdzKhZGJ2PcBfMnjVxVOJPFi98_0wUe4AamsfXDgdgGAMx6LFrt1Td9MsxJVQwbO6mF83Ew
      isRefreshToken: true
      Content-Type: application/json
      Content-Length: 63
      
      {
       "username" : "vikash@gmail.com",
       "password" : "123456"
      }
      
  ![Capture](https://github.com/vikuSingh/springboot_jwt_mysql/assets/20941580/8523cd97-59d6-4a70-b8be-39de76d6cf6c)

  Thanks.



        
