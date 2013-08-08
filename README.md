#### Overview
a small single-page application written in backbone with marionette and spring mvc.

libs n things:
- backbone js
- marionette js
- underscore js
- jquery
- spring
- spring mvc
- hibernate
- joda time

#### running the app
from the command line:
`$> mvn clean install tomcat7:run`

the above should generate the database schema for you, if it doesn't, run this:
`$> mvn clean install hibernate4:export`

if that doesn't work, make sure your `hibernate.properties` are set correctly, and maybe create a database with the name `myfinances`


#### running from an IDE
- if you're using intelliJ or eclipse, there should be a way to import a project from maven; do that.
- then you should be able to add a build configuration for maven; use the steps from above.
