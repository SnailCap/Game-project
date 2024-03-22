package ee.taltech.survivors.helper.config;

import ee.taltech.survivors.helper.enums.CharacterClassName;
import ee.taltech.survivors.helper.enums.ConfigNodeName;
import ee.taltech.survivors.helper.enums.ProjectileName;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static ee.taltech.survivors.helper.constants.AssetsFilepaths.*;

public class ConfigReader {

    public static Map<String, String> getCharacterClassInfoMap(CharacterClassName className) {
        return getJSONMap(
                CHARACTER_CLASSES_CONFIG_FILEPATH,
                String.valueOf(className)
        );
    }

    public static Map<String, String> getProjectileInfoMap(ProjectileName projectileName) {
        return getJSONMap(
                PROJECTILES_CONFIG_FILEPATH,
                String.valueOf(projectileName)
        );
    }

    public static Map<String, String> getProjectileTextureMap(ProjectileName projectileName) {
        JSONObject infoMap = getJSONObject(
                PROJECTILES_CONFIG_FILEPATH,
                String.valueOf(projectileName)
        );

        return (Map<String, String>) infoMap.get("TEXTURE");
    }

    public static Map<String, String> getAbilityInfoMap(String abilityName) {
        return getJSONMap(
                ABILITIES_CONFIG_FILEPATH,
                abilityName
        );
    }

    public static Map<String, Map<String, String>> getCharacterClassFramedTextures(CharacterClassName characterClassName) {
        JSONObject characterProperties = getJSONObject(
                CHARACTER_CLASSES_CONFIG_FILEPATH,
                String.valueOf(characterClassName)
        );

        JSONObject characterClassTexturesObject = (JSONObject) characterProperties.get("TEXTURES");

        return (Map<String, Map<String, String>>) characterClassTexturesObject.get(
                String.valueOf(ConfigNodeName.FRAMED_TEXTURES)
        );
    }

    public static Map<String, Map<String, String>> getCharacterClassStaticTextures(CharacterClassName characterClassName) {
        JSONObject characterProperties = getJSONObject(
                CHARACTER_CLASSES_CONFIG_FILEPATH,
                String.valueOf(characterClassName)
        );

        JSONObject characterClassTexturesObject = (JSONObject) characterProperties.get("TEXTURES");

        return (Map<String, Map<String, String>>) characterClassTexturesObject.get(
                String.valueOf(ConfigNodeName.STATIC_TEXTURES)
        );
    }

    public static Map<String, String> getJSONMap(String filepath, String node) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("assets/" + filepath)) {
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            return (Map<String, String>) jsonObject.get(node);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject getJSONObject(String filepath, String node) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("assets/" + filepath)) {
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            return (JSONObject) jsonObject.get(node);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
