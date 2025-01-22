package com.fsse2401.project.api;

import com.fsse2401.project.config.EnvConfig;
import com.fsse2401.project.data.stripe.domainObject.response.StripeResponseDto;
import com.fsse2401.project.service.impl.StripeCheckoutServiceImpl;
import com.fsse2401.project.util.JwtUtil;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.stripe.model.checkout.Session;


import static com.fsse2401.project.config.EnvConfig.PRO_S3_BASE_URL;
@RestController
@CrossOrigin({EnvConfig.DEV_BASE_URL, EnvConfig.PRO_BASE_URL, PRO_S3_BASE_URL})
public class StripeApi {

    @Autowired
    private final StripeCheckoutServiceImpl stripeCheckoutService;

    public StripeApi(StripeCheckoutServiceImpl stripeCheckoutService) {
        this.stripeCheckoutService = stripeCheckoutService;
    }

    @PostMapping("/stripe/checkout/{tid}")
    public StripeResponseDto postStripeCheckOut(JwtAuthenticationToken jwtToken, @PathVariable String tid) throws StripeException {
        return new StripeResponseDto(stripeCheckoutService.stripeCheckOut(JwtUtil.getFirebaseUserData(jwtToken), tid));
    }

    @GetMapping("/stripe/session/{sessionId}")
    public String getTransactionStatus(@PathVariable String sessionId) throws StripeException {
        Stripe.apiKey = "sk_test_51P7untCKEIE0doCRjd1VhCtVUgiQPWcWzdlQfJZa3NCHac1oes3tYe4QSd09WWeYtniGfc30ULEMRL54yyKJWxWF00wxI5uBUH";
        // Retrieve session details from Stripe using sessionId
        Session session = Session.retrieve(sessionId);
        String status = session.getPaymentStatus();
        System.out.println(status);
        return status;
    }

    //method may not be use of stripe webhook
//    @PostMapping("/stripe/webhook")
//    public ResponseEntity<String> webhook(@RequestBody String payload,
//                                          @RequestHeader("Stripe-Signature") String stripeSignatureHeader) {
//        Event event;
//        // Stripe CLI secret
//        final String END_POINT_SECRET = "sk_test_51P7untCKEIE0doCRjd1VhCtVUgiQPWcWzdlQfJZa3NCHac1oes3tYe4QSd09WWeYtniGfc30ULEMRL54yyKJWxWF00wxI5uBUH";
//
//        // Step 1
//        try {
//            event = Webhook.constructEvent(payload, stripeSignatureHeader,
//                    END_POINT_SECRET);
//        } catch (SignatureVerificationException e) {
//            System.out.println("Failed signature verification");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        } catch (JsonSyntaxException e) {
//            System.out.println("Json syntax error");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // Step 2
//        EventDataObjectDeserializer dataObjectDeserializer =
//                event.getDataObjectDeserializer();
//        StripeObject stripeObject = null;
//
//        if(dataObjectDeserializer.getObject().isPresent()) {
//            stripeObject = dataObjectDeserializer.getObject().get();
//        } else {
//            System.out.println("EventDataObjectDeserializer failed");
//        }
//
//
//        // Step 3
//        if ("checkout.session.completed".equals(event.getType())) {
//            // complete checkout session
//            // Put your session completed handling here
//        } else {
//            // log other unhandled event
//            Logger logger = LoggerFactory.getLogger(StripeApi.class);
////            log.error("Unhandled event type: " + event.getType());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        return new ResponseEntity<>("Success", HttpStatus.OK);
//    }
}
