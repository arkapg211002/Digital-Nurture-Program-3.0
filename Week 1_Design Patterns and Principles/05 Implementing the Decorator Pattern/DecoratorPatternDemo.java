public class DecoratorPatternDemo {
    public static void main(String[] args) {
        System.out.println();

        // Create a basic email notifier
        Notifier emailNotifier = new EmailNotifier();

        // Wrap it with SMS notifier decorator
        Notifier smsNotifier = new SMSNotifierDecorator(emailNotifier);

        // Wrap it with Slack notifier decorator
        Notifier slackNotifier = new SlackNotifierDecorator(smsNotifier);

        // Send a notification
        slackNotifier.send("Hello, this is a test notification!");
    }
}
