package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProdReadyFeaturesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdReadyFeaturesApplication.class, args);
	}

	//Remember : spring-devtools-> not 100% reliable/not that much useful for local system setup.
	//using it we control local machine and can also hit restart(reflect local change to remote repo) on some remote repository as well.
	//1. Add "spring-boot-devtools" dependency in pom.xml
	//2. Trigger the IntelliJ Idea to build the project every time sees any change inside Java code
	//3. Go to preference cmd+, then go to build execution deployment inside that go to compiler.
	//4. Tick/check the option "build project automatically"
	// Why it is important?
	// The devtool does not look for any changes inside java code, it actually looks at the changes
	// made in the compiled code/classes with the help of class loader and then if yes, it triggers the auto restart by itself.
	// so to do that our ide has to "Build the project automatically" then our devtools can take the charge.
	//5. Also in "Advanced Settings" go to "compiler" section then check this box "Allow auto-make to start even if developed application is currently running"
}
