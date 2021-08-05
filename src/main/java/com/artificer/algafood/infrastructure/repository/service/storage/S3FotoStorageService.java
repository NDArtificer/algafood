package com.artificer.algafood.infrastructure.repository.service.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.artificer.algafood.core.storage.StorageProperties;
import com.artificer.algafood.domain.service.FotoStorageService;

public class S3FotoStorageService implements FotoStorageService {

	@Autowired
	private AmazonS3 amazonS3;
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		String filePath = getFilePath(nomeArquivo);
		
		URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), filePath);
		
		
		return FotoRecuperada.builder()
				.url(url.toString())
				.build();
	}

	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			String filePath = getFilePath(novaFoto.getNomeArquivo());

			ObjectMetadata objectMetaData = new ObjectMetadata();
			objectMetaData.setContentType(novaFoto.getContentType());
			objectMetaData.setContentLength(novaFoto.getSize());

			var putObejctRequest = new PutObjectRequest(storageProperties.getS3()
					.getBucket(), filePath, novaFoto.getInputStream(), objectMetaData)
					.withCannedAcl(CannedAccessControlList.PublicRead);

			amazonS3.putObject(putObejctRequest);
		} catch (Exception e) {
			throw new StrorageException("Não foi Possível fazer o upload do arquivo para o bucket da Amazon S3!", e);
		}
	}

	private String getFilePath(String nomeArquivo) {
		return String.format("%s/%s", storageProperties.getS3().getDirectoryPhotos(), nomeArquivo);
	}

	@Override
	public void remover(String nomeArquivo) {
		try {
			String filePath = getFilePath(nomeArquivo);
			
			var deleteOjectRequest = new DeleteObjectRequest(storageProperties.getS3().getBucket(), filePath);
			
			amazonS3.deleteObject(deleteOjectRequest);
			
		} catch (Exception e) {
			throw new StrorageException("Não foi Possível fazer a deleção do arquivo para o bucket da Amazon S3!", e);
		}
	}

}
