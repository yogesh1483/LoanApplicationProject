package com.cibilscore.main.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetCibilScoreController {
	Random random = new Random();
	@GetMapping("/getCibilScore/{pancardNumber}")
	public int getCibilScore(@PathVariable("pancardNumber") String pancardNumber) {
		if(pancardNumber.length()==12) {
		return random.nextInt(600,900);
		}
		else {
			return 0;
		}
	}
	
}
