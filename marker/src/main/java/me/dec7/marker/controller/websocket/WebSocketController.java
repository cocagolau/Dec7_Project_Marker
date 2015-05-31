package me.dec7.marker.controller.websocket;

import me.dec7.marker.common.vo.CalcInput;
import me.dec7.marker.common.vo.Result;

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
