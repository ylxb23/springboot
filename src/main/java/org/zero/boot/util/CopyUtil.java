package org.zero.boot.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.springframework.util.FastByteArrayOutputStream;

/**
 * 对象克隆工具
 * @date 2017年11月6日 上午10:44:35
 * @author zero
 */
public class CopyUtil {
	
	/**
	 * 深度复制
	 * attention:
	 * 1.这种深度复制方法对象需实现Serializable接口
	 * 2.ByteArrayOutput/InputStream,在write/readObject时需要通过序列化/反系列化对象，性能上可能不是很好
	 * @param source
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T clone(T source) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(source);
			oos.flush();
			oos.close();
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			baos.close();
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (T)ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	/**
	 * 深度复制
	 * 借用springframework中的FastByteArrayOutputStream提升序列化的效率
	 * @param source
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T clonePro(T source) {
		FastByteArrayOutputStream fbaos = new FastByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(fbaos);
			oos.writeObject(source);
			oos.flush();
			oos.close();
			ObjectInputStream ois = new ObjectInputStream(fbaos.getInputStream());
			fbaos.close();
			return (T)ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
}
