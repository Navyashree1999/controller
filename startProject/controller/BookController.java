package com.example.startProject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.startProject.model.Book;

@RestController
public class BookController {
	private HashMap<Integer,Book> bookhashmap = new  HashMap<Integer,Book>(); 
	private static Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@PostMapping("insertBook")
	public ResponseEntity insertBook(@RequestBody Book book){
		logger.info("Book comming for insertion : {}", book);
		if(bookhashmap.containsKey(book.getId())) 
		{
			logger.error("Book already present");
			return new ResponseEntity(HttpStatus.BAD_REQUEST); 
		}
		bookhashmap.put(book.getId(),book);
		return new ResponseEntity(HttpStatus.CREATED);
		
	}
	@PutMapping("updateBook")
	public Book updateBook(@RequestBody Book book) {
		bookhashmap.put(book.getId(),book);
		return bookhashmap.get(book.getId());
		
	}
	
	@DeleteMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int bookId) {
		bookhashmap.remove( bookId);
		return "Book deleted Sucessfully";
		
	}
	@GetMapping("/book/{bookId}")
	public Book getBookById(@PathVariable("bookId") int bookId) {
		logger.info("bookId Received :{}",bookId);
		return bookhashmap.get(bookId);
	}
	@GetMapping("/books")
	public List<Book> getBooks(){
		return bookhashmap.values().stream()
			.collect(Collectors.toList());
	}
	
	
	
	
	

}
