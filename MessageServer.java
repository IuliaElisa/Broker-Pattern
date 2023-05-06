import MessageMarshaller.Message;

public class MessageServer {
    public Message get_answer(Message msg) {
        System.out.println("Server received " + msg.data + " from " + msg.sender);

        Message answer = new Message("Server", msg.data);
        return answer;
    }
}
