package kr.ac.kku.cs.wp.demo.tools.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil {

    public static <T> T createObjectFromJson(Class<T> clazz, JSONObject jsonObject) throws Exception {
        // Get the declared constructor (even if it's private) and make it accessible
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true); // Allows us to access private constructors if necessary
        
        // Create an instance of the class
        T obj = constructor.newInstance();
        
        // Get all fields of the class
        Field[] fields = clazz.getDeclaredFields();
        
        // Iterate over each field
        for (Field field : fields) {
            field.setAccessible(true); // Make the private fields accessible
            String fieldName = field.getName();
            
            // Check if the JSON object has a matching key for the field
            if (jsonObject.has(fieldName)) {
                
                // Handle lists
                if (List.class.isAssignableFrom(field.getType())) {
                    ParameterizedType listType = (ParameterizedType) field.getGenericType();
                    Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];
                    
                    JSONArray jsonArray = jsonObject.getJSONArray(fieldName);
                    List<Object> list = new ArrayList<>();
                    
                    // Convert each element in the JSONArray to the appropriate type
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Object item;
                        if (listClass.isPrimitive() || listClass == String.class || Number.class.isAssignableFrom(listClass)) {
                            // Direct assignment for primitives and strings
                            item = jsonArray.get(i);
                        } else {
                            // Recursive call for complex objects
                            item = createObjectFromJson(listClass, jsonArray.getJSONObject(i));
                        }
                        list.add(item);
                    }
                    field.set(obj, list);
                    
                // Handle primitive types and other objects
                } else {
                    Object value = jsonObject.get(fieldName);
                    
                    // Handle primitive types (like int, boolean, etc.)
                    if (field.getType().isPrimitive()) {
                        if (field.getType() == int.class) {
                            field.setInt(obj, jsonObject.getInt(fieldName));
                        } else if (field.getType() == boolean.class) {
                            field.setBoolean(obj, jsonObject.getBoolean(fieldName));
                        } // Add other primitive types if necessary
                    } else {
                        // For objects, directly set the value
                        field.set(obj, value);
                    }
                }
            }
        }
        return obj;
    }
}
