package cn.alandelip.config;

import software.amazon.awssdk.auth.signer.AwsS3V4Signer;
import software.amazon.awssdk.auth.signer.AwsSignerExecutionAttribute;
import software.amazon.awssdk.core.interceptor.Context;
import software.amazon.awssdk.core.interceptor.ExecutionAttributes;
import software.amazon.awssdk.core.interceptor.ExecutionInterceptor;
import software.amazon.awssdk.http.SdkHttpFullRequest;
import software.amazon.awssdk.regions.Region;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;

/**
 * @author Alan.Zhufeng Xu, 4/18/2019.
 */
public class S3PresignExecutionInterceptor implements ExecutionInterceptor {

	final private AwsS3V4Signer signer;
	final private String serviceName;
	final private Region region;
	final private Duration expirationTime;
	final private Integer timeOffset;
	private URI signedURI;

	public S3PresignExecutionInterceptor(Region region, Duration expirationTime) {
		this.signer = AwsS3V4Signer.create();
		this.serviceName = "s3";
		this.region = region;
		this.expirationTime = expirationTime;
		this.timeOffset = 2;
	}

	@Override
	public void beforeTransmission(Context.BeforeTransmission context, ExecutionAttributes executionAttributes) {
		// remove all headers because a Browser that downloads the shared URL will not send the exact values. X-Amz-SignedHeaders should only contain the host header.
		SdkHttpFullRequest modifiedSdkRequest = (SdkHttpFullRequest) context.httpRequest().toBuilder().clearHeaders().build();

		executionAttributes.putAttribute(AwsSignerExecutionAttribute.PRESIGNER_EXPIRATION, Instant.ofEpochSecond(0).plus(expirationTime));
		executionAttributes.putAttribute(AwsSignerExecutionAttribute.SERVICE_SIGNING_NAME, serviceName);
		executionAttributes.putAttribute(AwsSignerExecutionAttribute.SIGNING_REGION, region);
		executionAttributes.putAttribute(AwsSignerExecutionAttribute.TIME_OFFSET, timeOffset);
		SdkHttpFullRequest signedRequest = signer.presign(modifiedSdkRequest, executionAttributes);// sign(getRequest, new ExecutionAttributes());
		signedURI = signedRequest.getUri();
	}

	public URI getSignedURI() {
		return signedURI;
	}

}