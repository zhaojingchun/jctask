package com.jc.model.command;

import com.jc.common.LogHelper;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-5-15
 * Time: 下午4:09
 * To change this template use File | Settings | File Templates.
 */

/**
 * 命令模式的优缺点
 * 首先，命令模式的封装性很好：每个命令都被封装起来，对于客户端来说，需要什么功能就去调用相应的命令，
 * 而无需知道命令具体是怎么执行的。比如有一组文件操作的命令：新建文件、复制文件、删除文件。如果把这三
 * 个操作都封装成一个命令类，客户端只需要知道有这三个命令类即可，至于命令类中封装好的逻辑，客户端则无需知道。
 * 其次，命令模式的扩展性很好，在命令模式中，在接收者类中一般会对操作进行最基本的封装，命令类则通过对这些
 * 基本的操作进行二次封装，当增加新命令的时候，对命令类的编写一般不是从零开始的，有大量的接收者类可供调用，
 * 也有大量的命令类可供调用，代码的复用性很好。比如，文件的操作中，我们需要增加一个剪切文件的命令，则只需
 * 要把复制文件和删除文件这两个命令组合一下就行了，非常方便。
 * 最后说一下命令模式的缺点，那就是命令如果很多，开发起来就要头疼了。特别是很多简单的命令，实现起来就几行
 * 代码的事，而使用命令模式的话，不用管命令多简单，都需要写一个命令类来封装。
 */
public class Client {
    public static void main(String[] args){
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        //客户端直接执行具体命令方式（此方式与类图相符）
        command.execute();

        //客户端通过调用者来执行命令
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.action();
    }
}
abstract class Command{
    abstract public void execute();
}
//命令
class ConcreteCommand extends Command{
    private Receiver receiver;
    public ConcreteCommand(Receiver receiver){
        this.receiver = receiver;
    }
    public void execute() {
        LogHelper.log.info("ConcreteCommand.execute");
        receiver.action();
    }
}
//调用者
class Invoker{
    private Command command;

    Command getCommand() {
        return command;
    }

    void setCommand(Command command) {
        this.command = command;
    }
    public void action(){
        LogHelper.log.info("Invoker.action");
        command.execute();
    }
}
//接受者
class Receiver{
    public void action(){
        LogHelper.log.info("Receiver.action");
    }
}