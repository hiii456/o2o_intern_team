package com.o2o.action.server.rest;

import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.o2o.action.server.app.internexApp;
import com.o2o.action.server.repo.CategoryRepository;
import com.o2o.action.server.util.CommonUtil;

@RestController
public class internexController {
	private final internexApp internexApp;

	@Autowired
	private CategoryRepository categoryRepository;

	public internexController() {
		internexApp = new internexApp();
	}

	@RequestMapping(value = "/internex", method = RequestMethod.POST)
	public @ResponseBody String processActions(@RequestBody String body, HttpServletRequest request,
			HttpServletResponse response) {
		String jsonResponse = null;
		try {
			System.out.println("request : " + body + "," + categoryRepository);
			jsonResponse = internexApp.handleRequest(body, CommonUtil.getHttpHeadersMap(request)).get();
			System.out.println("response : " + jsonResponse);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return jsonResponse;
	}

}
