
#RESTful Web Services Basic Authentication

Basic authentication is the simplest way to securing any URL. User should have permission to the server to access the URL. This is Container-managed authentication.

#What is Authentication?

Is the process to identifying the user, who can access the system. Http basic authentication is the first steps for RESTful security. There are many ways to implements the RESTful security, the basic authentication is the top level security where Base64 encoded string is used to access the server.

###Note –

Basic authentication is not 100% secure, because it is not strongly encrypt the password. We need to use the HTTPS or other encryption mechanism between client and server.

#Flow the below steps to implements the basic Authentication

##Step 1

Develop a Restful webservices using jersey RESTful api. If you don’t know how to develop webservices in jersey read my previous [post](http://javaant.com/restful-web-services-using-jersey/#.VbhuG_mqqkp).

## Step 2

Open the web.xml file and add the below code inside the web-app tag.
```
  <!--?xml version="1.0" encoding="UTF-8"?-->
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemalocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
  <display-name>BasicAuthentication</display-name>
  <display-name>RestFulWebServices</display-name>
  <servlet>
    <servlet-name>RestTest</servlet-name>
    <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
    <init-param>
      <param-name>javax.ws.rs.Application</param-name>
      <param-value>com.controller.MyRESTApplication</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>RestTest</servlet-name>
    <url-pattern>/*</url-pattern>
  </servlet-mapping>
  <security-constraint>
    <web-resource-collection>
        <url-pattern>/*</url-pattern>
    </web-resource-collection>
    <auth-constraint>
        <role-name>admin</role-name>
    </auth-constraint>
</security-constraint>
<security-constraint>
    <web-resource-collection>
        <url-pattern>/*</url-pattern>
    </web-resource-collection>
    <auth-constraint>
        <role-name>customer</role-name>
    </auth-constraint>
</security-constraint>
<login-config>
    <auth-method>BASIC</auth-method>
    <realm-name>my-default-realm</realm-name>
</login-config>
</web-app>
```

##Step 3

Now open the tomcat-users.xml. and write the user details like below.

```
<tomcat-users>
<user name="admin" password="admin" roles="admin">
</user></tomcat-users>
```

Now type the url in any client it will ask for usrid and password. Provide the user id and password which is there in the tocat-users.xml.

###Note-

If it is not accepting userid and password , open the server.xml and remove all Realm tag.

```
<!--?xml version='1.0' encoding='utf-8'?-->
<!--
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 -->
<!-- Note:  A "Server" is not itself a "Container", so you may not
      define subcomponents such as "Valves" at this level.
      Documentation at /docs/config/server.html
  -->
<server port="8005" shutdown="SHUTDOWN">
  <!-- Security listener. Documentation at /docs/config/listeners.html
   <Listener className="org.apache.catalina.security.SecurityListener" />
   -->
  <!--APR library loader. Documentation at /docs/apr.html -->
  <listener classname="org.apache.catalina.core.AprLifecycleListener" sslengine="on">
  <!--Initialize Jasper prior to webapps are loaded. Documentation at /docs/jasper-howto.html -->
  <listener classname="org.apache.catalina.core.JasperListener">
  <!-- Prevent memory leaks due to use of particular java/javax APIs-->
  <listener classname="org.apache.catalina.core.JreMemoryLeakPreventionListener">
  <listener classname="org.apache.catalina.mbeans.GlobalResourcesLifecycleListener">
  <listener classname="org.apache.catalina.core.ThreadLocalLeakPreventionListener">

  <!-- Global JNDI resources
        Documentation at /docs/jndi-resources-howto.html
   -->
  <globalnamingresources>
    <!-- Editable user database that can also be used by
          UserDatabaseRealm to authenticate users
     -->
    <resource name="UserDatabase" auth="Container" type="org.apache.catalina.UserDatabase" description="User database that can be updated and saved" factory="org.apache.catalina.users.MemoryUserDatabaseFactory" pathname="conf/tomcat-users.xml">
  </resource></globalnamingresources>

  <!-- A "Service" is a collection of one or more "Connectors" that share
        a single "Container" Note:  A "Service" is not itself a "Container",
        so you may not define subcomponents such as "Valves" at this level.
        Documentation at /docs/config/service.html
    -->
  <service name="Catalina">

    <!--The connectors can use a shared executor, you can define one or more named thread pools-->
    <!--
     <Executor name="tomcatThreadPool" namePrefix="catalina-exec-"
         maxThreads="150" minSpareThreads="4"/>
     -->


    <!-- A "Connector" represents an endpoint by which requests are received
          and responses are returned. Documentation at :
          Java HTTP Connector: /docs/config/http.html (blocking &#038; non-blocking)
          Java AJP  Connector: /docs/config/ajp.html
          APR (HTTP/AJP) Connector: /docs/apr.html
          Define a non-SSL HTTP/1.1 Connector on port 8080
     -->
    <connector port="8080" protocol="HTTP/1.1" connectiontimeout="20000" redirectport="8443">
    <!-- A "Connector" using the shared thread pool-->
    <!--
     <Connector executor="tomcatThreadPool"
                port="8080" protocol="HTTP/1.1"
                connectionTimeout="20000"
                redirectPort="8443" />
     -->
    <!-- Define a SSL HTTP/1.1 Connector on port 8443
          This connector uses the JSSE configuration, when using APR, the
          connector should be using the OpenSSL style configuration
          described in the APR documentation -->
    <!--
     <Connector port="8443" protocol="HTTP/1.1" SSLEnabled="true"
                maxThreads="150" scheme="https" secure="true"
                clientAuth="false" sslProtocol="TLS" />
     -->

    <!-- Define an AJP 1.3 Connector on port 8009 -->
    <connector port="8009" protocol="AJP/1.3" redirectport="8443">


    <!-- An Engine represents the entry point (within Catalina) that processes
          every request.  The Engine implementation for Tomcat stand alone
          analyzes the HTTP headers included with the request, and passes them
          on to the appropriate Host (virtual host).
          Documentation at /docs/config/engine.html -->

    <!-- You should set jvmRoute to support load-balancing via AJP ie :
     <Engine name="Catalina" defaultHost="localhost" jvmRoute="jvm1">
     -->
    <engine name="Catalina" defaulthost="localhost">

      <!--For clustering, please take a look at documentation at:
           /docs/cluster-howto.html  (simple how to)
           /docs/config/cluster.html (reference documentation) -->
      <!--
       <Cluster className="org.apache.catalina.ha.tcp.SimpleTcpCluster"/>
       -->

      <!-- Use the LockOutRealm to prevent attempts to guess user passwords
            via a brute-force attack -->
      
      <!--<Realm className="org.apache.catalina.realm.LockOutRealm"> -->
        <!-- This Realm uses the UserDatabase configured in the global JNDI
              resources under the key "UserDatabase".  Any edits
              that are performed against this UserDatabase are immediately
              available for use by the Realm.  -->
       <!-- <Realm className="org.apache.catalina.realm.UserDatabaseRealm"
                resourceName="UserDatabase"/>
       </Realm>-->

      <host name="localhost" appbase="webapps" unpackwars="true" autodeploy="true">

        <!-- SingleSignOn valve, share authentication between web applications
              Documentation at: /docs/config/valve.html -->
        <!--
         <Valve className="org.apache.catalina.authenticator.SingleSignOn" />
         -->

        <!-- Access log processes all example.
              Documentation at: /docs/config/valve.html
              Note: The pattern used is equivalent to using pattern="common" -->
        <valve classname="org.apache.catalina.valves.AccessLogValve" directory="logs" prefix="localhost_access_log." suffix=".txt" pattern="%h %l %u %t &quot;%r&quot; %s %b">

      </valve></host>
    </engine>
  </connector></connector></service>
</listener></listener></listener></listener></listener></server>
```
