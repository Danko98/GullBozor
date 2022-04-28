package uz.gullbozor.gullbozor.smsSender;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("twilio")
public class TwilioSmsSender implements SmsSender{

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);

    private final TwilioConfiguration twilioConfiguration;

    private final int max = 99999;
    private final int min = 10001;
    private final int randomNumber = (int) (Math.random() * (max - min + 1)) +min;

    @Autowired
    public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }


    @Override
    public void sendSms(PhoneNumber phoneNumber) {
        if (isPhoneNumberValid(phoneNumber.getPhoneNumber())) {
            com.twilio.type.PhoneNumber to = new com.twilio.type.PhoneNumber(phoneNumber.getPhoneNumber());
            com.twilio.type.PhoneNumber from = new com.twilio.type.PhoneNumber("+16406008844");

            MessageCreator creator = Message.creator(to,from, "GullBozor.uz dan sms kod: "+randomNumber );
            creator.create();
            LOGGER.info("Send sms {}" + phoneNumber);

        }else {
            throw  new IllegalArgumentException(
                    "Phone number [" + phoneNumber.getPhoneNumber() + "] is not a valid number");
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        //TODO: Implement phone number validator
        return true;
    }
}
