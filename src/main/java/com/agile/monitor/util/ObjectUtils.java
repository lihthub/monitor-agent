package com.agile.monitor.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对象工具类
 * 
 * @author lihaitao
 * @since 2019-04-24
 */
public class ObjectUtils {
	
	private static final Logger log = LoggerFactory.getLogger(ObjectUtils.class);
	
	/**
	 * 两个有相同字段的对象强转（支持Map类型）
	 * 
	 * @param sourceObj 要转换的对象实例
	 * @param targetObjClass 目标对象的class
	 */
	public static <T> T convertObject(Object sourceObj, Class<T> targetObjClass) {
		Objects.requireNonNull(sourceObj, "sourceObj must not be null");
		Objects.requireNonNull(targetObjClass, "targetObj must not be null");
		T targetObj = null;
		try {
			targetObj = targetObjClass.newInstance();
		} catch (InstantiationException e) {
			log.error(e.getMessage(), e);
			return null;
		} catch (IllegalAccessException e) {
			log.error(e.getMessage(), e);
			return null;
		}
		if (targetObj != null) {
			transferObject(sourceObj, targetObj);
		}
		return targetObj;
	}
	
	/**
	 * 将一个对象的值转移到另一个对象（支持Map类型）
	 * 
	 * @param sourceObj 源对象实例
	 * @param targetObj 目标对象实例
	 */
	@SuppressWarnings("unchecked")
	public static void transferObject(Object sourceObj, Object targetObj) {
		Objects.requireNonNull(sourceObj, "sourceObj must not be null");
		Objects.requireNonNull(targetObj, "targetObj must not be null");
		if (sourceObj instanceof Map) {
			Map<Object, Object> sourceMap = (Map<Object, Object>) sourceObj;
			Set<Object> sourceMapKeys = sourceMap.keySet();
			if (sourceMapKeys != null) {
				if (targetObj instanceof Map) {
					// Map to Map
					for (Object key : sourceMapKeys) {
						Object value = sourceMap.get(key);
						if (value == null) {
							continue;
						}
						Map<Object, Object> targetMap = (Map<Object, Object>) targetObj;
						targetMap.put(key, value);
					}
				} else {
					// Map to Object
					Class<? extends Object> targetObjClass = targetObj.getClass();
					Field[] targetObjFields = targetObjClass.getDeclaredFields();
					for (Object key : sourceMapKeys) {
						Object value = sourceMap.get(key);
						if (value == null) {
							continue;
						}
						for (Field targetObjField : targetObjFields) {
							if (Modifier.isFinal(targetObjField.getModifiers())) {
								continue;
							}
							if (Objects.equals(key, targetObjField.getName())
									&& isAssignable(value.getClass(), targetObjField.getType())) {
								targetObjField.setAccessible(true);
								try {
									targetObjField.set(targetObj, value);
								} catch (IllegalArgumentException e) {
									log.error(e.getMessage(), e);
								} catch (IllegalAccessException e) {
									log.error(e.getMessage(), e);
								}
							}
						}
					}
				}
			}
			return;
		}
		Class<? extends Object> sourceObjClass = sourceObj.getClass();
		Class<? extends Object> targetObjClass = targetObj.getClass();
		Field[] sourceObjFields = sourceObjClass.getDeclaredFields();
		Field[] targetObjFields = targetObjClass.getDeclaredFields();
		for (Field sourceObjField : sourceObjFields) {
			if (Modifier.isFinal(sourceObjField.getModifiers())) {
				continue;
			}
			sourceObjField.setAccessible(true);
			if (targetObj instanceof Map) {
				// Object to Map
				Map<Object, Object> targetMap = (Map<Object, Object>) targetObj;
				Object value = null;
				try {
					value = sourceObjField.get(sourceObj);
				} catch (IllegalArgumentException e) {
					log.error(e.getMessage(), e);
				} catch (IllegalAccessException e) {
					log.error(e.getMessage(), e);
				}
				if (value != null) {
					targetMap.put(sourceObjField.getName(), value);
				}
			} else {
				// Object to Object
				for (Field targetObjField : targetObjFields) {
					if (Modifier.isFinal(targetObjField.getModifiers())) {
						continue;
					}
					if (Objects.equals(sourceObjField.getName(), targetObjField.getName())
							&& isAssignable(sourceObjField.getType(), targetObjField.getType())) {
						targetObjField.setAccessible(true);
						Object value = null;
						try {
							value = sourceObjField.get(sourceObj);
							if (value != null) {
								targetObjField.set(targetObj, value);
							}
						} catch (IllegalArgumentException e) {
							log.error(e.getMessage(), e);
						} catch (IllegalAccessException e) {
							log.error(e.getMessage(), e);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 是否可以将sourceClass类型的值赋值给targetClass类型
	 */
	private static boolean isAssignable(Class<?> sourceClass, Class<?> targetClass) {
		if (sourceClass == null || targetClass == null) {
			return false;
		}
		Class<?> getSourceClass = getTypeClass(sourceClass);
		Class<?> getTargetClass = getTypeClass(targetClass);
		if (getSourceClass == null || getTargetClass == null) {
			return false;
		}
		return getTargetClass.isAssignableFrom(getSourceClass);
	}
	
	/**
	 * 引用类型Class转为基本类型Class
	 */
	private static Class<?> getTypeClass(Class<?> clazz) {
		if (clazz == null) {
			return null;
		}
		if (clazz == Boolean.class
				|| clazz == Character.class
				|| clazz == Byte.class
				|| clazz == Short.class
				|| clazz == Integer.class
				|| clazz == Long.class
				|| clazz == Float.class
				|| clazz == Double.class) {
			Field field = null;
			try {
				field = clazz.getField("TYPE");
				if (field != null) {
					return (Class<?>) field.get(clazz);
				}
			} catch (NoSuchFieldException e) {
				log.error(e.getMessage(), e);
			} catch (SecurityException e) {
				log.error(e.getMessage(), e);
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage(), e);
			}
			return clazz;
		} else {
			return clazz;
		}
	}
	
}
