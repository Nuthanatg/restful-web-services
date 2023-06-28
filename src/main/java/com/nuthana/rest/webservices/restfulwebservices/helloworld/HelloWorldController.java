package com.nuthana.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//1.Expose a REST API
@RestController
public class HelloWorldController {

	// internationalization
	private MessageSource messageSource;

	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

//2.give a specific url to the rest api (/hello-world)

	// @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	// to get JSON format
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World"); // it return back Json format
	}

	// Path Parameters
	// /users/{id}/todos/{id} => /users/2/todos/200 this-2,200 are the variables
	// ,this variables are called Path parameters
	// /hello-world/path-variable/{name}
	// /hello-world/path-variable/nuthana =>lets say someone send this request to
	// capture that variable name(nuthana) we use @PathVariable

	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World , %s", name));
		// or "Hello World" + name);
	}

	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);

		// return "Hello World V2";

		// 1:thing we need to do define all messages
		// 2:wirte the code to pick those values up
//		- Example: `en` - English (Good Morning)
//		- Example: `nl` - Dutch (Goedemorgen)
//		- Example: `fr` - French (Bonjour)
//		- Example: `de` - Deutsch (Guten Morgen)

	}
}
