package whuilan.chapter1_2;

public class SmartDate {
    private final int year;
    private final int month;
    private final int day;
    //int[] bigMonth = {1,3,5,7,8,10,12};
    int[] smallMonth = {4,6,9,11};
    public SmartDate(int y,int m,int d) throws Exception{
        if(y == 0 || y < -1000 || y > 10000){
            throw new Exception("年份错误！");
        }
        if(m<1 || m>12){
            throw new Exception("月份超出范围！");
        }
        if(d<1 || d>31){
            throw new Exception("天数超出范围！");
        }
        /*大月和小月*/
        if(m==4 || m ==6 || m==9 || m==11){
            if(d == 31){throw new Exception("该月只有30天！");}
        }
        if(m == 2){
            if(y%400 == 0 || (y%4 == 0 && y%100 != 0)){
                if(d > 29){ throw new Exception("该年为闰年，2月只有29天！");}
            }else{
                if(d > 28){ throw new Exception("该年为平年，2月只有28天！"); }
            }
        }
        year = y;
        month = m;
        day = d;
    }

    /*解析形如5/22/1998这样的日期*/
    public SmartDate(String date){
        String[] dates = date.split("/");
        month = Integer.parseInt(dates[0]);
        day = Integer.parseInt(dates[1]);
        year = Integer.parseInt(dates[2]);
    }

    public String toString(){
        return  year+"年"+month+"月"+day+"日";
    }

    /*蔡勒（Zeller）公式，是一个计算星期的公式，随便给一个日期，就能用这个公式推算出是星期几,
    *蔡勒公式只适合于1582年（中国明朝万历十年）10月15日之后的情形 */
    public String dayOfWeek(){
        int m=month;
        int y=year;
        if(m==1 || m==2){
            m=m+12;
            y=y-1;
        }
        int week= (int) (y-2000+Math.floor((y-2000)/4)+5-40+Math.floor(26*(m+1)/10)+day-1);
        int weekday=week % 7;
        String[] w={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        return w[weekday];
    }

    public static void main(String[] args){
        try{
            SmartDate date = new SmartDate(2019,4,19);
            System.out.print(date+" ");
            System.out.println(date.dayOfWeek());
        }catch (Exception e){
            System.out.println(e);
        }

    }

}
