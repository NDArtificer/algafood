package com.artificer.algafood.core.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.artificer.algafood.core.storage.StorageProperties.StorageType;
import com.artificer.algafood.domain.service.FotoStorageService;
import com.artificer.algafood.infrastructure.repository.service.storage.LocalFotoStorageService;
import com.artificer.algafood.infrastructure.repository.service.storage.S3FotoStorageService;

@Configuration
public class StorageConfig {

	@Autowired
	private StorageProperties storageProperties;

	@Bean
	@ConditionalOnProperty(name = "algafood.storage.type", havingValue = "s3")
	public AmazonS3 amazonS3() {
		var credentials = new BasicAWSCredentials(storageProperties.getS3().getIdAccessKey(),
				storageProperties.getS3().getIdSecretAccessKey());

		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(storageProperties.getS3().getRegion()).build();
	}

	@Bean
	public FotoStorageService fotoStorageService() {
		if (StorageType.S3.equals(storageProperties.getType())) {
			return new S3FotoStorageService();
		} else {
			return new LocalFotoStorageService();
		}
	}

}
