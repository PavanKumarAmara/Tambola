package com.pavan.projects.tambola;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pavan.projects.tambola.repository.Numbers;
import com.pavan.projects.tambola.repository.NumbersRepository;

@Component
public class TambolaCron {
	@Autowired
	NumbersRepository numbersRepository;
	@Scheduled(cron = "0 * * * * ?")
	public void cronToGenerateNumber() {
		List<Numbers> numbers = numbersRepository.findAll();
		if(numbers.size()<91) {
			int randomNumber = getRandomNumber(1, 90);
			while(!notInGeneratedList(randomNumber,numbers)) {
				randomNumber = getRandomNumber(1, 90);
			}
			System.out.println("GeneratedNumber is "+ randomNumber);
			numbersRepository.save(new Numbers(randomNumber));
		}
	}
	
	private boolean notInGeneratedList(int randomNumber, List<Numbers> numbers ) {
		for(Numbers number:numbers) {
			if(number.getNumber() == randomNumber)
				return false;
		}
		return true;
	}

	private int getRandomNumber(int min, int limit) {
		return (int)(Math.random()*(limit-min+1)+min);
	}
}
