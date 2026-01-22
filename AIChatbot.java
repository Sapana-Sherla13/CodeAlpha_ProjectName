import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class AIChatbot extends JFrame implements ActionListener {

    JTextArea chatArea;
    JTextField userInput;
    JButton sendButton;

    HashMap<String, String> responses = new HashMap<>();

    public AIChatbot() {

        // Train chatbot (FAQs)
        responses.put("hello", "Hello! How can I help you?");
        responses.put("hi", "Hi there! What can I do for you?");
        responses.put("how are you", "I'm doing great! Thanks for asking.");
        responses.put("what is java", "Java is an object-oriented programming language.");
        responses.put("what is ai", "Artificial Intelligence allows machines to think and learn.");
        responses.put("bye", "Goodbye! Have a great day");

        setTitle("AI Chatbot");
        setSize(400, 500);,
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));

        userInput = new JTextField();
        sendButton = new JButton("Send");

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(userInput, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(this);
        userInput.addActionListener(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // NLP Processing
    String processInput(String input) {
        input = input.toLowerCase().replaceAll("[^a-z ]", "").trim();

        for (String key : responses.keySet()) {
            if (input.contains(key)) {
                return responses.get(key);
            }
        }
        return "Sorry, I didn't understand that. Can you rephrase?";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = userInput.getText();
        chatArea.append("You: " + input + "\n");

        String reply = processInput(input);
        chatArea.append("Bot: " + reply + "\n\n");

        userInput.setText("");
    }

    public static void main(String[] args) {
        new AIChatbot();
    }
}
