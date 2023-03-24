package kr.co.kbs.controller;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EchoController {

	private static final Logger LOG = LoggerFactory.getLogger(EchoController.class);

	@Autowired
	private HttpServletRequest request;

	@CrossOrigin("*")
	@RequestMapping(value = "/**",
	                consumes = MediaType.ALL_VALUE,
	                produces = MediaType.APPLICATION_JSON_VALUE,
	                method   = {RequestMethod.GET,
	                            RequestMethod.POST,
	                            RequestMethod.PUT,
	                            RequestMethod.DELETE,
	                            RequestMethod.OPTIONS})
	public ResponseEntity<String> echoBack(@RequestBody(required = false) String body) {

		LOG.info("");
		LOG.info("");
		LOG.info("");
		LOG.info("");
		LOG.info("");
		LOG.info("CLIENT SEND REQUEST  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" );
		final Map<String, String> headers  = Collections.list(request.getHeaderNames()).stream()
			.collect(Collectors.toMap(Function.identity(), request::getHeader));
		LOG.info("REQUEST PROTOCOL      :  {}", request.getProtocol());
		LOG.info("REQUEST METHOD        :  {}", request.getMethod());
		LOG.info("REQUEST PATH          :  {}", request.getRequestURL());
		LOG.info("REQUEST PARAMETERS    :  {}", request.getParameterMap());
		LOG.info("REQUEST COOKIES       :  {}", request.getCookies());
		LOG.info("REQUEST HEADERS       :\n{}", headers);
		LOG.info("REQUEST BODY          :\n{}", body);
		LOG.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ECHO RECEIVE REQUEST" );
		LOG.info("ECHO SEND RESPONSE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		HttpHeaders responseHeaders = new HttpHeaders();
		headers.forEach((key, value) -> responseHeaders.set(key, value));
		LOG.info("RESPONSE HEADER       :\n{}", responseHeaders);
		LOG.info("RESPONSE BODY         :\n{}", body);
		LOG.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CLIENT RECEIVE RESPONSE");
		LOG.info("");
		LOG.info("");
		LOG.info("");
		LOG.info("");
		LOG.info("");

		return new ResponseEntity<>(body, responseHeaders, HttpStatus.OK);
	}
}
