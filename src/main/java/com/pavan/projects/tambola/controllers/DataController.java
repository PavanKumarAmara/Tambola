package com.pavan.projects.tambola.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.projects.tambola.repository.Numbers;
import com.pavan.projects.tambola.repository.NumbersRepository;
import com.pavan.projects.tambola.repository.Ticket;
import com.pavan.projects.tambola.repository.TicketRepository;
import com.pavan.projects.tambola.repository.TicketValues;
import com.pavan.projects.tambola.repository.TicketValuesRepository;
import com.pavan.projects.tambola.repository.Users;
import com.pavan.projects.tambola.repository.UsersRepository;
import com.pavan.projects.tambola.response.beans.TicketResponse;

@RestController
public class DataController {
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	TicketValuesRepository ticketValuesRepository;
	@Autowired
	NumbersRepository numbersRepository;
	
	@RequestMapping("/users")
	public void listUsers() {
		List<Users> users = usersRepository.findAll();
		System.out.println(users);
	}
	
	@RequestMapping("/getuser/{email}")
	public void getUser(@PathVariable String email) {
		try {
			Users user = usersRepository.getById(email);
			System.out.println(user);
		}catch(Exception e) {
			
		}
	}
	
	@RequestMapping("/getactiveticket/{email}")
	public Ticket getActiveTicket(@PathVariable String email) {
		Ticket ticket = null;
		try {
			ticket = ticketRepository.findByEmail(email);
			System.out.println(ticket);
		}catch(Exception e) {
			
		}
		return ticket;
	}
	
	@RequestMapping("/getticketvalues/{ticketid}")
	public TicketResponse getTicketValues(@PathVariable long ticketid) {
		TicketValues ticketValues = null;
		TicketResponse tr = new TicketResponse();
		try {
			ticketValues = ticketValuesRepository.getById(ticketid);
			System.out.println(ticketValues);
			String[] dbValues = ticketValues.getTicketValues().split("#");
			Set<Integer> values = new TreeSet<Integer>();
			for(String str: dbValues) {
				values.add(Integer.parseInt(str));
			}
			tr.setTicketValues(new ArrayList<Integer[]>());
			populateTicketValues(tr.getTicketValues(), values);
		}catch(Exception e) {
			
		}
		return tr;
	}
	
	@RequestMapping("/generateTicket/{email}")
	public TicketResponse generateTicket(@PathVariable String email) {
		List<Integer[]> ticketValues = new ArrayList<Integer[]>();
		try {
			Set<Integer> values =  generateTicketValues();
			System.out.println(values);
			populateTicketValues(ticketValues,values);
		}catch(Exception e) {
		}
		System.out.println(ticketValues);
		TicketResponse tr = new TicketResponse(email,ticketValues);
//		StringBuilder tcvString = new StringBuilder("");
//		for(Integer[] inte: ticketValues) {
//			for(int i=0;i<5;i++)
//			tcvString.append(inte[i]+"#");
//		}
//		long time = System.currentTimeMillis();
//		Ticket t = new Ticket(email,time);
//		ticketRepository.save(t);
//		TicketValues tv = new TicketValues(time,tcvString.toString());
//		ticketValuesRepository.save(tv);
		return tr;
	}
	
	@RequestMapping(value="/confirmTicket/{email}")
	public long saveTicket(@PathVariable String email, @RequestBody TicketResponse tr ) {
		System.out.println(email);
		System.out.println(tr);
		StringBuilder tcvString = new StringBuilder("");
		for(Integer[] inte: tr.getTicketValues()) {
			for(int i=0;i<5;i++)
			tcvString.append(inte[i]+"#");
		}
		long time = System.currentTimeMillis();
		Ticket t = new Ticket(email,time);
		ticketRepository.save(t);
		TicketValues tv = new TicketValues(time,tcvString.toString());
		ticketValuesRepository.save(tv);
		return time;
	}
	
	@RequestMapping(value="/getgeneratednumbers")
	public List<Numbers> getGeneratedNumbers(){
		try {
			return numbersRepository.findAll();
		}catch(Exception e) {
			
		}
		return null;
	}
//	@RequestMapping(path="/generateNumber")
//	public String generateNumber(Model model) {
//		Set<Integer> generatedNumbers = null;
//		int number = 0;
//		while (true) {
//			 number = getRandomNumber(1,91);
//			if(!generatedNumbers.contains(number)) {
//				generatedNumbers.add(number);
//				break;
//			}
//			number = getRandomNumber(1,91);
//		}
//		System.out.println(generatedNumbers);
//		model.addAttribute("newNumber", number);
//		model.addAttribute("generatedNumbers", generatedNumbers);
//		return "ticket";
//		
//	}
	
	private void populateTicketValues(List<Integer[]> ticketValues, Set<Integer> values) {
		int count = 0;
		ticketValues.add(new Integer[5]);
		ticketValues.add(new Integer[5]);
		ticketValues.add(new Integer[5]);
		for(Integer value:values) {
			if(count<5) {
				ticketValues.get(0)[count]=value;
			}else if(count<10) {
				ticketValues.get(1)[count-5]=value;
			}else if(count<15) {
				ticketValues.get(2)[count-10]=value;
			}
			count++;
		}		
	}

	public Set<Integer> generateTicketValues() {
		Set<Integer> generatedNumbers = new TreeSet<>();
		int[] store = new int [9];
		Arrays.fill(store, 0);
		int min= 1; 
		int limit = 91;
		while(generatedNumbers.size()<15) {
			int number = getRandomNumber(min,limit)  ;
			int position = getPosition(number);
			if(store[position] < 2) {
				store[position] =  store[position]+1;
				generatedNumbers.add(number);
			}
		}
		System.out.println("generatedNumbers size() : "+ generatedNumbers.size());
		return generatedNumbers;
	}

	private int getRandomNumber(int min, int limit) {
		return (int)(Math.random()*(limit-min+1)+min);
	}

	private int getPosition(int number) {
		if(number < 10) {
			return 0;
		}else {
			int temp = Integer.parseInt((number+"").substring(0,1));
			if( number >= 90) {
				temp-=1;
			}
			return temp;
		}
	}
}
