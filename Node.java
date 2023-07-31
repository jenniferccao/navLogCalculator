public class Node {
    public Leg data;
    public Node next;

    public Node (Leg data) {
        this.data = data;
        this.next = null;
    }

    public void setNext (Node newNode) {
        this.next = newNode;
    }

    public void setData(Leg newData) {
        this.data = newData;
    }

    public Leg getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

}
