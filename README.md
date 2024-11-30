#### Plugins
	- id 'java'
	- id 'war'
	- id 'org.springframework.boot' version '3.1.0'
	- id 'io.spring.dependency-management' version '1.1.6'

 
#### dependencies 
	- implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	- implementation 'org.springframework.boot:spring-boot-starter-web'
	- compileOnly 'org.projectlombok:lombok'
	- developmentOnly 'org.springframework.boot:spring-boot-devtools'
	- annotationProcessor 'org.projectlombok:lombok'
	- providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'
	- testImplementation 'org.springframework.boot:spring-boot-starter-test'
	- testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

thymeleaf, lombok, spring-boot-devtools, spring-boot-starter-web
