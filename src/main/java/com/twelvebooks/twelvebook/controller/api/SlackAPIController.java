package com.twelvebooks.twelvebook.controller.api;


import com.twelvebooks.twelvebook.util.SlackNotifier;
import com.twelvebooks.twelvebook.dto.SlackAPIDto;
import lombok.RequiredArgsConstructor;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/slack")
@RequiredArgsConstructor
public class SlackAPIController {


    @Autowired
    private SlackNotifier slackNotifier;


        @GetMapping("/test")
    public void webHook() {

            SlackApi api = new SlackApi("https://hooks.slack.com/services/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");    //웹훅URL
            api.call(new SlackMessage("#twelvebooks", "TEST-WEBHOOK", " test"));


        }





    @PostMapping
    public ResponseEntity<Boolean> send(@RequestBody SlackNotifier.SlackMessageAttachement message, SlackAPIDto slackAPIDto) {

        BeanUtils.copyProperties(slackAPIDto, message);
    return ResponseEntity.ok(slackNotifier.notify(SlackNotifier.SlackTarget.CH_INCOMING, message));
}




}
