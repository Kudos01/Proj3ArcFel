

class LinkedList {

    private static int num_elements;
    private Node head;

    // Default constructor
    public LinkedList() {

    }

    // appends the specified element to the end of this list.
    public void add(Object object) {

        // Initialize Node only incase of 1st element
        if (head == null) {
            head = new Node(object);
        }


        Node tmp = new Node(object);
        Node current = head;

        // Let's check for NPE before iterate over crunchifyCurrent
        if (current != null) {

            // starting at the head node, crawl to the end of the list and then add element after last node
            while (current.getNext() != null) {
                current = current.getNext();
            }

            // the last node's "next" reference set to our new node
            current.setNext(current);
        }

        // increment the number of elements variable
        incrementNumElements();
    }


    // inserts the specified element at the specified position in this list
    public void addAtIndex(Object object, int index) {
        Node tmp = new Node(object);
        Node current = head;

        // Let's check for NPE before iterate over crunchifyCurrent
        if (current != null) {
            // crawl to the requested index or the last element in the list, whichever comes first
            for (int i = 0; i < index && current.getNext() != null; i++) {
                current = current.getNext();
            }
        }

        // set the new node's next-node reference to this node's next-node reference
        tmp.setNext(current.getNext());

        // now set this node's next-node reference to the new node
        current.setNext(tmp);

        // increment the number of elements variable
        incrementNumElements();
    }

    public Object get(int index)
    // returns the element at the specified position in this list.
    {
        // index must be 1 or higher
        if (index < 0)
            return null;
        Node current = null;
        if (head != null) {
            current = head.getNext();
            for (int i = 0; i < index; i++) {
                if (current.getNext() == null)
                    return null;
                current = current.getNext();
            }
            return current.getObj();
        }
        return current;

    }

    // removes the element at the specified position in this list.
    public boolean remove(int index) {

        // if the index is out of range, exit
        if (index < 1 || index > size())
            return false;

        Node current = head;
        if (head != null) {
            for (int i = 0; i < index; i++) {
                if (current.getNext() == null)
                    return false;

                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());

            // decrement the number of elements variable
            decrementNumElements();
            return true;

        }
        return false;
    }

    // returns the number of elements in this list.
    public int size() {
        return getNum_elements();
    }

    public String toString() {
        String output = "";

        if (head != null) {
            Node current = head.getNext();
            while (current != null) {
                output += "[" + current.getObj().toString() + "]";
                current = current.getNext();
            }

        }
        return output;
    }

    private class Node {
        // reference to the next node in the chain, or null if there isn't one.
        Node next;

        // data carried by this node. could be of any type you need.
        Object obj;

        // Node constructor
        public Node(Object object) {
            next = null;
            obj = object;
        }

        // another Node constructor if we want to specify the node to point to.
        @SuppressWarnings("unused")
        public Node(Object object, Node nextValue) {
            next = nextValue;
            obj = object;
        }

        // these methods should be self-explanatory
        public Object getObj() {
            return obj;
        }

        @SuppressWarnings("unused")
        public void setData(Object object) {
            obj = object;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node nextValue) {
            next = nextValue;
        }

    }


    private static int getNum_elements() {
        return num_elements;
    }

    private static void incrementNumElements() {
        num_elements++;
    }

    private void decrementNumElements() {
        num_elements--;
    }

}

/*class LinkedList<E> {

    Node head;
    private static int num_items = -1;

    static class Node {
        Object obj;
        Node next;

        //new node constructor
        Node(Object object){
            obj = object;
            next = null;
        }
    }

    public LinkedList<E> add(LinkedList<E> list, Object object) {
        // Create a new node with given data
        Node new_node = new Node(object);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = new_node;

        }
        else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }

            // Insert the new_node at last node
            last.next = new_node;
        }

        num_items++;
        // Return the list by head
        return list;
    }

    // Method to delete a node in the LinkedList by KEY
    public LinkedList<E> deleteByKey(LinkedList<E> list, Object object) {
        // Store head node
        Node currNode = list.head, prev = null;


        if (currNode != null && currNode.obj == object) {
            list.head = currNode.next; // Changed head

            // Display the message
            System.out.println(object + " found and deleted");

            // Return the updated List
            return list;
        }

        while (currNode != null && currNode.obj != object) {
            // If currNode does not hold key
            // continue to next node
            prev = currNode;
            currNode = currNode.next;
        }

        if (currNode != null) {
            // Since the key is at currNode
            // Unlink currNode from linked list
            prev.next = currNode.next;

            // Display the message
            System.out.println(object + " found and deleted");
        }

        if (currNode == null) {
            // Display the message
            System.out.println(object + " not found");
        }

        // return the List
        return list;
    }

    // Method to delete a node in the LinkedList by POSITION
    public LinkedList<E> deleteAtPosition(LinkedList<E> list, int index) {
        // Store head node
        Node currNode = list.head, prev = null;

        //
        // CASE 1:
        // If index is 0, then head node itself is to be deleted

        if (index == 0 && currNode != null) {
            list.head = currNode.next; // Changed head

            // Display the message
            System.out.println(index + " position element deleted");

            // Return the updated List
            return list;
        }

        //
        // CASE 2:
        // If the index is greater than 0 but less than the size of LinkedList
        //
        // The counter
        int counter = 0;

        // Count for the index to be deleted,
        // keep track of the previous node
        // as it is needed to change currNode.next
        while (currNode != null) {

            if (counter == index) {
                // Since the currNode is the required position
                // Unlink currNode from linked list
                prev.next = currNode.next;

                // Display the message
                System.out.println(index + " position element deleted");
                break;
            }
            else {
                // If current position is not the index
                // continue to next node
                prev = currNode;
                currNode = currNode.next;
                counter++;
            }
        }

        // If the position element was found, it should be at currNode
        // Therefore the currNode shall not be null
        //
        // CASE 3: The index is greater than the size of the LinkedList
        //
        // In this case, the currNode should be null
        if (currNode == null) {
            // Display the message
            System.out.println(index + " position element not found");
        }

        // return the List
        return list;
    }

    public void getAllItemsInList(LinkedList<E> list){
        Node node = list.getHead();
        while(node != null){
            node = node.next;
        }
    }

    public Node getHead() {
        return head;
    }
}

 */
