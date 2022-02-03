package com.glanner.api.controller;

import com.glanner.api.dto.request.SendMailReqDto;
import com.glanner.api.dto.request.SendSmsReqDto;
import com.glanner.api.dto.response.BaseResponseEntity;
import com.glanner.api.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/mail")
    public ResponseEntity<BaseResponseEntity> sendMail(@RequestBody SendMailReqDto reqDto){
        notificationService.sendMail(reqDto);
        return ResponseEntity.status(200).body(new BaseResponseEntity(200, "Success"));
    }

    @PostMapping("/sms")
    public ResponseEntity<BaseResponseEntity> sendSMS(@RequestBody SendSmsReqDto reqDto){
        try {
            notificationService.sendSms(reqDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(200).body(new BaseResponseEntity(200, "Success"));
    }
}
