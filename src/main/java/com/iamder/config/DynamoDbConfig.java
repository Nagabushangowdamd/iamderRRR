package com.iamder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
@PropertySource("file:${CONFIG_BASE}/iamder/application.properties")
public class DynamoDbConfig {

	@Autowired
	Environment env;
	
	public String getAWSAccessKey() {
		return env.getProperty("amazon.access.key");
	}
	
	public String getAWSSecretKey() {
		return env.getProperty("amazon.access.secret-key");
	}
	
	public String getAWSRegion() {
		return env.getProperty("amazon.region");
	}
	
	public String getAWSURL() {
		return env.getProperty("amazon.end-point.url");
	}
	
	/*
	 * @Value("${amazon.access.key}") private String awsAccessKey;
	 * 
	 * @Value("${amazon.access.secret-key}") private String awsSecretKey;
	 * 
	 * @Value("${amazon.region}") private String awsRegion;
	 * 
	 * @Value("${amazon.end-point.url}") private String awsDynamoDBEndPoint;
	 */

	@Bean
	public DynamoDBMapper mapper() {
		return new DynamoDBMapper(amazonDynamoDBConfig());
	}

	public AmazonDynamoDB amazonDynamoDBConfig() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(getAWSURL(), getAWSRegion()))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(getAWSAccessKey(), getAWSSecretKey())))
				.build();
	}
}