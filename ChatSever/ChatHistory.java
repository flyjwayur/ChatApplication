package ChatSever1403729;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HyeSoo
 */
public class ChatHistory {

    private static final ChatHistory instance = new ChatHistory();
    private List<ChatEntry> history = new ArrayList<ChatEntry>();
    private ArrayList<HistoryObserver> listObserver = new ArrayList<HistoryObserver>();

    private ChatHistory() {
    }
    
    public static ChatHistory getInstance() {
        return instance;
    }

    //ChatEntry entry = new ChatEntry(); 
    public void addObserver(HistoryObserver a)  {
        this.listObserver.add(a);
    }
    
    public void notifyObservers(ChatEntry e, HistoryObserver observer )    {
        for (HistoryObserver a: this.listObserver)  {
            if (a != observer)  {
                a.newEntry(e);
            }
        }
    }
    
    public void add(ChatEntry entry, HistoryObserver observer) {
        history.add(entry);
        notifyObservers(entry, observer);
        // System.out.println("test:" + entry);
    }
    
    public String getHistory () {
        StringBuilder sb = new StringBuilder();
        for (ChatEntry c : history) {
            sb.append(c.getMessage());
            sb.append("::");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
