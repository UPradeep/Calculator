package com.calc.CalcDemo.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.calc.CalcDemo.Models.CalcResult;
import com.calc.CalcDemo.Models.Calculator;


@Controller
@CrossOrigin
public class CalculatorController {	
	
	@PostMapping("/calculate")
	public ResponseEntity calculate(
			@RequestParam(value="leftOperand") String leftOperand,
			@RequestParam(value="operator") String operator,
			@RequestParam(value="rightOperand") String rightOperand,
			Model model)
	{
		double leftNumber;
		double rightNumber;

		try {
			leftNumber = Double.parseDouble(leftOperand);
		}
		catch (NumberFormatException ex) {
			leftNumber = 0;
		}

		try {
			rightNumber = Double.parseDouble(rightOperand);
		}
		catch (NumberFormatException ex) {
			rightNumber = 0;
		}
		
		Calculator calculator = new Calculator(
				leftNumber,
				rightNumber,
				operator
		);
		
		double result = calculator.calculateResult();
		CalcResult res = new CalcResult(result);
		return new ResponseEntity(res.getResult(),HttpStatus.OK);
	}
}