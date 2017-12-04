package br.com.xlabi.configuration;

import java.io.IOException;

import javax.servlet.MultipartConfigElement;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@EnableCaching
@ComponentScan(basePackages = "br.com.xlabi")
@PropertySource(value = { "classpath:application.properties" })
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean
	public CacheManager cacheManager() {
		// A EhCache based Cache manager
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
		factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
		factory.setShared(true);
		return factory;
	}

	/**
	 * Configure ViewResolvers to deliver preferred views.
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}

	/**
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript
	 * etc...
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
	}

	/**
	 * Configure MessageSource to lookup any validation/error message in
	 * internationalized property files
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		return new MultipartConfigElement("");
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getResolver() throws IOException {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		// Set the maximum allowed size (in bytes) for each individual file.
		resolver.setMaxUploadSizePerFile(100000000);// 5MB
		// You may also set other available properties.

		return resolver;
	}

	// @Bean
	// public VelocityConfigurer velocityConfigurer() {
	// VelocityConfigurer vc = new VelocityConfigurer();
	// vc.setResourceLoaderPath("/WEB-INF/mail/");
	// return vc;
	// }

	@Bean
	public VelocityEngine velocityEngine() throws Exception {

		VelocityEngine velocityEngine = new VelocityEngine();
		// velocityEngine.addProperty(RuntimeConstants.RESOURCE_LOADER,
		// "classpath");
		// velocityEngine.addProperty("classpath.resource.loader.class",
		// ClasspathResourceLoader.class.getName());
		// velocityEngine.addProperty("input.encoding", "UTF-8");
		// velocityEngine.addProperty("output.encoding", "UTF-8");
		// velocityEngine.addProperty("file.resource.loader.path",
		// "/WEB-INF/mail");
		// velocityEngine.addProperty("file.resource.loader.cache", "false");

		try {
			velocityEngine.addProperty("resource.loader", "class, file");
			velocityEngine.addProperty("class.resource.loader.class",
					"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			velocityEngine.addProperty("file.resource.loader.class",
					"org.apache.velocity.runtime.resource.loader.FileResourceLoader");
			velocityEngine.addProperty("file.resource.loader.path", "classpath:application.properties");
			velocityEngine.setProperty("runtime.references.strict", true);
			velocityEngine.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");

			velocityEngine.init();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to initialize Velocity engine", e);
		}

		return velocityEngine;
	}

	@Bean
	public JavaMailSender javaMailService() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtplw.com.br");
		javaMailSender.setPort(587);
		javaMailSender.setUsername("renangomes21");
		javaMailSender.setPassword("DICVnCgW0431");
		return javaMailSender;
	}

	@Bean
	public SimpleMailMessage simpleMailMessage() {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("renansi@gmail.com");
		simpleMailMessage.setReplyTo("renansi@gmail.com");
		return simpleMailMessage;
	}

	/**
	 * Optional. It's only required when handling '.' in @PathVariables which
	 * otherwise ignore everything after last '.' in @PathVaidables argument.
	 * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164],
	 * still present in Spring 4.1.7. This is a workaround for this issue.
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(true);
	}
}
