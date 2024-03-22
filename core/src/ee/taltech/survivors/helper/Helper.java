package ee.taltech.survivors.helper;

import ee.taltech.survivors.world.abilities.Ability;

public class Helper {
    public static Ability getAbilityObjectByClassPath(String classPath) {
        try {
            // Load the class dynamically
            Class<?> clazz = Class.forName(classPath);
            return (Ability) clazz.newInstance();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
