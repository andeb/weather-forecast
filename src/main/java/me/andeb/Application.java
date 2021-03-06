package me.andeb;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

/**
 * Configuração da aplicação.
 * 
 * @author Anderson de Borba
 */
@ComponentScan
@EnableAutoConfiguration()
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	/**
	 * Main method, used to run the application.
	 */
	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(Application.class);
		SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
		addDefaultProfile(app, source);
		Environment env = app.run(args).getEnvironment();
		String serverPort = env.getProperty("server.port") == null ? "8080" : env.getProperty("server.port");
		log.info(/**/
				/**/"Access URLs:\n----------------------------------------------------------\n\t" +
				/**/"Local: \t\thttp://127.0.0.1:{}\n\t" +
				/**/"External: \thttp://{}:{}\n----------------------------------------------------------", /**/
				/**/serverPort, InetAddress.getLocalHost().getHostAddress(), serverPort);

	}

	private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
		if (!source.containsProperty("spring.profiles.active") && !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {
			app.setAdditionalProfiles("dev");
		}
	}

}
