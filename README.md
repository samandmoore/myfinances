a small single-page application written in backbone with marionette and spring mvc.

stuff:
- backbone js
- marionette js
- underscore js
- jquery
- spring
- spring mvc
- hibernate
- joda time

from the command line:
`$> mvn clean install tomcat7:run`

the above should generate the database schema for you, if it doesn't, run this:
`$> mvn clean install hibernate4:export`

if that doesn't work, make sure your `hibernate.properties` are set correctly, and maybe create a database with the name `myfinances`
