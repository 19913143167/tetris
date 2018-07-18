package zhh.game.tetris.global;

import java.lang.reflect.Array;

/**
 * 公用工具类
 * @author zhaohuihua
 */
public class Utilities {
	private Utilities() {
	}

	/**
	 * 向数组增加一个项<br>
	 * 增加至末尾<br>
	 * @param array Object[] 数组
	 * @param item Object 待增加的项
	 * @return Object[] 增加项后的数组
	 */
	public static Object[] arrayAddItem(Object[] array, Object item) {
		return arrayAddItem(array, item, false);
	}
	
	/**
	 * 向数组增加一个项<br>
	 * @param array Object[] 数组
	 * @param item Object 待增加的项
	 * @param first boolean 是否增加至首位<br>
	 *         true: 增加至首位<br>
	 *         false: 增加至末尾<br>
	 * @return Object[] 增加项后的数组
	 */
    public static Object[] arrayAddItem(Object[] array, Object item, boolean first) {
    	if(array == null)
    		return null;
    	int length = array.length;
    	Class clazz = array.getClass().getComponentType();
    	Object[] result = (Object[])Array.newInstance(clazz, length + 1);
    	if(length != 0)
    		System.arraycopy(array, 0, result, first ? 1 : 0, length);
    	Array.set(result, first ? 0 : length, item);
    	return result;
    }

    /**
     * 从数组删除一个项
     * @param array Object[] 数组
     * @param item Object 待删除的项
     * @return Object[] 删除项后的数组
     */
    public static Object[] arrayRemoveItem(Object[] array, Object item) {
    	int length = array == null ? 0 : array.length;
    	if(length == 0) return array;
    	int count = 0;
    	Class clazz = array.getClass().getComponentType();
    	Object[] temp = (Object[])Array.newInstance(clazz, length);
    	for(int i = 0; i < length; i++) {
    		if(array[i] != item) {
    			temp[count] = array[i];
    			count++;
    		}
    	}
    	if(count == length)
    		return array;
    	Object[] result = (Object[])Array.newInstance(clazz, count);
    	System.arraycopy(temp, 0, result, 0, count);
    	array = result;
    	return result;
    }
}
