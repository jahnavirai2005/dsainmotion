package com.dsainmotion;

import com.dsainmotion.entity.Feedback;
import com.dsainmotion.entity.StudyVault;
import com.dsainmotion.entity.User;
import com.dsainmotion.repository.FeedbackRepository;
import com.dsainmotion.repository.StudyVaultRepository;
import com.dsainmotion.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class StudyVaultController {

    @Autowired
    private StudyVaultRepository studyVaultRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    // Helper to load user data for header consistency
    private void loadUserData(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            User user = userRepository.getUserById(userId);
            if (user != null) {
                String firstName = user.getFirstName() != null ? user.getFirstName().trim() : "User";
                model.addAttribute("name", firstName);
                model.addAttribute("email", user.getEmail());
                String initials = firstName.isEmpty() ? "U" : String.valueOf(Character.toUpperCase(firstName.charAt(0)));
                model.addAttribute("initials", initials);
            }
        }
    }


    @GetMapping("/study-vault")
    public String studyVault(HttpSession session, Model model) {
        model.addAttribute("activePage", "study-vault");
        model.addAttribute("isLoggedIn", session.getAttribute("userId") != null);
        loadUserData(session, model);
        return "study-vault";
    }

    @GetMapping("/topic/{name}")
    public String viewTopic(@PathVariable("name") String name, HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login?next=topic/" + name;
        }
        loadUserData(session, model);

        // Normalize for main Stack card
        String normalized = name.equalsIgnoreCase("Stack") ? "Stack" : name;
        StudyVault topicData = studyVaultRepository.findById(normalized).orElse(null);

        // If topic doesn't exist in DB yet, we provide a placeholder or handle it
        if (topicData == null) {
            String content = "Content for " + normalized + " is coming soon! Our experts are working on it.";
            if (normalized.equals("Stack")) {
                content = getStackContent();
            }
            topicData = new StudyVault(normalized, content);
        }

        model.addAttribute("topic", normalized);
        model.addAttribute("content", topicData.getContent());
        return "topic";
    }

    @PostMapping("/feedback")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> submitFeedback(
            @RequestParam String topic,
            @RequestParam String message,
            HttpSession session) {
        
        Map<String, Object> response = new HashMap<>();
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            response.put("success", false);
            response.put("message", "User not authenticated");
            return ResponseEntity.status(401).body(response);
        }

        try {
            Feedback feedback = new Feedback();
            feedback.setUserId(userId);
            feedback.setTopic(topic);
            feedback.setMessage(message);
            
            feedbackRepository.save(feedback);
            
            response.put("success", true);
            response.put("message", "Feedback submitted successfully!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error saving feedback: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    private String getStackContent() {
        return """
            <h2>Stack Data Structure</h2>
            <p>A stack is a linear data structure which follows a particular order in which the operations are performed. The order may be LIFO (Last In First Out) or FILO (First In Last Out).</p>

            <h3>Basic Operations</h3>
            <p>Mainly the following three basic operations are performed in the stack:</p>
            <ul>
                <li><strong>Push:</strong> Adds an item in the stack. If the stack is full, then it is said to be an Overflow condition.</li>
                <li><strong>Pop:</strong> Removes an item from the stack. The items are popped in the reversed order in which they are pushed. If the stack is empty, then it is said to be an Underflow condition.</li>
                <li><strong>Peek or Top:</strong> Returns top element of stack.</li>
                <li><strong>isEmpty:</strong> Returns true if stack is empty, else false.</li>
            </ul>

            <h3>Stack Representation</h3>
            <p>The following diagram depicts a stack and its operations:</p>
            <p>A stack can be implemented by means of Array, Structure, Pointer, and Linked List. Stack can either be a fixed size one or it may have a sense of dynamic resizing. Here, we are going to implement stack using arrays, which makes it a fixed size stack implementation.</p>

            <h3>Basic Operations Implementation</h3>
            <p>Let us see how each operation can be implemented on the stack using array data structure.</p>

            <h4>Push Operation</h4>
            <p>The process of putting a new data element onto stack is known as a Push Operation. Push operation involves a series of steps:</p>
            <ul>
                <li>Step 1: Checks if the stack is full.</li>
                <li>Step 2: If the stack is full, produces an error and exit.</li>
                <li>Step 3: If the stack is not full, increments top to point next empty space.</li>
                <li>Step 4: Adds data element to the stack location, where top is pointing.</li>
                <li>Step 5: Returns success.</li>
            </ul>

            <h4>Pop Operation</h4>
            <p>Accessing the content while removing it from the stack, is known as a Pop Operation. In an array implementation of pop() operation, the data element is not actually removed, instead top is decremented to a lower position in the stack to point to the next value. But in linked-list implementation, pop() actually removes data element and deallocates memory space.</p>
            <p>A Pop operation may involve the following steps:</p>
            <ul>
                <li>Step 1: Checks if the stack is empty.</li>
                <li>Step 2: If the stack is empty, produces an error and exit.</li>
                <li>Step 3: If the stack is not empty, accesses the data element at which top is pointing.</li>
                <li>Step 4: Decreases the value of top by 1.</li>
                <li>Step 5: Returns success.</li>
            </ul>

            <h4>Peek Operation</h4>
            <p>Peek operation is also known as an element access operation. It returns the value of the top most element of the stack without deleting it from the stack.</p>

            <h3>Stack Implementation using Arrays</h3>
            <pre><code>class Stack {
    private int maxSize;
    private int[] stackArray;
    private int top;

    public Stack(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack is full");
            return;
        }
        stackArray[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return stackArray[top--];
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }
}</code></pre>

            <h3>Stack Implementation using Linked List</h3>
            <pre><code>class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class Stack {
    private Node top;

    public Stack() {
        this.top = null;
    }

    public void push(int value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        int popped = top.data;
        top = top.next;
        return popped;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return top.data;
    }

    public boolean isEmpty() {
        return (top == null);
    }
}</code></pre>

            <h3>Applications of Stack</h3>
            <ul>
                <li><strong>Expression Evaluation:</strong> Stack is used to evaluate prefix, postfix and infix expressions.</li>
                <li><strong>Expression Conversion:</strong> An expression can be represented in prefix, postfix or infix notation. Stack can be used to convert one form of expression to another.</li>
                <li><strong>Syntax Parsing:</strong> Many compilers use stack for parsing the syntax of expressions, program blocks etc. before translating into low level code.</li>
                <li><strong>Backtracking:</strong> Stack is used in backtracking problems like finding paths in a maze, finding all possible solutions etc.</li>
                <li><strong>Function Calls:</strong> Stack is used to keep track of function calls in a program.</li>
                <li><strong>Memory Management:</strong> Stack is used for memory management in programming languages.</li>
            </ul>
            """;
    }
}
