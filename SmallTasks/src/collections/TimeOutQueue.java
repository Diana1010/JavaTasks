package collections;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedHashMap;

public class TimeOutQueue<T> {

	private static final int COUNT = 2;
	private  static int size = 0;
	
	Deque<T> queue = new ArrayDeque<T>(); 	
	LinkedHashMap<T, Long[]> map = new LinkedHashMap<T, Long[]>(); 
	
	
		
	public void offer (T t, long millis)
	{
		Date date = new Date();
		queue.addLast(t);
		size++;
		Long [] arrayTime  = new Long[COUNT];
		arrayTime[0]= millis;
		arrayTime[1] = date.getTime();
		map.put(t, arrayTime);
	}
	
	public T poll () {
		T el  ;
		while  (queue.size() > 0) {
			 el = queue.peek() ;
			if (! (isAlive(el, map.get(el)))) {				
				map.remove(el);
				size--;
				queue.pollFirst();				 
			}
			else break;
		}
		
		if (queue.peek() != null)
		{
			el = queue.pollFirst();
			size--;
			map.remove(el);
			return 	el;
		}
		else return null;

	}
	
	private boolean isAlive (T el , Long[] mas) {
		boolean flag = false;
		Date date = new Date();
		long timeNow = date.getTime();
		if ( (timeNow - mas[1]) < mas[0])
		{
			flag = true;
		}
			
		return flag;
	}
	
	
	
}
