package com.example.ecommerceapp.Entity;

import com.example.ecommerceapp.Entity.Enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {

    private String method;

    private PaymentStatus status;

    private  String paymentId;

    private String razorPayPaymentLinkId;

    private String razorpayPaymentLinkReferencesId;

    private  String razorpayPaymentLinkStatus;

    private  String razorpayPaymentId;



}
