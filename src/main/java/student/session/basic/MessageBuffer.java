package student.session.basic;

import student.session.system.user.User;

import java.util.*;

public class MessageBuffer
{
    static Map<String, Queue<String>> msgMap = new HashMap<>();
    static public Queue getMessage(User user)
    {
        Queue msgQueue = msgMap.get(user.getUserName());
        if(msgQueue==null)
            setMessage(user,"System:No message yet");
        else if(msgQueue.isEmpty())
            setMessage(user,"System:No message yet");
        msgQueue = msgMap.get(user.getUserName());
        Queue temp = new LinkedList(msgMap.get(user.getUserName()));
        msgQueue.clear();
        return temp;
    }
    static public void setMessage(User user, String str)
    {
        Queue msgQueue = msgMap.get(user.getUserName());
        if(msgQueue==null)
            msgMap.put(user.getUserName(),new LinkedList<>());
        msgMap.get(user.getUserName()).add(str);
    }
}
