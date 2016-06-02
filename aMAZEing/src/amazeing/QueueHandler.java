package amazeing;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class QueueHandler {
    private String action_direction;
    private String action_type;
    
    //Constructor
    public QueueHandler(String action_direction, String action_type) {
        this.action_direction = action_direction;
        this.action_type = action_type;
    }
    
    public String getDirection() {
        return action_direction;
    }
    public String getType() {
        return action_type;
    }
}
