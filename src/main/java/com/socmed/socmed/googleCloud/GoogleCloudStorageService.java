package com.socmed.socmed.googleCloud;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GoogleCloudStorageService {

    @Value("${gcp.project-id}")
    private String projectId;


    private Storage storage;

    private static  String BUCKET_NAME= "socmed";

    public GoogleCloudStorageService() {
        StorageOptions options = StorageOptions.newBuilder()
                .setProjectId(projectId)
                .build();

        storage = options.getService();
    }

    public Bucket createBucket(String bucketName) {
        Bucket bucket = storage.create(BucketInfo.of(bucketName));
        return bucket;
    }

    public void uploadFile( String fileName, byte[] fileContent) {
        BlobInfo blobInfo = BlobInfo.newBuilder(BUCKET_NAME, fileName).build();
        storage.create(blobInfo, fileContent);
    }
    public String getFileUrl( String fileName) {
        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        Blob blob = storage.get(blobId);
        String fileUrl = blob.getMediaLink();
        return fileUrl;
    }

    public byte[] downloadFile( String fileName) {
        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        Blob blob = storage.get(blobId);
        return blob.getContent();
    }
}
