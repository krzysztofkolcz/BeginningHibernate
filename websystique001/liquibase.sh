liquibase --driver=com.mysql.jdbc.Driver \
     --classpath=/home/kkolcz/.m2/repository/mysql/mysql-connector-java/5.1.38/mysql-connector-java-5.1.38.jar \
     --changeLogFile=/home/kkolcz/IdeaProjects/BeginningHibernate/websystique001/src/main/resources/liquibase/changelog.xml \
     --url="jdbc:mysql://localhost/websystique001" \
     --username=websystique001\
     --password=websystique001\
     migrate
