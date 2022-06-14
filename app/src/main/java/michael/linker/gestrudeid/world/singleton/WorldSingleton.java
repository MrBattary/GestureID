package michael.linker.gestrudeid.world.singleton;

import michael.linker.gestrudeid.sensor.wrapper.manager.ASensorManager;
import michael.linker.gestrudeid.world.IWorld;
import michael.linker.gestrudeid.world.World;
import michael.linker.gestrudeid.world.exception.WorldFailedException;

/**
 * Singleton class that keeps single instance of the World
 */
public class WorldSingleton {
    private static IWorld world = null;

    private WorldSingleton() {
    }

    /**
     * Get instance of the World
     *
     * @param sensorManager Wrapper around hardware SensorManager
     * @return World instance
     * @throws WorldFailedException If the creation of the World fails
     */
    public static IWorld getInstance(final ASensorManager sensorManager)
            throws WorldFailedException {
        if (world == null) {
            world = new World(sensorManager);
        }
        return world;
    }
}
