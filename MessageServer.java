import MessageMarshaller.Message;

public class MessageServer {
    public Message get_answer(Message msg, String sender, String receiver) {

        System.out.println(receiver + " received " + msg.data + " from " + msg.sender);

        Message answer = new Message(sender, msg.data);
        return answer;
    }
}
