package os.core;

import org.springframework.stereotype.Component;
import os.util.Fat;
import os.util.Node;
import os.util.OutMemory;


import java.util.TreeMap;

@Component
public class Malloc{
    public Node Emp[]=new Node[10005];
    public Node hea=new Node();
    public Node swa=new Node();
    public Malloc(){
        hea.next=new Node();hea.next.mem=900;
        swa.next=new Node();swa.next.mem=124;
    }

    public void first(int flag0, String name, Fat fat){
        Node head=flag0==1?hea:swa;int ans=0;
        for(Node root = head; root.next!=null; root=root.next){
            ans+=root.mem;
            if(root.next.mem>=fat.mem&&root.next.name==null){
                Node neww=new Node();neww.name=name;
                neww.mem=fat.mem;root.next.mem-=fat.mem;
                fat.address=ans;
                neww.next=root.next;root.next=neww;
                break;
            }
        }
    }

    public void meg(int flag0,String name,Fat fat){
        Node head=flag0==1?hea:swa;
        for(Node root = head; root.next!=null; root=root.next){
            if(root.next.name!=null&&root.next.name.equals(name)){
                fat.address=-1;
                root.next.name=null;break;
            }
        }
        for(Node root = head; root.next!=null&&root.next.next!=null; root=root.next){
            if(root.next.name==null&&root.next.next.name==null){
                root.next.next.mem+=root.next.mem;
                root.next=root.next.next;
            }
        }
    }
    public TreeMap<Integer,String> getAns(){
        TreeMap<Integer, String> ans = new TreeMap<>();
        int address=0;
        for(Node root = hea; root.next!=null; root=root.next){
            address+=root.mem;
            ans.put(address,root.next.name);
        }
        return ans;
    }
    public OutMemory getOutMemory(){
        OutMemory outMemory=new OutMemory();
        outMemory.outMemory=getAns();
        return outMemory;
    }

    public void fresh(){
        hea.next=new Node();hea.next.mem=900;
        swa.next=new Node();swa.next.mem=124;
    }
}