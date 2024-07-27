package com.funonfire.controller;

import com.funonfire.dto.RestResponseDto;
import com.funonfire.dto.SearchRequestDto;
import com.funonfire.dto.inbound.MessageRequestDto;
import com.funonfire.entity.Channel;
import com.funonfire.entity.Message;
import com.funonfire.service.ChatService;
import com.funonfire.service.MessageService;
import com.funonfire.util.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;
    private final ChatService chatService;

    @GetMapping(value = "/{userId}/{channelId}")
    public ResponseEntity<Object> getMessages(
            @PathVariable String userId,
            @PathVariable String channelId,
            SearchRequestDto searchRequest
    ) {
        RestResponseDto<List<Message>> messages = messageService.getMessages(
                userId,
                channelId,
                searchRequest
        );
        return ResponseHandler.response(
                messages,
                "Fetched Successfully",
                true,
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/{senderId}")
    public ResponseEntity<Object> sendMessage(
            @PathVariable String senderId,
            @RequestParam Boolean isFirst,
            @RequestBody MessageRequestDto request
    ) {
        if (isFirst) {
            Channel channel =chatService.createChannel(senderId, request.getToUserId(), request.getChannelType());
            request.setChannelId(channel.getId());
        }
        messageService.sendMessage(request);
        return ResponseHandler.response(
                "Message sent Successfully",
                true,
                HttpStatus.OK
        );
    }

}
