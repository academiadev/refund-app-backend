package br.com.academiadev.reembolsoazul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.academiadev.reembolsoazul.dto.RefundDTO;
import br.com.academiadev.reembolsoazul.exception.UserNotFoundException;
import br.com.academiadev.reembolsoazul.service.RefundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Refund")
@RestController
@RequestMapping("/refund")
public class RefundController {

	@Autowired
	private RefundService refundService;

	@ApiOperation(value = "Cadastra um reembolso")
	@ApiImplicitParams({ //
		@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") //
	})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Reembolso cadastrado com sucesso") })
	@PostMapping("/")
	public ResponseEntity<RefundDTO> register(@RequestBody RefundDTO refundDTO) throws UserNotFoundException {
		refundService.register(refundDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna todos os reembolsos cadastrados", response = RefundDTO[].class)
	@ApiImplicitParams({ //
		@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") //
	})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Lista recebida com sucesso") })
	@GetMapping("/")
	public ResponseEntity<List<RefundDTO>> getAll() {
		return new ResponseEntity<>(refundService.findAll(), HttpStatus.OK);
	}

}
