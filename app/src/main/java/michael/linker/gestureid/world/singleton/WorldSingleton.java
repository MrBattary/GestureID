package michael.linker.gestureid.world.singleton;

import michael.linker.gestureid.sensor.wrapper.manager.ASensorManager;
import michael.linker.gestureid.world.IWorld;
import michael.linker.gestureid.world.World;
import michael.linker.gestureid.world.exception.WorldFailedException;

/**
 * Singleton class that keeps single instance of the World
 */
public class WorldSingleton {
    private static IWorld world = null;

    private WorldSingleton() {
    }

    /**
     * Initialize instance of the World
     *
     * @param sensorManager Wrapper around hardware SensorManager
     * @throws WorldFailedException If the creation of the World fails
     */
    public static void initialize(final ASensorManager sensorManager) throws WorldFailedException {
        if (world == null) {
            world = new World(sensorManager);
        }
    }

    /**
     * Get instance of the World
     *
     * @return World instance
     * @throws WorldFailedException If the World is not found
     */
    public static IWorld getInstance()
            throws WorldFailedException {
        if (world == null) {
            throw new WorldFailedException("Instance of the World is not found!");
        }
        return world;
    }
}
