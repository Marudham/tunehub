package com.tunehub.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.tunehub.entities.Users;
import com.tunehub.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
	
	@Autowired
	UsersService userService;

	@GetMapping("/pay")
	public String pay() {
		return "pay";
	}
	
	@SuppressWarnings("finally")
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(HttpSession session) {
		int amount = 5000;
		Order order = null;
		try {
			RazorpayClient razorpay = new RazorpayClient("rzp_test_CglnmohLu2qBHK", "NXXHK2RNZjZI9Sh1GrEN6AgG");
			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", amount*100);
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcptid_11");
			
			order = razorpay.orders.create(orderRequest);
			
			String email = (String) session.getAttribute("email");
			
			Users user = userService.getUser(email);
			user.setPremium(true);
			userService.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return order.toString();
		}
	}
}
