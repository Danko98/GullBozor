package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.gullbozor.gullbozor.smsSender.SmsService;
import uz.gullbozor.gullbozor.smsSender.PhoneNumber;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/sms")
public class SmsController {

    private final SmsService smsService;

    @Autowired
    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }


    @PostMapping
    public void sendSms(@Valid @RequestBody PhoneNumber phoneNumber) {
        smsService.sendSms(phoneNumber);
    }
}
