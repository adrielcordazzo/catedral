package br.com.xlabi.controller.geral;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.xlabi.result.OutputMessage;
import br.com.xlabi.result.Result;

@Controller
public class WebsoketController {

	@Autowired
	private SimpMessagingTemplate webSocket;

	@MessageMapping("/webSocket")
	@SendTo("/sync/messages/{id}")
	public OutputMessage send(OutputMessage message, @DestinationVariable String id) throws Exception {

		System.out.println(id);
		String time = new SimpleDateFormat("HH:mm").format(new Date());
		message.setTime(time);

		return message;
	}

	@RequestMapping(value = { "/testeResposta" }, method = RequestMethod.GET)
	public @ResponseBody Result callableWithView(final Model model) {
		Result r = new Result();
		OutputMessage m = new OutputMessage();
		m.setData(new String("{}"));
		m.setUrl("paciente/PacienteList");
		m.setType("url");
		m.setTime("");

		webSocket.convertAndSend("/sync/messages/xxx", m);

		// Callable<Result> callable = new Callable<Result>() {
		// @Override
		// public Result call() throws Exception {
		// Thread.sleep(2000);
		// System.out.println("brasillll");
		// System.out.println("renan");
		// return new Result();
		// }
		// };
		// WebAsyncTask<Result> w = new WebAsyncTask<Result>(1, callable);

		return r;
	}
}
