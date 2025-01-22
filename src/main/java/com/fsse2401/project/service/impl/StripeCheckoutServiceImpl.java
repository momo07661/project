package com.fsse2401.project.service.impl;

import com.fsse2401.project.data.cart.domainObject.response.ResponseCartData;
import com.fsse2401.project.data.stripe.data.request.StripeRequestData;
import com.fsse2401.project.data.stripe.data.response.StripeResponseData;
import com.fsse2401.project.data.user.domainObject.FireBaseUserData;
import com.fsse2401.project.exception.exceptionList.CartItemNotFoundException;
import com.fsse2401.project.exception.exceptionList.DataNotFoundException;
import com.fsse2401.project.service.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StripeCheckoutServiceImpl {

    @Autowired
    private final CartItemServiceImpl cartItemService;

    @Autowired
    private final TransactionServiceImpl transactionService;

    public StripeCheckoutServiceImpl(CartItemServiceImpl cartItemService, UserService userService, TransactionServiceImpl transactionService) {
        this.cartItemService = cartItemService;
        this.transactionService = transactionService;
    }

    public StripeResponseData stripeCheckOut(FireBaseUserData data, String tid) throws StripeException {

        Stripe.apiKey = "sk_test_51P7untCKEIE0doCRjd1VhCtVUgiQPWcWzdlQfJZa3NCHac1oes3tYe4QSd09WWeYtniGfc30ULEMRL54yyKJWxWF00wxI5uBUH";

        String YOUR_DOMAIN = "http://localhost:5173";
//        String YOUR_DOMAIN = "https://shop.snowpig.shop";

        List<ResponseCartData> cartDataList = cartItemService.getCartItem(data);
        if (cartDataList.isEmpty()){
            throw new CartItemNotFoundException();
        }

        List<StripeRequestData> dataList = new ArrayList<>();

        // list for check any null in the cartItem
        for (ResponseCartData cartData: cartDataList){
            // check null before making stripe datalist
            if (cartData.getQuantity() == null || cartData.getProduct().getStripePriceId() == null){
                throw new DataNotFoundException();
            }
            dataList.add(new StripeRequestData(cartData));
        }

        // Create an array to hold line items
        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

        // Add line items from dataList to lineItems array
        for (StripeRequestData stripeRequestData: dataList) {
            lineItems.add(SessionCreateParams.LineItem.builder()
                    .setQuantity(stripeRequestData.getQuantity())
                    .setPrice(stripeRequestData.getPriceId())
                    .build()
            );
        }

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
//                        .setSuccessUrl(YOUR_DOMAIN + "/thankyou?session_id={CHECKOUT_SESSION_ID}")
                        .setSuccessUrl(YOUR_DOMAIN + "/checkingout/" + tid)
                        .setCancelUrl(YOUR_DOMAIN + "/error")
                        .addAllLineItem(lineItems) // Add line items to session params
                        .build();

        Session session = Session.create(params);
        transactionService.updateTransactionSessionId(data, Integer.parseInt(tid), session.getId());
//        return session.getUrl();
//
//        return new StripeResponseData(Session.create(params).getId());
        return new StripeResponseData(session.getUrl(), session.getId());
    }
}
