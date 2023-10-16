package com.example.demo.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Expense;
import com.example.demo.Service.ExpenseService;

@RestController
@RequestMapping("/expenses")
public class expenseController {
    @Autowired
    private ExpenseService expenseService;

    // @PostMapping
    // public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
    //     Expense savedExpense = expenseService.saveExpense(expense);
    //     return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    // }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @PostMapping("/upload")  // Specify the URL path for this endpoint
    public ResponseEntity<Object> createExpense(
        @RequestParam("category") String category,
        @RequestParam("amount") double amount,
        @RequestParam("pdf") MultipartFile pdf
    ) {
        try {

            if (pdf.isEmpty() || !pdf.getContentType().equals("application/pdf")) {
                return new ResponseEntity<>("Invalid or missing PDF file", HttpStatus.BAD_REQUEST);
            }
            // Extract text from the uploaded PDF and set it in the entity
            String pdfText = expenseService.extractTextFromPDF(pdf.getInputStream());

            // Create an Expense entity and set its attributes
            Expense expense = new Expense();
            expense.setCategory(category);
            expense.setAmount(amount);
            expense.setPdfData(pdf.getBytes());  // Save the binary PDF data in the entity

            // Save the expense and return a response
            Expense savedExpense = expenseService.saveExpense(expense);
            return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                "Failed to process the PDF.",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    // @PostMapping("/upload")
    // public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    // // Process and save the file here
    //     return ResponseEntity.ok("File uploaded successfully");
    // }

    
}
