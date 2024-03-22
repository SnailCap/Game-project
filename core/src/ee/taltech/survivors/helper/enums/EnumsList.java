package ee.taltech.survivors.helper.enums;

import java.util.ArrayList;
import java.util.List;

public class EnumsList {
    public static final List<Class<?>> ENUMS_LIST = new ArrayList<>();

    static {
        ENUMS_LIST.add(CharacterClassName.class);
        ENUMS_LIST.add(ProjectileName.class);
    }

    public static List<Class<?>> GET_ENUMS_LIST() {
        return ENUMS_LIST;
    }
}
