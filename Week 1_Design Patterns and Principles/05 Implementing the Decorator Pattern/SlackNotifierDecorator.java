
public class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier decoratedNotifier) {
        super(decoratedNotifier);
    }

    @Override
    public void send(String message) {
        super.send(message); // Send email first
        sendSlackMessage(message); // Then send Slack message
    }

    private void sendSlackMessage(String message) {
        System.out.println("Sending Slack message with message: " + message);
    }
}