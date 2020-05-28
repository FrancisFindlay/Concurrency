package concurrency;

import java.util.ArrayList;
import java.util.concurrent.*;

//任务在完成时可以返回值，则使用Callable接口，他的参数表示从call（）方法返回的值的类型；
//必须使用ExecutorService.submit（）调用他;
class TaskWithResult implements Callable<String>{
    private int id;
    public TaskWithResult(int id){
        this.id=id;
    }
    public String call(){
        return "result of TaskWithResult" + id;
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec=Executors.newCachedThreadPool();
        ArrayList<Future<String>> results=new ArrayList<>();
        for(int i=0;i<10;i++){
            results.add(exec.submit(new TaskWithResult(i)));
        }
        //submit()产生future对象,他用Callable的返回结果进行了参数化；
        //可以调用get（）来察看结果
        for(Future<String> fs:results){
            try{
                System.out.println(fs.get());
            }catch (InterruptedException e){
                System.out.println(e);
                return ;
            }catch (ExecutionException e){
                System.out.println(e);
                return ;
            }finally{
                exec.shutdown();
            }
        }
    }

}
