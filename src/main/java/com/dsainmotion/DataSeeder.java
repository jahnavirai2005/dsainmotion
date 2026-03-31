package com.dsainmotion;

import com.dsainmotion.entity.StudyVault;
import com.dsainmotion.repository.StudyVaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private StudyVaultRepository studyVaultRepository;

    @Override
    public void run(String... args) throws Exception {
        seedStudyVault();
    }

    private void seedStudyVault() {
        // --- ARRAYS ---
        saveTopic("Array", 
            "<div class='theory-section'><h2>Arrays: The Foundation</h2>" +
            "<p>An array is a linear data structure consisting of a collection of elements, each identified by at least one array index or key.</p>" +
            "<div style='text-align:center; margin:30px 0;'><svg width='400' height='100' viewBox='0 0 400 100'><rect x='10' y='20' width='60' height='40' rx='8' fill='rgba(99,102,241,0.2)' stroke='#6366f1' stroke-width='2'/><rect x='70' y='20' width='60' height='40' rx='8' fill='rgba(99,102,241,0.2)' stroke='#6366f1' stroke-width='2'/><rect x='130' y='20' width='60' height='40' rx='8' fill='rgba(99,102,241,0.2)' stroke='#6366f1' stroke-width='2'/><rect x='190' y='20' width='60' height='40' rx='8' fill='rgba(99,102,241,0.2)' stroke='#6366f1' stroke-width='2'/><text x='40' y='80' fill='#94a3b8' font-size='12' text-anchor='middle'>Idx 0</text><text x='100' y='80' fill='#94a3b8' font-size='12' text-anchor='middle'>Idx 1</text><text x='160' y='80' fill='#94a3b8' font-size='12' text-anchor='middle'>Idx 2</text><text x='220' y='80' fill='#94a3b8' font-size='12' text-anchor='middle'>Idx 3</text></svg></div>" +
            "<div class='key-point'><strong>Random Access</strong>Elements can be accessed directly using their index in constant time O(1).</div>" +
            "<table class='complexity-table'><tr><th>Access</th><th>Search</th><th>Insertion</th><th>Deletion</th></tr><tr><td>O(1)</td><td>O(n)</td><td>O(n)</td><td>O(n)</td></tr></table></div>");

        saveTopic("1D Array", "<div class='theory-section'><h2>1D Array</h2><p>A single-dimensional array is a list of variables of the same type.</p><div class='code-snippet'>int[] numbers = {1, 2, 3, 4, 5};</div></div>");
        saveTopic("2D Array", "<div class='theory-section'><h2>2D Array</h2><p>A 2D array is essentially an array of arrays, representing a grid or matrix.</p><div class='code-snippet'>int[][] matrix = new int[3][3];</div></div>");
        saveTopic("Multi-dimensional Array", "<div class='theory-section'><h2>Multi-dimensional Array</h2><p>Structures with 3 or more dimensions, used for complex data modeling.</p></div>");

        // --- LINKED LISTS ---
        saveTopic("Linked List", 
            "<div class='theory-section'><h2>Linked List</h2><p>A dynamic data structure where nodes are linked using pointers.</p>" +
            "<div style='text-align:center; margin:30px 0;'><svg width='400' height='100' viewBox='0 0 400 100'><rect x='10' y='20' width='60' height='40' rx='8' fill='rgba(168,85,247,0.1)' stroke='#a855f7' stroke-width='2'/><path d='M70 40 L100 40' stroke='#a855f7' stroke-width='2' marker-end='url(#arrowhead)'/><rect x='110' y='20' width='60' height='40' rx='8' fill='rgba(168,85,247,0.1)' stroke='#a855f7' stroke-width='2'/><defs><marker id='arrowhead' markerWidth='10' markerHeight='7' refX='0' refY='3.5' orient='auto'><polygon points='0 0, 10 3.5, 0 7' fill='#a855f7'/></marker></defs></svg></div>" +
            "<div class='key-point'><strong>Dynamic Memory</strong>Linked lists grow and shrink at runtime without costly re-allocations.</div></div>");

        saveTopic("Singly Linked List", "<div class='theory-section'><h2>Singly Linked List</h2><p>Each node points forward to the next node.</p></div>");
        saveTopic("Doubly Linked List", "<div class='theory-section'><h2>Doubly Linked List</h2><p>Nodes have 'next' and 'prev' pointers for bidirectional navigation.</p></div>");
        saveTopic("Circular Linked List", "<div class='theory-section'><h2>Circular Linked List</h2><p>The tail node connects back to the head node.</p></div>");

        // --- STACKS ---
        saveTopic("Stack (LIFO)", 
            "<div class='theory-section'><h2>Stack</h2><p>A Last-In, First-Out (LIFO) structure.</p>" +
            "<div style='text-align:center; margin:30px 0;'><svg width='200' height='200' viewBox='0 0 200 200'><rect x='60' y='140' width='80' height='30' rx='5' fill='rgba(236,72,153,0.2)' stroke='#ec4899' stroke-width='2'/><rect x='60' y='100' width='80' height='30' rx='5' fill='rgba(236,72,153,0.2)' stroke='#ec4899' stroke-width='2'/><path d='M100 30 L100 80' stroke='#ec4899' stroke-width='2' stroke-dasharray='4' marker-end='url(#arrowhead-pink)'/><defs><marker id='arrowhead-pink' markerWidth='10' markerHeight='7' refX='0' refY='3.5' orient='auto'><polygon points='0 0, 10 3.5, 0 7' fill='#ec4899'/></marker></defs></svg></div>" +
            "</div>");

        saveTopic("Simple Stack", "<div class='theory-section'><h2>Simple Stack</h2><p>Fixed size array implementation.</p></div>");
        saveTopic("Dynamic Stack", "<div class='theory-section'><h2>Dynamic Stack</h2><p>Resizes itself when full.</p></div>");
        saveTopic("Expression Stack", "<div class='theory-section'><h2>Expression Stack</h2><p>Evaluation of Infix to Postfix.</p></div>");

        // --- QUEUES ---
        saveTopic("Simple Queue", "<div class='theory-section'><h2>Simple Queue</h2><p>Basic FIFO logic.</p></div>");
        saveTopic("Circular Queue", "<div class='theory-section'><h2>Circular Queue</h2><p>Connects end to start to save space.</p></div>");
        saveTopic("Priority Queue", "<div class='theory-section'><h2>Priority Queue</h2><p>Elements processed by priority level.</p></div>");
        saveTopic("Deque (Double Ended Queue)", "<div class='theory-section'><h2>Deque</h2><p>Insertion/Deletion at both ends.</p></div>");

        // --- TREES ---
        saveTopic("Binary Tree", 
            "<div class='theory-section'><h2>Binary Tree</h2><p>Each node has at most two children.</p>" +
            "<div style='text-align:center; margin:30px 0;'><svg width='200' height='150' viewBox='0 0 200 150'><circle cx='100' cy='30' r='20' fill='rgba(255,255,255,0.05)' stroke='#6366f1' stroke-width='2'/><line x1='85' y1='45' x2='60' y2='80' stroke='#6366f1' stroke-width='2'/><line x1='115' y1='45' x2='140' y2='80' stroke='#6366f1' stroke-width='2'/><circle cx='50' cy='100' r='20' fill='rgba(255,255,255,0.05)' stroke='#6366f1' stroke-width='2'/><circle cx='150' cy='100' r='20' fill='rgba(255,255,255,0.05)' stroke='#6366f1' stroke-width='2'/></svg></div>" +
            "</div>");

        saveTopic("Binary Search Tree (BST)", "<div class='theory-section'><h2>BST</h2><p>Search-optimized tree where Left < Root < Right.</p></div>");
        saveTopic("AVL Tree (Self-balancing)", "<div class='theory-section'><h2>AVL Tree</h2><p>Self-balances via rotations.</p></div>");
        saveTopic("Heap (Min Heap, Max Heap)", "<div class='theory-section'><h2>Heap</h2><p>Max/Min stored at root.</p></div>");
        saveTopic("B-Tree", "<div class='theory-section'><h2>B-Tree</h2><p>Multi-way search tree for large files.</p></div>");
        saveTopic("B+ Tree", "<div class='theory-section'><h2>B+ Tree</h2><p>Optimized for range queries in DBs.</p></div>");
        saveTopic("Red-Black Tree", "<div class='theory-section'><h2>Red-Black Tree</h2><p>Guarantees O(log n) performance using coloring.</p></div>");
        saveTopic("Traversals", "<div class='theory-section'><h2>Traversals</h2><p>In-order, Pre-order, Post-order. Used to visit each node exactly once.</p></div>");
            
        System.out.println("Study Vault Re-Seeded with Premium Content!");
    }

    private void saveTopic(String topic, String content) {
        studyVaultRepository.save(new StudyVault(topic, content));
    }
}
