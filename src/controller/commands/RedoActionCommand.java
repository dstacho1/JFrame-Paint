package controller.commands;

public class RedoActionCommand implements ICommand {
    @Override
    public void execute() {
        CommandHistory.redo();
        System.out.println("RedoActionCommand triggered");
    }
}
