https://www.javainuse.com/spring/cloud-data-flow
https://www.javainuse.com/misc/rabbitmq-hello-world

1) Descargar el software asociado:

erlang 22.2 x64 (otp_win64_22.2.exe)
rabbitmq-server 3.8.2 (rabbitmq-server-3.8.2.exe)
curl windows x64

spring-cloud-dataflow-server-local-1.3.0.M1.jar (servidor Spring Cloud Data Flow)
spring-cloud-dataflow-shell-1.3.0.M1.jar (consolaa Spring Cloud Data Flow)


2)Configuración del Spring Cloud + RabbitMQ Server:

D:\workspaces\runtimes\RabbitMQ\rabbitmq_server-3.8.2\sbin>rabbitmq-server start

3)Configuración consola rabbitmq-server

D:\workspaces\runtimes\RabbitMQ\rabbitmq_server-3.8.2\sbin>rabbitmq-plugins.bat enable rabbitmq_management

Consola http://localhost:15672/#/ (user:guest; pass:guest)

4)Ejecucion Spring Cloud dataflow Server:

D:\workspaces\workEclipseCloud>java -jar spring-cloud-dataflow-server-local-1.3.0.M1.jar

5)Ejecucion Spring Cloud dataflow Console

d:\workspaces\workEclipseCloud>java -jar spring-cloud-dataflow-shell-1.3.0.M1.jar

Consola http://localhost:9393/dashboard

6)Compilar proyectos maven source;processor;sink 

maven://com.javainuse:source:jar:0.0.1-SNAPSHOT; maven clean; maven install
maven://com.javainuse:processor:jar:0.0.1-SNAPSHOT; maven clean; maven install
maven://com.javainuse:sink:jar:0.0.1-SNAPSHOT; maven clean; maven install

other test:

maven://io.spring.dataflow.sample:usage-detail-sender-rabbit:jar:0.0.1-SNAPSHOT
maven://io.spring.dataflow.sample:usage-cost-processor-rabbit:jar:0.0.1-SNAPSHOT
maven://io.spring.dataflow.sample:usage-cost-logger-rabbit:jar:0.0.1-SNAPSHOT


7)Configuracion Spring Cloud:

app register --name source-app --type source --uri maven://com.javainuse:source:jar:0.0.1-SNAPSHOT
app register --name processor-app --type processor --uri maven://com.javainuse:processor:jar:0.0.1-SNAPSHOT
app register --name sink-app --type sink --uri maven://com.javainuse:sink:jar:0.0.1-SNAPSHOT
stream create --name log-data --definition 'source-app|processor-app|sink-app'
stream deploy --name log-data

other

app register --name source-app --type source --uri maven://io.spring.dataflow.sample:usage-detail-sender-rabbit:jar:0.0.1-SNAPSHOT
app register --name processor-app --type processor --uri maven://io.spring.dataflow.sample:usage-cost-processor-rabbit:jar:0.0.1-SNAPSHOT
app register --name sink-app --type sink --uri maven://io.spring.dataflow.sample:usage-cost-logger-rabbit:jar:0.0.1-SNAPSHOT
stream create --name log-data --definition 'source-app|processor-app|sink-app'
stream deploy --name log-data

app import --uri https://dataflow.spring.io/rabbitmq-maven-latest (load source.http functionality)
stream create --name log-data-http --definition "http --server.port=9000 --spring.cloud.stream.bindings.output.contentType='application/json'|processor-app|sink-app" 
stream deploy --name log-data-http

curl -d {\"userId\":\"Pepe\",\"duration\":\"200\",\"data\":\"200\"} -H "Content-Type: application/json" -X POST http://localhost:9000


stream create --name source-1  --definition "source-app > :customSource" 
stream deploy --name source-1
stream create --name process-1  --definition ":customSource > processor-app | sink-app" 
stream deploy --name process-1
stream create --name process-2  --definition ":customSource > processor-app | sink-app" 
stream deploy --name process-2


8)Logs:

source: C:\Users\jeag2\AppData\Local\Temp\spring-cloud-dataflow-4724134253295791853\log-data-1580590131296\log-data.source-app
processor: C:\Users\jeag2\AppData\Local\Temp\spring-cloud-dataflow-4724134253295791853\log-data-1580590131114\log-data.processor-app
sink: C:\Users\jeag2\AppData\Local\Temp\spring-cloud-dataflow-4724134253295791853\log-data-1580590130949\log-data.sink-app


https://docs.spring.io/spring-cloud-dataflow/docs/1.3.0.M1/reference/html/_the_lifecycle_of_a_task.html#_creating_a_task_application
https://docs.spring.io/spring-cloud-dataflow-samples/docs/current/reference/htmlsingle/