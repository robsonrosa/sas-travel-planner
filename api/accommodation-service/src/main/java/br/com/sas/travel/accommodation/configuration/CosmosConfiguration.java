package br.com.sas.travel.accommodation.configuration;

import static com.azure.cosmos.DirectConnectionConfig.getDefaultConfig;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.core.ResponseDiagnostics;
import com.azure.spring.data.cosmos.core.ResponseDiagnosticsProcessor;
import com.azure.spring.data.cosmos.repository.config.EnableReactiveCosmosRepositories;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(CosmosProperties.class)
@EnableReactiveCosmosRepositories(basePackages = "br.com.sas.travel")
public class CosmosConfiguration extends AbstractCosmosConfiguration {

	private final CosmosProperties properties;

	@Bean
	public CosmosClientBuilder cosmosClientBuilder() {
		return new CosmosClientBuilder()
				.endpoint(properties.getUri())
				.key(properties.getKey())
				.directMode(getDefaultConfig());
	}

	@Bean
	public CosmosConfig cosmosConfig() {
		return CosmosConfig.builder()
				.responseDiagnosticsProcessor(new ResponseDiagnosticsProcessorImplementation())
				.enableQueryMetrics(properties.isQueryMetricsEnabled())
				.build();
	}

	@Override
	protected String getDatabaseName() {
		return properties.getDatabaseName();
	}

	private static class ResponseDiagnosticsProcessorImplementation implements ResponseDiagnosticsProcessor {

		@Override
		public void processResponseDiagnostics(@Nullable ResponseDiagnostics responseDiagnostics) {
			log.info("Response Diagnostics {}", responseDiagnostics);
		}
	}
}