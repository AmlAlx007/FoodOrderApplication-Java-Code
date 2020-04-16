package com.fagito.interceptor;

import java.io.BufferedReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


@Component
public class OrderInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		String str,wholeStr="";
		BufferedReader br=request.getReader();
		
		while ((str = br.readLine()) != null) {
			wholeStr += str;
		}
		
		
		JsonObject jsonObject = new JsonParser().parse(wholeStr).getAsJsonObject();
		
		
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				Request httpRequest = new Request.Builder()
				  .url("https://api.mailgun.net/v4/address/validate?address="+jsonObject.get("email"))
				  .method("GET", null)
				  .addHeader("Authorization", "Basic YXBpOmU0Njk3NjJlMmViZDRjMjdlZTNlYzA3ZDQ1MGQyNjU0LTkxNTE2MWI3LTVjMjc1ODE5")
				  .build();
				Response httpResponse = client.newCall(httpRequest).execute();
		
		
		System.out.println(httpResponse);
		
		return true;
		/*System.out.println(request);
		LocalDate localDate = LocalDate.now();
		DayOfWeek day = localDate.getDayOfWeek();
		System.out.println("day :" + day);
		if (day.getValue() == 5) {
			System.out.println("Website is down");
			response.getWriter().write("Website is down");
			return false;
		} else {
			return true;
		}*/
	}

}
