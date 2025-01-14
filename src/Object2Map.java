import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

public class Object2Map
{

    private static final Set<Class<?>> PRIMITIVE_TYPES = new HashSet<>(Set.of(
            boolean.class, byte.class, char.class, short.class, int.class, long.class,
            float.class, double.class, Boolean.class, Byte.class, Character.class,
            Short.class, Integer.class, Long.class, Float.class, Double.class, String.class));
    public static Map<String, Object> transform(Object object) throws IllegalAccessException
    {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for(Field field : fields)
        {
            field.setAccessible(true);
            if(PRIMITIVE_TYPES.contains(field.getType()))
            {
                map.put(field.getName(), field.get(object));
            }
            else if (Iterable.class.isAssignableFrom(field.getType()))
            {
                if(field.get(object) == null)
                {
                    map.put(field.getName(), null);
                    continue;
                }
                Iterator<?> iterator = ((Iterable<?>) field.get(object)).iterator();
                List<Object> list = new ArrayList<>();
                while (iterator.hasNext())
                {
                    Object element = iterator.next();
                    if(PRIMITIVE_TYPES.contains(element.getClass()))
                        list.add(element);
                    else
                        list.add(transform(element));
                }
                map.put(field.getName(), list);
            }
            else if(field.getType().isArray())
            {
                if(field.get(object) == null)
                {
                    map.put(field.getName(), null);
                    continue;
                }
                Class<?> componentType = field.get(object).getClass().getComponentType();
                if(PRIMITIVE_TYPES.contains(componentType))
                    map.put(field.getName(), field.get(object));
                else
                {
                    List<Object> list = new ArrayList<>();
                    for(int i = 0; i < Array.getLength(field.get(object)); i++)
                    {
                        list.add(transform(Array.get(field.get(object), i)));
                    }
                    map.put(field.getName(), list);
                }
            }
            else
            {
                if(field.get(object) == null)
                {
                    map.put(field.getName(), null);
                    continue;
                }
                map.put(field.getName(), transform(field.get(object)));
            }
        }

        return map;
    }

    public static void main(String[] args)
    {
        int[] numbers = {1, 2, 3, 4, 5};
        Person[] persons = new Person[2];
        persons[0] = new Person("name1", 18, numbers, "male");
        persons[1] = new Person("name2", 19, numbers, "female");
        String[] names = {"name1", "name2"};
        List<Person> personsList = new ArrayList<>();
        personsList.add(persons[0]);
        personsList.add(persons[1]);
        Person person = new Person("name", 18, numbers, "male", persons, names, personsList);

        try
        {
            Map<String, Object> map = transform(person);
            for (Map.Entry<String, Object> entry : map.entrySet())
                System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}
