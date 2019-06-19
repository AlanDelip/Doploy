package cn.alandelip.logic.impl;

import cn.alandelip.config.S3PresignExecutionInterceptor;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Map;

/**
 * @author Alan.Zhufeng Xu, 4/18/2019.
 */
public class S3Upload {
    private final static String BUCKET_NAME = "doploy";

    public static String upload(Map<String, String> root, Template tmpl, String key) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        Writer out = new OutputStreamWriter(bout);
        try {
            tmpl.process(root, out);
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }

        S3PresignExecutionInterceptor presignInterceptor = new S3PresignExecutionInterceptor(Region.US_EAST_2, Duration.ofDays(1));
        S3Client s3Client = S3Client.builder()
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .overrideConfiguration(ClientOverrideConfiguration.builder().addExecutionInterceptor(presignInterceptor).build())
                .region(Region.US_EAST_2)
                .build();
        PutObjectRequest req = PutObjectRequest.builder().bucket(BUCKET_NAME).key(key).build();
        s3Client.putObject(req, RequestBody.fromBytes(bout.toByteArray()));

        GetObjectRequest s3GetRequest = GetObjectRequest.builder().bucket(BUCKET_NAME).key(key).build();
        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(s3GetRequest);
        try {
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return presignInterceptor.getSignedURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
