import java.util.Scanner;

// Define the Task class with attributes
class Task {
    int taskId;
    String taskName;
    String status;

    // Constructor
    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskId: " + taskId + ", TaskName: " + taskName + ", Status: " + status;
    }
}

// Node class for the linked list
class Node {
    Task task;
    Node next;

    // Constructor
    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

// SinglyLinkedList class to manage tasks
class SinglyLinkedList {
    private Node head;

    // Constructor
    public SinglyLinkedList() {
        head = null;
    }

    // Method to add a task to the list
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // Method to search for a task by taskId
    public Task searchTask(int taskId) {
        Node temp = head;
        while (temp != null) {
            if (temp.task.taskId == taskId) {
                return temp.task;
            }
            temp = temp.next;
        }
        return null; // Task not found
    }

    // Method to traverse and print the list
    public void traverse() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.task);
            temp = temp.next;
        }
    }

    // Method to delete a task by taskId
    public void deleteTask(int taskId) {
        if (head == null) {
            return; // List is empty
        }

        if (head.task.taskId == taskId) {
            head = head.next; // Remove the head
            return;
        }

        Node temp = head;
        while (temp.next != null && temp.next.task.taskId != taskId) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next; // Remove the node
        }
    }
}

// Test the implementation
public class TaskManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        SinglyLinkedList taskList = new SinglyLinkedList();

        System.out.println();
        // Adding tasks based on user input
        System.out.println("Enter the number of tasks you want to add:");
        int numberOfTasks = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println("Enter task ID:");
            int taskId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            System.out.println("Enter task name:");
            String taskName = scanner.nextLine();

            System.out.println("Enter task status (Incomplete/Complete):");
            String status = scanner.nextLine();

            taskList.addTask(new Task(taskId, taskName, status));
        }

        // Traverse the list
        System.out.println("\nTasks in the list:");
        taskList.traverse();

        // Search for a task
        System.out.println("\nEnter task ID to search:");
        int searchId = scanner.nextInt();
        Task foundTask = taskList.searchTask(searchId);
        if (foundTask != null) {
            System.out.println("Found Task: " + foundTask);
        } else {
            System.out.println("Task not found.");
        }

        // Delete a task
        System.out.println("\nEnter task ID to delete:");
        int deleteId = scanner.nextInt();
        taskList.deleteTask(deleteId);

        // Traverse the list again
        System.out.println("\nTasks in the list after deletion:");
        taskList.traverse();

        scanner.close();
    }
}
