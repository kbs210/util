package kr.co.kbs.controller;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import kr.co.kbs.util.kisaseed.CryptionServiceSeed;
import org.json.JSONObject;
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

	@CrossOrigin("*")
	@RequestMapping(value = "/v2/insurances/payment",
			consumes = MediaType.ALL_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			method   = {RequestMethod.GET,
					RequestMethod.POST,
					RequestMethod.PUT,
					RequestMethod.DELETE,
					RequestMethod.OPTIONS})
	public ResponseEntity<String> IS05(@RequestBody(required = false) String body) {

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
		body = "";

		CryptionServiceSeed cryptionServiceSeed = new CryptionServiceSeed();
		String text = "12345678901234567890";
		String chiphertext= cryptionServiceSeed.encrypt(text);
		LOG.info("chiphertext : " + chiphertext);
		String plaintext= cryptionServiceSeed.decrypt(chiphertext);
		LOG.info("plaintext : " + plaintext);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("rsp_code","00000");
		jsonObject.put("rsp_msg","성공");
		jsonObject.put("search_timestamp","20230412090000");
		jsonObject.put("pay_due","01");
		jsonObject.put("pay_cycle","99");
		jsonObject.put("pay_cnt","1");
		jsonObject.put("pay_org_code","ZZZZ0000");
		jsonObject.put("pay_account_num1", chiphertext);
		jsonObject.put("pay_account_num2", plaintext);
		jsonObject.put("pay_date","99");
		jsonObject.put("pay_end_date","20240430");
		jsonObject.put("pay_amt","10000");
		jsonObject.put("currency_code","");
		jsonObject.put("is_auto_pay","true");

		body = jsonObject.toString();

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

		return new ResponseEntity<>(body, null, HttpStatus.OK);
	}
}
