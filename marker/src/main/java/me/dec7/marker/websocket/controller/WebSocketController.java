package me.dec7.marker.websocket.controller;

import me.dec7.marker.vo.CalcInput;
import me.dec7.marker.vo.Result;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
	
	@MessageMapping("/add")
	@SendTo("/topic/showResult")
	public Result showResult(CalcInput input) throws Exception{
		Result result = new Result(input.getNum1() + "+" + input.getNum2() + "=" + (input.getNum1() + input.getNum2()));
		
		return result;
	}

}
