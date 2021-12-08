package os.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import os.util.Direct;
import os.util.Fat;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class Directory{

    public HashMap<String, ArrayList<String>> vt = new HashMap<>();
    public String fir = "root";
    public String curr = "root";
    public HashMap<String, Fat> mp = new HashMap<>();

    @Autowired
    public Malloc allMemo;

    public Directory(){
        mp.put("root", new Fat());
        mp.get("root").path = "root";
    }

    public void fresh(){
        this.allMemo.fresh();
        vt = new HashMap<>();
        mp = new HashMap<>();
    }

    public void mkdir(String name){
        vt.computeIfAbsent(curr, k -> new ArrayList<>());
        if(!vt.get(curr).contains(name)) vt.get(curr).add(name);
    }

    public void change(String path){
        StringBuilder s= new StringBuilder();
        for(int i=0;i<path.length();i++){
            if(path.charAt(i)!='/') s.append(path.charAt(i));
            else{
                if(!"root".equals(s.toString())){
                    mkdir(s.toString());
                    curr=s.toString();
                }
                s=new StringBuilder();
            }
        }
    }

    public void create(int flag0,String name, Fat fat) {
        change(fat.path);
        vt.computeIfAbsent(curr, k -> new ArrayList<>());
        vt.get(curr).add(name);
        mp.put(name, fat);
        allMemo.first(flag0, name, mp.get(name));
        curr=fir;
    }

    public void rm(int flag0,String name,Fat fat){
        change(fat.path);
        vt.get(curr).remove(name);
        allMemo.meg(flag0,name,fat);
        mp.remove(name);
        curr=fir;
    }
    public Fat getFat(String name){
        return mp.get(name);
    }

    public String getDfs(StringBuffer ans,String root,String pre){
        if(vt.get(root)==null) return ans.toString();
        for(String pi:vt.get(root)){
            ans.append("\n"+pre+pi);
            getDfs(ans,pi,pre+"   ");
        }
        return ans.toString();
    }
    public Direct getDir(){
        Direct direct = new Direct();
        StringBuffer ans=new StringBuffer("");
        direct.direct=getDfs(ans,"root","");
        return direct;
    }
}