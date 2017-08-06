Spring Boot with Oauth2 demo

build with: <b>mvn clean package</b>
<br>
or run directly: <b>mvn spring-boot:run</b>
<br>
<br>
to get token, use command (linux or mac):
<br>
<b>user:</b><br>
curl -X POST -vu client:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=password&username=user@t.pl&grant_type=password&scope=openid&client_secret=secret&client_id=client"
<br>
<b>admin:</b><br>
curl -X POST -vu client:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=password&username=admin@t.pl&grant_type=password&scope=openid&client_secret=secret&client_id=client"