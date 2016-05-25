/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazeing;

/**
 *
 * @author vic
 */
public class QueueHandler {
    private String action_direction;
    private String action_type;
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
