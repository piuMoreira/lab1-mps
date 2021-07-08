package business.control.memento;

import business.control.validation.exceptions.UnableToUndoException;
import business.model.User;

import java.util.Stack;
import java.util.EmptyStackException;

public class UserCaretaker {
    private Stack<Memento> mementoStack = new Stack<>();
    private User user;

    public UserCaretaker(User user) {
        this.user = user;
    }

    public void backup() {
        this.mementoStack.push(this.user.save());
    }

    public void undo() throws UnableToUndoException {
        try {
            Memento poppedMemento = mementoStack.pop();

            this.user.restore(poppedMemento);
        } catch (EmptyStackException e) {
            throw new UnableToUndoException("Não foi possível desfazer a ação.");
        }
    }
}
