# football_api
This api allows you to check current sports events, tables, info about sport teams and save your favorite teams. Powers by www.thesportsdb.com
## Run

* Download or clone repository and run it in IntelliJ IDEA
* Go to  ```meal/src/main/resources/application.properties```
and in ```spring.datasource.url``` connect with your MySql database,
in ```spring.datasource.username and spring.datasource.password```
enter your username and password to database. Next in ```spring.mail.username and spring.mail.password``` enter valid
gmail email and password if you want api be able to send emails

# Register

* To make an account use client like Postman, go to:
```
http://localhost:{your_default_port}/registration
```
   and send a body in POST request like example below:
```
{
    "login": "seba123",
    "email": "seba123@email.com",
    "password": "password123"
}

```
## Api map:

* To select team info by team name type it in teamName like below (GET request):
```
http://localhost:{your_default_port}/team/name/{teamName}
```
* Select team by id (GET request):
```
http://localhost:{your_default_port}/team/id/{idTeam}
```
* Save team (POST request):
```
http://localhost:{your_default_port}/team/id/{idTeam}
```
* Select team events by team id (GET request):
```
http://localhost:{your_default_port}/team/event/{idTeam}
```
* Delete team from favorite (DELETE request): 
```
http://localhost:{your_default_port}/team/id/{idTeam}
```
* List closests events of your favouite teams (GET request):
```
http://localhost:{your_default_port}/team/my/next
```
* Send an email with closests events of your favouite teams (GET request):
```
http://localhost:{your_default_port}/team/my/send
```
* Select all available league (GET request):
```
http://localhost:{your_default_port}/team/table/lig
```
* Select current table by league id (GET request):
```
http://localhost:{your_default_port}/team/table/{idLeague}
```
* List your favorite teams (GET request):
```
http://localhost:{your_default_port}/team/my/saved
```
