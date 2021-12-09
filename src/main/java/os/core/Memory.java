package os.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import os.util.Fat;
import os.util.InMemory;
import os.util.Pair;
import os.util.ResultPage;

import javax.annotation.Resource;
import java.util.HashMap;

@Component
public class Memory{
    //地址映射
    public HashMap<Pair<String,Integer>,Integer> mp=new HashMap<>();
    public HashMap<Integer,Pair<String,Integer>> mmp=new HashMap<>();
    public int idx=-1;
    //内存空间及下标
    public Pair<String,Integer>[] qu=new Pair[100005];
    public int qian=1,hou=0;
    public int MAXX=64;

    @Autowired
    public Directory dir;

    public void fro(int flag,String name,int i){
        Pair<String,Integer> tmp=new Pair<>();
        tmp.setKey(name);tmp.setValue(i);
        if(flag==0){
            if(hou-qian+1==MAXX){
                mp.put(tmp,mp.get(qu[qian]));
                mmp.put(mp.get(qu[qian]),tmp);
                qian++;
            }
            else{
                for(int ii=0;ii<64;ii++){
                    if(mmp.get(ii)==null){idx=ii;break;}
                }
                mp.put(tmp,idx);mmp.put(idx,tmp);
            }
            qu[++hou]=tmp;
        }
    }
    public void LRU(String name,int i){
        int flag=0;
        Pair<String,Integer> tmp=new Pair<>();
        tmp.setValue(i);tmp.setKey(name);
        for(int j=qian;j<=hou;j++){
            if(qu[j].equals(tmp)){
                for(int k=j+1;k<=hou;k++) qu[k-1]=qu[k];
                qu[hou]=tmp;flag=hou;break;
            }
        }
        fro(flag,tmp.getKey(),tmp.getValue());
    }
    public void fileC(String name){
        Fat fat=dir.getFat(name);
        for(int i=1;i<=fat.mem;i++){
            synchronized (this) {
                LRU(name, i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        free(name);
    }
    public void free(String name){
        Pair<String,Integer> tmp=new Pair<>();
        tmp.setKey(name);
        for(int i=1;i<=dir.mp.get(name).mem;i++){
            tmp.setValue(i);
            mmp.remove(mp.get(tmp));
            mp.remove(tmp);
            for(int j=hou;j>=qian;j--){
                if(qu[j].equals(tmp)){
                    for(int k=j+1;k<=hou;k++) qu[k-1]=qu[k];
                    hou--;break;
                }
            }
        }
    }
    public HashMap<Integer,String> getAns(){
        HashMap<Integer, String> ans = new HashMap<>();
        for (int i = 0; i < 64; i++) {
            if(mmp.get(i)==null) ans.put(i,"未占用");
            else ans.put(i,mmp.get(i).getKey()+"第"+mmp.get(i).getValue()+"块");
        }
        return ans;
    }
    public InMemory getInMemory(){
        InMemory inMemory = new InMemory();
        inMemory.inMemory = getAns();
        return inMemory;
    }

    public ResultPage getResultPage(){
        ResultPage resultPage=new ResultPage();
        resultPage.direct = dir.getDir();
        resultPage.inMemory = this.getInMemory();
        resultPage.outMemory = dir.allMemo.getOutMemory();
        return resultPage;
    }

    public void refresh(){
        this.dir.fresh();
        mp=new HashMap<>();mmp=new HashMap<>();idx=-1;
        qu=new Pair[100005];qian=1;hou=0;
    }
}