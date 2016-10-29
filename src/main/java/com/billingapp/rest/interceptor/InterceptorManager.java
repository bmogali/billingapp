package com.billingapp.rest.interceptor;


import org.apache.cxf.Bus;
import org.apache.cxf.feature.AbstractFeature;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.InterceptorProvider;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.common.gzip.GZIPOutInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterceptorManager extends AbstractFeature {

	private static final Logger logger = LoggerFactory
			.getLogger(InterceptorManager.class);
	
	private static final Interceptor<Message> GZIP = new GZIPOutInterceptor();

	// private static final Interceptor< Message > GZIP = new
	// GZIPOutInterceptor(512);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.cxf.feature.AbstractFeature#initializeProvider(org.apache.
	 * cxf.interceptor.InterceptorProvider, org.apache.cxf.Bus)
	 */
	@Override
	protected void initializeProvider(InterceptorProvider provider, Bus bus) {
		/**
		 * Adding Gzip interceptor to all outbound requests/responses
		 */
		logger.debug(" ##############  Adding Gzip as OUT Interceptor ##############");

		provider.getOutInterceptors().add(GZIP);

	}
}