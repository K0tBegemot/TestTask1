<h2>Instruction for use</h2>
<h3 style="font-weight: bold">Content</h4>
<ul>
    <li><a href="#startup">Startup instruction</a></li>
    <li><a href="#documentation">Instruction for generating documentation</a></li>
</ul>
<h3><a name="startup"></a>Startup instruction</h3>
<p>The instructions assume that the reader runs the program using Ubuntu 22.04 . 
If you are using other operating systems, some steps may differ from those below.</p>
<h4>Installing the required software</h4>
<p>To run the software you must have the following dependencies:</p>
<ul>
<li>Docker Engine v24.0.6(tested version) or higher</li>
<li>Docker Compose v2.12.1(tested version) or higher</li>
</ul>
<p>The following steps in the instructions assume that the current user has been added to the docker group or docker is installed in rootless mode
Otherwise, all commands must be executed using the <b>sudo</b> command</p>
<p>More on this at these links:</p>
<ul>
<li><a href="https://docs.docker.com/engine/install/linux-postinstall/">Add current user to docker group</a></li>
<li><a href="https://docs.docker.com/engine/security/rootless/">Docker rootless mode</a></li>
</ul>
<h4>Starting the program</h4>
<p>
Before starting the program, you need to go to the <b>.env</b> file to edit the external ports through which the database and server will be accessible in your terminal.
All env variables, described in the file and their description:
</p>
<ul>
<li>SQL_DATABASE_HOST - internal hostname of database</li>
<li>SQL_DATABASE_PORT - internal database port</li>
<li>SQL_DATABASE_NAME - database name</li>
<li>SQL_DATABASE_USER - database admin name</li>
<li>SQL_DATABASE_PASSWORD - database admin password</li>
<li>SERVER_PORT - internal server port</li>
<li>SQL_DATABASE_EXTERNAL_PORT - external database port</li>
<li>SERVER_EXTERNAL_PORT - external server port</li>
</ul>
<p>To run program, execute following command in project parent folder: <br>
<code>docker compose up</code><br>or<br><code>docker compose up -d </code><br>for container detached mode</p>
<p>after this the service will be started</p>
<h3><a name="documentation"></a>Instruction for generating documentation</h3>
<h4>Generate Javadoc documentation</h4>
<p>To generate documentation, the following programs are required:</p>
<ul>
<li>Maven</li>
</ul>
<p>You need to execute the command:</p>
<code>mvn clean lombok:delombok javadoc:javadoc</code>
<p>and the documentation will appear in the folder ${PROJECT_ROOT}/target/site/apidocs</p>
<h4>OpenAPI documentation</h4>
<p>After starting the server, Swagger documentation will be available at /openapi/swagger-ui.html
<br>Yaml OpenAPI files at /openapi/v3/api-docs</p>