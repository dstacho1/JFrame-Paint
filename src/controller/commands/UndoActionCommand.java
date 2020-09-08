package controller.commands;



public class UndoActionCommand implements ICommand {
    @Override
    public void execute() {
        CommandHistory.undo();
    }
}
