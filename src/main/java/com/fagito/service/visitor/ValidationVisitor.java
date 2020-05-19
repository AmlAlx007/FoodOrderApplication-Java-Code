package com.fagito.service.visitor;

public interface ValidationVisitor {
	boolean visit(CustomerElement customerElement) throws Exception;
	boolean visit(PaymentElement paymentElement) throws Exception;
}
