package com.funonfire.controller;

import com.funonfire.dto.SearchRequestDto;
import com.funonfire.enums.ChannelTypeEnum;
import com.funonfire.service.ChatService;
import com.funonfire.util.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(@RequestParam("userId") String userId,
                                         SearchRequestDto searchRequest
    ) {
        return ResponseHandler.response(
                chatService.getAll(userId, searchRequest),
                "Fetched Successfully",
                true,
                HttpStatus.OK
        );
    }

    @PostMapping ("/{toUserId}/{fromUserId}")
    public ResponseEntity<Object> createChat(
            @PathVariable("toUserId") String toUserId,
            @PathVariable("fromUserId") String fromUserId,
            @RequestParam ChannelTypeEnum channelType
    ) {
        return ResponseHandler.response(
                chatService.createChannel(toUserId, fromUserId, channelType),
                "Channel created Successfully",
                true,
                HttpStatus.OK
        );
    }

}
