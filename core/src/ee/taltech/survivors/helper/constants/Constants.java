package ee.taltech.survivors.helper.constants;

/**
 * The Constants class contains various constants used throughout the application.
 */
public interface Constants {
    // Network-related constants
    int TCP_PORT = 8080;
    int UDP_PORT = 8081;

    // Viewport constants
    int VIEWPORT_WIDTH = 560;
    int VIEWPORT_HEIGHT = 400;

    // Physics constants
    int PPM = 1;
    float INITIAL_SPAWN_POSITION_X = 1000.0f;
    float INITIAL_SPAWN_POSITION_Y = 1000.0f;
    float MAIN_CHARACTER_DEFAULT_MOVEMENT_SPEED = 90f;

    // Character scaling constants
    int DEFAULT_MAIN_CHARACTER_SCALE = 1;
    float DEFAULT_CHARACTER_SCALE = 0.5f;
    float B2BODY_SIZE_MULTIPLIER = 17.0f;
}
