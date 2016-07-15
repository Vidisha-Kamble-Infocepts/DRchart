package com.ge.predix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Abhishek C
 *
 */
@EnableAutoConfiguration(exclude =
{
		//Add any configuration loading call you want to exclude

})
@PropertySource("classpath:application-default.properties")
@ComponentScan(basePackages={"com.ge.predix.*"})
@EnableScheduling
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);


	/**
	 * @param args -
	 */
	@SuppressWarnings(
			{
				"nls", "resource"
			})
	public static void main(String[] args) {

		final SpringApplication springApplication = new SpringApplication(Application.class);
		final ApplicationContext ctx = springApplication.run(args);

	}

	/**
	 * Ensure the Tomcat container comes up, not the Jetty one.
	 * @return - the factory
	 */
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory()
	{
		return new TomcatEmbeddedServletContainerFactory();
	}

	//@Bean
	//public CommandLineRunner demo(AssetRepository assetRepository){
	//return (args)->{
	//assetRepository.save(new Asset(1122121L));

	/*for(Asset asset:assetRepository.findAll())
			{
				log.info(asset.toString());
			}

			log.info("");*/
	//};

	//}
}
