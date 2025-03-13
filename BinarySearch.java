//Name: Rafael Figueora
//Date: 3/12/2025
//Purpose: This program will take in user inputs and store them as names in a binary search tree. The program will then print the names in pre-order, in-order, and post-order.abstract 
//Assignment: Binary Search Tree.
//Partners: Ben and Kenndi
//Extra Credit: I made the program iterative instead of recursive. Making me incorporate stacks to the program. Also the user decides what the data is and how much data is in the binary search tree. 




import java.util.Scanner;
import java.util.Stack;
public class BinarySearch {
    
    private Node root;

    //Iterative insertion of a new name into the Binary Search Tree to ignore potential duplicates. 
    public void insert(String name) {
        // If the tree is empty, the new node becomes the root.
        if (root == null) {
            root = new Node(name);
            return;
        }

        Node current = root;
        while (true) {
            int cmp = name.compareTo(current.name);

            // Go to the left subtree if new name is "less"
            if (cmp < 0) {
                if (current.left == null) {
                    current.left = new Node(name);
                    break;  // insertion done
                } else {
                    current = current.left;
                }
            }
            // Go to the right subtree if new name is "greater"
            else if (cmp > 0) {
                if (current.right == null) {
                    current.right = new Node(name);
                    break;  // insertion done
                } else {
                    current = current.right;
                }
            }
            else {
                // cmp == 0 means it's a duplicate. We'll ignore duplicates.
                break;
            }
        }
    }

    
    //Iterative Pre-order Traversal (Root -> Left -> Right).
     
    public void printPreOrderIterative() {
        System.out.print("Pre-order:  ");
        if (root == null) {
            System.out.println();
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.name + " ");

            // Push right child first so left child is processed first
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println();
    }

    /**
     * Iterative In-order Traversal (Left -> Root -> Right).
     * This will print the names in alphabetical order.
     */
    public void printInOrderIterative() {
        System.out.print("In-order:   ");
        Stack<Node> stack = new Stack<>();
        Node current = root;

        // Continue as long as we have nodes to visit
        while (current != null || !stack.isEmpty()) {
            // Go left as far as possible
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            // Pop the top node and visit it
            current = stack.pop();
            System.out.print(current.name + " ");
            // Then move to the right subtree
            current = current.right;
        }
        System.out.println();
    }

    /**
     * Iterative Post-order Traversal (Left -> Right -> Root).
     * Using two stacks for simplicity.
     */
    public void printPostOrderIterative() {
        System.out.print("Post-order: ");
        if (root == null) {
            System.out.println();
            return;
        }

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);

        // stack1 is used to traverse nodes, stack2 will collect them in reverse order
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            stack2.push(node);

            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }

        // Now pop everything from stack2 (this is post-order)
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().name + " ");
        }
        System.out.println();
    }

    // ------------------------------------------------------------------------
    // Demonstration in main()

    public static void main(String[] args) {
        BinarySearch NameTree = new BinarySearch();
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to add names
        System.out.println("Enter names to insert into the BST (type 'done' to finish):");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            if (!input.isEmpty()) {
                NameTree.insert(input);
            }
        }

        // Print NameTree in Pre-order, In-order, and Post-order (all iteratively)
        NameTree.printPreOrderIterative();
        NameTree.printInOrderIterative();
        NameTree.printPostOrderIterative();

        scanner.close();
    }
}
























































