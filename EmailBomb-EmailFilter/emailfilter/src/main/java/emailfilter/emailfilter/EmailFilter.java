package emailfilter.emailfilter;

import emailfilter.Connection;
import emailfilter.filterevidence.AbstractFilter;
import emailfilter.filterevidence.Filter;
import emailfilter.filterevidence.auto.AutoFilter;
import emailfilter.filterevidence.expression.Expression;
import emailfilter.filterevidence.expression.ExpressionFilter;

import javax.mail.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class EmailFilter extends AbstractFilter  {

    private AutoFilter filter1;
    private ExpressionFilter filter2;
    private Store store;

    private List<Message> list;
    private Connection connection;

    public AutoFilter getFilter1() {
        return filter1;
    }

    public void setFilter1(AutoFilter filter1) {
        this.filter1 = filter1;
    }

    public ExpressionFilter getFilter2() {
        return filter2;
    }

    public void setFilter2(ExpressionFilter filter2) {
        this.filter2 = filter2;
    }

    EmailFilter() { }

    public boolean connect(Connection connection){
        this.connection=connection;
        this.store=connection.connect();
        return connection.isConnected();
    }

    public void filtAndClear() {
        if(this.connection!=null&&this.connection.isConnected()){
            //获得邮箱内的邮件夹
            Folder folder = null;
            try {
                folder = store.getFolder("INBOX");
                folder.open(Folder.READ_WRITE);
                Message[] messages = folder.getMessages();
                List<Message> list = new ArrayList<Message>(Arrays.asList(messages));
                HashSet<Message> set=new HashSet<Message>(list);
                List<Message> list1=new ArrayList<Message>(),list2=new ArrayList<Message>();
                if(filter1!=null) list1=filter1.filt(list);
                if(filter2!=null) list2=filter2.filt(list);
                if(list2.size()>0){
                    HashSet<Message> set2=new HashSet<Message>(list2);
                    for(Message message:set){
                        if(list2.contains(message)){
                            if(filter2.getOperation()=='D'){
                                message.setFlag(Flags.Flag.DELETED,true);
                               // message.saveChanges();
                            }//删除
                            else {
                                message.setFlag(Flags.Flag.SEEN,true);
                                //message.saveChanges();
                            }//已读
                        }
                    }
                }
                if(list1.size()>0){
                    HashSet<Message> set1=new HashSet<Message>(list1);
                    for(Message message:set){
                        if(set1.contains(message)) {
                            message.setFlag(Flags.Flag.DELETED,true);
                            //message.saveChanges();
                        }//删除
                    }
                }
                folder.close(true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("尚未连接邮箱服务器");
        }

    }



}
