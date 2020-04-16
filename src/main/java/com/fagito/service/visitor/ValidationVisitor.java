package com.fagito.service.visitor;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fagito.dto.SignUpDTO;


public interface ValidationVisitor {
	boolean visit(CustomerElement customerElement) throws Exception;
	boolean visit(PaymentElement paymentElement) throws Exception;
}
