This is a backend project for docker lab.

We should follow those steps to run this project on our docker containers:

1. After opening a zip file we should open the terminal on project folder location.

2. We should build a docker image for the spring-boot-docker.
   so run the code on terminal:

   docker build -t spring-boot-docker.jar .

you should see the image, check with code :

docker images

3. After step 2 we should run :

docker-compose up

4. It will run and finish after finishing we need to make somethings manually.

4.1. First we need to start API-1 on docker pressing run button on docker desktop.

    it will run the backend app.

4.2. Connect to the DB.

write everything "postgres" name password dbname etc...

4.3. find table t_role and add:

2 columns.

role id: 1 and role name: ROLE_CUSTOMER
role id: 2 and role name: ROLE_ADMIN

5. open postman and import the postman file.

start with registering and test the application.

Mustafa Tughan GOKCE 48091
Alper Inan 48092
Muhammed Halil Adiguzel 41806
Sefa Akkaya 40308
