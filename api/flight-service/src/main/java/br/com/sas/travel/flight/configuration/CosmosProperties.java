package br.com.sas.travel.flight.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.data.cosmos")
public class CosmosProperties {

	private String uri;

	private String key;

	private String secondaryKey;

	private String databaseName;

	private boolean queryMetricsEnabled;

}