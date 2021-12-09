package os.util;

import java.util.HashMap;

public class Fat{
    public Fat(){

    }

    public Fat(int flag, int mem, int address ,String name, String content, String path, String user, String time) {
        this.name=name;
        this.flag = flag;
        this.mem = mem;
        this.address = address;
        this.content = content;
        this.path = path;
        this.user = user;
        this.time = time;
    }
//    public HashMap<Integer,Integer> mp;
    public int flag,mem,address;
    public String name,content,path,user,time;

    @Override
    public String toString() {
        return "\n信息{" +
                "mem=" + mem +
                ",address=" + address +
                ",\ncontent='" + content + '\'' +
                ",user='" + user + '\'' +
                ",time='" + time + '\'' +
                '}';
    }
}