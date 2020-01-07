package nju.software.pattern.delegate_pattern.simple;

public class Boss {
    public void command(String command,Leader leader){
        leader.doing(command);
    }
}
